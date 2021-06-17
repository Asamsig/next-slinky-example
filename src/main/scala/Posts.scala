import typings.grayMatter.{mod => matter}
import typings.node.{BufferEncoding, fsMod => fs, pathMod => path, processMod => process}
import typings.remark.{mod => remark}
import typings.remarkHtml.{mod => html}

import scala.scalajs.js
import scala.scalajs.js.Dictionary
import scala.scalajs.js.JSConverters.JSRichMap
object Posts {

  val postsDirectory = path.join(process.cwd(), "posts")

  val getSortedPostsData: () => js.Array[Dictionary[js.Any]] = () => {
    // Get file names under /posts
    val fileNames = fs.readdirSync(postsDirectory)
    val allPostsData: js.Array[Dictionary[js.Any]] = fileNames.map { fileName =>
      // Remove ".md" from file name to get id
      val id = fileName.replaceAll("\\.md$", "")

      // Read markdown file as string
      val fullPath = path.join(postsDirectory, fileName)
      val fileContents = fs.readFileSync(fullPath, BufferEncoding.utf8)

      // Use gray-matter to parse the post metadata section
      val matterResult = matter(fileContents)

      // Combine the data with the id
      matterResult.data.addOne("id" -> id).toJSDictionary
    }
    // Sort posts by date
    allPostsData.sortBy(_.apply("date").toString)
  }

  val getAllPostIds: () => js.Array[js.Object] = () => {
    val fileNames = fs.readdirSync(postsDirectory)

    // Returns an array that looks like this:
    // [
    //   {
    //     params: {
    //       id: 'ssg-ssr'
    //     }
    //   },
    //   {
    //     params: {
    //       id: 'pre-rendering'
    //     }
    //   }
    // ]
    fileNames.map { fileName =>
      js.Dynamic.literal(
        params = js.Dynamic.literal(
          id = fileName.replaceAll("\\.md$", "")
        )
      )
    }
  }

  val getPostData: String => Dictionary[js.Any] = (id: String) => {
    val fullPath = path.join(postsDirectory, s"$id.md")
    val fileContents = fs.readFileSync(fullPath, BufferEncoding.utf8)

    // Use gray-matter to parse the post metadata section
    val matterResult = matter(fileContents)

    // Use remark to convert markdown into HTML string
    val processedContent = remark() //TODO: The typings for remark are not great....
      .asInstanceOf[js.Dynamic]
      .use(html._to)
      .processSync(matterResult.content)

    val contentHtml = processedContent.toString

    // Combine the data with the id and contentHtml
    matterResult.data.addOne("id" -> id).addOne("contentHtml" -> contentHtml).toJSDictionary
  }

}

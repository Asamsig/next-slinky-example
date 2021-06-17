import typings.grayMatter.{mod => matter}
import typings.node.{BufferEncoding, fsMod => fs, pathMod => path, processMod => process}

import scala.scalajs.js
import scala.scalajs.js.Dictionary
import scala.scalajs.js.JSConverters.JSRichMap
import scala.scalajs.js.annotation.JSExport

object Posts {

  val postsDirectory = path.join(process.cwd(), "posts")

  val getSortedPostsData: () => js.Array[Dictionary[js.Any]] = () => {
    // Get file names under /posts
    val fileNames = fs.readdirSync(postsDirectory)
    val allPostsData: js.Array[Dictionary[js.Any]] = fileNames.map { fileName =>
      // Remove ".md" from file name to get id
      val id = fileName.replace(".md", "")

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

}

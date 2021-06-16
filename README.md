# Scala.js, Slinky and Next.js example

This example project shows how to use [Next.js](https://nextjs.org) together
with [Slinky](https://slinky.dev).

Following this excellent guide [Create a Next.js App](https://nextjs.org/learn/basics/create-nextjs-app).

## Setup how it works

Since Next.js is opinionated, we have to place our "pages" in the pages-folder,
since Scala.js can't output in such a controlled file-based way.

This means we have to use some JavaScript-code to glue the application together. 😔

Luckily we only need ONE line of JavaScript code per page. 💪

### Path aliases
We leverage Next.js' support for [path aliases](https://nextjs.org/docs/advanced-features/module-path-aliases),
this way we can consistently reach our Scala.js components, regardless of how nested we are in the `pages`-folder.

Consider this example:

-   The [pages/posts/first-post.js](pages/posts/first-post.js)-file resides in a subdirectory `posts`, this is for Next.js' routing purposes. So for us to actually reach our Scala.js components, we'd have to import it like so:

    > export { default } from "../../target/scalajs/FirstPost"

    When we instead leverage the path alias, we know that we can consistently import our components like this, no matter the nesting:

    > export { default } from "scalajs/FirstPost"

    Regardless of nesting, WIN!
    

-   The path to [src/main/resources](src/main/resources) is also aliased as `resources`, but if you add new directories,
    
    you will have to use e.g. `resources/new-directory/my-file`, but that should be intuitive.

    
-   See [jsconfig.json](jsconfig.json) for the specific configuration.

## Getting started

You need to:

-   Install the javascript libraries:

    > npm install

-   Create the bundle (in a separate terminal):

    > sbt

    > \> ~fastLinkJS

-   Run Next.js dev server:

    > npm run dev
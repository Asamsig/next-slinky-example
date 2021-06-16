import org.scalajs.linker.interface.ModuleSplitStyle

import scala.sys.process.Process

enablePlugins(ScalaJSPlugin, ScalablyTypedConverterExternalNpmPlugin)

Global / onChangedBuildSource := ReloadOnSourceChanges

scalaVersion := "2.13.4"

libraryDependencies += "me.shadaj" %%% "slinky-core" % "0.6.7" // core React functionality, no React DOM
libraryDependencies += "me.shadaj" %%% "slinky-web" % "0.6.7" // React DOM, HTML and SVG tags

scalacOptions += "-Ymacro-annotations"

scalaJSLinkerConfig ~= {
  _.withModuleKind(ModuleKind.ESModule)
    .withModuleSplitStyle(ModuleSplitStyle.SmallestModules)
}

Compile / fastLinkJS / scalaJSLinkerOutputDirectory := target.value / "scalajs"
Compile / fullLinkJS / scalaJSLinkerOutputDirectory := target.value / "scalajs"

stIgnore ++= List("react", "react-dom")
stFlavour := Flavour.Slinky
stStdlib := List("esnext")

externalNpm := {
  Process("npm", baseDirectory.value).!
  baseDirectory.value
}

Global / stRemoteCache := RemoteCache.S3Aws(
  bucket = "asamsig-scalablytyped-caches",
  region = "eu-north-1",
  prefix = Some("st-cache")
)

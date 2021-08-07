= LiCOS-JSON4Scala =
:awestruct-layout: base
:showtitle:
:prev_section: defining-frontmatter
:next_section: creating-pages
:homepage: https://www.besna.institute
:author: 株式会社BESNA研究所
:email: sakamoto.github@besna.institute
:scala_steward:
:scala_steward_url: https://scala-steward.org
:scala_steward_image: https://img.shields.io/badge/Scala_Steward-helping-blue.svg?style=flat&logo=data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAAA4AAAAQCAMAAAARSr4IAAAAVFBMVEUAAACHjojlOy5NWlrKzcYRKjGFjIbp293YycuLa3pYY2LSqql4f3pCUFTgSjNodYRmcXUsPD/NTTbjRS+2jomhgnzNc223cGvZS0HaSD0XLjbaSjElhIr+AAAAAXRSTlMAQObYZgAAAHlJREFUCNdNyosOwyAIhWHAQS1Vt7a77/3fcxxdmv0xwmckutAR1nkm4ggbyEcg/wWmlGLDAA3oL50xi6fk5ffZ3E2E3QfZDCcCN2YtbEWZt+Drc6u6rlqv7Uk0LdKqqr5rk2UCRXOk0vmQKGfc94nOJyQjouF9H/wCc9gECEYfONoAAAAASUVORK5CYII=
:license:
:license_image: https://img.shields.io/badge/License-Apache%202.0-blue.svg
:license_url: https://github.com/besna-institute/LiCOS-JSON4Scala/blob/master/LICENSE

ifdef::scala_steward[]
image:{scala_steward_image}[Scala Steward badge,link={scala_steward_url}]
endif::[]

ifdef::license[]
image:{license_image}[LICENSE, link={license_url}]
endif::[]

## API

### The latest version

https://besna-institute.github.io/LiCOS-JSON4ScalaDoc/0.5.1/api/index.html

### Old versions

https://besna-institute.github.io/LiCOS-JSON4ScalaDoc/index.html

## Import

### SBT

```scala
resolvers += "LiCOS-JSON4Scala-snapshots-repository" at "https://github.com/besna-institute/LiCOS-JSON4Scala/raw/main/maven-repo/snapshots"
```

```scala
libraryDependences += "online.licos" % "licos-json4scala_2.13" % "(version)"
```
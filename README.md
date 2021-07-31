# LiCOS-JSON4Scala

[![CircleCI](https://circleci.com/gh/besna-institute/LiCOS-JSON4Scala.svg?style=svg)](https://circleci.com/gh/besna-institute/LiCOS-JSON4Scala)
[![Build Status](https://travis-ci.org/besna-institute/LiCOS-JSON4Scala.svg?branch=master)](https://travis-ci.org/besna-institute/LiCOS-JSON4Scala)
[![Scala Steward badge](https://img.shields.io/badge/Scala_Steward-helping-blue.svg?style=flat&logo=data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAAA4AAAAQCAMAAAARSr4IAAAAVFBMVEUAAACHjojlOy5NWlrKzcYRKjGFjIbp293YycuLa3pYY2LSqql4f3pCUFTgSjNodYRmcXUsPD/NTTbjRS+2jomhgnzNc223cGvZS0HaSD0XLjbaSjElhIr+AAAAAXRSTlMAQObYZgAAAHlJREFUCNdNyosOwyAIhWHAQS1Vt7a77/3fcxxdmv0xwmckutAR1nkm4ggbyEcg/wWmlGLDAA3oL50xi6fk5ffZ3E2E3QfZDCcCN2YtbEWZt+Drc6u6rlqv7Uk0LdKqqr5rk2UCRXOk0vmQKGfc94nOJyQjouF9H/wCc9gECEYfONoAAAAASUVORK5CYII=)](https://scala-steward.org)
[![License](https://img.shields.io/badge/License-Apache%202.0-blue.svg)](https://github.com/besna-institute/LiCOS-JSON4Scala/blob/master/LICENSE)

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

### Gradle

```javascript
repositories {
    maven {
        url "https://github.com/besna-institute/LiCOS-JSON4Scala/raw/main/maven-repo/snapshots"
    }
}
```

```javascript
dependencies {
    compile group: 'online.licos', name: 'licos-json4scala_2.13', version: '(version)'
}
```

### Maven

```xml
<repository>
  <id>LiCOS-JSON4Scala-snapshots-repository</id>
  <url>https://github.com/besna-institute/LiCOS-JSON4Scala/raw/main/maven-repo/snapshots</url>
</repository>
```

```xml
<dependency>
  <groupId>online.licos</groupId>
  <artifactId>licos-json4scala_2.13</artifactId>
  <version>(version)</version>
</dependency>
```

### Ivy

```xml
<ivysettings>
    <settings defaultResolver="chain"/>
    <resolvers>
        <chain name="chain">
            <ibiblio name="LiCOS-JSON4Scala-snapshots-repository" m2compatible="true" root="https://github.com/besna-institute/LiCOS-JSON4Scala/raw/main/maven-repo/snapshots"/>
        </chain>
    </resolvers>
</ivysettings>
```

```xml
<dependency org="online.licos" name="licos-json4scala_2.13" rev="(version)"/>
```

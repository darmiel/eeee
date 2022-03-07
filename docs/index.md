# EEEE

EEEE* is a tiny framework for simple console input of values and generation of objects, which I
wrote mainly for exercises of my university, because often simple inputs with a scanner are
needed, and I did not want to rewrite them all the time.

Also, I didn't use any existing frameworks, ~~because apparently we are only allowed to use
self-written code in the exams, and thus I am allowed to use this mini-framework in the exams~~.

**EDIT:** Looks like all frameworks are allowed. Well shit.

---

**NOTE:** This framework is ab-so-lutely not suitable for production use. It should only serve for
practice. Also, definitely not type-safe.

[Here](https://github.com/darmiel/eeee/tree/main/src/main/java/example), you can find some examples for some modules.

---

## Installation

### Maven

1. Add the JitPack repository to your `pom.xml`:

```xml
<repositories>
  <repository>
    <id>jitpack.io</id>
    <url>https://jitpack.io</url>
  </repository>
</repositories>
```

2. Add the `eeee`-dependency:

[![](https://jitpack.io/v/darmiel/eeee.svg)](https://jitpack.io/#darmiel/eeee)

```xml
<dependency>
  <groupId>com.github.darmiel</groupId>
  <artifactId>eeee</artifactId>
  <version>2.4.1</version>
</dependency>
```

### Gradle

1. Add the JitPack repository to your build file:

```gradle
allprojects {
    repositories {
         ...
         maven { url 'https://jitpack.io' }
    }
}
```

2. Add the `eeee`-dependency:

[![](https://jitpack.io/v/darmiel/eeee.svg)](https://jitpack.io/#darmiel/eeee)

```gradle
dependencies {
    implementation 'com.github.darmiel:eeee:Tag'
}
```

---

*) To be honest, I've already forgotten the meaning of the abbreviation. But it was certainly
perfect!


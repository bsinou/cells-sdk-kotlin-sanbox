import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

buildscript {

    repositories {
        maven(url = "https://repo1.maven.org/maven2" )
        mavenCentral()
        gradlePluginPortal()
    }

    dependencies {
        classpath ("org.jetbrains.kotlin:kotlin-gradle-plugin:1.9.23")
        classpath ("org.jetbrains.kotlin:kotlin-serialization:1.9.23")
        classpath("org.jetbrains.kotlinx:kotlinx-serialization-json:1.4.0")
        // classpath(libs.kotlinx.serialization)
    }
}



plugins {
    kotlin("jvm")
    kotlin("plugin.serialization") version "1.9.23"
    alias(libs.plugins.spotless)
}

group = "org.sinou.android.kotlin"
version = "0.1.1-dev"

//spotless {
//    //check(false)
//
//    format("misc") {
//        target(".gitignore")
//        trimTrailingWhitespace()
//        indentWithSpaces() // Default is 4, change by providing an integer
//        endWithNewline()
//    }
//    kotlin {
//        ktfmt()
//    }
//}

tasks.test {
    useJUnitPlatform()
}

dependencies {
    dependencies {
        implementation(libs.kotlin.stdlib.jdk8)
        implementation(libs.kotlin.reflect)
        implementation(libs.moshi.kotlin)
        implementation(libs.moshi.adapters)
        implementation(libs.ktor.client.core)
        implementation(libs.ktor.client.content.negotiation)
        implementation(libs.kotlinx.datetime)
        testImplementation(libs.kotlintest.runner.junit5)
    }
}

tasks.withType<KotlinCompile>().configureEach {
    kotlinOptions {
        freeCompilerArgs += "-Xopt-in=kotlinx.serialization.ExperimentalSerializationApi"
    }
}
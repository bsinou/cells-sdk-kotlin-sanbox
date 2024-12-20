plugins {
    kotlin("jvm")
    alias(libs.plugins.spotless)
}

group = "org.sinou.android.kotlin"
version = "0.1.1-dev"

spotless {
    //check(false)

    format("misc") {
        target(".gitignore")
        trimTrailingWhitespace()
        indentWithSpaces() // Default is 4, change by providing an integer
        endWithNewline()
    }
    kotlin {
        ktfmt()
    }
}

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



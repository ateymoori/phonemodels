buildscript {

    val compose_version = "1.0.5"

    repositories {
        google()
        mavenCentral()
    }
    dependencies {
        classpath("com.android.tools.build:gradle:7.1.0-beta04")
        classpath("org.jetbrains.kotlin:kotlin-gradle-plugin:1.5.31")
        classpath("com.google.dagger:hilt-android-gradle-plugin:2.38.1")
    }
}

tasks.register("clean", Delete::class){
    delete(rootProject.buildDir)
}
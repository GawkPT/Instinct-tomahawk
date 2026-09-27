buildscript {
    dependencies {
        classpath(libs.android.shortcut.gradle)
        classpath(sylibs.gradleversionsx)
        // Instinct fix 2026-09-27: AGP 8.13.2's bundled R8 only understands Kotlin
        // metadata up to 2.3; the project compiles with Kotlin 2.4.20. R8 spent
        // 12-15 min emitting thousands of "malformed kotlin.Metadata" warnings,
        // then the hosted runner died ("lost communication") in
        // :app:minifyReleaseWithR8 on both v1.0.0 attempts. Pinning a newer
        // standalone R8 on the buildscript classpath makes AGP use it instead.
        classpath("com.android.tools:r8:9.4.26")
    }
}

plugins {
    alias(kotlinx.plugins.serialization) apply false
    alias(libs.plugins.aboutLibraries) apply false
    alias(libs.plugins.firebase.crashlytics) apply false
    alias(libs.plugins.google.services) apply false
    alias(libs.plugins.moko) apply false
    alias(libs.plugins.sqldelight) apply false
}

tasks.register<Delete>("clean") {
    delete(rootProject.layout.buildDirectory)
}

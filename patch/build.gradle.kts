val androidSourceCompatibility: JavaVersion.VERSION_17
val androidTargetCompatibility: JavaVersion.VERSION_17

plugins {
    id("java-library")
}

java {
    sourceCompatibility = JavaVersion.VERSION_17
    targetCompatibility = JavaVersion.VERSION_17
    sourceSets {
        main {
            java.srcDirs("libs/manifest-editor/lib/src/main/java")
            resources.srcDirs("libs/manifest-editor/lib/src/main")
        }
    }
}

dependencies {
    implementation(projects.apkzlib)
    implementation(projects.share.java)

    implementation(lspatch.commons.io)
    implementation(lspatch.beust.jcommander)
    implementation(lspatch.google.gson)
}

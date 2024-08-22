val verCode: Int by rootProject.extra
val verName: String by rootProject.extra
val androidSourceCompatibility: JavaVersion.VERSION_17
val androidTargetCompatibility: JavaVersion.VERSION_17

plugins {
    id("java-library")
}

java {
    sourceCompatibility = JavaVersion.VERSION_17
    targetCompatibility = JavaVersion.VERSION_17
}

dependencies {
    implementation(projects.patch)
}

fun Jar.configure(variant: String) {
    archiveBaseName.set("jar-v$verName-$verCode-$variant")
    destinationDirectory.set(file("${rootProject.projectDir}/out/$variant"))
    manifest {
        attributes("Main-Class" to "org.lsposed.patch.LSPatch")
    }
    dependsOn(configurations.runtimeClasspath)
    from(configurations.runtimeClasspath.get().map { if (it.isDirectory) it else zipTree(it) })

    into("assets") {
        from("src/main/assets")
        from("${rootProject.projectDir}/out/assets/$variant")
    }

    exclude("META-INF/*.SF", "META-INF/*.DSA", "META-INF/*.RSA", "META-INF/*.MF", "META-INF/*.txt", "META-INF/versions/**")
}

tasks.register<Jar>("buildDebug") {
    dependsOn(":meta-loader:copyDebug")
    dependsOn(":patch-loader:copyDebug")
    configure("debug")
}

tasks.register<Jar>("buildRelease") {
    dependsOn(":meta-loader:copyRelease")
    dependsOn(":patch-loader:copyRelease")
    configure("release")
}

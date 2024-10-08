val apiCode: Int by rootProject.extra
val verCode: Int by rootProject.extra
val verName: String by rootProject.extra
val coreVerCode: Int by rootProject.extra
val coreVerName: String by rootProject.extra
val androidSourceCompatibility: JavaVersion.VERSION_17
val androidTargetCompatibility: JavaVersion.VERSION_17

plugins {
    id("java-library")
}

java {
    sourceCompatibility = JavaVersion.VERSION_17
    targetCompatibility = JavaVersion.VERSION_17
}

val generateTask = task<Copy>("generateJava") {
    val template = mapOf(
        "apiCode" to apiCode,
        "verCode" to verCode,
        "verName" to verName,
        "coreVerCode" to coreVerCode,
        "coreVerName" to coreVerName
    )
    inputs.properties(template)
    from("src/template/java")
    into("$buildDir/generated/java")
    expand(template)
}

sourceSets["main"].java.srcDir("$buildDir/generated/java")
tasks["compileJava"].dependsOn(generateTask)

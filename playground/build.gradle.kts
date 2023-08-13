plugins {
    alias(libs.plugins.convention.kotlin)
    alias(libs.plugins.javafx.plugin)
    application
}

application {
    mainClass.set("dev.gallon.playground.Playground")
}

javafx {
    version = libs.versions.javafx.asProvider().get()
    modules = listOf("javafx.controls")
}

tasks.named("configJavafxRun") {
    // This task is not compatible with configuration task
    // An issue is open, but without any response as of now
    // https://github.com/openjfx/javafx-gradle-plugin/issues/136
    notCompatibleWithConfigurationCache(
        "https://github.com/openjfx/javafx-gradle-plugin/issues/136"
    )
}

dependencies {
    implementation(libs.kotlin.coroutines)
    implementation(libs.kotlin.coroutines.javafx)
    implementation(libs.kotlin.coroutines.slf4j)
    implementation(libs.atlantafx)

    // logging
    implementation(kotlin("reflect")) // for kt logging to determine logger names from classes
    runtimeOnly(libs.slf4j.api) // The logging api used by kotlin logging
    implementation(libs.log4j.kotlin) // The api we will use in the code
    implementation(libs.log4j.slf4j2) // The implementation of slf4j (log4j)

    testImplementation(libs.kotlin.test)
}

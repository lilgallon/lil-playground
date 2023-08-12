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
	testImplementation(libs.kotlin.test)
}

plugins {
	alias(libs.plugins.convention.kotlin)
	application
}

application {
	mainClass.set("dev.gallon.playground.MainKt")
}

dependencies {
	testImplementation(libs.kotlin.test)
}

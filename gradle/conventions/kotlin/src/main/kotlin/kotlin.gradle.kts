package conventions

plugins {
	// Currently, it is not possible to use version catalogs here…
	kotlin("jvm")
	id("conventions.versioning")
	id("org.jlleitschuh.gradle.ktlint")
}

repositories {
	mavenCentral()
	google()
}

kotlin {
	jvmToolchain(17)
}

tasks.withType<Test>().configureEach {
	maxParallelForks = (Runtime.getRuntime().availableProcessors() / 2).takeIf { it > 0 } ?: 1
}

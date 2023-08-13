plugins {
	`kotlin-dsl`
	alias(libs.plugins.ktlint)
}
group = "conventions"

dependencies {
	implementation(libs.gradle.ktlint)
	implementation(libs.gradle.kotlin)
	implementation(projects.versioning)
}

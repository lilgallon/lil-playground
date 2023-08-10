import org.lwjgl.Lwjgl
import org.lwjgl.Release
import org.lwjgl.lwjgl

repositories {
	// imgui
	maven("https://raw.githubusercontent.com/kotlin-graphics/mary/master")
	maven("https://jitpack.io")
}

plugins {
	alias(libs.plugins.convention.kotlin)
	alias(libs.plugins.lwjgl.plugin)
	application
}

application {
	mainClass.set("dev.gallon.playground.MainKt")
}

dependencies {
	lwjgl {
		version = Release.valueOf(libs.versions.lwjgl.asProvider().get().replace(".", "_"))
		implementation(Lwjgl.Preset.everything)
	}

	implementation(libs.imgui.core)
	implementation(libs.imgui.gl)
	implementation(libs.imgui.glfw)

	testImplementation(libs.kotlin.test)
}

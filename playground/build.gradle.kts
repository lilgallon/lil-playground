import org.apache.tools.ant.taskdefs.condition.Os
import org.lwjgl.Lwjgl
import org.lwjgl.Release
import org.lwjgl.Snapshot
import org.lwjgl.lwjgl

repositories {
	// imgui
	maven("https://raw.githubusercontent.com/kotlin-graphics/mary/master")
	maven("https://jitpack.io")

	// lwjgl
	// sonatype()
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
		version = Release.`3_3_2`
		implementation(Lwjgl.Preset.everything)
	}

	implementation(libs.imgui.core)
	implementation(libs.imgui.gl)
	implementation(libs.imgui.glfw)

	// val lwjglNatives = if (Os.isFamily(Os.FAMILY_WINDOWS)) {
	// 	"natives-windows"
	// } else if (Os.isFamily(Os.FAMILY_UNIX)) {
	// 	"natives-linux"
	// } else if (Os.isFamily(Os.FAMILY_MAC)) {
	// 	"natives-macos"
	// } else {
	// 	throw GradleException("Unsupported Operating System")
	// }

	// configurations.implementation.get().resolvedConfiguration.resolvedArtifacts
	// 	.forEach {
	// 		if (it.moduleVersion.id.group == "org.lwjgl") {
	// 			runtimeOnly("org.lwjgl:${it.moduleVersion.id.name}:${it.moduleVersion.id.version}:$lwjglNatives")
	// 		}
	// 	}

	testImplementation(libs.kotlin.test)
}

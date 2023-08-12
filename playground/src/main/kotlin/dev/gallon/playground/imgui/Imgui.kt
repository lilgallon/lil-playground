package dev.gallon.playground.imgui

import glm_.vec4.Vec4
import imgui.ImGui
import imgui.ImGui.begin
import imgui.ImGui.colorEdit4
import imgui.ImGui.end
import imgui.ImGui.menuItem
import imgui.ImGui.plotLines
import imgui.ImGui.text
import imgui.ImGui.textColored
import imgui.Jdsl.*
import imgui.WindowFlag

fun withChild(s: String, function: () -> Unit) {
	TODO("Not yet implemented")
}

var myToolActive: Boolean = true
var color: Vec4 = Vec4()

fun example() {
	begin("My First Tool", ::myToolActive, WindowFlag.MenuBar)
	menuBar {
		menu("File") {
			menuItem("Open..", "Ctrl+O") { /* Do stuff */ }
			menuItem("Save", "Ctrl+S")   { /* Do stuff */ }
			menuItem("Close", "Ctrl+W")  {  }
		}
	}

	// Edit a color (stored as FloatArray[4] or Vec4)
	colorEdit4("Color", color);

	// Plot some values
	val myValues = floatArrayOf(0.2f, 0.1f, 1f, 0.5f, 0.9f, 2.2f)
	plotLines("Frame Times", myValues)

	// Display contents in a scrolling region
	textColored(Vec4(1, 1, 0, 1), "Important Stuff")
	withChild("Scrolling") {
		(0..50).forEach {
			text("%04d: Some text", it)
		}
	}
	end()
}

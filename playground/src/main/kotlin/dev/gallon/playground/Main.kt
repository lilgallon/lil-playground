package dev.gallon.playground

import dev.gallon.playground.imgui.example
import imgui.ImGui
import imgui.classes.Context
import imgui.font.FontAtlas
import imgui.font.FontConfig
import org.lwjgl.Version
import org.lwjgl.glfw.Callbacks.glfwFreeCallbacks
import org.lwjgl.glfw.GLFW.*
import org.lwjgl.glfw.GLFWErrorCallback
import org.lwjgl.opengl.GL
import org.lwjgl.opengl.GL11.*
import org.lwjgl.system.MemoryStack.stackPush
import org.lwjgl.system.MemoryUtil.NULL
import kotlin.properties.Delegates

var window by Delegates.notNull<Long>();
var imGuiContext by Delegates.notNull<Context>();

fun main() {
	println("Hello LWJGL " + Version.getVersion() + "!");

	init();
	loop();

	// Free the window callbacks and destroy the window
	glfwFreeCallbacks(window);
	glfwDestroyWindow(window);

	imGuiContext.destroy()

	// Terminate GLFW and free the error callback
	glfwTerminate();
	glfwSetErrorCallback(null)?.free();
}

fun init() {
	// Setup an error callback. The default implementation
	// will print the error message in System.err.
	GLFWErrorCallback.createPrint(System.err).set()

	// Initialize GLFW. Most GLFW functions will not work before doing this.
	check(glfwInit()) { "Unable to initialize GLFW" }

	// Configure GLFW
	glfwDefaultWindowHints() // optional, the current window hints are already the default

	glfwWindowHint(GLFW_VISIBLE, GLFW_FALSE) // the window will stay hidden after creation
	glfwWindowHint(GLFW_RESIZABLE, GLFW_TRUE) // the window will be resizable

	// Create the window
	window = glfwCreateWindow(300, 300, "Hello World!", NULL, NULL)
	if (window == NULL) throw RuntimeException("Failed to create the GLFW window")

	// Setup a key callback. It will be called every time a key is pressed, repeated or released.
	glfwSetKeyCallback(window) { window: Long, key: Int, scancode: Int, action: Int, mods: Int ->
		if (key == GLFW_KEY_ESCAPE && action == GLFW_RELEASE) glfwSetWindowShouldClose(window, true) // We will detect this in the rendering loop
	}

	// Get the thread stack and push a new frame
	stackPush().use { stack ->
		val pWidth = stack.mallocInt(1) // int*
		val pHeight = stack.mallocInt(1) // int*

		// Get the window size passed to glfwCreateWindow
		glfwGetWindowSize(window, pWidth, pHeight)

		// Get the resolution of the primary monitor
		val vidmode = glfwGetVideoMode(glfwGetPrimaryMonitor())
			?: throw RuntimeException("Could not get primary monitor resolution")

		// Center the window
		glfwSetWindowPos(
			window,
			(vidmode.width() - pWidth[0]) / 2,
			(vidmode.height() - pHeight[0]) / 2
		)
	}

	// Make the OpenGL context current
	glfwMakeContextCurrent(window)
	// Enable v-sync
	glfwSwapInterval(1)

	// Make the window visible
	glfwShowWindow(window)
}

var t: Boolean = true

fun loop() {
	// This line is critical for LWJGL's interoperation with GLFW's
	// OpenGL context, or any context that is managed externally.
	// LWJGL detects the context that is current in the current thread,
	// creates the GLCapabilities instance and makes the OpenGL
	// bindings available for use.
	// This line is critical for LWJGL's interoperation with GLFW's
	// OpenGL context, or any context that is managed externally.
	// LWJGL detects the context that is current in the current thread,
	// creates the GLCapabilities instance and makes the OpenGL
	// bindings available for use.
	GL.createCapabilities()

	imGuiContext = Context()
	ImGui.styleColorsDark()
	// ImGui.setCurrentFont()
	// imGuiContext.io.fonts.addFontFromFileTTF("fonts/roboto.ttf", 18f)

	// Set the clear color
	glClearColor(1.0f, 0.0f, 0.0f, 0.0f)

	// Run the rendering loop until the user has attempted to close
	// the window or has pressed the ESCAPE key.
	while (!glfwWindowShouldClose(window)) {
		glClear(GL_COLOR_BUFFER_BIT or GL_DEPTH_BUFFER_BIT) // clear the framebuffer

		ImGui.newFrame()
		ImGui.text("TRUCUCUCU")
		// example()
		// ImGui.showDemoWindow(::t);
		ImGui.render()

		glfwSwapBuffers(window) // swap the color buffers

		// Poll for window events. The key callback above will only be
		// invoked during this call.
		glfwPollEvents()
	}
}

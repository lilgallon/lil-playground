package dev.gallon.playground

import javafx.application.Application
import javafx.scene.Scene
import javafx.scene.control.Label
import javafx.scene.layout.StackPane
import javafx.stage.Stage
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.javafx.JavaFx
import java.text.SimpleDateFormat
import java.util.*
import kotlin.coroutines.CoroutineContext

class Playground : Application(), CoroutineScope {

	/**
	 * It is possible to cancel all the children coroutines of our app by
	 * cancelling this job. It works because this job is passed in the main
	 * coroutine scope.
	 *
	 * TODO: We may rather use a SupervisorJob, so that cancellation is not
	 *  propagated upwards. It means that if a child cancels its execution, it
	 *  won't cancel this job, and terminate this App.
	 */
	private var job = Job()

	/**
	 * This is the main coroutine context. Rather than using Dispatchers.Main,
	 * we use Dispatchers.JavaFx that has a custom support for delay() and other
	 * coroutine methods (such as awaitPulse and asFlow). It overrides
	 * Dispatchers.Main.
	 *
	 * If we launch a coroutine directly from this class, it will use this
	 * context.
	 */
	override val coroutineContext: CoroutineContext
		get() = Dispatchers.JavaFx + job

	override fun start(primaryStage: Stage?) {
		val javaVersion = System.getProperty("java.version")
		val javafxVersion = System.getProperty("javafx.version")

		log("javaVersion = $javaVersion")
		log("javafxVersion = $javafxVersion")

		val l = Label("Hello, JavaFX $javafxVersion, running on Java $javaVersion.")
		val scene = Scene(StackPane(l), 640.0, 480.0)
		primaryStage?.setScene(scene)
		primaryStage?.show()
	}

	fun cancel() {
		job.cancel()
		job = Job()
	}
}

fun log(msg: String) = println(
	"${SimpleDateFormat("yyyyMMdd-HHmmss.sss").format(Date())} " +
		"[${Thread.currentThread().name}] $msg")

fun main(args: Array<String>) {
	Application.launch(Playground::class.java, *args)
}

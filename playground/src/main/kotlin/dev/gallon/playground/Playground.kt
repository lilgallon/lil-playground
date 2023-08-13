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
import kotlinx.coroutines.slf4j.MDCContext
import org.apache.logging.log4j.kotlin.logger
import org.slf4j.MDC
import kotlin.coroutines.CoroutineContext

const val COROUTINE_MDC = "coroutine"

class Playground : Application(), CoroutineScope {

    private val logger = logger()

    /**
     * It is possible to cancel all the children coroutines of our app by
     * cancelling this job. It works because this job is passed in the main
     * coroutine scope.
     *
     * We may rather use a SupervisorJob, so that cancellation is not
     * propagated upwards. It means that if a child cancels its execution, it
     * won't cancel this job, and terminate this App.
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
        get() = Dispatchers.JavaFx + job + MDCContext()

    init {
        MDC.put(COROUTINE_MDC, "main")
    }

    override fun start(primaryStage: Stage?) {
        val javaVersion = System.getProperty("java.version")
        val javafxVersion = System.getProperty("javafx.version")

        logger.info { "javaVersion = $javaVersion" }
        logger.info { "javafxVersion = $javafxVersion" }

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

fun main(args: Array<String>) {
    Application.launch(Playground::class.java, *args)
}

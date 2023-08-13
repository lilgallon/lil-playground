package dev.gallon.playground.modules

import dev.gallon.playground.api.Module
import javafx.scene.control.Label
import javafx.scene.layout.Pane

class ExampleModule : Module("example") {

    override suspend fun build(): Pane = Pane(Label(toString()))

    override fun destroy() {
        logger.info { "Destroyed $name" }
    }
}

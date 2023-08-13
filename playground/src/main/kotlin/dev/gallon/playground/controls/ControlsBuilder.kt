package dev.gallon.playground.controls

import javafx.scene.control.ChoiceBox
import javafx.scene.control.Label
import javafx.scene.layout.Pane
import javafx.scene.layout.VBox
import org.apache.logging.log4j.kotlin.logger

object ControlsBuilder {
    private val logger = logger()

    fun <T> buildSelect(vararg selectables: T, onSelect: (T) -> Unit): Pane {
        val label = Label("Module")
        val select = ChoiceBox<T>().apply {
            setOnAction { _ ->
                onSelect(value)
            }
        }
        select.items.addAll(selectables)

        return VBox(label, select).apply { spacing = 5.0 }.also {
            logger.debug("Built select control: $it")
        }
    }
}

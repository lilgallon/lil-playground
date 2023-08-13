package dev.gallon.playground.api

import javafx.scene.layout.Pane
import org.apache.logging.log4j.kotlin.logger

abstract class Module(val name: String) {
    protected val logger = logger(name)
    override fun toString(): String = name
    abstract suspend fun build(): Pane
    abstract fun destroy()
}

package UI

import algorithm.AdjacencyList
import algorithm.Graph
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application

var graph: Graph = AdjacencyList()

fun main() = application {
	Window(onCloseRequest = ::exitApplication) {
		App()
	}
}






package UI

import algorithm.graph.AdjacencyList
import algorithm.graph.Graph
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application

var graph: Graph = AdjacencyList()

fun main() = application {
	Window(
		onCloseRequest = ::exitApplication,
		title = "Алгоритм поиска кратчайшего пути в графе. Алгоритм Дейкстры.",
	) {
		App()
	}
}

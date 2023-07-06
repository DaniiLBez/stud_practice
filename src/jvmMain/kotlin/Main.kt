package UI

import algorithm.AdjacencyList
import algorithm.Graph
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.WindowSize
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






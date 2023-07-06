package algorithm

import algorithm.graph.Edge
import algorithm.graph.Graph
import algorithm.graph.Vertex
import java.util.PriorityQueue
import kotlin.math.abs

@Suppress("DEPRECATION")
class Dijkstra(private val graph: Graph) {
	private var distanceFromSource = hashMapOf<Vertex, Visit>()
	private var path = mutableListOf<Edge>()

	private var queue = PriorityQueue<Vertex> { first, second ->
		abs(distance(second.name, distanceFromSource) - distance(first.name, distanceFromSource)).toInt()
	}

	private var steps = mutableListOf<MementoDijkstra>()
	private fun route(
		destination: String,
		paths: HashMap<Vertex, Visit>
	): MutableList<Edge> {
		var vertex = graph.vertex(destination)
		loop@while (true) {
			val visit = paths[vertex] ?: break

			when (visit.type) {
				VisitType.EDGE -> visit.edge?.let {
					path.add(it)
					vertex = it.source
				}
				VisitType.START -> break@loop
			}
			steps.add(MementoDijkstra(vertex!!, distanceFromSource, queue, path))
		}
		return path
	}

	private fun distance(
		destination: String,
		paths: HashMap<Vertex, Visit>
	): Double {
		val path = route(destination, paths)
		return path.sumByDouble { it.weight ?: 0.0 }
	}

	fun shortestPath(startVertex: String) {

		val start = graph.vertex(startVertex) ?: return
		distanceFromSource[start] = Visit(VisitType.START)
		queue.add(start)

		steps.add(MementoDijkstra(start, distanceFromSource, queue, path))

		while (queue.isNotEmpty()) {
			val vertex = queue.remove()
			val edges = graph.edges(vertex)

			edges.forEach {
				val weight = it.weight ?: return@forEach

				if (distanceFromSource[it.destination] == null ||
					distance(vertex.name, distanceFromSource) + weight < distance(it.destination.name, distanceFromSource)
				) {
					distanceFromSource[it.destination] = Visit(VisitType.EDGE, it)
					queue.add(it.destination)
				}
				steps.add(MementoDijkstra(vertex, distanceFromSource, queue, path))
			}
			steps.add(MementoDijkstra(vertex, distanceFromSource, queue, path))
		}
	}

	fun shortestPath(
		source: String,
		destination: String
	) {
		this.shortestPath(source)
		path = route(destination, distanceFromSource)
	}

	override fun toString(): String {
		return buildString {
			append("Start Vertex: ${graph.getStartVertex()}\n\n")
			append("Graph: \n$graph\n\n")
			distanceFromSource.keys.forEach {

				if (it.name == graph.getStartVertex()) return@forEach

				shortestPath(graph.getStartVertex()!!, it.name)
				var sumWeight = 0.0
				path.forEach { edge ->
					sumWeight += edge.weight ?: 0.0
					append("${edge.source.name} --|${edge.weight ?: 0.0}|--> ${edge.destination.name} +\n")
				}
				append("Weight of the path: $sumWeight\n\n")
			}
		}
	}

}

class Visit(val type: VisitType, val edge: Edge? = null)

enum class VisitType {
	START,
	EDGE
}

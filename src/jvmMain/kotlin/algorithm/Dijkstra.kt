package algorithm

import java.util.PriorityQueue
import kotlin.math.abs

@Suppress("DEPRECATION")
class Dijkstra(private val graph: Graph) {
	private var distanceFromSource: HashMap<Vertex, Visit>? = null
	private var resultPath: MutableList<Edge>? = null
	private fun route(
		destination: String,
		paths: HashMap<Vertex, Visit>
	): MutableList<Edge> {
		var vertex = graph.vertex(destination)
		val path = mutableListOf<Edge>()
		loop@while (true) {
			val visit = paths[vertex] ?: break

			when (visit.type) {
				VisitType.EDGE -> visit.edge?.let {
					path.add(it)
					vertex = it.source
				}
				VisitType.START -> break@loop
			}
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

	private fun shortestPath(startVertex: String) {
		val start = graph.vertex(startVertex)

		if (start == null) {
			distanceFromSource = HashMap()
			return
		}

		val paths = hashMapOf<Vertex, Visit>()
		paths[start] = Visit(VisitType.START)

		val priorityQueue = PriorityQueue<Vertex> { first, second ->
			abs(distance(second.name, paths) - distance(first.name, paths)).toInt()
		}

		priorityQueue.add(start)

		while (priorityQueue.isNotEmpty()) {
			val vertex = priorityQueue.remove()
			val edges = graph.edges(vertex)

			edges.forEach {
				val weight = it.weight ?: return@forEach

				if (paths[it.destination] == null ||
					distance(vertex.name, paths) + weight < distance(it.destination.name, paths)
				) {
					paths[it.destination] = Visit(VisitType.EDGE, it)
					priorityQueue.add(it.destination)
				}
			}
		}
		distanceFromSource = paths
	}

	fun shortestPath(
		source: String,
		destination: String
	) {
		this.shortestPath(source)
		resultPath = route(destination, distanceFromSource!!)
	}
	fun getPath() = resultPath

	override fun toString(): String {
		return buildString {
			append("Start Vertex: ${graph.getStartVertex()}")
			append("Graph: $graph")
			distanceFromSource!!.keys.forEach {
				shortestPath(graph.getStartVertex()!!, it.name)
				var sumWeight = 0.0
				resultPath?.forEach { edge ->
					sumWeight += edge.weight ?: 0.0
					append("${edge.source.name} --|${edge.weight ?: 0.0}|--> ${edge.destination.name} +\n")
				}
				append("Weight of the path: $sumWeight\n")
			}
		}
	}
}

class Visit(val type: VisitType, val edge: Edge? = null)

enum class VisitType {
	START,
	EDGE
}

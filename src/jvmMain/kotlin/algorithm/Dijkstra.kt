package algorithm

import algorithm.graph.Edge
import algorithm.graph.Graph
import algorithm.graph.Vertex
import java.util.PriorityQueue
import kotlin.math.abs

@Suppress("DEPRECATION")
class Dijkstra(private val graph: Graph) {

	private var distanceFromSource: HashMap<Vertex, Visit> = HashMap()
	private var resultPath: MutableList<Edge> = mutableListOf()
	private var priorityQueue: PriorityQueue<Vertex> = PriorityQueue()
	private var steps = mutableListOf<MementoDijkstra>()

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

	fun shortestPath(startVertex: String) {

		steps.add(MementoDijkstra(
			graph.getStartVertex()!!,
			distanceFromSource,
			resultPath,
			priorityQueue)
//			mutableListOf("Стартовая вершина: ${graph.getStartVertex()}"))
		)

		val start = graph.vertex(startVertex)

		if (start == null) {
			distanceFromSource = HashMap()
			return
		}

		distanceFromSource[start] = Visit(VisitType.START)

		priorityQueue = PriorityQueue<Vertex> { first, second ->
			abs(distance(second.name, distanceFromSource) - distance(first.name, distanceFromSource)).toInt()
		}

		priorityQueue.add(start)

		while (priorityQueue.isNotEmpty()) {
			val vertex = priorityQueue.remove()
			val edges = graph.edges(vertex)

			edges.forEach {
				val weight = it.weight ?: return@forEach

				if (distanceFromSource[it.destination] == null ||
					distance(vertex.name, distanceFromSource) + weight < distance(it.destination.name, distanceFromSource)
				) {
					distanceFromSource[it.destination] = Visit(VisitType.EDGE, it)
					priorityQueue.add(it.destination)
				}
				steps.add(
					MementoDijkstra(
						vertex.name,
						distanceFromSource,
						resultPath,
						priorityQueue
//						mutableListOf("Произведена релаксация вершины ${vertex.name}.")
					)
				)
			}
			steps.add(MementoDijkstra(
				"",
				distanceFromSource,
				resultPath,
				priorityQueue)
//				mutableListOf("Обработка вершины ${vertex.name} окончена"))
			)
		}
	}

	fun shortestPath(
		source: String,
		destination: String
	) {
		this.shortestPath(source)
		resultPath = route(destination, distanceFromSource)
	}
	fun getPath() = resultPath
	fun getDistanceFromSourse() = distanceFromSource

	override fun toString(): String {
		return buildString {
			append("Start Vertex: ${graph.getStartVertex()}\n\n")
			append("Graph: \n$graph\n\n")
			distanceFromSource.keys.forEach {

				if (it.name == graph.getStartVertex()) return@forEach

				shortestPath(graph.getStartVertex()!!, it.name)
				var sumWeight = 0.0
				resultPath.forEach { edge ->
					sumWeight += edge.weight ?: 0.0
					append("${edge.source.name} --|${edge.weight ?: 0.0}|--> ${edge.destination.name} +\n")
				}
				append("Weight of the path: $sumWeight\n\n")
			}
		}
	}

	fun getMemento() = steps
}

class Visit(val type: VisitType, val edge: Edge? = null)

enum class VisitType {
	START,
	EDGE
}

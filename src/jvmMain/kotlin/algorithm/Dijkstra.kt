package algorithm

import java.util.PriorityQueue
import kotlin.math.abs

@Suppress("DEPRECATION")
class Dijkstra(private val graph: Graph) {
	private fun route(
		destination: Vertex,
		paths: HashMap<Vertex, Visit>
	): MutableList<Edge>{
		var vertex = destination
		val path = mutableListOf<Edge>()
		loop@while(true){
			val visit = paths[vertex] ?: break

			when(visit.type){
				VisitType.EDGE -> visit.edge?.let{
					path.add(it)
					vertex = it.source
				}
				VisitType.START -> break@loop
			}
		}
		return path
	}

	private fun distance(
		destination: Vertex,
		paths: HashMap<Vertex, Visit>
	): Double{
		val path = route(destination, paths)
		return path.sumByDouble{ it.weight ?: 0.0 }
	}

	fun shortestPath(start: Vertex): HashMap<Vertex, Visit>{
		val paths = hashMapOf<Vertex, Visit>()
		paths[start] = Visit(VisitType.START)

		val priorityQueue = PriorityQueue<Vertex> { first, second ->
			abs(distance(second, paths) - distance(first, paths)).toInt()
		}

		priorityQueue.add(start)

		while(priorityQueue.isNotEmpty()){
			val vertex = priorityQueue.remove()
			val edges = graph.edges(vertex)

			edges.forEach{
				val weight = it.weight ?: return@forEach

				if (paths[it.destination] == null ||
					distance(vertex, paths) + weight < distance(it.destination, paths)){
					paths[it.destination] = Visit(VisitType.EDGE, it)
					priorityQueue.add(it.destination)
				}
			}
		}
		return paths
	}

	fun shortestPath(
		destination: Vertex,
		paths: HashMap<Vertex, Visit>
	): MutableList<Edge>{
		return route(destination, paths)
	}

}

class Visit(val type: VisitType, val edge: Edge? = null)

enum class VisitType{
	START,
	EDGE
}

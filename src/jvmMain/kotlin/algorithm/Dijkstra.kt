package algorithm

import java.util.PriorityQueue
import kotlin.math.abs

@Suppress("DEPRECATION")
class Dijkstra<T>(private val graph: Graph<T>) {
	private fun route(
		destination: Vertex<T>,
		paths: HashMap<Vertex<T>, Visit<T>>
	): MutableList<Edge<T>>{
		var vertex = destination
		val path = mutableListOf<Edge<T>>()
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
		destination: Vertex<T>,
		paths: HashMap<Vertex<T>, Visit<T>>
	): Double{
		val path = route(destination, paths)
		return path.sumByDouble{ it.weight ?: 0.0 }
	}

	fun shortestPath(start: Vertex<T>): HashMap<Vertex<T>, Visit<T>>{
		val paths = hashMapOf<Vertex<T>, Visit<T>>()
		paths[start] = Visit(VisitType.START)

		val priorityQueue = PriorityQueue<Vertex<T>> { first, second ->
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
		destination: Vertex<T>,
		paths: HashMap<Vertex<T>, Visit<T>>
	): MutableList<Edge<T>>{
		return route(destination, paths)
	}

}

class Visit<T>(val type: VisitType, val edge: Edge<T>? = null)

enum class VisitType{
	START,
	EDGE
}

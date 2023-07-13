package algorithm.dijkstra

import algorithm.graph.DirectedEdge
import algorithm.graph.GraphModel
import algorithm.graph.Vertexes
import algorithm.graph.WeightedGraphModel
import java.util.*

class Dijkstra(graphModel: WeightedGraphModel) {
	private var edgeTo = mutableListOf<DirectedEdge?>()
	private var distTo = mutableListOf<Double>()
	private var visitedVertex = mutableListOf<Boolean>()
	private val priorityQueue = PriorityQueue<Vertexes>()
	private var steps = mutableListOf<ShortestPath>()
	private val digraph = graphModel
	private fun relaxation(graph: GraphModel, vertex: Int) {

		steps.add(
			ShortestPath(
				vertex,
				visitedVertex,
				edgeTo,
				priorityQueue,
				mutableListOf("Обрабатываемая вершина: ${digraph.name(vertex)}")
			)
		)
		graph.getEdgesForVertex(vertex).forEach {
			val vertexTo = it.to
			if (distTo[vertexTo] > (distTo[vertex] + it.weight)) {
				val log = "Произведена релаксация!\nМетка вершины ${digraph.name(vertexTo)} была изменена с " + distTo[vertexTo].toString() + " на " + (distTo[vertex] + it.weight).toString()
				distTo[vertexTo] = distTo[vertex] + it.weight
				edgeTo[vertexTo] = it
				if (priorityQueue.contains(Vertexes(vertexTo))) {
					val iterator = priorityQueue.iterator()
					while (iterator.hasNext()) {
						val currentElement = iterator.next()
						if (currentElement.value == vertexTo) {
							iterator.remove()
							break
						}
					}
					priorityQueue.offer(Vertexes(distTo[vertexTo], vertexTo))
				} else {
					priorityQueue.offer(Vertexes(distTo[vertexTo], vertexTo))
				}
				steps.add(
					ShortestPath(
						vertex,
						visitedVertex,
						edgeTo,
						priorityQueue,
						mutableListOf(log)
					)
				)
			}
		}
		visitedVertex[vertex] = true
		steps.add(
			ShortestPath(
				-1,
				visitedVertex,
				edgeTo,
				priorityQueue,
				mutableListOf("Вершина ${digraph.name(vertex)} обработана")
			)
		)
	}

	fun buildWay(graph: GraphModel, source: Int): MutableList<ShortestPath> {
		(0..graph.getVertexCount()).forEach { _ ->
			distTo.add(Double.MAX_VALUE)
		}
		edgeTo = mutableListOf(*arrayOfNulls(graph.getVertexCount()))
		distTo[source] = 0.0
		priorityQueue.offer(Vertexes(distTo[source], source))
		visitedVertex = MutableList(graph.getVertexCount()) { false }
		steps.add(
			ShortestPath(
				-1,
				visitedVertex,
				edgeTo,
				priorityQueue,
				mutableListOf("Начальное состояние алгоритма. В очереди только вершина ${digraph.name(source)}")
			)
		)
		while (priorityQueue.isNotEmpty()) {
			relaxation(graph, priorityQueue.poll().value)
		}

		return steps
	}

	fun hasPathTo(v: Int): Boolean {
		return distTo[v] < Double.MAX_VALUE
	}

	fun pathTo(v: Int): Iterable<DirectedEdge>? {
		if (!hasPathTo(v)) return null
		val path = Stack<DirectedEdge>()
		var edges = edgeTo[v]
		while (edges != null) {
			path.push(edges)
			edges = edgeTo[edges.from]
		}
		return path
	}
}

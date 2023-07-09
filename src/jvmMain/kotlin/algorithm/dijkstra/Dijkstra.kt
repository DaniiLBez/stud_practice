package algorithm.dijkstra

import algorithm.graph.Digraph
import algorithm.graph.DirectedEdge
import algorithm.graph.Entry
import java.util.*

class Dijkstra : ShortestWayAlgorithm {
	private var edgeTo = mutableListOf<DirectedEdge?>()
	private var distTo = mutableListOf<Double>()
	private var visitedVertex = mutableListOf<Boolean>()
	private val priorityQueue = PriorityQueue<Entry>()
	private var steps = mutableListOf<ShortestWay>()

	fun relax(graph: Digraph, vertex: Int) {
		steps.add(
			ShortestWay(
				vertex,
				visitedVertex,
				edgeTo,
				priorityQueue,
				mutableListOf("Выбрана текущая вершина: $vertex")
			)
		)
		graph.getEdgesForVertex(vertex).forEach {
			val vertexTo = it.to
			if (distTo[vertexTo] > (distTo[vertex] + it.weight)) {
				val log = "Была произведена релаксация, метка вершины $vertexTo была изменена с " + distTo[vertexTo].toString() + " на " + (distTo[vertex] + it.weight).toString()
				distTo[vertexTo] = distTo[vertex] + it.weight
				edgeTo[vertexTo] = it
				if (priorityQueue.contains(Entry(vertexTo))) {
					val iterator = priorityQueue.iterator()
					while (iterator.hasNext()) {
						val currentElement = iterator.next()
						if (currentElement.value == vertexTo) {
							iterator.remove()
							break
						}
					}
					priorityQueue.offer(Entry(distTo[vertexTo], vertexTo))
				} else {
					priorityQueue.offer(Entry(distTo[vertexTo], vertexTo))
				}
				steps.add(
					ShortestWay(
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
			ShortestWay(
				-1,
				visitedVertex,
				edgeTo,
				priorityQueue,
				mutableListOf("Обработка вершины $vertex была закончена")
			)
		)
	}

	override fun buildWay(g: Digraph, source: Int, target: Int): MutableList<ShortestWay> {
		(0..g.getVertexCount()).forEach {
			distTo.add(Double.MAX_VALUE)
		}
		edgeTo = mutableListOf(*arrayOfNulls(g.getVertexCount()))
		distTo[source] = 0.0
		priorityQueue.offer(Entry(distTo[source], source))
		visitedVertex = MutableList(g.getVertexCount()) { false }
		steps.add(
			ShortestWay(
				-1,
				visitedVertex,
				edgeTo,
				priorityQueue,
				mutableListOf("Начальное состояние алгоритма. В очереди только вершина $source")
			)
		)
		while (priorityQueue.isNotEmpty()) {
			relax(g, priorityQueue.poll().value)
		}

		return steps
	}

	override fun hasPathTo(v: Int): Boolean {
		return distTo[v] < Double.MAX_VALUE
	}

	override fun pathTo(v: Int): Iterable<DirectedEdge>? {
		if (!hasPathTo(v)) return null
		val path = Stack<DirectedEdge>()
		var e = edgeTo[v]
		while (e != null) {
			path.push(e)
			e = edgeTo[e.from]
		}
		return path
	}
}

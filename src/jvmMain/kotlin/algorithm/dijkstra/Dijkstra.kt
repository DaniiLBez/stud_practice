package algorithm.dijkstra

import algorithm.graph.Digraph
import algorithm.graph.DirectedEdge
import java.util.*


class Dijkstra : ShortestWayAlgorithm {
	private var edgeTo: Array<DirectedEdge>
	private var distTo: DoubleArray
	private var visitedVertex: BooleanArray
	private var priorityQueue: PriorityQueue<Map.Entry<*, *>>? = null
	private var steps: MutableList<MementoShortestWay>? = null
	private fun relax(graph: Digraph, vertex: Int) {
		steps!!.add(
			MementoShortestWay(
				vertex,
				visitedVertex,
				edgeTo,
				priorityQueue,
				arrayOf<String>("Выбрана текущая вершина: %s", Integer.toString(vertex))
			)
		)
		for (edge in graph.getEdgesForVertex(vertex)) {
			val vertexTo = edge.to
			if (distTo[vertexTo] > distTo[vertex] + edge.weight) {
				val log = ("Была произведена релаксация, метка вершины %s была изменена с "
					+ distTo[vertexTo] + " на " + (distTo[vertex] + edge.weight))
				distTo[vertexTo] = distTo[vertex] + edge.weight
				edgeTo[vertexTo] = edge
				if (priorityQueue!!.contains(MutableMap.MutableEntry<Any?, Any?>(vertexTo))) {
					val iterator = priorityQueue!!.iterator()
					while (iterator.hasNext()) {
						val (_, value) = iterator.next()
						if (value == vertexTo) {
							iterator.remove()
							break
						}
					}
					priorityQueue!!.offer(MutableMap.MutableEntry<Any?, Any?>(distTo[vertexTo], vertexTo))
				} else {
					priorityQueue!!.offer(MutableMap.MutableEntry<Any?, Any?>(distTo[vertexTo], vertexTo))
				}
				steps!!.add(
					MementoShortestWay(
						vertex, visitedVertex,
						edgeTo, priorityQueue, arrayOf<String>(log, Integer.toString(vertexTo))
					)
				)
			}
		}
		visitedVertex[vertex] = true
		steps!!.add(
			MementoShortestWay(
				-1,
				visitedVertex,
				edgeTo,
				priorityQueue,
				arrayOf("Обаботка вершины ${vertex} была закончена")
			)
		)
	}

	fun buildWay(graph: Digraph, source: Int, target: Int): List<MementoShortestWay>? {
		steps = ArrayList<MementoShortestWay>()
		edgeTo = arrayOfNulls(graph.getVertexCount())
		distTo = DoubleArray(graph.getVertexCount())
		visitedVertex = BooleanArray(graph.getVertexCount())
		priorityQueue = PriorityQueue()
		for (vertexNumber in 0 until graph.getVertexCount()) distTo[vertexNumber] = Double.POSITIVE_INFINITY
		distTo[source] = 0.0
		priorityQueue!!.offer(MutableMap.MutableEntry<Any?, Any?>(distTo[source], source))
		steps!!.add(
			MementoShortestWay(
				-1,
				visitedVertex,
				edgeTo,
				priorityQueue,
				arrayOf<String>(
					"Начальное состояние алгоритма. В очереди только одна вершина: %s",
					Integer.toString(source)
				)
			)
		)
		while (!priorityQueue!!.isEmpty()) {
			relax(graph, priorityQueue!!.poll().value)
		}
		return steps
	}

	fun hasPathTo(v: Int): Boolean {
		return distTo[v] < Double.POSITIVE_INFINITY
	}

	fun pathTo(v: Int): Iterable<DirectedEdge>? {
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

package algorithm.graph

import algorithm.dijkstra.ShortestWay
import algorithm.dijkstra.ShortestWayAlgorithm
import java.util.*


class WeightedDigraph(stream: String, separator: String) {
	private var graph: Digraph? = null
	private var vertexNameOfNumber: HashMap<String, Int>? = null

	init {
		val graphStringRepresentation = prepareGraphStringRepresentation(stream, separator)
		val graphSize = graphStringRepresentation.stream().map { obj: List<String> -> obj.size }
			.reduce { a: Int, b: Int ->
				Integer.sum(
					a,
					b
				)
			}.orElse(0)
		buildGraphFromStringRepresentation(graphStringRepresentation, graphSize)
	}

	private fun prepareGraphStringRepresentation(stream: String, separator: String): List<List<String>> {
		val graphStringRepresentation = mutableListOf<List<String>>()
		val scanner = Scanner(stream)
		while (scanner.hasNext()) {
			val stringGraph = scanner.nextLine().split(String.format("[\\%s]", separator)).toList()
			graphStringRepresentation.add(stringGraph)
		}
		return graphStringRepresentation
	}

	private fun buildGraphFromStringRepresentation(graphStringRepresentation: List<List<String>>, graphSize: Int) {
		graph = Digraph(graphSize)
		vertexNameOfNumber = HashMap()
		for (graphLine in graphStringRepresentation) {
			val sourceVertexName = graphLine[0]
			if (graphLine.size > 1) {
				val targetVertexName = graphLine[1]
				vertexNameOfNumber!!.putIfAbsent(sourceVertexName, vertexNameOfNumber!!.size)
				vertexNameOfNumber!!.putIfAbsent(targetVertexName, vertexNameOfNumber!!.size)
				val sourceVertexNumber = vertexNameOfNumber!![sourceVertexName]!!
				val targetVertexNumber = vertexNameOfNumber!![targetVertexName]!!
				val weight = graphLine[2].toDouble()
				graph!!.addEdge(DirectedEdge(sourceVertexNumber, targetVertexNumber, weight))
			} else {
				vertexNameOfNumber!!.putIfAbsent(sourceVertexName, vertexNameOfNumber!!.size)
			}
		}
	}

	fun index(s: String): Int {
		return vertexNameOfNumber!![s]!!
	}

	fun name(vertex: Int): String {
		return ArrayList(vertexNameOfNumber!!.keys).stream()
			.filter { el: String ->
				(vertexNameOfNumber!![el]
					== vertex)
			}
			.findFirst()
			.get()
	}

	fun shortestWay(source: String, target: String, algorithm: ShortestWayAlgorithm): List<ShortestWay> {
		return algorithm.buildWay(graph!!, index(source), index(target)) ?: listOf()
	}
}

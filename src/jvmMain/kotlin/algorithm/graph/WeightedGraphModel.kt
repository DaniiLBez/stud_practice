package algorithm.graph

import algorithm.dijkstra.Dijkstra
import algorithm.dijkstra.ShortestPath
import java.util.*

class WeightedGraphModel(stream: String, separator: String) {
	private var graph: GraphModel? = null
	private var vertexNameOfNumber = hashMapOf<String, Int>()

	init {
		val graphStringRepresentation = prepareGraphStringRepresentation(stream, separator)
		val graphSize = graphStringRepresentation.stream().map { obj: List<String> -> obj.size }
			.reduce { firstSize: Int, secondSize: Int ->
				Integer.sum(
					firstSize,
					secondSize
				)
			}.orElse(0)
		buildGraphFromStringRepresentation(graphStringRepresentation, graphSize)
	}

	private fun prepareGraphStringRepresentation(stream: String, separator: String): List<List<String>> {
		val graphStringRepresentation = mutableListOf<List<String>>()
		val scanner = Scanner(stream)
		while (scanner.hasNext()) {
			val stringGraph = scanner.nextLine().split(separator).toList()
			graphStringRepresentation.add(stringGraph)
		}
		return graphStringRepresentation
	}

	private fun buildGraphFromStringRepresentation(graphStringRepresentation: List<List<String>>, graphSize: Int) {
		graph = GraphModel(graphSize)
		for (graphLine in graphStringRepresentation) {
			val sourceVertexName = graphLine[0]
			if (graphLine.size > 2) {
				val targetVertexName = graphLine[1]
				vertexNameOfNumber.putIfAbsent(sourceVertexName, vertexNameOfNumber.size)
				vertexNameOfNumber.putIfAbsent(targetVertexName, vertexNameOfNumber.size)
				val sourceVertexNumber = vertexNameOfNumber[sourceVertexName]!!
				val targetVertexNumber = vertexNameOfNumber[targetVertexName]!!
				val weight = graphLine[2].toDouble()
				graph!!.addEdge(DirectedEdge(sourceVertexNumber, targetVertexNumber, weight))
			} else {
				vertexNameOfNumber.putIfAbsent(sourceVertexName, vertexNameOfNumber.size)
			}
		}
	}

	fun index(s: String): Int {
		return vertexNameOfNumber[s]!!
	}

	fun name(vertex: Int): String {
		return ArrayList(vertexNameOfNumber.keys).stream()
			.filter { el: String ->
				(
					vertexNameOfNumber[el]
						== vertex
					)
			}
			.findFirst()
			.get()
	}

	fun shortestPath(source: String, target: String, algorithm: Dijkstra): List<ShortestPath> {
		return algorithm.buildWay(graph!!, index(source))
	}
}

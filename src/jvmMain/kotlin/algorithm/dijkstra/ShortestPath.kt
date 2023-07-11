package algorithm.dijkstra

import Constants
import algorithm.graph.DirectedEdge
import algorithm.graph.Vertexes
import algorithm.graph.WeightedGraphModel
import java.util.*

class ShortestPath(
	private val currentVertex: Int,
	processedVertices: MutableList<Boolean>,
	currentWays: MutableList<DirectedEdge?>,
	inQueueVertices: PriorityQueue<Vertexes>?,
	val log: MutableList<String>
) {
	private val processedVertices: MutableList<Boolean>
	private val currentWays: MutableList<DirectedEdge?>
	private val inQueueVertices: PriorityQueue<Vertexes>

	init {
		this.processedVertices = processedVertices.toMutableList()
		this.currentWays = currentWays.toMutableList()
		this.inQueueVertices = PriorityQueue(inQueueVertices)
	}

	fun getCurrentVertex(digraph: WeightedGraphModel, separator: String): String {
		return if (currentVertex != -1) digraph.name(currentVertex) + separator + "\n" else ""
	}

	fun getProcessedVertices(digraph: WeightedGraphModel, separator: String?): String {
		val stringBuilder = StringBuilder()
		for (i in processedVertices.indices) {
			if (processedVertices[i]) {
				stringBuilder.append(digraph.name(i)).append(separator).append("\n")
			}
		}
		return stringBuilder.toString()
	}

	fun getCurrentWays(digraph: WeightedGraphModel): String {
		val stringBuilder = StringBuilder()
		for (edge in currentWays) {
			if (edge != null) {
				stringBuilder.append(digraph.name(edge.from))
					.append(Constants.SEPARATOR)
					.append(digraph.name(edge.to))
					.append(Constants.SEPARATOR)
					.append(edge.weight)
					.append("\n")
			}
		}
		return stringBuilder.toString()
	}

	fun getInQueueVertices(digraph: WeightedGraphModel, separator: String?): String {
		return buildString {
			inQueueVertices.forEach {
				append(digraph.name(it.value)).append(separator).append("\n")
			}
		}
	}

	override fun toString(): String {
		return "MementoShortestWay{" +
			"currentVertex=" + currentVertex +
			", processedVertices=" + processedVertices.toString() +
			", currentWays=" + currentWays.toString() +
			", inQueueVertices=" + inQueueVertices +
			", log=" + log.toString() +
			'}'
	}
}

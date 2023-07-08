package algorithm.dijkstra

import algorithm.graph.DirectedEdge
import algorithm.graph.Entry
import algorithm.graph.WeightedDigraph
import java.util.*


class MementoShortestWay(
	private val currentVertex: Int,
	processedVertices: BooleanArray,
	currentWays: MutableList<DirectedEdge?>,
	inQueueVertices: PriorityQueue<Entry>?,
	val log: Array<String>
) {
	private val processedVertices: BooleanArray
	private val currentWays: MutableList<DirectedEdge?>
	private val inQueueVertices: PriorityQueue<Entry>

	init {
		this.processedVertices = processedVertices.clone()
		this.currentWays = currentWays
		this.inQueueVertices = PriorityQueue(inQueueVertices)
	}

	fun getCurrentVertex(digraph: WeightedDigraph, separator: String): String {
		return if (currentVertex != -1) digraph.name(currentVertex) + separator + "\n" else ""
	}

	fun getProcessedVertices(digraph: WeightedDigraph, separator: String?): String {
		val stringBuilder = StringBuilder()
		for (i in processedVertices.indices) {
			if (processedVertices[i]) {
				stringBuilder.append(digraph.name(i)).append(separator).append("\n")
			}
		}
		return stringBuilder.toString()
	}

	fun getCurrentWays(digraph: WeightedDigraph, separator: String?): String {
		val stringBuilder = StringBuilder()
		for (edge in currentWays) {
			if (edge != null) {
				stringBuilder.append(digraph.name(edge.from))
					.append(separator)
					.append(digraph.name(edge.to))
					.append(separator)
					.append(edge.weight)
					.append("\n")
			}
		}
		return stringBuilder.toString()
	}

	fun getInQueueVertices(digraph: WeightedDigraph, separator: String?): String {
		return buildString {
			inQueueVertices.forEach {
				append(digraph.name(it.value))
			}
		}
	}

	override fun toString(): String {
		return "MementoShortestWay{" +
			"currentVertex=" + currentVertex +
			", processedVertices=" + Arrays.toString(processedVertices) +
			", currentWays=" + currentWays.toString() +
			", inQueueVertices=" + inQueueVertices +
			", log=" + Arrays.toString(log) +
			'}'
	}
}

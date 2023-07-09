package model.algorithm

import com.mxgraph.model.mxCell

class ShortestWayView(
	private val currentVertex: Any?,
	private val processedVertices: MutableList<mxCell>?,
	private val currentWays: MutableList<mxCell>?,
	private val inQueueVertices: MutableList<mxCell>?,
	private val answer: MutableList<mxCell>?,
	private val log: String?
) {

	fun getCurrentVertex(): Any? {
		return currentVertex
	}

	fun getProcessedVertices(): MutableList<mxCell>? {
		return processedVertices
	}

	fun getCurrentWays(): MutableList<mxCell>? {
		return currentWays
	}

	fun getInQueueVertices(): MutableList<mxCell>? {
		return inQueueVertices
	}

	fun getAnswer(): MutableList<mxCell>? {
		return answer
	}

	fun getLog(): String? {
		return log
	}
}

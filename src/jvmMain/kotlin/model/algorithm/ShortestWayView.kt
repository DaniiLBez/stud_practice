package model.algorithm


class ShortestWayView(
	private val currentVertex: Any?,
	private val processedVertices: Array<Any>?,
	private val currentWays: Array<Any>?,
	private val inQueueVertices: Array<Any>?,
	private val answer: Array<Any>?,
	private val log: String?) {

	fun getCurrentVertex(): Any? {
		return currentVertex
	}

	fun getProcessedVertices(): Array<Any>? {
		return processedVertices
	}

	fun getCurrentWays(): Array<Any>? {
		return currentWays
	}

	fun getInQueueVertices(): Array<Any>? {
		return inQueueVertices
	}

	fun getAnswer(): Array<Any>? {
		return answer
	}

	fun getLog(): String? {
		return log
	}

}



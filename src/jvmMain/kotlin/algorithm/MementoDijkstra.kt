package algorithm

import algorithm.graph.Edge
import algorithm.graph.Vertex
import java.util.PriorityQueue

class MementoDijkstra(
	curVertex: String,
	curDistances: HashMap<Vertex, Visit>,
	currentWay: MutableList<Edge>,
	currentQueue: PriorityQueue<Vertex>,
//	logs: MutableList<String>
) {
	var currentVertex = curVertex
	var distances = curDistances.clone()
	var curPath = currentWay
	var curQueue = PriorityQueue(currentQueue)
//	var logs = logs

	override fun toString(): String {
		return buildString {
			append(this@MementoDijkstra.curPath.toString()).append("\n")
			append(this@MementoDijkstra.currentVertex + "\n")
			append(distances.toString()+"\n")
			append(curQueue.toString()+"\n")
//			append(logs).append("\n")
		}
	}
}

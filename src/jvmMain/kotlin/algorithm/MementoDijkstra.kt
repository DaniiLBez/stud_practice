package algorithm

import algorithm.graph.Edge
import algorithm.graph.Vertex
import java.util.PriorityQueue

class MementoDijkstra(
	curVertex: Vertex,
	curDistances: HashMap<Vertex, Visit>,
	curQueueVertexes: PriorityQueue<Vertex>,
	curPaths: MutableList<Edge>
) {
	val currentVertex = curVertex
	val distances = curDistances.clone()
	val inQueueVertexes = PriorityQueue(curQueueVertexes)
	val paths = curPaths
}

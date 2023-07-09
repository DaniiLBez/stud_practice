package algorithm.graph

class Digraph(private val vertexCount: Int) {
	private val edges = mutableListOf<MutableSet<DirectedEdge>>()
	init {
		(0..vertexCount).forEach { _ ->
			edges.add(mutableSetOf())
		}
	}

	fun getVertexCount(): Int {
		return vertexCount
	}

	fun addEdge(edge: DirectedEdge) {
		edges[edge.from].add(edge)
	}

	fun getEdgesForVertex(vertex: Int): Iterable<DirectedEdge> {
		return edges[vertex]
	}
}

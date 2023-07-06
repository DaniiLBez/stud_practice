package algorithm.graph

data class Edge(
	val source: Vertex,
	val destination: Vertex,
	val weight: Double? = null
)

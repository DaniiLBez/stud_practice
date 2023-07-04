package algorithm

data class Edge<T>(
	val source: Vertex<T>,
	val destination: Vertex<T>,
	val weight: Double? = null
)

package algorithm

interface Graph {
	fun setStart(name: String)
	fun getStartVertex(): String?
	fun createVertex(data: String): Vertex
	fun add(
		source: Vertex,
		destination: Vertex,
		weight: Double?
	)
	fun edges(source: Vertex): MutableList<Edge>
	fun weight(source: Vertex, destination: Vertex): Double?

	fun vertex(name: String): Vertex?
}

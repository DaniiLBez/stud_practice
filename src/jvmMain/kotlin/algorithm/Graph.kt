package algorithm

interface Graph <T>{
	fun setStart(vertex: Vertex<T>)
	fun createVertex(data: T): Vertex<T>
	fun add(
		source: Vertex<T>,
		destination: Vertex<T>,
		weight: Double?
	)
	fun edges(source: Vertex<T>): MutableList<Edge<T>>
	fun weight(source: Vertex<T>, destination: Vertex<T>): Double?
}

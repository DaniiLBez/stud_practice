package algorithm

class AdjacencyList<T>: Graph<T> {
	private val adjacencies: HashMap<Vertex<T>, MutableList<Edge<T>>> = HashMap()
	private var startVertex: Vertex<T>? = null

	override fun setStart(vertex: Vertex<T>) {
		startVertex = vertex
	}

	override fun vertex(name: T): Vertex<T>? {
		for(vertex in this.adjacencies.keys){
			if(vertex.name == name){
				return vertex
			}
		}
		return null
	}

	override fun createVertex(data: T): Vertex<T> {
		val vertex = Vertex(adjacencies.count(), data)
		adjacencies[vertex] = mutableListOf()
		return vertex
	}

	override fun add(source: Vertex<T>, destination: Vertex<T>, weight: Double?) {
		val edge = Edge(source, destination, weight)
		adjacencies[source]?.add(edge)
	}

	override fun edges(source: Vertex<T>) =
		adjacencies[source] ?: mutableListOf()

	override fun weight(source: Vertex<T>, destination: Vertex<T>) =
		edges(source).firstOrNull{
			it.destination == destination
		}?.weight

	override fun toString(): String {
		return buildString { // 1
			adjacencies.forEach { (vertex, edges) -> // 2
				val edgeString = edges.joinToString{
					it.destination.name.toString()
				} // 3
				append("${vertex.name} ---> [ $edgeString ]\n") // 4
			}
		}
	}
}

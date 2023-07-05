package algorithm

class AdjacencyList: Graph {
	private val adjacencies: HashMap<Vertex, MutableList<Edge>> = HashMap()
	private var startVertex: String? = null

	override fun getStartVertex() = startVertex
	override fun setStart(name: String) {
		startVertex = name
	}
	override fun vertex(name: String): Vertex? {
		for(vertex in this.adjacencies.keys){
			if(vertex.name == name){
				return vertex
			}
		}
		return null
	}

	override fun createVertex(data: String): Vertex {
		val vertex = Vertex(adjacencies.count(), data)
		adjacencies[vertex] = mutableListOf()
		return vertex
	}

	override fun add(source: Vertex, destination: Vertex, weight: Double?) {
		val edge = Edge(source, destination, weight)
		adjacencies[source]?.add(edge)
	}

	override fun edges(source: Vertex) =
		adjacencies[source] ?: mutableListOf()

	override fun weight(source: Vertex, destination: Vertex) =
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

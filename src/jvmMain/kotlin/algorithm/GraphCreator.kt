package algorithm

class GraphCreator(_data: IDataSource) {
	private val data = _data
	private var graph: Graph = AdjacencyList()

	fun create() {
		val startVertex = data.getGraphStartVertex()
		graph.setStart(startVertex)

		for (nameVertex in data.getRawData().keys) {
			val currentVertex = graph.vertex(nameVertex) ?: graph.createVertex(nameVertex)
			data.getRawData()[nameVertex]?.forEach {
				val destinationVertex = graph.vertex(it.first) ?: graph.createVertex(it.first)
				graph.add(
					currentVertex,
					destinationVertex,
					it.second
				)
			}
		}
	}
	fun getGraph() = this.graph
}

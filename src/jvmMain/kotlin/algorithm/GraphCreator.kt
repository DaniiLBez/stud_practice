package algorithm

class GraphCreator(_data: IDataSource) {
	private val data = _data
	private var graph: Graph<String> = AdjacencyList()

	fun create(){
		val startVertex = graph.createVertex(data.getGraphStartVertex())
		graph.setStart(startVertex)

		for(nameVertex in data.getRawData().keys){
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

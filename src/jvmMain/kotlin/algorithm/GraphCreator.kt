package algorithm

class GraphCreator(_data: IDataSource) {
	private val data = _data
	private var graph: Graph<String> = AdjacencyList()

	fun create(){
		val startVertex = graph.createVertex(data.getGraphStartVertex())
		graph.setStart(startVertex)

		for(nameVertex in data.getRawData().keys){
			val currentVertex = graph.createVertex(nameVertex)
			data.getRawData()[nameVertex]?.forEach {
				graph.add(
					currentVertex,
					graph.createVertex(it.first),
					it.second
				)
			}
		}

		println(graph)
	}


}

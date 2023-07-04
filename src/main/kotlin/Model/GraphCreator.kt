package Model

class GraphCreator(_data: IDataSourse) {
	private val data = _data
	private var graph: Graph = Graph()

	fun create(){
		val startVertex = Vertex(data.getGraphStartVertex())
		graph.setStartVertex(startVertex)

		for(nameVertex in data.getRawData().keys){
			val currentVertex = Vertex(nameVertex)
			currentVertex.addEdge(data.getRawData()[nameVertex]!!)
			graph.addVertex(currentVertex)
		}

	println("${graph.getVertexes()}")
	}


}

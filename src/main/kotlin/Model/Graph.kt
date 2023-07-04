package Model

class Graph {
	private var startVertex: Vertex = Vertex()
	private var vertexes: MutableList<Vertex>  = mutableListOf()

	fun addVertex(vertex: Vertex): Unit{
		vertexes.add(vertex)
	}
	fun setStartVertex(_startVertex: Vertex){
		startVertex = _startVertex
	}
	fun getStartVertex(): Vertex{
		return startVertex
	}

//	fun setVertexes(_vertexes: MutableList<Vertex>){
//		vertexes = _vertexes
//	}
	fun getVertexes(): MutableList<Vertex>{
		return vertexes
	}

//	fun save(): GraphMomento{}
//
//	fun load(graph: GraphMomento): Unit{}

}

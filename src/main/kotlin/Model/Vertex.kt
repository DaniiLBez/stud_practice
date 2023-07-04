package Model

class Vertex(){
	private var name: String = ""
	private var neighbors: MutableList<Pair<String, Double>> = mutableListOf()

	constructor(_name: String):this(){
		name = _name
	}

	fun addEdge(fromVertex: String, weight: Double): Unit{
		neighbors.add(Pair(fromVertex, weight))
	}
	fun addEdge(neighborsList: MutableList<Pair<String, Double>>): Unit{
		neighbors = neighborsList
	}
}

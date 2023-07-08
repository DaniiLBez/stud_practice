package UI

import algorithm.graph.Graph

class GraphRepresentation(graph: Graph) {

	val vertexesList = graph.edges().keys.toMutableList().map { it.name }
	val uniqueEdges = mutableSetOf(
		graph.edges().values
			.flatten()
			.toMutableList()
			.map { Triple(it.source.name, it.destination.name, it.weight.toString()) }
	)

}

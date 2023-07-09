package algorithm.dijkstra

import algorithm.graph.Digraph
import algorithm.graph.DirectedEdge


interface ShortestWayAlgorithm {
	fun buildWay(g: Digraph, source: Int, target: Int): List<MementoShortestWay>?
	fun hasPathTo(v: Int): Boolean
	fun pathTo(v: Int): Iterable<DirectedEdge?>?
}


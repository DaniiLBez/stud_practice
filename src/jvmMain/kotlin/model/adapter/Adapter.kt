package model.adapter

import Constants
import algorithm.dijkstra.Dijkstra
import algorithm.dijkstra.ShortestPath
import algorithm.graph.WeightedGraphModel
import com.mxgraph.model.mxCell
import com.mxgraph.view.mxGraph
import model.algorithm.ShortestPathView
import java.util.*
import java.util.function.Consumer

class Adapter {
	private fun convertFromMxGraphToGraph(graph: mxGraph): String {
		val vertex: MutableSet<String> = HashSet()
		val graphString = StringBuilder()
		for (edges in graph.getChildEdges(graph.defaultParent)) {
			val edge: mxCell = edges as mxCell
			val source = edge.source.value as String
			val target = edge.target.value as String
			vertex.add(source)
			vertex.add(target)
			graphString.append(source).append(Constants.SEPARATOR).append(target).append(Constants.SEPARATOR).append(edge.value).append("\n")
		}
		for (vertexes in graph.getChildVertices(graph.defaultParent)) {
			val ver: mxCell = vertexes as mxCell
			if (!vertex.contains(ver.value)) {
				vertex.add(ver.value as String)
				graphString.append(ver.value as String).append(Constants.SEPARATOR).append("\n")
			}
		}
		return graphString.toString()
	}

	private fun convertFromGraphToMxGraph(graph: mxGraph, subgraph: String): MutableList<mxCell>? {
		val cells: MutableList<mxCell> = ArrayList<mxCell>()
		val scanner = Scanner(subgraph)
		while (scanner.hasNextLine()) {
			val readingString = scanner.nextLine().split(java.lang.String.format("[\\%s]", Constants.SEPARATOR).toRegex())
				.dropLastWhile { it.isEmpty() }
				.toTypedArray()
			if (readingString.size == 1) {
				for (vertexes in graph.getChildVertices(graph.defaultParent)) {
					if ((vertexes as mxCell).value.equals(readingString[0])) {
						cells.add(vertexes)
					}
				}
			} else {
				for (edges in graph.getChildEdges(graph.defaultParent)) {
					val source = (edges as mxCell).source.value as String
					val target = (edges).target.value as String
					if (source == readingString[0] && target == readingString[1]) {
						cells.add(edges)
						cells.add(edges.source as mxCell)
						cells.add(edges.target as mxCell)
					}
				}
			}
		}
		return if (cells.isEmpty()) null else cells.toMutableList()
	}

	private fun convertToView(
		graph: mxGraph,
		digraph: WeightedGraphModel,
		mementos: List<ShortestPath>
	): MutableList<ShortestPathView> {
		val mementosView = mutableListOf<ShortestPathView>()
		for (memento in mementos) {
			val currentVertex = convertFromGraphToMxGraph(graph, memento.getCurrentVertex(digraph, Constants.SEPARATOR))
			val processedVertices = convertFromGraphToMxGraph(graph, memento.getProcessedVertices(digraph, Constants.SEPARATOR))
			val currentWays = convertFromGraphToMxGraph(graph, memento.getCurrentWays(digraph))
			val inQueueVertices = convertFromGraphToMxGraph(graph, memento.getInQueueVertices(digraph, Constants.SEPARATOR))
			val log: String = memento.log[0]
			if (currentVertex != null) mementosView.add(
				ShortestPathView(
					currentVertex[0],
					processedVertices,
					currentWays,
					inQueueVertices,
					null,
					log
				)
			) else mementosView.add(
				ShortestPathView(
					null,
					processedVertices,
					currentWays,
					inQueueVertices,
					null,
					log
				)
			)
		}
		return mementosView
	}

	fun shortestPath(currentAlgorithm: String?, graph: Any?, s: Any?, t: Any?, callbackEnd: Consumer<List<ShortestPathView>>) {
		Thread {
			val digraph = WeightedGraphModel(convertFromMxGraphToGraph(graph as mxGraph), Constants.SEPARATOR)
			var mementosView = mutableListOf<ShortestPathView>()
			var algorithm: Dijkstra? = null
			val sourceStr = (s as mxCell).value as String
			val targetStr = (t as mxCell).value as String
			when (currentAlgorithm) {
				"Алгоритм Дейкстры" -> {
					algorithm = Dijkstra(digraph)
					mementosView =
						convertToView(graph, digraph, digraph.shortestPath(sourceStr, targetStr, algorithm))
				}
			}
			if (algorithm != null) {
				val stringCreator = StringBuilder()
				val partPath = StringBuilder()
				val path = mutableListOf<String>()
				var pathWeight = 0.0
				val log: String
				val answer: MutableList<mxCell>?
				if (algorithm.hasPathTo(digraph.index(targetStr))) {
					for (elements in algorithm.pathTo(digraph.index(targetStr))!!) {
						stringCreator
							.append(digraph.name(elements.from))
							.append(Constants.SEPARATOR)
							.append(digraph.name(elements.to))
							.append(Constants.SEPARATOR)
							.append(elements.weight)
							.append("\n")
						partPath
							.append(digraph.name(elements.from))
							.append("->")
							.append(digraph.name(elements.to))
							.append(": ")
							.append(elements.weight)
							.append("\n")
						pathWeight += if (path.contains(digraph.name(elements.to))) {
							path.add(digraph.name(elements.from))
							path.add("->")
							elements.weight
						} else {
							path.add(digraph.name(elements.to))
							path.add("->")
							path.add(digraph.name(elements.from))
							path.add("->")
							elements.weight
						}
					}
					path.removeAt(path.size - 1)
					path.reverse()
					path.add("=")
					path.add(pathWeight.toString())

					val logPath = buildString {
						path.forEach {
							append(it)
						}
					}

					log = "Путь из вершины $sourceStr в вершину $targetStr найден \n$partPath$logPath"
					answer = convertFromGraphToMxGraph(graph, stringCreator.toString())
				} else {
					answer = null
					log = "Пути из вершины $sourceStr в вершину $targetStr не существует \n"
				}
				mementosView.add(
					ShortestPathView(
						null, null, null, null,
						answer, log
					)
				)
			}
			callbackEnd.accept(mementosView)
		}.start()
	}
}

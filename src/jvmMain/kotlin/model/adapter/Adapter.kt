package model.adapter

import Constants
import algorithm.dijkstra.Dijkstra
import algorithm.dijkstra.ShortestWay
import algorithm.dijkstra.ShortestWayAlgorithm
import algorithm.graph.WeightedDigraph
import com.mxgraph.model.mxCell
import com.mxgraph.view.mxGraph
import model.algorithm.ShortestWayView
import java.util.*
import java.util.function.Consumer

class Adapter : IAdapter {
	private fun convertFromMxGraphToGraph(graph: mxGraph): String {
		val vertex: MutableSet<String> = HashSet()
		val sb = StringBuilder()
		for (e in graph.getChildEdges(graph.defaultParent)) {
			val edge: mxCell = e as mxCell
			val source = edge.source.value as String
			val target = edge.target.value as String
			vertex.add(source)
			vertex.add(target)
			sb.append(source).append(Constants.SEPARATOR).append(target).append(Constants.SEPARATOR).append(edge.value).append("\n")
		}
		for (v in graph.getChildVertices(graph.defaultParent)) {
			val ver: mxCell = v as mxCell
			if (!vertex.contains(ver.value)) {
				vertex.add(ver.value as String)
				sb.append(ver.value as String).append(Constants.SEPARATOR).append("\n")
			}
		}
		return sb.toString()
	}

	private fun convertFromGraphToMxGraph(graph: mxGraph, subgraph: String): MutableList<mxCell>? {
		val cells: MutableList<mxCell> = ArrayList<mxCell>()
		val scanner = Scanner(subgraph)
		while (scanner.hasNextLine()) {
			val a = scanner.nextLine().split(java.lang.String.format("[\\%s]", Constants.SEPARATOR).toRegex())
				.dropLastWhile { it.isEmpty() }
				.toTypedArray()
			if (a.size == 1) {
				for (v in graph.getChildVertices(graph.defaultParent)) {
					if ((v as mxCell).value.equals(a[0])) {
						cells.add(v)
					}
				}
			} else {
				for (e in graph.getChildEdges(graph.defaultParent)) {
					val source = (e as mxCell).source.value as String
					val target = (e).target.value as String
					if (source == a[0] && target == a[1]) {
						cells.add(e)
						cells.add(e.source as mxCell)
						cells.add(e.target as mxCell)
					}
				}
			}
		}
		return if (cells.isEmpty()) null else cells.toMutableList()
	}

	private fun convertToView(
		graph: mxGraph,
		digraph: WeightedDigraph,
		mementos: List<ShortestWay>
	): MutableList<ShortestWayView> {
		val mementosView: MutableList<ShortestWayView> = ArrayList<ShortestWayView>()
		for (memento in mementos) {
			val currentVertex = convertFromGraphToMxGraph(graph, memento.getCurrentVertex(digraph, Constants.SEPARATOR))
			val processedVertices = convertFromGraphToMxGraph(graph, memento.getProcessedVertices(digraph, Constants.SEPARATOR))
			val currentWays = convertFromGraphToMxGraph(graph, memento.getCurrentWays(digraph, Constants.SEPARATOR))
			val inQueueVertices = convertFromGraphToMxGraph(graph, memento.getInQueueVertices(digraph, Constants.SEPARATOR))
			val log: String = memento.log[0]
			if (currentVertex != null) mementosView.add(
				ShortestWayView(
					currentVertex[0],
					processedVertices,
					currentWays,
					inQueueVertices,
					null,
					log
				)
			) else mementosView.add(
				ShortestWayView(
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

	override fun shortestWay(alg: String?, gr: Any?, s: Any?, t: Any?, callbackEnd: Consumer<List<ShortestWayView>>) {
		Thread {
			val digraph = WeightedDigraph(convertFromMxGraphToGraph(gr as mxGraph), Constants.SEPARATOR)
			var mementosView = mutableListOf<ShortestWayView>()
			var algorithm: ShortestWayAlgorithm? = null
			val sourceStr = (s as mxCell).value as String
			val targetStr = (t as mxCell).value as String
			when (alg) {
				Constants.DIJKSTRA -> {
					algorithm = Dijkstra()
					mementosView =
						convertToView(gr, digraph, digraph.shortestWay(sourceStr, targetStr, algorithm))
				}
			}
			if (algorithm != null) {
				val sb = StringBuilder()
				val log: String
				val answer: MutableList<mxCell>?
				if (algorithm.hasPathTo(digraph.index(targetStr))) {
					for (e in algorithm.pathTo(digraph.index(targetStr))!!) {
						sb.append(digraph.name(e!!.from))
							.append(Constants.SEPARATOR)
							.append(digraph.name(e.to))
							.append(Constants.SEPARATOR).append(e.weight)
							.append("\n")
					}
					log = "Путь из вершины $sourceStr в вершину $targetStr найден \n$sb"
					answer = convertFromGraphToMxGraph(gr, sb.toString())
				} else {
					answer = null
					log = "Пути из вершины $sourceStr в вершину $targetStr не существует \n"
				}
				mementosView.add(
					ShortestWayView(
						null, null, null, null,
						answer, log
					)
				)
			}
			callbackEnd.accept(mementosView)
		}.start()
	}
}

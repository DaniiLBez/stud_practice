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
		for (e in graph.getChildEdges(graph.getDefaultParent())) {
			val edge: mxCell = e as mxCell
			val source = edge.getSource().getValue() as String
			val target = edge.getTarget().getValue() as String
			vertex.add(source)
			vertex.add(target)
			sb.append(source).append(Constants.SEPARATOR).append(target).append(Constants.SEPARATOR).append(edge.getValue()).append("\n")
		}
		for (v in graph.getChildVertices(graph.getDefaultParent())) {
			val ver: mxCell = v as mxCell
			if (!vertex.contains(ver.getValue())) {
				vertex.add(ver.getValue() as String)
				sb.append(ver.getValue() as String).append(Constants.SEPARATOR).append("\n")
			}
		}
		return sb.toString()
	}

	private fun convertFromGraphToMxGraph(graph: mxGraph, subgraph: String): Array<Any>? {
		val cells: MutableList<mxCell> = ArrayList<mxCell>()
		val scanner = Scanner(subgraph)
		while (scanner.hasNextLine()) {
			val a = scanner.nextLine().split(java.lang.String.format("[\\%s]", Constants.SEPARATOR).toRegex())
				.dropLastWhile { it.isEmpty() }
				.toTypedArray()
			if (a.size == 1) {
				for (v in graph.getChildVertices(graph.getDefaultParent())) {
					if ((v as mxCell).getValue().equals(a[0])) {
						cells.add(v as mxCell)
					}
				}
			} else {
				for (e in graph.getChildEdges(graph.getDefaultParent())) {
					val source = (e as mxCell).getSource().getValue() as String
					val target = (e as mxCell).getTarget().getValue() as String
					if (source == a[0] && target == a[1]) {
						cells.add(e as mxCell)
						cells.add((e as mxCell).getSource() as mxCell)
						cells.add((e as mxCell).getTarget() as mxCell)
					}
				}
			}
		}
		return if (cells.isEmpty()) null else cells.toTypedArray()
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
			val log: String =
				java.lang.String.format(memento.log.get(0), digraph.name(memento.log.get(1).toInt()))
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

	override fun  shortestWay(alg: String?, gr: Any?, s: Any?, t: Any?, callbackEnd: Consumer<List<ShortestWayView?>?>?) {
		Thread {
			val digraph = WeightedDigraph(convertFromMxGraphToGraph(gr as mxGraph), Constants.SEPARATOR)
			var mementosView: MutableList<ShortestWayView>? = null
			var algorithm: ShortestWayAlgorithm? = null
			val sourceStr = (s as mxCell).getValue() as String
			val targetStr = (t as mxCell).getValue() as String
			when (alg) {
				Constants.DIJKSTRA -> {
					algorithm = Dijkstra()
					mementosView =
						convertToView(gr as mxGraph, digraph, digraph.shortestWay(sourceStr, targetStr, algorithm!!))
				}
			}
			if (algorithm != null) {
				val sb = StringBuilder()
				val answer: Array<Any>?
				val log: String
				if (algorithm.hasPathTo(digraph.index(targetStr))) {
					for (e in algorithm.pathTo(digraph.index(targetStr))!!) {
						sb.append(digraph.name(e!!.from))
							.append(Constants.SEPARATOR)
							.append(digraph.name(e.to))
							.append(Constants.SEPARATOR).append(e.weight)
							.append("\n")
					}
					log = "Путь из вершины $sourceStr в вершину $targetStr найден \n$sb"
					answer = convertFromGraphToMxGraph(gr as mxGraph, sb.toString())
				} else {
					answer = null
					log = "Пути из вершины $sourceStr в вершину $targetStr не существует \n"
				}
				mementosView!!.add(
					ShortestWayView(
						null, null, null, null,
						answer, log
					)
				)
			}
			callbackEnd!!.accept(mementosView)
		}.start()
	}
}


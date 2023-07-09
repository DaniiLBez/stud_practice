package model.states

import Constants
import com.mxgraph.model.mxCell
import com.mxgraph.view.mxGraph
import java.io.*

class GraphCreatorModelImpl : GraphCreatorModel {
	override val graph: mxGraph
	private val parent: Any

	init {
		graph = object : mxGraph() {
			override fun isCellSelectable(cell: Any): Boolean {
				return if ((cell as mxCell).isEdge) {
					false
				} else super.isCellSelectable(cell)
			}
		}
		parent = graph.getDefaultParent()
		graph.setCellsEditable(false) // Нельзя редактировать
		graph.setCellsResizable(false) // Нельзя изменять текст
		graph.setDisconnectOnMove(false) // Нельзя двигать ребро
		graph.setCellsDisconnectable(false) // Нельзя отрывать ребро от вершины
		graph.setEdgeLabelsMovable(false) // Нельзя двигать именную метку ребра
		graph.setKeepEdgesInBackground(true) // Ребра на заднем плане
		graph.setCellsSelectable(false)
		graph.setCellsMovable(false)
		StyleManager.initMyCustomEdgeNormalStyle(graph)
		StyleManager.initMyCustomEdgeSelectedStyle(graph)
		StyleManager.initMyCustomVertexNormalStyle(graph)
		StyleManager.initMyCustomVertexSelectedStyle(graph)
		StyleManager.initMyCustomCurrentVertexStyle(graph)
		StyleManager.initMyCustomInQueueVertexStyle(graph)
	}

	override fun addVertex(name: String?, posX: Double, posY: Double, width: Double, height: Double): Boolean {
		var myName = name
		for (v in graph.getChildVertices(parent)) {
			if ((v as mxCell).value == myName) return false
		}
		if (name!!.length > Constants.MAX_SIZE_VERTEX_NAME)
			myName = name.substring(0, Constants.MAX_SIZE_VERTEX_NAME) + "..."
		graph.model.beginUpdate()
		graph.insertVertex(parent, null, myName, posX, posY, width, height, Constants.MY_CUSTOM_VERTEX_NORMAL_STYLE)
		graph.model.endUpdate()
		return true
	}

	override fun addEdge(weight: String?, vertex1: Any?, vertex2: Any?): Boolean {
		try {
			val w = weight!!.toDouble()
			if (w < 0) return false
			graph.model.beginUpdate()
			graph.insertEdge(parent, null, weight, vertex1, vertex2, Constants.MY_CUSTOM_EDGE_NORMAL_STYLE)
			graph.model.endUpdate()
		} catch (e: NumberFormatException) {
			return false
		}
		return true
	}

	override fun delete(cells: Array<Any>) {
		graph.model.beginUpdate()
		graph.removeCells(cells)
		graph.model.endUpdate()
	}

	override fun checkExistEdge(s: Any?, t: Any?): Int {
		val sourceVertex = s as mxCell
		val targetVertex = t as mxCell
		for (i in 0 until sourceVertex.edgeCount) {
			val target = (sourceVertex.getEdgeAt(i) as mxCell).target as mxCell
			if (target === targetVertex) return i
		}
		return -1
	}

	override fun setNormalStyle() {
		graph.setCellStyle(Constants.MY_CUSTOM_EDGE_NORMAL_STYLE, graph.getChildEdges(graph.defaultParent))
		graph.setCellStyle(Constants.MY_CUSTOM_VERTEX_NORMAL_STYLE, graph.getChildVertices(graph.defaultParent))
	}

	override fun setStyleSelected(flag: Boolean, cells: MutableList<mxCell>?) {
		for (c in cells!!) {
			if (flag) {
				if (c.isVertex) {
					graph.setCellStyle(Constants.MY_CUSTOM_VERTEX_SELECTED_STYLE, arrayOf<Any>(c))
				} else if (c.isEdge) graph.setCellStyle(Constants.MY_CUSTOM_EDGE_SELECTED_STYLE, arrayOf<Any>(c))
			} else {
				if (c.isVertex) graph.setCellStyle(
					Constants.MY_CUSTOM_VERTEX_NORMAL_STYLE,
					arrayOf<Any>(c)
				) else if (c.isEdge) graph.setCellStyle(Constants.MY_CUSTOM_EDGE_NORMAL_STYLE, arrayOf<Any>(c))
			}
		}
	}

	override fun setStyle(style: String?, cells: MutableList<mxCell>) {
		graph.setCellStyle(style, cells.toTypedArray())
	}

	@Throws(IOException::class)
	override fun saveGraph(fileName: String?) {
		ObjectOutputStream(fileName?.let { FileOutputStream(it) }).use { outputStream ->
			outputStream.writeObject(
				graph.getChildCells(parent)
			)
		}
	}

	@Throws(IOException::class, ClassNotFoundException::class)
	override fun loadGraph(fileName: String?) {
		ObjectInputStream(fileName?.let { FileInputStream(it) }).use { inputStream ->
			graph.removeCells(graph.getChildCells(parent))
			graph.addCells(inputStream.readObject() as Array<Any?>)
		}
	}

// 	fun getGraph(): Any {
// 		return graph
// 	}
}

package model.states

import Constants
import com.mxgraph.model.mxCell
import com.mxgraph.view.mxGraph
import java.io.*

@Suppress("UNCHECKED_CAST")
class CreationAreaModel {
	val graph: mxGraph
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
		graph.setCellsEditable(false)
		graph.setCellsResizable(false)
		graph.setDisconnectOnMove(false)
		graph.setCellsDisconnectable(false)
		graph.setEdgeLabelsMovable(false)
		graph.setKeepEdgesInBackground(true)
		graph.setCellsSelectable(false)
		graph.setCellsMovable(false)
		StyleManager.customEdgeNormalStyle(graph)
		StyleManager.customEdgeSelectedStyle(graph)
		StyleManager.customVertexNormalStyle(graph)
		StyleManager.customVertexSelectedStyle(graph)
		StyleManager.customCurrentVertexStyle(graph)
		StyleManager.customInQueueVertexStyle(graph)
	}

	fun addVertex(name: String?, posX: Double, posY: Double, width: Double, height: Double): Boolean {
		var myName = name
		for (v in graph.getChildVertices(parent)) {
			if ((v as mxCell).value == myName) return false
		}
		if (name!!.length > Constants.MAX_SIZE_VERTEX_NAME)
			myName = name.substring(0, Constants.MAX_SIZE_VERTEX_NAME) + "..."
		graph.model.beginUpdate()
		graph.insertVertex(parent, null, myName, posX, posY, width, height, Constants.VERTEX_NORMAL_STYLE)
		graph.model.endUpdate()
		return true
	}

	fun addEdge(weight: String?, vertexFirst: Any?, vertexSecond: Any?): Boolean {
		try {
			val checkingWeight = weight!!.toDouble()
			if (checkingWeight < 0) return false
			graph.model.beginUpdate()
			graph.insertEdge(parent, null, weight, vertexFirst, vertexSecond, Constants.EDGE_NORMAL_STYLE)
			graph.model.endUpdate()
		} catch (error: NumberFormatException) {
			return false
		}
		return true
	}

	fun delete(cells: Array<Any>) {
		graph.model.beginUpdate()
		graph.removeCells(cells)
		graph.model.endUpdate()
	}

	fun checkExistEdge(s: Any?, t: Any?): Int {
		val sourceVertex = s as mxCell
		val targetVertex = t as mxCell
		for (i in 0 until sourceVertex.edgeCount) {
			val target = (sourceVertex.getEdgeAt(i) as mxCell).target as mxCell
			if (target === targetVertex) return i
		}
		return -1
	}

	fun setNormalStyle() {
		graph.setCellStyle(Constants.EDGE_NORMAL_STYLE, graph.getChildEdges(graph.defaultParent))
		graph.setCellStyle(Constants.VERTEX_NORMAL_STYLE, graph.getChildVertices(graph.defaultParent))
	}

	fun setStyleSelected(flag: Boolean, cells: MutableList<mxCell>?) {
		for (cellsElements in cells!!) {
			if (flag) {
				if (cellsElements.isVertex) {
					graph.setCellStyle(Constants.VERTEX_SELECTED_STYLE, arrayOf<Any>(cellsElements))
				} else if (cellsElements.isEdge) graph.setCellStyle(Constants.EDGE_SELECTED_STYLE, arrayOf<Any>(cellsElements))
			} else {
				if (cellsElements.isVertex) graph.setCellStyle(
					Constants.VERTEX_NORMAL_STYLE,
					arrayOf<Any>(cellsElements)
				) else if (cellsElements.isEdge) graph.setCellStyle(Constants.EDGE_NORMAL_STYLE, arrayOf<Any>(cellsElements))
			}
		}
	}

	fun setStyle(style: String?, cells: MutableList<mxCell>) {
		graph.setCellStyle(style, cells.toTypedArray())
	}

	@Throws(IOException::class)
	fun saveGraph(fileName: String?) {
		ObjectOutputStream(fileName?.let { FileOutputStream(it) }).use { outputStream ->
			outputStream.writeObject(
				graph.getChildCells(parent)
			)
		}
	}

	@Throws(IOException::class, ClassNotFoundException::class)
	fun loadGraph(fileName: String?) {
		ObjectInputStream(fileName?.let { FileInputStream(it) }).use { inputStream ->
			graph.removeCells(graph.getChildCells(parent))
			graph.addCells(inputStream.readObject() as Array<Any?>)
		}
	}
}

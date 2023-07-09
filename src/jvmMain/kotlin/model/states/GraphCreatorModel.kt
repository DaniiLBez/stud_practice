package model.states

import java.io.IOException


interface GraphCreatorModel {
	fun addVertex(name: String?, posX: Double, posY: Double, width: Double, height: Double): Boolean
	fun addEdge(weight: String?, vertex1: Any?, vertex2: Any?): Boolean
	fun delete(cells: Array<Any>)
	fun checkExistEdge(s: Any?, t: Any?): Int
	fun setNormalStyle()
	fun setStyleSelected(flag: Boolean, cells: Array<Any>)
	fun setStyle(style: String?, cells: Array<Any>)

	@Throws(IOException::class)
	fun saveGraph(fileName: String?)

	@Throws(IOException::class, ClassNotFoundException::class)
	fun loadGraph(fileName: String?)
	val graph: Any?
}

package controller

import UI.GraphCreatorView
import java.awt.event.MouseEvent

interface GraphCreatorController {
	fun saveGraph()
	fun loadGraph()
	fun setStateOfMotion()
	fun setStateAddingVertices()
	fun setStateOfConnectionVerticies()
	fun setStateOfDelete()
	fun setStateOfAlgorithm()
	fun nextStep()
	fun backStep()
	fun startAlgorithm()
	fun finishAlgorithm()
	fun resetAlgorithm()
	fun mousePressed(e: MouseEvent?, cell: Any?)
	fun mouseReleased(e: MouseEvent?, cell: Any?)
	fun setView(view: GraphCreatorView?)
}

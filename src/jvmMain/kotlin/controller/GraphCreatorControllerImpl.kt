package controller

import View.GraphCreatorView
import model.adapter.IAdapter
import model.states.GraphCreatorModel
import model.states.states.*
import java.awt.event.MouseEvent
import java.io.File
import java.io.IOException

class GraphCreatorImpl(private var model: GraphCreatorModel, private var adapter: IAdapter) : GraphCreatorController {
	private var view: GraphCreatorView? = null
	private var currentState: IState? = null

	override fun saveGraph() {
		val file: File = view!!.showFileChooserDialog("Сохранить граф")!!
		if (file != null) {
			try {
				model.saveGraph(file.absoluteFile.toString())
			} catch (e: IOException) {
				view!!.showErrorDialog("Ошибка", "Не удалось сохраненить граф. Попробуйте еще раз!")
			}
		}
	}

	override fun loadGraph() {
		val file: File = view!!.showFileChooserDialog("Загрузить граф")!!
		if (file != null) {
			try {
				model.loadGraph(file.absoluteFile.toString())
			} catch (e: Exception) {
				view!!.showErrorDialog("Ошибка", "Не удалось загрузить граф. Попробуйте еще раз!")
			}
		}
	}

	override fun setStateOfMotion() {
		currentState!!.close()
		currentState = MoveState(model)
		view!!.setLabelHelp((currentState as MoveState).status)
	}

	override fun setStateAddingVertices() {
		currentState!!.close()
		currentState = AddVertexState(model, view!!)
		view!!.setLabelHelp((currentState as AddVertexState).status)
	}

	override fun setStateOfConnectionVerticies() {
		currentState!!.close()
		currentState = ConnectionVertexState(model, view!!)
		view!!.setLabelHelp((currentState as ConnectionVertexState).status)
	}

	override fun setStateOfDelete() {
		currentState!!.close()
		currentState = DeleteState(model)
		view!!.setLabelHelp((currentState as DeleteState).status)
	}

	override fun setStateOfAlgorithm() {
		currentState!!.close()
		if (currentState !is AlgorithmShortestWayState) {
			currentState = AlgorithmShortestWayState(model, view!!, adapter)
		}
		view!!.setLabelHelp(currentState!!.status)
	}

	override fun nextStep() {
		currentState!!.nextStep()
		view!!.setLabelHelp(currentState!!.status)
	}

	override fun backStep() {
		currentState!!.backStep()
		view!!.setLabelHelp(currentState!!.status)
	}

	override fun startAlgorithm() {
		currentState!!.startAlgorithm()
		view!!.setLabelHelp(currentState!!.status)
	}

	override fun finishAlgorithm() {
		currentState!!.finishAlgorithm()
		view!!.setLabelHelp(currentState!!.status)
	}

	override fun resetAlgorithm() {
		currentState!!.resetAlgorithm()
		view!!.setLabelHelp(currentState!!.status)
	}

	override fun mousePressed(e: MouseEvent?, cell: Any?) {
		currentState!!.mousePressed(e!!.x.toDouble(), e.y.toDouble(), cell)
		view!!.setLabelHelp(currentState!!.status)
	}

	override fun mouseReleased(e: MouseEvent?, cell: Any?) {
		if (e != null) currentState!!.mouseReleased(e.x.toDouble(), e.y.toDouble(), cell) else currentState!!.mouseReleased(
			-1.0,
			-1.0,
			cell
		)
		view!!.setLabelHelp(currentState!!.status)
	}

	override fun setView(view: GraphCreatorView?) {
		this.view = view
		currentState = AddVertexState(model, view!!)
		view.setLabelHelp(currentState!!.status)
	}
}

package controller

import model.adapter.Adapter
import model.states.CreationAreaModel
import model.states.particularStates.*
import ui.AppViewRealization
import java.awt.event.MouseEvent
import java.io.IOException

class CreationAreaController(private var model: CreationAreaModel, private var adapter: Adapter) {
	private var view: AppViewRealization? = null
	private var currentState: IState? = null

	fun saveGraph() {
		val file = view!!.showFileChooserDialog("Сохранить граф")
		if (file != null) {
			try {
				model.saveGraph(file.absoluteFile.toString())
			} catch (error: IOException) {
				view!!.showErrorDialog("Ошибка", "Не удалось сохраненить граф. Попробуйте еще раз!")
			}
		}
	}

	fun loadGraph() {
		val file = view!!.showFileChooserDialog("Загрузить граф")
		if (file != null) {
			try {
				model.loadGraph(file.absoluteFile.toString())
			} catch (error: Exception) {
				view!!.showErrorDialog("Ошибка", "Не удалось загрузить граф. Попробуйте еще раз!")
			}
		}
	}

	fun setStateOfMotion() {
		currentState!!.close()
		currentState = MoveState(model)
		view!!.setLabelHelp((currentState as MoveState).status)
	}

	fun setStateAddingVertices() {
		currentState!!.close()
		currentState = AddVertexState(model, view!!)
		view!!.setLabelHelp((currentState as AddVertexState).status)
	}

	fun setStateOfAddEdge() {
		currentState!!.close()
		currentState = AddEdgeState(model, view!!)
		view!!.setLabelHelp((currentState as AddEdgeState).status)
	}

	fun setStateOfDelete() {
		currentState!!.close()
		currentState = DeleteState(model)
		view!!.setLabelHelp((currentState as DeleteState).status)
	}

	fun setStateOfAlgorithm() {
		currentState!!.close()
		if (currentState !is AlgorithmShortestPathState) {
			currentState = AlgorithmShortestPathState(model, view!!, adapter)
		}
		view!!.setLabelHelp(currentState!!.status)
	}

	fun nextStep() {
		currentState!!.nextStep()
		view!!.setLabelHelp(currentState!!.status)
	}

	fun backStep() {
		currentState!!.backStep()
		view!!.setLabelHelp(currentState!!.status)
	}

	fun startAlgorithm() {
		currentState!!.startAlgorithm()
		view!!.setLabelHelp(currentState!!.status)
	}

	fun finishAlgorithm() {
		currentState!!.finishAlgorithm()
		view!!.setLabelHelp(currentState!!.status)
	}

	fun resetAlgorithm() {
		currentState!!.resetAlgorithm()
		view!!.setLabelHelp(currentState!!.status)
	}

	fun mousePressed(mouseEvent: MouseEvent?, cell: Any?) {
		currentState!!.mousePressed(mouseEvent!!.x.toDouble(), mouseEvent.y.toDouble(), cell)
		view!!.setLabelHelp(currentState!!.status)
	}

	fun mouseReleased(mouseEvent: MouseEvent?, cell: Any?) {
		if (mouseEvent != null) currentState!!.mouseReleased(mouseEvent.x.toDouble(), mouseEvent.y.toDouble(), cell) else currentState!!.mouseReleased(
			-1.0,
			-1.0,
			cell
		)
		view!!.setLabelHelp(currentState!!.status)
	}

	fun setView(view: AppViewRealization?) {
		this.view = view
		currentState = AddVertexState(model, view!!)
		view.setLabelHelp(currentState!!.status)
	}
}

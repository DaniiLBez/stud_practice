package controller

import UI.GraphCreatorViewImpl
import model.adapter.Adapter
import model.states.GraphCreatorModel
import model.states.states.*
import java.awt.event.MouseEvent
import java.io.IOException

class GraphCreatorController(private var model: GraphCreatorModel, private var adapter: Adapter) {
	private var view: GraphCreatorViewImpl? = null
	private var currentState: IState? = null

	fun saveGraph() {
		val file = view!!.showFileChooserDialog("Сохранить граф")
		if (file != null) {
			try {
				model.saveGraph(file.absoluteFile.toString())
			} catch (e: IOException) {
				view!!.showErrorDialog("Ошибка", "Не удалось сохраненить граф. Попробуйте еще раз!")
			}
		}
	}

	fun loadGraph() {
		val file = view!!.showFileChooserDialog("Загрузить граф")
		if (file != null) {
			try {
				model.loadGraph(file.absoluteFile.toString())
			} catch (e: Exception) {
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

	fun setStateOfConnectionVerticies() {
		currentState!!.close()
		currentState = ConnectionVertexState(model, view!!)
		view!!.setLabelHelp((currentState as ConnectionVertexState).status)
	}

	fun setStateOfDelete() {
		currentState!!.close()
		currentState = DeleteState(model)
		view!!.setLabelHelp((currentState as DeleteState).status)
	}

	fun setStateOfAlgorithm() {
		currentState!!.close()
		if (currentState !is AlgorithmShortestWayState) {
			currentState = AlgorithmShortestWayState(model, view!!, adapter)
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

	fun mousePressed(e: MouseEvent?, cell: Any?) {
		currentState!!.mousePressed(e!!.x.toDouble(), e.y.toDouble(), cell)
		view!!.setLabelHelp(currentState!!.status)
	}

	fun mouseReleased(e: MouseEvent?, cell: Any?) {
		if (e != null) currentState!!.mouseReleased(e.x.toDouble(), e.y.toDouble(), cell) else currentState!!.mouseReleased(
			-1.0,
			-1.0,
			cell
		)
		view!!.setLabelHelp(currentState!!.status)
	}

	fun setView(view: GraphCreatorViewImpl?) {
		this.view = view
		currentState = AddVertexState(model, view!!)
		view.setLabelHelp(currentState!!.status)
	}
}

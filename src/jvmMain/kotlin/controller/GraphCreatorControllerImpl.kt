package controller

import java.awt.event.MouseEvent
import java.io.File
import java.io.IOException


class GraphCreatorImpl {
	private val model: GraphCreatorModel? = null
	private val adapter: GraphAdapter? = null
	private var view: GraphCreatorView? = null
	private var currentState: State? = null

	fun GraphCreatorControllerImpl(model: GraphCreatorModel?, adapter: GraphAdapter?) {
		this.model = model
		this.adapter = adapter
	}

	fun saveGraph() {
		val file: File = view.showFileChooserDialog("Сохранить граф")
		if (file != null) {
			try {
				model.saveGraph(file.absoluteFile.toString())
			} catch (e: IOException) {
				view.showErrorDialog("Ошибка", "Не удалось сохраненить граф. Попробуйте еще раз!")
			}
		}
	}

	fun loadGraph() {
		val file: File = view.showFileChooserDialog("Загрузить граф")
		if (file != null) {
			try {
				model.loadGraph(file.absoluteFile.toString())
			} catch (e: Exception) {
				view.showErrorDialog("Ошибка", "Не удалось загрузить граф. Попробуйте еще раз!")
			}
		}
	}

	fun setStateOfMotion() {
		currentState.close()
		currentState = MoveState(model)
		view.setLabelHelp(currentState.getStatus())
	}

	fun setStateOfAddingVertices() {
		currentState.close()
		currentState = AddVertexState(model, view)
		view.setLabelHelp(currentState.getStatus())
	}

	fun setStateOfConnectionVertices() {
		currentState.close()
		currentState = ConnectionVertexState(model, view)
		view.setLabelHelp(currentState.getStatus())
	}

	fun setStateOfDelete() {
		currentState.close()
		currentState = DeleteState(model)
		view.setLabelHelp(currentState.getStatus())
	}

	fun setStateOfAlgorithm() {
		currentState.close()
		if (currentState !is AlgorithmShortestWayState) {
			currentState = AlgorithmShortestWayState(model, view, adapter)
		}
		view.setLabelHelp(currentState.getStatus())
	}

	fun nextStep() {
		currentState.nextStep()
		view.setLabelHelp(currentState.getStatus())
	}

	fun backStep() {
		currentState.backStep()
		view.setLabelHelp(currentState.getStatus())
	}

	fun startAlgorithm() {
		currentState.startAlgorithm()
		view.setLabelHelp(currentState.getStatus())
	}

	fun finishAlgorithm() {
		currentState.finishAlgorithm()
		view.setLabelHelp(currentState.getStatus())
	}

	fun resetAlgorithm() {
		currentState.resetAlgorithm()
		view.setLabelHelp(currentState.getStatus())
	}

	fun mousePressed(e: MouseEvent, cell: Any?) {
		currentState.mousePressed(e.getX(), e.getY(), cell)
		view.setLabelHelp(currentState.getStatus())
	}

	fun mouseReleased(e: MouseEvent?, cell: Any?) {
		if (e != null) currentState.mouseReleased(e.getX(), e.getY(), cell) else currentState.mouseReleased(
			-1,
			-1,
			cell
		)
		view.setLabelHelp(currentState.getStatus())
	}

	fun setView(view: GraphCreatorView) {
		this.view = view
		currentState = AddVertexState(model, view)
		view.setLabelHelp(currentState.getStatus())
	}
}

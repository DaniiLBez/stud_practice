package model.states.states

import View.GraphCreatorView
import com.mxgraph.model.mxCell
import model.states.GraphCreatorModel

// Описывает состояние добавления ребра
class ConnectionVertexState(model: GraphCreatorModel, view: GraphCreatorView) : IState {
	private val model: GraphCreatorModel
	private val view: GraphCreatorView
	private var sourceVertex: mxCell? = null

	init {
		this.model = model
		this.view = view
	}

	// Обработка первого нажатия
	private fun handlerFirstPressed(cell: mxCell) {
		sourceVertex = cell
		model.setStyleSelected(true, mutableListOf(sourceVertex!!))
	}

	// Обработка второго нажатия
	private fun handlerSecondPressed(cell: mxCell) {
		if (model.checkExistEdge(sourceVertex, cell) == -1) {
			val weight: String = view.showInputDialog("Добавить ребро", "Введите вес ребра")!!
			if (weight != null && !weight.isEmpty()) {
				if (!model.addEdge(weight, sourceVertex, cell)) {
					view.showErrorDialog("Ошибка", "Не правильный формат данных")
				}
			}
			model.setStyleSelected(false, mutableListOf(sourceVertex!!))
			sourceVertex = null
		}
	}

	override fun nextStep() {}
	override fun backStep() {}
	override fun startAlgorithm() {}
	override fun finishAlgorithm() {}
	override fun resetAlgorithm() {}
	override fun mousePressed(posX: Double, posY: Double, cell: Any?) {
		if (cell != null && (cell as mxCell).isVertex) {
			val clickCell = cell
			if (sourceVertex == null) {
				handlerFirstPressed(clickCell)
			} else {
				handlerSecondPressed(clickCell)
			}
		} else if (sourceVertex != null) {
			model.setStyleSelected(false, mutableListOf(sourceVertex!!))
			sourceVertex = null
		}
	}

	override fun mouseReleased(posX: Double, posY: Double, cell: Any?) {}
	override val status: String
		get() = if (sourceVertex == null) "Выделите первую вершину для создания дуги" else "Выделите вторую вершину, которую хотите соединить"

	override fun close() {
		model.setNormalStyle()
	}
}

package model.states.states

import Constants
import View.GraphCreatorView
import model.states.GraphCreatorModel

// Описывает состояние добавления вершины
class AddVertexState(model: GraphCreatorModel, view: GraphCreatorView) : IState {
	private val model: GraphCreatorModel
	private val view: GraphCreatorView

	init {
		this.model = model
		this.view = view
	}

	override fun nextStep() {}
	override fun backStep() {}
	override fun startAlgorithm() {}
	override fun finishAlgorithm() {}
	override fun resetAlgorithm() {}
	override fun mousePressed(posX: Double, posY: Double, cell: Any?) {
		val name: String = view.showInputDialog("Добавить вершину", "Введите название вершины")!!
		if (name != null && !name.isEmpty()) {
			if (!model.addVertex(
					name,
					posX - Constants.WIDTH_VERTEX / 2,
					posY - Constants.HEIGHT_VERTEX / 2,
					Constants.WIDTH_VERTEX,
					Constants.HEIGHT_VERTEX
				)
			) {
				view.showErrorDialog("Ошибка", "Такая вершина уже существует!")
			}
		}
	}

	override fun mouseReleased(posX: Double, posY: Double, cell: Any?) {}
	override val status: String
		get() = "Кликните на рабочую область, чтобы добавить вершину"

	override fun close() {}
}

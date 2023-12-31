package model.states.particularStates

import Constants
import model.states.CreationAreaModel
import ui.AppView

class AddVertexState(model: CreationAreaModel, view: AppView) : IState {
	private val model: CreationAreaModel
	private val view: AppView

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
		val name = view.showInputDialog("Добавить вершину", "Введите название вершины")
		if (!name.isNullOrEmpty()) {
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

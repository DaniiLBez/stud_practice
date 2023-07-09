package model.states.states

import com.mxgraph.view.mxGraph
import model.states.GraphCreatorModel

// Описывает состояние удаление ребер и вершин
class DeleteState(model: GraphCreatorModel) : IState {
	private val model: GraphCreatorModel

	init {
		this.model = model
	}

	override fun nextStep() {}
	override fun backStep() {}
	override fun startAlgorithm() {}
	override fun finishAlgorithm() {}
	override fun resetAlgorithm() {}
	override fun mousePressed(posX: Double, posY: Double, cell: Any?) {
		if (cell != null) {
			val cells = arrayOf(cell)
			model.delete((model.graph as mxGraph).getAllEdges(cells))
			model.delete(cells)
		}
	}

	override fun mouseReleased(posX: Double, posY: Double, cell: Any?) {}
	override val status: String
		get() = "Кликните по объекту, который хотите удалить"

	override fun close() {}
}

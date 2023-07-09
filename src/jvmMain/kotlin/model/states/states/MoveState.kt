package model.states.states
import com.mxgraph.model.mxCell
import com.mxgraph.view.mxGraph
import model.states.GraphCreatorModel

// Описывает состояние движения вершин
class MoveState(graphCreatorModel: GraphCreatorModel) : IState {
	private val model: GraphCreatorModel
	private var currentCell: mxCell? = null

	init {
		model = graphCreatorModel
		(model.graph as mxGraph).isCellsSelectable = true
		(model.graph as mxGraph).isCellsMovable = true
	}

	override fun nextStep() {}
	override fun backStep() {}
	override fun startAlgorithm() {}
	override fun finishAlgorithm() {}
	override fun resetAlgorithm() {}
	override fun mousePressed(posX: Double, posY: Double, cell: Any?) {
		if (cell != null && (cell as mxCell).isVertex) {
			currentCell = cell
			model.setStyleSelected(true, mutableListOf(cell))
		}
	}

	override fun mouseReleased(posX: Double, posY: Double, cell: Any?) {
		if (currentCell != null) {
			model.setStyleSelected(false, mutableListOf(currentCell!!))
			currentCell = null
		}
	}

	override val status: String
		get() = if (currentCell == null) "Выделите и перемещайте объекты" else "Перемещайте курсор для перемещения объекта"

	override fun close() {
		(model.graph as mxGraph).isCellsSelectable = false
		(model.graph as mxGraph).isCellsMovable = false
		model.setNormalStyle()
	}
}

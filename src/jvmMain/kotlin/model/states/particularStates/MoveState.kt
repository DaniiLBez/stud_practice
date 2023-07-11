package model.states.particularStates
import com.mxgraph.model.mxCell
import model.states.CreationAreaModel

class MoveState(graphCreatorModel: CreationAreaModel) : IState {
	private val model: CreationAreaModel
	private var currentCell: mxCell? = null

	init {
		model = graphCreatorModel
		model.graph.isCellsSelectable = true
		model.graph.isCellsMovable = true
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
		model.graph.isCellsSelectable = false
		model.graph.isCellsMovable = false
		model.setNormalStyle()
	}
}

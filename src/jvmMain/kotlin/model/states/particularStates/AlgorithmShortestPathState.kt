package model.states.particularStates

import Constants
import com.mxgraph.model.mxCell
import model.adapter.Adapter
import model.algorithm.ShortestPathView
import model.states.CreationAreaModel
import ui.AppView

class AlgorithmShortestPathState(model: CreationAreaModel, view: AppView, adapter: Adapter) : IState {
	private val model: CreationAreaModel
	private val view: AppView
	private val adapter: Adapter
	private var stepsView: List<ShortestPathView?>? = null
	private var curStatus: Status = Status.NORMAL
	private var sourceVertex: mxCell? = null
	private var indexStep = -1

	init {
		this.model = model
		this.view = view
		this.adapter = adapter
	}

	// Обработка нажатия кнопки мыши, если не выбрана не одна вершина
	private fun handlerNormal(source: mxCell) {
		curStatus = Status.SELECT_ONE_VERTEX
		sourceVertex = source
		model.setStyleSelected(true, mutableListOf(sourceVertex!!))
		view.setEnabledResetButton(true)
	}

	// Обработка нажатия кнопки мыши, если выбрана одна вершина
	private fun handlerSelectOneVer(target: mxCell) {
		curStatus = Status.PROCESSING
		model.setStyleSelected(true, mutableListOf(target))
		view.setLog("____________________________________________________________________________")
		view.setLog("Поиск кратчайшего пути из вершины " + sourceVertex!!.value + " в вершину " + target.value)
		view.setLog("_____________________________________________________________________________")
		adapter.shortestPath(
			"Алгоритм Дейкстры", model.graph, sourceVertex, target
		) { mementosView ->
			stepsView = mementosView
			curStatus = Status.DISPLAY
			view.setEnabledStartButton(true)
			view.setLabelHelp(status)
		}
	}

	// Обработка нажатия кнопки мыши, если путь уже показан
	private fun handlerDisplay(cell: mxCell) {
		close()
		view.setEnabledResetButton(true)
		model.setStyleSelected(true, mutableListOf(cell))
		curStatus = Status.SELECT_ONE_VERTEX
		sourceVertex = cell
		indexStep = -1
	}

	// Показать шаг алгоритма
	private fun showMemento(viewMemento: ShortestPathView) {
		model.setNormalStyle()
		val currentVertex = viewMemento.getCurrentVertex()
		val processedVertices = viewMemento.getProcessedVertices()
		val currentWays = viewMemento.getCurrentWays()
		val inQueueVertices = viewMemento.getInQueueVertices()
		val answer = viewMemento.getAnswer()
		if (currentWays != null) model.setStyleSelected(true, currentWays)
		if (inQueueVertices != null) model.setStyle(Constants.QUEUE_VERTEX_STYLE, inQueueVertices)
		if (currentVertex != null) model.setStyle(Constants.CURRENT_VERTEX_STYLE, mutableListOf(currentVertex as mxCell))
		if (processedVertices != null) model.setStyle(Constants.VERTEX_SELECTED_STYLE, processedVertices)
		if (answer != null) model.setStyleSelected(true, answer)
		view.setLog(viewMemento.getLog())
	}

	override fun nextStep() {
		if (indexStep != -1 && indexStep + 1 < stepsView!!.size - 1) {
			showMemento(stepsView!![++indexStep]!!)
			view.setEnabledBackButton(true)
		}
		if (indexStep == stepsView!!.size - 2) view.setEnabledNextButton(false)
	}

	override fun backStep() {
		if (indexStep != -1 && indexStep - 1 >= 0) {
			showMemento(stepsView!![--indexStep]!!)
			view.setEnabledNextButton(true)
		}
		if (indexStep == 0) view.setEnabledBackButton(false)
	}

	override fun startAlgorithm() {
		view.setEnabledStartButton(false)
		view.setEnabledFinishButton(true)
		view.setEnabledNextButton(true)
		view.setEnabledBackButton(true)
		indexStep = 0
		showMemento(stepsView!![indexStep]!!)
	}

	override fun finishAlgorithm() {
		view.setEnabledStartButton(false)
		view.setEnabledFinishButton(false)
		view.setEnabledNextButton(false)
		view.setEnabledBackButton(false)
		for (i in indexStep + 1 until stepsView!!.size - 1) {
			view.setLog(stepsView!![i]!!.getLog())
		}
		showMemento(stepsView!!.last()!!)
	}

	override fun resetAlgorithm() {
		close()
		curStatus = Status.NORMAL
		sourceVertex = null
		indexStep = -1
	}

	override fun mousePressed(posX: Double, posY: Double, cell: Any?) {
		if (cell != null && (cell as mxCell).isVertex) {
			val clickCell = cell
			when (curStatus) {
				Status.NORMAL -> handlerNormal(clickCell)
				Status.SELECT_ONE_VERTEX -> handlerSelectOneVer(
					clickCell
				)
				Status.DISPLAY -> handlerDisplay(
					clickCell
				)

				else -> {}
			}
		}
	}

	override fun mouseReleased(posX: Double, posY: Double, cell: Any?) {}
	override val status: String
		get() {
			return when (curStatus) {
				Status.NORMAL -> "Выделите вершину, из которой хотите найти кратчайших путь"
				Status.SELECT_ONE_VERTEX -> "Выделите конечную вершину кратчайшего пути"
				Status.PROCESSING -> "Обработка..."
				Status.DISPLAY -> when (indexStep) {
					-1 -> {
						"Нажмите старт,чтобы начать пошаговый просмотр алгоритма"
					}
					stepsView!!.size - 1 -> {
						stepsView!!.last()!!.getLog()!!.split("\\n")[0]
					}
					else -> {
						"Пошаговый режим включен"
					}
				}
			}
		}

	override fun close() {
		model.setNormalStyle()
		view.setEnabledStartButton(false)
		view.setEnabledFinishButton(false)
		view.setEnabledBackButton(false)
		view.setEnabledNextButton(false)
		view.setEnabledResetButton(false)
	}

	private enum class Status {
		NORMAL,
		SELECT_ONE_VERTEX,
		PROCESSING,
		DISPLAY
	}
}

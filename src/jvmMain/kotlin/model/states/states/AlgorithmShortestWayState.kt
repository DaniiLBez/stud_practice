package model.states.states

import Constants
import UI.GraphCreatorView
import com.mxgraph.model.mxCell
import model.adapter.IAdapter
import model.algorithm.ShortestWayView
import model.states.GraphCreatorModel


class AlgorithmShortestWayState(model: GraphCreatorModel, view: GraphCreatorView, adapter: IAdapter) : IState {
	private val model: GraphCreatorModel
	private val view: GraphCreatorView
	private val adapter: IAdapter
	private var stepsView: List<ShortestWayView?>? = null
	private var curStatus: Status =
		Status.NORMAL
	private var sourceVertex: mxCell? = null
	private var indexStep = -1

	init {
		this.model = model
		this.view = view
		this.adapter = adapter
	}

	//Обработка нажатия кнопки мыши, если не выбрана не одна вершина
	private fun handlerNormal(source: mxCell) {
		curStatus = Status.SELECT_ONE_VERTEX
		sourceVertex = source
		model.setStyleSelected(true, arrayOf(sourceVertex!!))
		view.setEnabledResetButton(true)
	}

	//Обработка нажатия кнопки мыши, если выбрана одна вершина
	private fun handlerSelectOneVer(target: mxCell) {
		curStatus = Status.PROCESSING
		model.setStyleSelected(true, arrayOf(target))
		view.setLog("----------------------------------------------------------------------------------")
		view.setLog("Поиск кратчайшего пути из вершины " + sourceVertex!!.value + " в вершину " + target.value + "!!!")
		view.setLog("----------------------------------------------------------------------------------")
		adapter.shortestWay(
			"Алгоритм Дейкстры", model.graph, sourceVertex, target
		) { mementosView ->
			stepsView = mementosView
			curStatus = Status.DISPLAY
			view.setEnabledStartButton(true)
			view.setLabelHelp(status)
		}
	}

	//Обработка нажатия кнопки мыши, если путь уже показан
	private fun handlerDisplay(cell: mxCell) {
		close()
		view.setEnabledResetButton(true)
		model.setStyleSelected(true, arrayOf(cell))
		curStatus = Status.SELECT_ONE_VERTEX
		sourceVertex = cell
		indexStep = -1
	}

	//Показать шаг алгоритма
	private fun showMemento(viewMemento: ShortestWayView) {
		model.setNormalStyle()
		val currentVertex = viewMemento.getCurrentVertex()
		val processedVertices = viewMemento.getProcessedVertices()!!
		val currentWays = viewMemento.getCurrentWays()
		val inQueueVertices = viewMemento.getInQueueVertices()!!
		val answer = viewMemento.getAnswer()!!
		if (currentWays != null) model.setStyleSelected(true, currentWays)
		if (inQueueVertices != null) model.setStyle(Constants.QUEUE_VERTEX_STYLE, inQueueVertices)
		if (currentVertex != null) model.setStyle(Constants.CURRENT_VERTEX_STYLE, arrayOf(currentVertex))
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
		indexStep = stepsView!!.size - 1
		showMemento(stepsView!![indexStep]!!)
	}

	override fun resetAlgorithm() {
		close()
		curStatus =Status.NORMAL
		sourceVertex = null
		indexStep = -1
	}

	override fun mousePressed(posX: Double, posY: Double, cell: Any?) {
		if (cell != null && (cell as mxCell).isVertex) {
			val clickCell = cell
			if (curStatus == Status.NORMAL) handlerNormal(clickCell) else if (curStatus == Status.SELECT_ONE_VERTEX) handlerSelectOneVer(
				clickCell
			) else if (curStatus == Status.DISPLAY) handlerDisplay(
				clickCell
			)
		}
	}

	override fun mouseReleased(posX: Double, posY: Double, cell: Any?) {}
	override val status: String?
		get() {
			when (curStatus) {
				Status.NORMAL -> return "Выделите вершину, из которой хотите найти кратчайших путь"
				Status.SELECT_ONE_VERTEX -> return "Выделите конечную вершину кратчайшего пути"
				Status.PROCESSING -> return "Обработка..."
				Status.DISPLAY -> return if (indexStep == -1) {
					"Нажмите старт,чтобы начать пошаговый просмотр алгоритма"
				} else if (indexStep == stepsView!!.size - 1) {
					stepsView!![stepsView!!.size - 1]!!.getLog()!!.split("\\n").get(0)
				} else {
					"Пошаговый режим включен"
				}
			}
			return null
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

import controller.CreationAreaController
import model.adapter.Adapter
import model.states.CreationAreaModel
import model.states.particularStates.AddEdgeState
import model.states.particularStates.AddVertexState
import model.states.particularStates.AlgorithmShortestPathState
import model.states.particularStates.DeleteState
import model.states.particularStates.IState
import model.states.particularStates.MoveState
import kotlin.test.Test
import ui.AppView
import ui.AppViewRealization
import kotlin.test.AfterTest
import kotlin.test.BeforeTest
import kotlin.test.assertEquals


class CreationAreaControllerTest {
	private var currentState: IState? = null
	private var model: CreationAreaModel? = null
	private var view: AppView? = null
	private var adapter: Adapter? = null
	private var controller: CreationAreaController? = null

	@BeforeTest
	fun initTest() {
		model = CreationAreaModel()
		adapter = Adapter()
		controller = CreationAreaController(model!!, adapter!!)
		view = AppViewRealization(controller!!, model!!)
	}

	@AfterTest
	fun afterTest() {
		currentState = null
		model = null
		controller = null
		adapter = null
		view = null
	}

	@Test
	fun setStateOfMotion() {
		currentState = MoveState(model!!)
		assertEquals("Выделите и перемещайте объекты", currentState?.status)
	}

	@Test
	fun setStateOfAddingVertices() {
		currentState = model?.let { AddVertexState(it, view!!) }
		assertEquals("Кликните на рабочую область, чтобы добавить вершину", currentState?.status)
	}

	@Test
	fun setStateOfConnectionVertices() {
		currentState = AddEdgeState(model!!, view!!)
		assertEquals("Выделите первую вершину для создания дуги", currentState?.status)
	}

	@Test
	fun setStateOfDelete() {
		currentState = DeleteState(model!!)
		assertEquals("Кликните по объекту, который хотите удалить", currentState?.status)
	}

	@Test
	fun setStateOfAlgorithm() {
		currentState = AlgorithmShortestPathState(model!!, view!!, adapter!!)
		assertEquals("Выделите вершину, из которой хотите найти кратчайших путь", currentState?.status)
	}
}

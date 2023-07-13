import controller.CreationAreaController
import model.adapter.Adapter
import model.states.CreationAreaModel
import org.junit.Test
import ui.AppViewRealization
import kotlin.test.AfterTest
import kotlin.test.BeforeTest
import kotlin.test.assertEquals

class CreationAreaViewTest {
	private var view: AppViewRealization? = null

	@BeforeTest
	fun initTest() {
		val model = CreationAreaModel()
		val controller = CreationAreaController(model, Adapter())
		view = AppViewRealization(controller, model)
	}

	@AfterTest
	fun afterTest() {
		view = null
	}

	@Test
	fun TestClickMoveButton() {
		view!!.getTopBar()?.getMoveButton()?.doClick()
		assertEquals("Выделите и перемещайте объекты", view!!.getTopBar()?.getLabelHelp()?.text)
		try {
			Thread.sleep(1000)
		} catch (e: InterruptedException) {
			e.printStackTrace()
		}
	}

	@Test
	fun TestClickDeleteButton() {
		view?.getTopBar()?.getDeleteButton()?.doClick()
		assertEquals("Кликните по объекту, который хотите удалить", view!!.getTopBar()?.getLabelHelp()?.text)
		try {
			Thread.sleep(1000)
		} catch (e: InterruptedException) {
			e.printStackTrace()
		}
	}

	@Test
	fun TestClickAddVertexButton() {
		view!!.getTopBar()?.getAddVertexButton()?.doClick()
		assertEquals("Кликните на рабочую область, чтобы добавить вершину", view!!.getTopBar()?.getLabelHelp()?.text)
		try {
			Thread.sleep(1000)
		} catch (e: InterruptedException) {
			e.printStackTrace()
		}
	}

	@Test
	fun TestClickConnectionVertexButton() {
		view!!.getTopBar()?.getConnectVertexButton()?.doClick()
		assertEquals("Выделите первую вершину для создания дуги", view!!.getTopBar()?.getLabelHelp()?.text)
		try {
			Thread.sleep(1000)
		} catch (e: InterruptedException) {
			e.printStackTrace()
		}
	}
}

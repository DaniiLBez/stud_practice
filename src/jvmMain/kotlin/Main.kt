import controller.CreationAreaController
import model.adapter.Adapter
import model.states.CreationAreaModel
import ui.AppViewRealization

fun main() {
	val creationAreaModel = CreationAreaModel()
	val creationAreaController = CreationAreaController(creationAreaModel, Adapter())
	AppViewRealization(creationAreaController, creationAreaModel)
}

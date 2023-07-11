import ui.AppViewRealization
import controller.CreationAreaController
import model.adapter.Adapter
import model.states.CreationAreaModel

fun main() {
	val creationAreaModel = CreationAreaModel()
	val creationAreaController = CreationAreaController(creationAreaModel, Adapter())
	AppViewRealization(creationAreaController, creationAreaModel)
}

import UI.GraphCreatorViewImpl
import controller.GraphCreatorController
import model.adapter.Adapter
import model.states.GraphCreatorModel

fun main() {
	val graphCreatorModel = GraphCreatorModel()
	val graphCreatorController = GraphCreatorController(graphCreatorModel, Adapter())
	GraphCreatorViewImpl(graphCreatorController, graphCreatorModel)
}

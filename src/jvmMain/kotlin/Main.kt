import View.GraphCreatorViewImpl
import model.adapter.MxGraphAdapter
import model.states.GraphCreatorModel
import model.states.GraphCreatorModelImpl

fun main() {
	val graphCreatorModel = GraphCreatorModelImpl()
	val graphCreatorController = GraphCreatorControllerImpl(graphCreatorModel, MxGraphAdapter())
	GraphCreatorViewImpl(graphCreatorController, graphCreatorModel)
}

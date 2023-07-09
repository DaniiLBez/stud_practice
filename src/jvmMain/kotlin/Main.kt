import View.GraphCreatorViewImpl
import controller.GraphCreatorImpl
import model.adapter.MxGraphAdapter
import model.states.GraphCreatorModel
import model.states.GraphCreatorModelImpl

fun main() {
	val graphCreatorModel = GraphCreatorModelImpl()
	val graphCreatorController = GraphCreatorImpl(graphCreatorModel, MxGraphAdapter())
	GraphCreatorViewImpl(graphCreatorController, graphCreatorModel)
}

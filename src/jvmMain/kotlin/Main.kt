import View.GraphCreatorViewImpl
import controller.GraphCreatorImpl
import model.adapter.Adapter
import model.states.GraphCreatorModelImpl

fun main() {
	val graphCreatorModel = GraphCreatorModelImpl()
	val graphCreatorController = GraphCreatorImpl(graphCreatorModel, Adapter())
	GraphCreatorViewImpl(graphCreatorController, graphCreatorModel)
}

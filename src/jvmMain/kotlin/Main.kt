fun main() {
	GraphCreatorModel graphCreatorModel = GraphCreatorModelImpl()
	GraphCreatorControllerImpl graphCreatorController = GraphCreatorControllerImpl(graphCreatorModel, MxGraphAdapter())
	GraphCreatorViewImpl(graphCreatorController, graphCreatorModel)
}

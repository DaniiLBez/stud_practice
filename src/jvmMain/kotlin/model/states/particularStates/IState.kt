package model.states.particularStates

interface IState {
	fun nextStep()
	fun backStep()
	fun startAlgorithm()
	fun finishAlgorithm()
	fun resetAlgorithm()
	fun mousePressed(posX: Double, posY: Double, cell: Any?)
	fun mouseReleased(posX: Double, posY: Double, cell: Any?)
	val status: String?

	fun close()
}

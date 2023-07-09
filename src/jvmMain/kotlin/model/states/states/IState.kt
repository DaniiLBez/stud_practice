package model.states.states
/**
 * Интерфейс состояний
 * void nextStep() - следующий шаг алгоритма
 * void backStep() - предыдущий шаг алгоритма
 * void mousePressed(double posX, double posY, Object cell) - реакция на нажатие кнопки мыши
 * void mouseReleased(double posX, double posY, Object cell) - реакция на отпускание кнопки мыши
 * String getStatus() - Возвращает текущие состояние данного стейта
 * void close() - устанавливает исходное состояние
 */
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

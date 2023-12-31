package ui

import Constants
import controller.CreationAreaController
import java.awt.BorderLayout
import java.awt.Color
import java.awt.event.ActionEvent
import java.awt.event.ActionListener
import java.util.function.Consumer
import javax.swing.ImageIcon
import javax.swing.JButton
import javax.swing.JPanel

class BottomAppBar(controller: CreationAreaController) : JPanel() {
	private val controller: CreationAreaController
	private var backStepButton: JButton? = null
	private var nextStepButton: JButton? = null
	private var startButton: JButton? = null
	private var finishButton: JButton? = null
	private var resetButton: JButton? = null
	private var ranAlgorithm: JButton? = null

	init {
		this.controller = controller
		initGUI()
	}

	private fun initGUI() {
		layout = BorderLayout()
		background = Color(190, 160, 255)
		initButtonControlAlgorithms()
		initStartAlgorithm()
	}

	private fun initButtonControlAlgorithms() {
		val panel = JPanel()
		panel.background = Color(190, 160, 255)
		val buttonList: MutableList<JButton> = ArrayList()
		backStepButton = createButton(
			Constants.BACK,
			{ event: ActionEvent? -> controller.backStep() }, buttonList, false
		)
		startButton = createButton(
			Constants.START,
			{ event: ActionEvent? -> controller.startAlgorithm() }, buttonList, false
		)
		finishButton = createButton(
			Constants.FINISH,
			{ event: ActionEvent? -> controller.finishAlgorithm() }, buttonList, false
		)
		nextStepButton = createButton(
			Constants.NEXT,
			{ event: ActionEvent? -> controller.nextStep() }, buttonList, false
		)
		resetButton = createButton(
			Constants.RESET,
			{ event: ActionEvent? -> controller.resetAlgorithm() }, buttonList, false
		)

		buttonList.forEach(
			Consumer { button: JButton? ->
				panel.add(
					button
				)
			}
		)
		add(panel, BorderLayout.NORTH)
	}

	private fun initStartAlgorithm() {
		val panel = JPanel()
		panel.background = Color(190, 160, 255)
		val buttonList: MutableList<JButton> = ArrayList()

		ranAlgorithm = createRanButton(
			{ e: ActionEvent? -> controller.setStateOfAlgorithm() },
			buttonList, true
		)

		buttonList.forEach(
			Consumer { button: JButton? ->
				panel.add(
					button
				)
			}
		)

		add(panel, BorderLayout.CENTER)
	}

	private fun createButton(
		name: String,
		listener: ActionListener,
		container: MutableList<JButton>,
		enabled: Boolean
	): JButton {
		val imageOfIcon = ImageIcon(name)
		val button = JButton()
		button.icon = imageOfIcon
		button.addActionListener(listener)
		button.background = Color.WHITE
		button.isEnabled = enabled
		container.add(button)
		return button
	}

	private fun createRanButton(
		listener: ActionListener,
		container: MutableList<JButton>,
		enabled: Boolean
	): JButton {
		val imageOfIcon = ImageIcon("images/ran.png")
		val button = JButton("Запустить алгоритм")
		button.icon = imageOfIcon
		button.addActionListener(listener)
		button.background = Color.WHITE
		button.isEnabled = enabled
		container.add(button)
		return button
	}

	fun setEnabledNextButton(show: Boolean) {
		nextStepButton!!.isEnabled = show
	}

	fun setEnabledBackButton(show: Boolean) {
		backStepButton!!.isEnabled = show
	}

	fun setEnabledStartButton(show: Boolean) {
		startButton!!.isEnabled = show
	}

	fun setEnabledFinishButton(show: Boolean) {
		finishButton!!.isEnabled = show
	}

	fun setEnabledResetButton(show: Boolean) {
		resetButton!!.isEnabled = show
	}
}

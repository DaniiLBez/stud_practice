package ui

import Constants
import controller.CreationAreaController
import java.awt.BorderLayout
import java.awt.Color
import java.awt.Font
import java.awt.GridBagLayout
import java.awt.event.ActionEvent
import java.awt.event.ActionListener
import java.util.function.Consumer
import javax.swing.BorderFactory
import javax.swing.JButton
import javax.swing.JLabel
import javax.swing.JPanel

class TopAppBar(controller: CreationAreaController) : JPanel() {
	private val controller: CreationAreaController
	private var labelHelp: JLabel? = null
	private var moveButton: JButton? = null
	fun getMoveButton() = moveButton

	private var addVertexButton: JButton? = null
	private var connectVertexButton: JButton? = null
	private var deleteButton: JButton? = null

	init {
		this.controller = controller
		initGUI()
	}

	private fun initGUI() {
		layout = BorderLayout()
		background = Color(190, 160, 255)
		initButtons()
		initLabelHelp()
	}

	private fun initButtons() {
		val panelButton = JPanel()
		panelButton.background = Color(190, 160, 255)
		val buttonList: MutableList<JButton> = ArrayList()

		createButton(
			Constants.SAVE,
			{ event: ActionEvent? -> controller.saveGraph() }, buttonList, true
		)
		createButton(
			Constants.LOAD,
			{ event: ActionEvent? -> controller.loadGraph() }, buttonList, true
		)
		moveButton = createButton(
			Constants.MOVE,
			{ event: ActionEvent? -> controller.setStateOfMotion() }, buttonList, true
		)
		addVertexButton = createButton(
			Constants.ADD_VERTEX,
			{ e: ActionEvent? -> controller.setStateAddingVertices() }, buttonList, true
		)
		connectVertexButton = createButton(
			Constants.CONNECT_VERTEX,
			{ event: ActionEvent? -> controller.setStateOfAddEdge() }, buttonList, true
		)
		deleteButton = createButton(
			Constants.DELETE,
			{ event: ActionEvent? -> controller.setStateOfDelete() }, buttonList, true
		)

		buttonList.forEach(
			Consumer { button: JButton? ->
				panelButton.add(
					button
				)
			}
		)

		add(panelButton, BorderLayout.NORTH)
	}

	private fun initLabelHelp() {
		val panelLabel = JPanel()
		panelLabel.layout = GridBagLayout()
		panelLabel.background = Color(223, 208, 255)
		labelHelp = JLabel()
		labelHelp!!.horizontalAlignment = JLabel.CENTER
		labelHelp!!.verticalAlignment = JLabel.CENTER
		labelHelp!!.foreground = Color.BLACK
		labelHelp!!.font = Font("Roboto", Font.BOLD, 13)
		panelLabel.add(labelHelp)
		add(panelLabel, BorderLayout.CENTER)
	}

	private fun createButton(
		name: String,
		listener: ActionListener,
		container: MutableList<JButton>,
		enabled: Boolean
	): JButton {
		val button = JButton(name)
		button.border = BorderFactory.createLineBorder(Color(78, 0, 102), 2, true)
		button.background = Color.WHITE
		button.addActionListener(listener)
		button.isEnabled = enabled
		container.add(button)
		return button
	}

	fun setLabelHelp(text: String?) {
		labelHelp!!.text = text
	}

	fun getLabelHelp() = labelHelp
	fun getDeleteButton() = deleteButton
	fun getAddVertexButton() = addVertexButton
	fun getConnectVertexButton() = connectVertexButton
}

package com.company.view

import Constants
import controller.GraphCreatorController
import java.awt.BorderLayout
import java.awt.Color
import java.awt.GridBagLayout
import java.awt.event.ActionEvent
import java.awt.event.ActionListener
import java.util.function.Consumer
import javax.swing.JButton
import javax.swing.JComboBox
import javax.swing.JLabel
import javax.swing.JPanel

internal class ToolBar(controller: GraphCreatorController) : JPanel() {
	private val controller: GraphCreatorController
	private var comboBoxAlgorithms: JComboBox<*>? = null
	var labelHelp: JLabel? = null
		private set
	private var backStepButton: JButton? = null
	private var nextStepButton: JButton? = null
	private var startButton: JButton? = null
	private var finishButton: JButton? = null
	private var resetButton: JButton? = null
	var moveButton: JButton? = null
		private set
	var addVertexButton: JButton? = null
		private set
	var connectVertexButton: JButton? = null
		private set
	var deleteButton: JButton? = null
		private set

	init {
		this.controller = controller
		initGUI()
	}

	private fun initGUI() {
		layout = BorderLayout()
		initButtons()
		initLabelHelp()
		initButtonControlAlgorithms()
	}

	private fun initButtons() {
		val panelButton = JPanel()
		val buttonList: MutableList<JButton> = ArrayList()
		createButton(Constants.SAVE,
			{ e: ActionEvent? -> controller.saveGraph() }, buttonList, true
		)
		createButton(Constants.LOAD,
			{ e: ActionEvent? -> controller.loadGraph() }, buttonList, true
		)
		moveButton = createButton(Constants.MOVE,
			{ e: ActionEvent? -> controller.setStateOfMotion() }, buttonList, true
		)
		addVertexButton = createButton(Constants.ADD_VERTEX,
			{ e: ActionEvent? -> controller.setStateOfAddingVertices() }, buttonList, true
		)
		connectVertexButton = createButton(Constants.CONNECT_VERTEX,
			{ e: ActionEvent? -> controller.setStateOfConnectionVertices() }, buttonList, true
		)
		deleteButton = createButton(Constants.DELETE,
			{ e: ActionEvent? -> controller.setStateOfDelete() }, buttonList, true
		)
		comboBoxAlgorithms = JComboBox<Any?>(ITEMS)
		(comboBoxAlgorithms as JComboBox<Any?>).addActionListener(ActionListener { e: ActionEvent? -> controller.setStateOfAlgorithm() })
		buttonList.forEach(Consumer { button: JButton? ->
			panelButton.add(
				button
			)
		})
		panelButton.add(comboBoxAlgorithms)
		add(panelButton, BorderLayout.NORTH)
	}

	private fun initLabelHelp() {
		val panelLabel = JPanel()
		panelLabel.layout = GridBagLayout()
		panelLabel.background = Color(222, 239, 216)
		labelHelp = JLabel()
		labelHelp!!.horizontalAlignment = JLabel.CENTER
		labelHelp!!.verticalAlignment = JLabel.CENTER
		labelHelp!!.foreground = Color(106, 119, 61)
		panelLabel.add(labelHelp)
		add(panelLabel, BorderLayout.CENTER)
	}

	private fun initButtonControlAlgorithms() {
		val panel = JPanel()
		val buttonList: MutableList<JButton> = ArrayList()
		backStepButton = createButton(Constants.BACK,
			{ e: ActionEvent? -> controller.backStep() }, buttonList, false
		)
		startButton = createButton(Constants.START,
			{ e: ActionEvent? -> controller.startAlgorithm() }, buttonList, false
		)
		resetButton = createButton(Constants.RESET,
			{ e: ActionEvent? -> controller.resetAlgorithm() }, buttonList, false
		)
		finishButton = createButton(Constants.FINISH,
			{ e: ActionEvent? -> controller.finishAlgorithm() }, buttonList, false
		)
		nextStepButton = createButton(Constants.NEXT,
			{ e: ActionEvent? -> controller.nextStep() }, buttonList, false
		)
		buttonList.forEach(Consumer { button: JButton? ->
			panel.add(
				button
			)
		})
		add(panel, BorderLayout.SOUTH)
	}

	private fun createButton(
		name: String,
		listener: ActionListener,
		container: MutableList<JButton>,
		enabled: Boolean
	): JButton {
		val button = JButton(name)
		button.addActionListener(listener)
		button.isEnabled = enabled
		container.add(button)
		return button
	}

	fun setLabelHelp(text: String?) {
		labelHelp!!.text = text
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

	val selectAlgorithm: String?
		get() = if (comboBoxAlgorithms!!.selectedItem != null) comboBoxAlgorithms!!.selectedItem.toString() else null

	companion object {
		private val ITEMS = arrayOf<String?>(Constants.DIJKSTRA)
	}
}

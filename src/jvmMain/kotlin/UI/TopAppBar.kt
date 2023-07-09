package UI

import Constants
import controller.GraphCreatorController
import java.awt.BorderLayout
import java.awt.Color
import java.awt.Font
import java.awt.GridBagLayout
import java.awt.event.ActionEvent
import java.awt.event.ActionListener
import java.util.function.Consumer
import javax.swing.BorderFactory
import javax.swing.JButton
import javax.swing.JComboBox
import javax.swing.JLabel
import javax.swing.JPanel

class TopAppBar(controller: GraphCreatorController) : JPanel() {
	private val controller: GraphCreatorController
	private var boxSaveLoad: JComboBox<*>? = null
	var labelHelp: JLabel? = null
		private set
	var moveButton: JButton? = null
		private set
	var addVertexButton: JButton? = null
		private set
	var connectVertexButton: JButton? = null
		private set
	var deleteButton: JButton? = null
		private set

	var dijkstraButton: JButton? = null

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
			{ e: ActionEvent? -> controller.setStateAddingVertices() }, buttonList, true
		)
		connectVertexButton = createButton(Constants.CONNECT_VERTEX,
			{ e: ActionEvent? -> controller.setStateOfConnectionVerticies() }, buttonList, true
		)
		deleteButton = createButton(Constants.DELETE,
			{ e: ActionEvent? -> controller.setStateOfDelete() }, buttonList, true
		)

		buttonList.forEach(Consumer { button: JButton? ->
			panelButton.add(
				button
			)
		})

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

	companion object {
		private val SaveLoad = arrayOf<String?>("Меню","Загрузить граф", "Сохранить Граф")
		private val AddVertexEdges = arrayOf<String?>("Добавить","Добавить вершину", "Добавить ребро")
	}
}

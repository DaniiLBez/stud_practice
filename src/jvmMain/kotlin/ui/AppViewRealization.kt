package ui

import Constants
import controller.CreationAreaController
import model.states.CreationAreaModel
import java.awt.*
import java.io.File
import javax.swing.*
import javax.swing.border.EmptyBorder

class AppViewRealization(controller: CreationAreaController, model: CreationAreaModel) :
	AppView {
	private val controller: CreationAreaController
	private val model: CreationAreaModel
	private var topBar: TopAppBar? = null
	private var bottomBar: BottomAppBar? = null
	private var textArea: JTextArea? = null
	private val frame = JFrame("Поиск кратчайшего пути в графе. Алгоритм Дейсктры.")

	init {
		this.controller = controller
		this.model = model
		initGUI()
		controller.setView(this)
	}

	private fun initGUI() {
		frame.defaultCloseOperation = WindowConstants.EXIT_ON_CLOSE
		val sSize = Toolkit.getDefaultToolkit().screenSize
		frame.setSize(sSize.width, sSize.height)
		frame.setLocationRelativeTo(null)

		val panel = JPanel()
		panel.layout = BorderLayout(Constants.INTEND, Constants.INTEND)
		panel.border = EmptyBorder(Constants.INTEND, Constants.INTEND, Constants.INTEND, Constants.INTEND)
		panel.background = Color(250, 234, 255)

		topBar = TopAppBar(controller)
		topBar!!.preferredSize = Dimension((sSize.width * 0.95).toInt(), (sSize.height * 0.1).toInt())

		bottomBar = BottomAppBar(controller)
		bottomBar!!.preferredSize = Dimension((sSize.width * 0.95).toInt(), (sSize.height * 0.1).toInt())

		val creator = CreationArea(controller, model)
		creator.preferredSize = Dimension((sSize.width * 0.7).toInt(), (sSize.height * 0.75).toInt())
		creator.border = BorderFactory.createLineBorder(Color(190, 160, 255), 3)
		creator.background = Color.WHITE

		textArea = JTextArea(100, 50)
		textArea!!.isEnabled = false
		textArea!!.disabledTextColor = Color.BLACK
		textArea!!.font = Font("Roboto", Font.TRUETYPE_FONT, 12)

		val scrollPane = JScrollPane(textArea)
		scrollPane.preferredSize = Dimension((sSize.width * 0.25).toInt(), (sSize.height * 0.75).toInt())
		scrollPane.border = BorderFactory.createLineBorder(Color(190, 160, 255), 3)

		panel.add(scrollPane, BorderLayout.CENTER)
		topBar?.let { panel.add(it, BorderLayout.NORTH) }
		panel.add(creator, BorderLayout.WEST)
		bottomBar?.let { panel.add(it, BorderLayout.SOUTH) }
		frame.contentPane.add(panel)
		frame.pack()
		frame.isVisible = true
	}

	override fun setEnabledStartButton(show: Boolean) {
		bottomBar!!.setEnabledStartButton(show)
	}

	override fun setEnabledFinishButton(show: Boolean) {
		bottomBar!!.setEnabledFinishButton(show)
	}

	override fun setEnabledNextButton(show: Boolean) {
		bottomBar!!.setEnabledNextButton(show)
	}

	override fun setEnabledBackButton(show: Boolean) {
		bottomBar!!.setEnabledBackButton(show)
	}

	override fun setEnabledResetButton(show: Boolean) {
		bottomBar!!.setEnabledResetButton(show)
	}

	override fun setLabelHelp(strHelp: String?) {
		topBar!!.setLabelHelp(strHelp)
	}

	override fun setLog(message: String?) {
		textArea!!.append(message + "\n")
	}

	override fun showErrorDialog(title: String?, message: String?) {
		JOptionPane.showMessageDialog(frame, message, title, JOptionPane.ERROR_MESSAGE)
	}

	override fun showInputDialog(title: String?, message: String?): String? {
		return JOptionPane.showInputDialog(frame, message, title, JOptionPane.INFORMATION_MESSAGE)
	}

	override fun showFileChooserDialog(title: String?): File? {
		val fileOpen = JFileChooser()
		val ret = fileOpen.showDialog(null, title)
		return if (ret == JFileChooser.APPROVE_OPTION) fileOpen.selectedFile else null
	}
}

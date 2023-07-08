package View

import com.company.View.ToolBar
import controller.GraphCreatorController
import model.GraphCreatorModel
import java.awt.BorderLayout
import java.awt.Color
import java.awt.Dimension
import java.awt.Toolkit
import java.io.File
import javax.swing.*
import javax.swing.border.EmptyBorder

class GraphCreatorViewImpl(controller: GraphCreatorController, model: GraphCreatorModel):
	JFrame("Поиск кратчайшего пути в графе. Алгоритм Дейкстры."), GraphCreatorView
{
	private val controller: GraphCreatorController
	private val model: GraphCreatorModel
	private var toolBar: ToolBar? = null
	private var textArea: JTextArea? = null

	init {
		this.controller = controller
		this.model = model
		initGUI()
		controller.setView(this)
	}

	private fun initGUI() {
		val sSize = Toolkit.getDefaultToolkit().screenSize
		setSize(sSize.width, sSize.height)
		isResizable = false
		setLocationRelativeTo(null)
		val panel = JPanel()
		panel.layout = BorderLayout(INTEND, INTEND)
		panel.border = EmptyBorder(INTEND, INTEND, INTEND, INTEND)
		toolBar = ToolBar(controller)
		toolBar.setPreferredSize(Dimension((sSize.width * 0.95).toInt(), (sSize.height * 0.12).toInt()))
		val creator = Creator(controller, model)
		creator.setPreferredSize(Dimension((sSize.width * 0.7).toInt(), (sSize.height * 0.75).toInt()))
		textArea = JTextArea(100, 50)
		textArea!!.isEnabled = false
		textArea!!.disabledTextColor = Color.BLACK
		val scrollPane = JScrollPane(textArea)
		scrollPane.preferredSize = Dimension((sSize.width * 0.25).toInt(), (sSize.height * 0.75).toInt())
		panel.add(scrollPane, BorderLayout.WEST)
		panel.add(toolBar, BorderLayout.NORTH)
		panel.add(creator, BorderLayout.CENTER)
		contentPane.add(panel)
		pack()
		isVisible = true
	}

	override fun setEnabledStartButton(show: Boolean) {
		toolBar.setEnabledStartButton(show)
	}

	override fun setEnabledFinishButton(show: Boolean) {
		toolBar.setEnabledFinishButton(show)
	}

	override fun setEnabledNextButton(show: Boolean) {
		toolBar.setEnabledNextButton(show)
	}

	override fun setEnabledBackButton(show: Boolean) {
		toolBar.setEnabledBackButton(show)
	}

	override fun setEnabledResetButton(show: Boolean) {
		toolBar.setEnabledResetButton(show)
	}

	override fun setLabelHelp(strHelp: String?) {
		toolBar.setLabelHelp(strHelp)
	}

	override fun setLog(message: String?) {
		textArea!!.append(message + "\n")
	}

	override fun showErrorDialog(title: String?, message: String?) {
		JOptionPane.showMessageDialog(this, message, title, JOptionPane.ERROR_MESSAGE)
	}

	override fun showInputDialog(title: String?, message: String?): String? {
		return JOptionPane.showInputDialog(this, message, title, JOptionPane.INFORMATION_MESSAGE)
	}

	override fun showFileChooserDialog(title: String?): File? {
		val fileOpen = JFileChooser()
		val ret = fileOpen.showDialog(null, title)
		return if (ret == JFileChooser.APPROVE_OPTION) fileOpen.selectedFile else null
	}

	override val selectAlgorithm: String
		get() = toolBar.getSelectAlgorithm()

	fun getToolBar(): ToolBar? {
		return toolBar
	}
}

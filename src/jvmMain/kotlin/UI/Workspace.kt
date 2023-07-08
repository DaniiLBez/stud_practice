package UI

import java.awt.*
import javax.swing.BorderFactory
import javax.swing.JButton
import javax.swing.JFrame
import javax.swing.JPanel
import javax.swing.JScrollPane
import javax.swing.JTextArea
import javax.swing.border.EmptyBorder

fun workSpace() {
	val size = Toolkit.getDefaultToolkit().screenSize

	val frame = JFrame("Поиск кратчайшего пути в графе. Алгоритм Дейкстры.")
	frame.setSize(size.width, size.height)
	frame.defaultCloseOperation = JFrame.EXIT_ON_CLOSE
	frame.isResizable = false
	frame.setLocationRelativeTo(null)
	frame.isVisible = true

	val workspace = JPanel()
	workspace.layout = BorderLayout(5, 5)
	workspace.border = EmptyBorder(5, 5, 5, 5)
	workspace.background = Color.WHITE

	val topAppBar = JPanel()
	topAppBar.preferredSize = Dimension((size.width * 0.95).toInt(), (size.height * 0.12).toInt())

	val add = JButton("Добавить")
	val delete = JButton("Удалить")
	val drop = JButton("Сбросить")

	topAppBar.add(add)
	topAppBar.add(delete)
	topAppBar.add(drop)

	val graphspace = JPanel()
	graphspace.preferredSize = Dimension((size.width * 0.7).toInt(), (size.height * 0.7).toInt())
	graphspace.border = BorderFactory.createLineBorder(Color.GREEN)
	graphspace.background = Color.WHITE

	val loggerText = JTextArea(100, 50)
	loggerText.isEnabled = false

	val loggerSpace = JScrollPane(loggerText)
	loggerSpace.size = Dimension((size.width * 0.25).toInt(), (size.height * 0.7).toInt())

	val bottomAppBar = JPanel()
	bottomAppBar.preferredSize = Dimension((size.width * 0.95).toInt(), (size.height * 0.1).toInt())

	workspace.add(loggerSpace, BorderLayout.CENTER)
	workspace.add(topAppBar, BorderLayout.NORTH)
	workspace.add(graphspace, BorderLayout.WEST)
	workspace.add(bottomAppBar, BorderLayout.SOUTH)

	frame.contentPane.add(workspace)
	frame.pack()

}

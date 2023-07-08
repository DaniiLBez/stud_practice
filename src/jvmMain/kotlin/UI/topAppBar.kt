package UI

import java.awt.BorderLayout
import java.awt.Color
import java.awt.GridLayout
import javax.swing.JButton
import javax.swing.JPanel

fun topAppBar(workspace: JPanel) {
	val topAppBar = JPanel()
	topAppBar.layout = GridLayout(1, 0)
	topAppBar.background = Color.PINK

	val add = JButton("Добавить")
	val delete = JButton("Удалить")
	val drop = JButton("Сбросить")

	topAppBar.add(add)
	topAppBar.add(delete)
	topAppBar.add(drop)

	workspace.add(topAppBar, BorderLayout.CENTER)
}

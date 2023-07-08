package UI

import java.awt.BorderLayout
import java.awt.Color
import java.awt.Dimension
import java.awt.SplashScreen
import java.awt.Toolkit
import javax.swing.BorderFactory
import javax.swing.JFrame
import javax.swing.JPanel
import javax.swing.border.EmptyBorder

fun graphSpace(workspace: JPanel){
	val graphspace = JPanel()
	graphspace.preferredSize = Dimension(640, 480)
	graphspace.border = EmptyBorder(100, 500, 60, 20)
	graphspace.border = BorderFactory.createLineBorder(Color.GREEN)
	graphspace.background = Color.WHITE
	graphspace.isVisible = true

	workspace.add(graphspace)
}

package UI

import androidx.compose.ui.awt.ComposeWindow
import controller.FileReader
import controller.GraphCreator
import java.awt.FileDialog

fun SelectTxtFile() {
	val fd = FileDialog(ComposeWindow(), "Choose file to import", FileDialog.LOAD)
	fd.file = "tree.txt"
	fd.isVisible = true
	val fileString = fd.directory + fd.file
	val fileReader = FileReader(fileString)
	fileReader.readData()
	val graphCreator = GraphCreator(fileReader)
	graphCreator.create()
	graph = graphCreator.getGraph()
}

package View

import java.io.File

interface GraphCreatorView {
	fun setEnabledStartButton(show: Boolean)
	fun setEnabledFinishButton(show: Boolean)
	fun setEnabledNextButton(show: Boolean)
	fun setEnabledBackButton(show: Boolean)
	fun setEnabledResetButton(show: Boolean)
	fun setLabelHelp(strHelp: String?)
	fun setLog(message: String?)
	fun showErrorDialog(title: String?, message: String?)
	fun showInputDialog(title: String?, message: String?): String?
	fun showFileChooserDialog(title: String?): File?
	val selectAlgorithm: String?
}

package UI

import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable

@Composable
@Preview
fun App() {
	MaterialTheme() {
		Scaffold(
			topBar = { MyTopAppBar() },
			bottomBar = { iconInformation() }
			// floatingActionButton = {  }
		) {
			leftRigthStartIcons()
			drawGraph()
			loggerInfo()
		}
	}
}

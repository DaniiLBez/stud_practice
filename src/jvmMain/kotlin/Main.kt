import androidx.compose.animation.Animatable
import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.*
import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.LocalContextMenuRepresentation
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material.icons.filled.Clear
import androidx.compose.runtime.*
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import androidx.compose.material.*
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.outlined.PlayArrow
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.awt.ComposeWindow
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.WindowPosition
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.selects.select
import java.awt.FileDialog
import java.io.File


@Composable
@Preview
fun App() {
	MaterialTheme {
		Scaffold(
			topBar = { MyTopAppBar() },
			bottomBar = { MyGroundAppBar()},
			floatingActionButton = {  Start() },
			floatingActionButtonPosition = FabPosition.Center,
		)
		{
			//ReadStartNode()
		}
	}

}

fun main() = application {
	Window(onCloseRequest = ::exitApplication) {
		App()
	}
}

@Composable
fun MyTopAppBar() {
	TopAppBar(
		title = { Text("Поиск кратчайшего пути в графе. Алгоритм Дейкстры.") },
		navigationIcon = {
			IconButton(onClick = { /* doSomething() */ }) {
				Icon(Icons.Filled.Menu, contentDescription = "Меню.")
			}
		},
		actions = {
			IconButton(onClick = { SelectTxtFile(Mode.IMPORT) }) {
				Icon(Icons.Filled.Add, contentDescription = "Загрузить граф.")
			}
			IconButton(onClick = {}) {
				Icon(Icons.Filled.Clear , contentDescription = "Очистить поле.")
			}
			IconButton(onClick = {}) {
				Icon(Icons.Filled.Delete , contentDescription = "Очистить поле.")
			}
			IconButton(onClick = {}) {
				Icon(Icons.Filled.KeyboardArrowRight , contentDescription = "Очистить поле.")
			}
		}
	)
}

@Composable
fun MyGroundAppBar() {
	BottomAppBar{
		IconButton(onClick = {  }) { Icon(Icons.Filled.Favorite, contentDescription = "Избранное")}
		Spacer(Modifier.weight(1f, true))
		IconButton(onClick = {  }) { Icon(Icons.Filled.Info, contentDescription = "Информация о приложении")}
	}
}

@Composable
fun Start() {
	val scope = rememberCoroutineScope()
	val angle = remember { Animatable(initialValue = 0f) }
	val checked = remember { mutableStateOf(false) }
	FloatingActionButton(
		content = {
			Icon(
				modifier = Modifier.rotate(angle.value),
				imageVector = Icons.Filled.PlayArrow,
				tint = if (checked.value) Color.Green else Color.Black,
				contentDescription = null
			)
		},
		onClick = {
			//ReadStartNode()
			scope.launch() {
				angle.snapTo(targetValue = 0f)
				angle.animateTo(targetValue = 360f, animationSpec = keyframes { durationMillis = 2000 })
				checked.value = false
			}
			checked.value = true
		}
	)
}


enum class Mode {
	IMPORT,
	EXPORT
}
//@Composable
fun SelectTxtFile(mode: Mode): File? {
	val fd = if (mode == Mode.IMPORT) {
		FileDialog(ComposeWindow(), "Choose file to import", FileDialog.LOAD)
	} else {
		FileDialog(ComposeWindow(), "Choose file to import", FileDialog.LOAD)
	}
	fd.file = "tree.txt"
	fd.isVisible = true
	val fileString = fd.directory + fd.file
	if (fileString != "nullnull") {
		return File(fileString)
	}
	return null
}

//@OptIn(ExperimentalMaterialApi::class)
//fun ReadStartNode() = application {
//	val openDialog = remember { mutableStateOf(true) }
//	var text by remember { mutableStateOf("") }
//	AlertDialog(
//		onDismissRequest = {
//			openDialog.value = false
//		},
//		title = {
//			Text(text = "Title")
//		},
//		text = {
//			Column() {
//				TextField(
//					value = text,
//					onValueChange = { text = it }
//				)
//				Text("Custom Text")
//				Checkbox(checked = false, onCheckedChange = {})
//			}
//		},
//		buttons = {
//			Row(
//				modifier = Modifier.padding(all = 8.dp),
//				horizontalArrangement = Arrangement.Center
//			) {
//				Button(
//					modifier = Modifier.fillMaxWidth(),
//					onClick = { openDialog.value = false }
//				) {
//					Text("Dismiss")
//				}
//			}
//		}
//	)
//}

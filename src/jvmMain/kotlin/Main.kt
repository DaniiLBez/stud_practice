import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.keyframes
import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.awt.ComposeWindow
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import kotlinx.coroutines.launch
import java.awt.FileDialog


@Composable
@Preview
fun App() {
	MaterialTheme {
		Scaffold(
			topBar = { MyTopAppBar() },
			floatingActionButton = { leftRigthStartIcons() }
		)
		{

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
	var showMenu by remember { mutableStateOf(false) }
	var showAdd by remember { mutableStateOf(false) }
	TopAppBar(
		title = { Text("Поиск кратчайшего пути в графе. Алгоритм Дейкстры.") },
		navigationIcon = {
			IconButton(onClick = { showMenu = !showMenu}) {
				Icon(Icons.Filled.Menu, contentDescription = "Меню")
			}
			DropdownMenu(expanded = showMenu, onDismissRequest = { showMenu = false }) {
				DropdownMenuItem(onClick = { SelectTxtFile() }) {
					Text("Загрузить граф")
				}
				Divider()
				DropdownMenuItem(onClick = {  }) {
					Text("Сохранить граф")
				}
				Divider()
			}
		},
		actions = {
			IconButton(onClick = {}) {
				Icon(Icons.Filled.Done , contentDescription = "Сброс")
			}
			IconButton(onClick = {showAdd = !showAdd}) {
				Icon(Icons.Filled.Add, contentDescription = "Добавить объект графа")
				DropdownMenu(expanded = showAdd, onDismissRequest = { showAdd = false}) {
					DropdownMenuItem(onClick = {  }) {
						Text("Добавить вершину")
					}
					Divider()
					DropdownMenuItem(onClick = {  }) {
						Text("Добавить ребро")
					}
					Divider()
				}
			}
			IconButton(onClick = {}) {
				Icon(Icons.Filled.Delete , contentDescription = "Удалить объект графа")
			}
			IconButton(onClick = {}) {
				Icon(Icons.Filled.Refresh , contentDescription = "Сброс")
			}
		}
	)
}

@Composable
fun leftRigthStartIcons() {
	val scope = rememberCoroutineScope()
	val angle = remember { Animatable(initialValue = 0f) }
	val checked = remember { mutableStateOf(false) }
	Row(
		modifier = Modifier
			.fillMaxWidth()
			.padding(bottom = 16.dp),
		horizontalArrangement = Arrangement.Center,
	) {
		IconButton(onClick = {}, modifier = Modifier.size(50.dp)) {
			Icon(Icons.Filled.KeyboardArrowLeft, contentDescription = "Шаг назад")
		}
		Spacer(modifier = Modifier.width(40.dp))
		IconButton(onClick = {
			scope.launch() {
				angle.snapTo(targetValue = 0f)
				angle.animateTo(targetValue = 360f, animationSpec = keyframes { durationMillis = 2000 })
				checked.value = false
			}
			checked.value = true
		},
			modifier = Modifier.size(48.dp)) {
			Icon(modifier = Modifier.rotate(angle.value),
				imageVector = Icons.Filled.PlayArrow,
				tint = if (checked.value) Color.Green else Color.Black,
				contentDescription = null)
		}
		Spacer(modifier = Modifier.width(40.dp))
		IconButton(onClick = {}, modifier = Modifier.size(48.dp)) {
			Icon(Icons.Filled.KeyboardArrowRight, contentDescription = "Шаг вперед")
		}
	}
}

//@Composable
fun SelectTxtFile() {
	val fd = FileDialog(ComposeWindow(), "Choose file to import", FileDialog.LOAD)
	fd.file = "tree.txt"
	fd.isVisible = true
	val fileString = fd.directory + fd.file
}


package UI

import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics


@Composable
fun MyTopAppBar() {
	var showMenu by remember { mutableStateOf(false) }
	var showAdd by remember { mutableStateOf(false) }
	TopAppBar(
		backgroundColor = Color(red = 58, green = 1, blue = 105),
		title = { Text("Поиск кратчайшего пути в графе. Алгоритм Дейкстры.") },
		contentColor = Color.White,
		navigationIcon = {
			IconButton(
				onClick = { showMenu = !showMenu}
			) {
				Icon(
					Icons.Filled.Menu,
					modifier = Modifier.semantics {
						contentDescription = "Меню"
					},
					contentDescription = "Меню",
					tint = Color.White
				)
			}
			DropdownMenu(
				expanded = showMenu,
				onDismissRequest = { showMenu = false }
			) {
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
				Icon(
					Icons.Filled.Done ,
					contentDescription = "Сброс",
					tint = Color.White)
			}
			IconButton(onClick = {showAdd = !showAdd}) {
				Icon(
					Icons.Filled.Add,
					contentDescription = "Добавить объект графа",
					tint = Color.White)
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
				Icon(
					Icons.Filled.Delete,
					contentDescription = "Удалить элмент графа",
					tint = Color.White
				)
			}
			IconButton(onClick = {}) {
				Icon(Icons.Filled.Refresh,
					contentDescription = "Сброс",
					tint = Color.White
				)
			}
		}
	)
}

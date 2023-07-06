package UI

import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.runtime.*

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
				Icon(Icons.Filled.Delete , contentDescription = "Удалить элмент графа")
			}
			IconButton(onClick = {}) {
				Icon(Icons.Filled.Refresh , contentDescription = "Сброс")
			}
		}
	)
}

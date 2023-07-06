package UI

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.unit.dp


@Composable
fun MyTopAppBar() {
	var showMenu by remember { mutableStateOf(false) }
	var showAdd by remember { mutableStateOf(false) }
	TopAppBar(
		modifier = Modifier.size(height = 80.dp, width = 1550.dp),
		backgroundColor = Color(red = 58, green = 1, blue = 105),
		title = { Text("Поиск кратчайшего пути в графе. Алгоритм Дейкстры.") },
		contentColor = Color.White,
		navigationIcon = {
			Column(
				modifier = Modifier.padding(8.dp)
			) {
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
				Text(
					text = "Меню",
					color = Color.White
				)
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
			}
		},
		actions = {
			Column(
				modifier = Modifier.padding(8.dp)
			) {
				IconButton(
					onClick = {showAdd = !showAdd},
					modifier = Modifier.padding(start = 23.dp)) {
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
				Text(text = "Добавление", color = Color.White)
			}
			Column(
				modifier = Modifier.padding(8.dp)
			) {
				IconButton(
					onClick = {},
					modifier = Modifier.padding(start = 15.dp)) {
					Icon(
						Icons.Filled.Delete,
						contentDescription = "Удалить элмент графа",
						tint = Color.White
					)
				}
				Text(text = "Удаление", color = Color.White)
			}
			Column(
				modifier = Modifier.padding(8.dp)
			) {
				IconButton(onClick = {}) {
					Icon(Icons.Filled.Refresh,
						contentDescription = "Сброс",
						tint = Color.White
					)
				}
				Text(text = "Сброс", color = Color.White)
			}
		}
	)
}

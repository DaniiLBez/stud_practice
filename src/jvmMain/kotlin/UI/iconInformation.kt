package UI

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Info
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun iconInformation() {
	BottomAppBar(
		modifier = Modifier.size(height = 80.dp, width = 1550.dp),
		backgroundColor = Color(red = 58, green = 1, blue = 105),
		contentColor = Color.White
	) {
		Column(
			modifier = Modifier.padding(start = 1420.dp)
		) {
			IconButton(
				onClick = {},
				modifier = Modifier
					.padding(start = 25.dp)
			) {
				Icon(Icons.Filled.Info, contentDescription = null)
			}
			Text(
				text = "Информация",
				color = Color.White
			)
		}
	}
}

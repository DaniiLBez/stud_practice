package UI

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun drawGraph() {
	Box(
		modifier = Modifier
			.fillMaxHeight()
			.fillMaxWidth()
			.padding(
				start = 10.dp,
				top = 10.dp,
				end = 315.dp,
				bottom = 135.dp
			)
			.border(
				width = 2.dp,
				color = Color(red = 58, green = 1, blue = 105),
			)
			.background(Color.White)
	) {
	}
}

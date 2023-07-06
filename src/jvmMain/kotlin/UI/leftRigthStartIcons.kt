package UI

import algorithm.Dijkstra
import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.keyframes
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.launch

@Composable
fun leftRigthStartIcons() {
	val scope = rememberCoroutineScope()
	val angle = remember { Animatable(initialValue = 0f) }
	val checked = remember { mutableStateOf(false) }
	Row(
		modifier = Modifier
			.fillMaxWidth()
			.padding(
				top = 660.dp,
				bottom = 15.dp),
		horizontalArrangement = Arrangement.Center,
	) {
		OutlinedButton(
			onClick = {},
			modifier = Modifier.size(50.dp),
			shape = CircleShape,
			border= BorderStroke(3.dp, Color(red = 58, green = 1, blue = 105)),
			colors = ButtonDefaults.outlinedButtonColors(contentColor =  Color(red = 58, green = 1, blue = 105))
			) {
			Icon(
				Icons.Filled.ArrowBack,
				contentDescription = "Шаг назад")
		}
		Spacer(modifier = Modifier.width(40.dp))
		OutlinedButton(
			onClick =
			{
				scope.launch() {
					angle.snapTo(targetValue = 0f)
					angle.animateTo(targetValue = 360f, animationSpec = keyframes { durationMillis = 2000 })
					checked.value = false
				}
				val dijkstra = Dijkstra(graph)
				dijkstra.shortestPath(graph.getStartVertex()!!)
				println(dijkstra.toString())
				checked.value = true
			},
			modifier = Modifier.size(50.dp),
			shape = CircleShape,
			border= BorderStroke(3.dp, Color(red = 58, green = 1, blue = 105)),
			colors = ButtonDefaults.outlinedButtonColors(contentColor =  Color(red = 58, green = 1, blue = 105))
		) {
			Icon(modifier = Modifier.rotate(angle.value),
				imageVector = Icons.Filled.PlayArrow,
				tint = if (checked.value) Color.Green else Color(red = 58, green = 1, blue = 105),
				contentDescription = null)
		}
		Spacer(modifier = Modifier.width(40.dp))
		Button(
			onClick = {},
			modifier = Modifier.size(width = 114.dp, height = 50.dp),
			border = BorderStroke(3.dp, Color(red = 58, green = 1, blue = 105)),
			colors = ButtonDefaults.outlinedButtonColors(contentColor =  Color(red = 58, green = 1, blue = 105)),
			shape = RoundedCornerShape(50),
		) {
			Text(
				text = "Результат",
				fontWeight = FontWeight.Bold)
		}
		Spacer(modifier = Modifier.width(40.dp))
		OutlinedButton(
			onClick = {},
			modifier = Modifier.size(50.dp),
			shape = CircleShape,
			border= BorderStroke(3.dp, Color(red = 58, green = 1, blue = 105)),
			colors = ButtonDefaults.outlinedButtonColors(contentColor =  Color(red = 58, green = 1, blue = 105))
		) {
			Icon(
				Icons.Filled.ArrowForward,
				contentDescription = "Шаг вперед"
			)
		}
	}
}

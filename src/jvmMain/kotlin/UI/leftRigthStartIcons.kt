package UI

import algorithm.Dijkstra
import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.keyframes
import androidx.compose.foundation.layout.*
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowLeft
import androidx.compose.material.icons.filled.KeyboardArrowRight
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
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
			val dijkstra = Dijkstra(graph)
			dijkstra.shortestPath(graph.getStartVertex()!!)
			println(dijkstra.toString())
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

//package UI
//
//import androidx.compose.foundation.gestures.detectDragGestures
//import androidx.compose.foundation.layout.offset
//import androidx.compose.material.Button
//import androidx.compose.runtime.*
//import androidx.compose.ui.Modifier
//import androidx.compose.ui.geometry.Offset
//import androidx.compose.ui.input.pointer.pointerInput
//
//@Composable
//fun viewGraph() {
//	var position by remember { mutableStateOf(Offset.Zero) }
//	Button(
//		modifier = Modifier
//			.offset { position }
//			.pointerInput(Unit) {
//				detectDragGestures { change, dragAmount ->
//					var oldPosition = position
//					awaitPointerEventScope {
//						while (true) {
//							val event = awaitPointerEvent()
//							val newPosition = oldPosition + dragAmount
//							position = newPosition
//
//							if (event.changes.any { it.pressed }) {
//								break
//							}
//						}
//					}
//				}
//			},
//		onClick = { /* Handle onClick event if needed */ }
//	) {
//		// Object content
//	}
//}
//

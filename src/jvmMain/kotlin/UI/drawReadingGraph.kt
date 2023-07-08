package UI

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.awt.SwingPanel
import androidx.compose.ui.graphics.Canvas
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.mxgraph.swing.mxGraphComponent
import com.mxgraph.view.mxGraph
import javax.swing.BoxLayout
import javax.swing.JPanel

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
		SwingPanel(
			background = Color.Red,
			modifier = Modifier.fillMaxSize().border(BorderStroke(2.dp, Color(red = 58, green = 1, blue = 105))),
			factory = {
				val mxgraph = mxGraph()
				val parent = mxgraph.defaultParent
				val vertex1 = mxgraph.insertVertex(parent, null, "A", 20.0, 20.0, 80.0, 30.0)
				val vertex2 = mxgraph.insertVertex(parent, null, "B", 120.0, 120.0, 80.0, 30.0)
				val edge = mxgraph.insertEdge(parent, null, "Edge", vertex1, vertex2)

				val graphComponent = mxGraphComponent(mxgraph)

				JPanel().apply {
					layout = BoxLayout(this, BoxLayout.Y_AXIS)
					add(graphComponent)
				}
			}
		)
	}
}

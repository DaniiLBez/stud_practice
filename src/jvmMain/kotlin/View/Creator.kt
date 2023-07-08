package View

import controller.GraphCreatorController
import model.GraphCreatorModel
import com.mxgraph.swing.mxGraphComponent
import com.mxgraph.util.mxEvent
import com.mxgraph.view.mxGraph
import java.awt.event.MouseAdapter
import java.awt.event.MouseEvent

internal class Creator(controller: GraphCreatorController, model: GraphCreatorModel) :
	mxGraphComponent(model.getGraph() as mxGraph)
{
	private val controller: GraphCreatorController

	init {
		this.controller = controller
		initGUI()
		isFocusable = true
	}

	private fun initGUI() {
		isConnectable = false
		isCenterZoom = true
		setZoomFactor(ZOOM_FACTORY)
		addMouseWheelListener {
			e -> val scaled: Double = getGraph().view.scale
			if (e.wheelRotation > 0 && scaled - MAX_SCALE < EPS) {
				zoomIn()
			} else if (e.wheelRotation < 0 && scaled - MIN_SCALE > EPS) {
				zoomOut()
			}
		}
		getGraphControl().addMouseListener(object : MouseAdapter() {
			override fun mousePressed(e: MouseEvent) {
				controller.mousePressed(e, getCellAt(e.x, e.y))
			}

			override fun mouseReleased(e: MouseEvent) {
				controller.mouseReleased(e, getCellAt(e.x, e.y))
			}
		}
		)
		getGraph().addListener(mxEvent.MOVE_CELLS) { o, mxEventObject -> controller.mouseReleased(null, null) }
	}

	companion object {
		private const val MIN_SCALE = 0.5
		private const val MAX_SCALE = 1.0
		private const val EPS = 0.000001
		private const val ZOOM_FACTORY = 1.05
	}
}

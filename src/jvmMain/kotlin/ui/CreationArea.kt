package ui

import com.mxgraph.swing.mxGraphComponent
import com.mxgraph.util.mxEvent
import controller.CreationAreaController
import model.states.CreationAreaModel
import java.awt.event.MouseAdapter
import java.awt.event.MouseEvent

internal class CreationArea(private val controller: CreationAreaController, model: CreationAreaModel) :
	mxGraphComponent(model.graph) {

	init {
		initGUI()
		isFocusable = true
	}

	private fun initGUI() {
		isConnectable = false
		isCenterZoom = true
		setZoomFactor(ZOOM_FACTORY)
		addMouseWheelListener {
				mouseWheelEvent ->
			val scaled: Double = getGraph().view.scale
			if (mouseWheelEvent.wheelRotation > 0 && scaled - MAX_SCALE < EPS) {
				zoomIn()
			} else if (mouseWheelEvent.wheelRotation < 0 && scaled - MIN_SCALE > EPS) {
				zoomOut()
			}
		}
		getGraphControl().addMouseListener(object : MouseAdapter() {
			override fun mousePressed(mouseEvent: MouseEvent) {
				controller.mousePressed(mouseEvent, getCellAt(mouseEvent.x, mouseEvent.y))
			}

			override fun mouseReleased(mouseEvent: MouseEvent) {
				controller.mouseReleased(mouseEvent, getCellAt(mouseEvent.x, mouseEvent.y))
			}
		}
		)
		getGraph().addListener(mxEvent.MOVE_CELLS) { _, mxEventObject -> controller.mouseReleased(null, null) }
	}

	companion object {
		private const val MIN_SCALE = 0.5
		private const val MAX_SCALE = 1.0
		private const val EPS = 0.000001
		private const val ZOOM_FACTORY = 1.05
	}
}

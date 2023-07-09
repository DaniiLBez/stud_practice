package model.states

import Constants
import com.mxgraph.util.mxConstants
import com.mxgraph.view.mxGraph
import java.util.*
import kotlin.collections.HashMap

internal object StyleManager {
	//Установить стиль для вершин в нормальном состоянии
	fun initMyCustomVertexNormalStyle(graph: mxGraph) {
		val stylesheet = graph.stylesheet
		val vertexStyle: MutableMap<String, Any> = Hashtable()
		vertexStyle[mxConstants.STYLE_FILLCOLOR] = Constants.FILL_COLOR_NORMAL
		vertexStyle[mxConstants.STYLE_FONTCOLOR] = Constants.FONT_COLOR_NORMAL
		vertexStyle[mxConstants.STYLE_STROKEWIDTH] = Constants.STROKE_VERTEX_SIZE
		vertexStyle[mxConstants.STYLE_STROKECOLOR] = Constants.STROKE_COLOR_NORMAL
		vertexStyle[mxConstants.STYLE_FONTSIZE] = Constants.FONT_SIZE
		vertexStyle[mxConstants.STYLE_PERIMETER] = mxConstants.PERIMETER_ELLIPSE
		vertexStyle[mxConstants.STYLE_FONTSTYLE] = mxConstants.FONT_BOLD
		vertexStyle[mxConstants.STYLE_SHAPE] = mxConstants.SHAPE_ELLIPSE
		vertexStyle[mxConstants.STYLE_VERTICAL_ALIGN] = mxConstants.ALIGN_MIDDLE
		stylesheet.putCellStyle(Constants.VERTEX_NORMAL_STYLE, vertexStyle)
	}

	//Установить стиль для ребер в нормальном состоянии
	fun initMyCustomEdgeNormalStyle(graph: mxGraph) {
		val stylesheet = graph.stylesheet
		val edgeStyle: MutableMap<String, Any> = HashMap()
		edgeStyle[mxConstants.STYLE_ROUNDED] = true
		edgeStyle[mxConstants.STYLE_SHAPE] = mxConstants.SHAPE_CONNECTOR
		edgeStyle[mxConstants.STYLE_ENDARROW] = mxConstants.ARROW_CLASSIC
		edgeStyle[mxConstants.STYLE_VERTICAL_ALIGN] = mxConstants.ALIGN_MIDDLE
		edgeStyle[mxConstants.STYLE_ALIGN] = mxConstants.ALIGN_CENTER
		edgeStyle[mxConstants.STYLE_STROKECOLOR] = Constants.STROKE_COLOR_NORMAL
		edgeStyle[mxConstants.STYLE_FILLCOLOR] = Constants.STROKE_COLOR_NORMAL
		edgeStyle[mxConstants.STYLE_STROKEWIDTH] = Constants.STROKE_EDGE_SIZE
		edgeStyle[mxConstants.STYLE_FONTCOLOR] = Constants.FONT_COLOR_NORMAL
		edgeStyle[mxConstants.STYLE_FONTSIZE] = Constants.FONT_SIZE
		edgeStyle[mxConstants.STYLE_FONTSTYLE] = mxConstants.FONT_BOLD
		edgeStyle[mxConstants.STYLE_LABEL_BACKGROUNDCOLOR] = Constants.FILL_COLOR_NORMAL
		edgeStyle[mxConstants.STYLE_LABEL_BORDERCOLOR] = Constants.STROKE_COLOR_NORMAL
		stylesheet.putCellStyle(Constants.EDGE_NORMAL_STYLE, edgeStyle)
	}

	//Установить стиль для вершин в выделеном состоянии
	fun initMyCustomVertexSelectedStyle(graph: mxGraph) {
		val stylesheet = graph.stylesheet
		val vertexStyle: MutableMap<String, Any> = Hashtable()
		vertexStyle[mxConstants.STYLE_FILLCOLOR] = Constants.FILL_COLOR_SELECTED
		vertexStyle[mxConstants.STYLE_FONTCOLOR] = Constants.FONT_COLOR_SELECTED
		vertexStyle[mxConstants.STYLE_STROKEWIDTH] = Constants.STROKE_VERTEX_SIZE
		vertexStyle[mxConstants.STYLE_STROKECOLOR] = Constants.STROKE_COLOR_SELECTED
		vertexStyle[mxConstants.STYLE_FONTSIZE] = Constants.FONT_SIZE
		vertexStyle[mxConstants.STYLE_PERIMETER] = mxConstants.PERIMETER_ELLIPSE
		vertexStyle[mxConstants.STYLE_FONTSTYLE] = mxConstants.FONT_BOLD
		vertexStyle[mxConstants.STYLE_SHAPE] = mxConstants.SHAPE_ELLIPSE
		vertexStyle[mxConstants.STYLE_VERTICAL_ALIGN] = mxConstants.ALIGN_MIDDLE
		stylesheet.putCellStyle(Constants.VERTEX_SELECTED_STYLE, vertexStyle)
	}

	//Установить стиль для ребер в выделеном состоянии
	fun initMyCustomEdgeSelectedStyle(graph: mxGraph) {
		val stylesheet = graph.stylesheet
		val edgeStyle: MutableMap<String, Any> = Hashtable()
		edgeStyle[mxConstants.STYLE_ROUNDED] = true
		edgeStyle[mxConstants.STYLE_SHAPE] = mxConstants.SHAPE_CONNECTOR
		edgeStyle[mxConstants.STYLE_ENDARROW] = mxConstants.ARROW_CLASSIC
		edgeStyle[mxConstants.STYLE_VERTICAL_ALIGN] = mxConstants.ALIGN_MIDDLE
		edgeStyle[mxConstants.STYLE_ALIGN] = mxConstants.ALIGN_CENTER
		edgeStyle[mxConstants.STYLE_STROKECOLOR] = Constants.STROKE_COLOR_SELECTED
		edgeStyle[mxConstants.STYLE_FILLCOLOR] = Constants.STROKE_COLOR_SELECTED
		edgeStyle[mxConstants.STYLE_STROKEWIDTH] = Constants.STROKE_EDGE_SIZE
		edgeStyle[mxConstants.STYLE_FONTCOLOR] = Constants.FONT_COLOR_SELECTED
		edgeStyle[mxConstants.STYLE_FONTSIZE] = Constants.FONT_SIZE
		edgeStyle[mxConstants.STYLE_FONTSTYLE] = mxConstants.FONT_BOLD
		edgeStyle[mxConstants.STYLE_LABEL_BACKGROUNDCOLOR] = Constants.FILL_COLOR_SELECTED
		edgeStyle[mxConstants.STYLE_LABEL_BORDERCOLOR] = Constants.STROKE_COLOR_SELECTED
		edgeStyle[mxConstants.STYLE_VERTICAL_LABEL_POSITION] = mxConstants.ALIGN_BOTTOM
		stylesheet.putCellStyle(Constants.EDGE_SELECTED_STYLE, edgeStyle)
	}

	fun initMyCustomCurrentVertexStyle(graph: mxGraph) {
		val stylesheet = graph.stylesheet
		val vertexStyle: MutableMap<String, Any> = Hashtable()
		vertexStyle[mxConstants.STYLE_FILLCOLOR] = Constants.FILL_COLOR_CURRENT
		vertexStyle[mxConstants.STYLE_FONTCOLOR] = Constants.FONT_COLOR_CURRENT
		vertexStyle[mxConstants.STYLE_STROKEWIDTH] = Constants.STROKE_VERTEX_SIZE
		vertexStyle[mxConstants.STYLE_STROKECOLOR] = Constants.STROKE_COLOR_CURRENT
		vertexStyle[mxConstants.STYLE_FONTSIZE] = Constants.FONT_SIZE
		vertexStyle[mxConstants.STYLE_PERIMETER] = mxConstants.PERIMETER_ELLIPSE
		vertexStyle[mxConstants.STYLE_FONTSTYLE] = mxConstants.FONT_BOLD
		vertexStyle[mxConstants.STYLE_SHAPE] = mxConstants.SHAPE_ELLIPSE
		vertexStyle[mxConstants.STYLE_VERTICAL_ALIGN] = mxConstants.ALIGN_MIDDLE
		stylesheet.putCellStyle(Constants.CURRENT_VERTEX_STYLE, vertexStyle)
	}

	fun initMyCustomInQueueVertexStyle(graph: mxGraph) {
		val stylesheet = graph.stylesheet
		val vertexStyle: MutableMap<String, Any> = Hashtable()
		vertexStyle[mxConstants.STYLE_FILLCOLOR] = Constants.FILL_COLOR_NORMAL
		vertexStyle[mxConstants.STYLE_FONTCOLOR] = Constants.FONT_COLOR_NORMAL
		vertexStyle[mxConstants.STYLE_STROKEWIDTH] = "3"
		vertexStyle[mxConstants.STYLE_STROKECOLOR] = Constants.STROKE_COLOR_SELECTED
		vertexStyle[mxConstants.STYLE_FONTSIZE] = Constants.FONT_SIZE
		vertexStyle[mxConstants.STYLE_PERIMETER] = mxConstants.PERIMETER_ELLIPSE
		vertexStyle[mxConstants.STYLE_FONTSTYLE] = mxConstants.FONT_BOLD
		vertexStyle[mxConstants.STYLE_SHAPE] = mxConstants.SHAPE_ELLIPSE
		vertexStyle[mxConstants.STYLE_VERTICAL_ALIGN] = mxConstants.ALIGN_MIDDLE
		stylesheet.putCellStyle(Constants.QUEUE_VERTEX_STYLE, vertexStyle)
	}
}

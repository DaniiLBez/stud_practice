package model.adapter

import algorithm.graph.Graph
import com.mxgraph.model.mxCell
import com.mxgraph.view.mxGraph
import java.util.Scanner

class GraphAdapter {

	fun convertToGraph(mxgraph: mxGraph): String{
		val vertex = HashSet<String>()
		return buildString {
			mxgraph.getChildEdges(mxgraph.defaultParent).forEach {
				val edge = mxCell(it)
				val source = edge.source.value.toString()
				val target = edge.target.value.toString()
				vertex.add(source)
				vertex.add(target)
				append(source).append(" ").append(target).append(" ").append(edge.value).append("\n")
			}
			mxgraph.getChildVertices(mxgraph.defaultParent).forEach {
				val vert = mxCell(it)
				if(vert.value !in vertex){
					append(vert.value).append(" ").append("\n")
				}
			}
		}
	}

	fun convertToMx(mxgraph: mxGraph, graph: String): MutableList<Any>{
		val cells = mutableListOf<Any>()
		val scanner = Scanner(graph)
		while (scanner.hasNextLine()){
			val a = scanner.nextLine().split(" ")
			if (a.size == 1){
				mxgraph.getChildVertices(mxgraph.defaultParent).forEach {
					if(mxCell(it).value == a[0]){
						cells.add(mxCell(it))
					}
				}
			} else{
				mxgraph.getChildEdges(mxgraph.defaultParent).forEach {
					val source = mxCell(it).source.value.toString()
					val target = mxCell(it).target.value.toString()
					if(source == a[0] && target == a[1]){
						cells.add(mxCell(it))
						cells.add(mxCell(it).source as mxCell)
						cells.add(mxCell(it).target as mxCell)
					}
				}
			}
		}
		return cells
	}



}

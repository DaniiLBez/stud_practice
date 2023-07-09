package model.adapter

import model.algorithm.ShortestWayView
import java.util.function.Consumer

interface IAdapter {
	fun shortestWay(alg: String?, gr: Any?, s: Any?, t: Any?, callbackEnd: Consumer<List<ShortestWayView>>)
}

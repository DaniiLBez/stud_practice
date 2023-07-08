package algorithm.graph


class DirectedEdge(val from: Int, val to: Int, val weight: Double) {

	override fun toString(): String {
		return "DirectedEdge{" +
			"source=" + from +
			", target=" + to +
			", weight=" + weight +
			'}'
	}
}

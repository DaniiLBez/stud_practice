package algorithm

open class IDataSource {
	protected var rawGraphData: MutableMap<String, MutableList<Pair<String, Double>>> = mutableMapOf()
	protected var startVertex: String = ""

	open fun getRawData(): MutableMap<String, MutableList<Pair<String, Double>>>{
		return rawGraphData
	}

	open fun getGraphStartVertex(): String{
		return startVertex
	}
}


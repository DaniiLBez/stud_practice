package Model

open class IDataSourse(_fileName: String) {
	protected var fileName: String = _fileName
	protected var rawGraphData: MutableMap<String, MutableList<Pair<String, Double>>> = mutableMapOf()
	protected var startVertex: String = ""

	open fun getRawData(): MutableMap<String, MutableList<Pair<String, Double>>>{
		return  rawGraphData
	}

	open fun getGraphStartVertex(): String{
		return startVertex
	}
}

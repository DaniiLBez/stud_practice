package algorithm

import java.io.*

class FileReader(_fileName: String) : IDataSource() {
	protected var fileName: String = _fileName

	fun readData() {
		val file = File(fileName)

		if (file.exists()) {
			val lines = file.readLines()
			startVertex = lines[0]

			for (i in 1 until lines.size) {
				val(fromVertex, inVertex, weight) = lines[i].split(" ")
				if (rawGraphData.containsKey(fromVertex)) {
					rawGraphData[fromVertex]!!.add(Pair(inVertex, weight.toDouble()))
				} else {
					rawGraphData[fromVertex] = mutableListOf(Pair(inVertex, weight.toDouble()))
				}
			}
		} else println("Не удалось открыть файл")
	}

// 	override fun getRawData(): MutableMap<String, MutableList<Pair<String, Double>>>{
// 		return rawData
// 	}
// 	override fun getStartVertex(): String {
// 		return startVertex
// 	}
}

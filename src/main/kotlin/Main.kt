package Model

fun main(args: Array<String>) {

	val fileReader = FileReader("C:\\Users\\Татьяна\\Desktop\\2_course\\4_sem\\team_project\\src\\main\\kotlin\\Model\\Graph")
	fileReader.readData()
	val graphCreator = GraphCreator(fileReader)
	graphCreator.create()
}

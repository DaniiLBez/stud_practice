import com.mxgraph.model.mxCell
import model.states.CreationAreaModel
import kotlin.test.AfterTest
import kotlin.test.BeforeTest
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertNotEquals
import kotlin.test.assertNotNull


class CreationAreaModelTest {
	private var model: CreationAreaModel? = null

	@BeforeTest
	fun initTest(){
		model = CreationAreaModel()
	}

	@AfterTest
	fun afterTest(){
		model = null
	}

	@Test
	fun addVertexTest(){
		val graph = model?.graph
		model?.addVertex("Test", 10.0, 10.0, 50.0, 50.0)
		var vertex: Any? = null
		graph?.getChildVertices(graph.defaultParent)?.forEach {
			if ((it as mxCell).value == "Test") { vertex = it }
		}
		assertNotNull(vertex)
		assertEquals("Test", (vertex as mxCell).value)
	}

	@Test
	fun addEdgeTest(){
		val graph = model?.graph
		model?.addVertex("Hello", 10.0, 10.0, 50.0, 50.0)
		model?.addVertex("There", 100.0, 100.0, 50.0, 50.0)
		val vertexes = graph?.getChildVertices(graph.defaultParent)?.toList()
		model?.addEdge("150", vertexes!![0], vertexes[1])
		assertNotEquals(-1, model?.checkExistEdge(vertexes!![0], vertexes[1]))
	}

	@Test
	fun deleteTest(){
		val graph = model?.graph
		model!!.addVertex("Hi", 100.0, 100.0, 50.0, 50.0)
		model!!.delete(graph?.getChildVertices(graph.defaultParent)!!)
		assertEquals(0, graph.getChildCells(graph.defaultParent)?.size)
	}
}

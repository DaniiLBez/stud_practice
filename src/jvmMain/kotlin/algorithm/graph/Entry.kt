package algorithm.graph

import java.util.*

class Entry : Comparable<Entry?> {
	private var key = 0.0
	var value: Int

	constructor(key: Double, value: Int) {
		this.key = key
		this.value = value
	}

	constructor(value: Int) {
		this.value = value
	}

	override operator fun compareTo(other: Entry?): Int {
		return key.compareTo(other!!.key)
	}

	override fun equals(other: Any?): Boolean {
		if (this === other) return true
		if (other == null || javaClass != other.javaClass) return false
		val entry = other as Entry
		return value == entry.value
	}

	override fun hashCode(): Int {
		return Objects.hash(value)
	}

	override fun toString(): String {
		return "Entry{" +
			"key=" + key +
			", value=" + value +
			'}'
	}
}

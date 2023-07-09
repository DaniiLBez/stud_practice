package algorithm.graph

import java.util.*


class Entry : Comparable<Entry?> {
	var key = 0.0
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

	override fun equals(o: Any?): Boolean {
		if (this === o) return true
		if (o == null || javaClass != o.javaClass) return false
		val entry = o as Entry
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

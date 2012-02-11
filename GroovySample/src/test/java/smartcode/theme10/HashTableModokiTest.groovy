package smartcode.theme10;

import spock.lang.Specification;

class CustomHashTableTest extends Specification {
	HashTableModoki target = new HashTableModoki()

	def setup(){
		target.set("key1", "value1")
		target.set("",     "value2")
		target.set(null,   "value3")
		target.set(100,    "value4")
		target.set(0.9,    "value5")
		target.set(true,   "value6")
		target.set(null,   "value7")
	}

	def "HashTableModoki Test"() {
		expect:
		target.get(key) == value

		where:
		key     | value
		"key1"  | "value1"
		""      | "value2"
		100     | "value4"
		0.9     | "value5"
		true    | "value6"
		null    | "value7"
		"null"  | null
	}
}

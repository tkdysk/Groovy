package smartcode.theme10

class HashTableModoki {
	Object[] objArray = new Object[1000]

	def getCustomHash(Object key){
		if (key==null) return 0
		return Math.abs(key.hashCode()) % 1000
	}

	def set(Object key, Object value) {
		def defValues = objArray[getCustomHash(key)]
		def values = []
		if (defValues!=null) {
			values = defValues
			for (Object obj : values) {
				Object defKey = ((Object[]) obj)[0]
				if ((key==null && defKey==null) || key.equals(defKey)) {
					values.remove(obj)
					break
				}
			}
		}
		values << [key, value]
		objArray[getCustomHash(key)] = values
	}

	def get(Object key){
		def values = objArray[getCustomHash(key)]
		for (Object obj : values) {
			Object defKey = ((Object[]) obj)[0]
			if ((key==null && defKey==null) || key.equals(defKey)) {
				return ((Object[]) obj)[1]
			}
		}
		return null
	}
}
package sample

/**
 *  「引数で渡された文字列のリストのうち第2引数で指定した長さ以上の要素をすべて大文字に変換し、
 *  その要素を関数内部で新しく定義したリストに追加して返す」関数
 **/
def filterNames(List<String> names, int len) {
	return names.findAll{it.size()>=len}.collect{it.toString().toUpperCase()}
}

assert filterNames([
	"aassa",
	"aSSa",
	"aaa",
	"assssaa",
	"aaa"
], 4) == ["AASSA", "ASSA", "ASSSSAA"]

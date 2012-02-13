package bot.curator.app.analysis

import bot.curator.common.bean.Result

interface Analyzer {
	Result analyze()
}


abstract class AbstarctAnalyzer implements Analyzer {

	List<String> insignificantTags = []


	Boolean isInsignificantTag(String tag){
		if(!tag) return Boolean.TRUE
		return tag.length()==1 ||
		(tag.length()==2 && (tag.getBytes().size()==2||(tag.getBytes().size()==3)) ) ||
		(tag.length()==3 && tag.getBytes().size()==3) ||
		insignificantTags.contains(tag)
	}
}

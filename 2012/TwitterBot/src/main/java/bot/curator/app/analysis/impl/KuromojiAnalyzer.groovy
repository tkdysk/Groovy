package bot.curator.app.analysis.impl

import java.util.List;

import org.atilika.kuromoji.Token;
import org.atilika.kuromoji.Tokenizer
import org.atilika.kuromoji.Tokenizer.Builder
import org.atilika.kuromoji.Tokenizer.Mode
import bot.curator.app.analysis.AbstarctAnalyzer
import bot.curator.app.analysis.Analyzer
import bot.curator.common.bean.Item
import bot.curator.common.bean.Result

class KuromojiAnalyzer extends AbstarctAnalyzer {
	private Result result = new Result()

	KuromojiAnalyzer(Item item){
		this.result.setItem(item)
	}


	Result analyze() {
		String target = result.getItem().getData()

		Builder builder = Tokenizer.builder()
		builder.mode(Mode.NORMAL)
		List<Token> tokens = builder.build().tokenize(target)

		//		for (Token token : tokens) {
		////			println "-------------------------"
		//				println "□\n"+token.getSurfaceForm()+" : "+token.getAllFeaturesArray()
		////			println "□SurfaceForm\n"+token.getSurfaceForm()
		////			println "□PartOfSpeech\n"+token.getAllFeaturesArray()
		////			println "□PartOfSpeech\n"+token.getPartOfSpeech()
		////			println "□Reading\n"+token.getReading()
		//		}

		List<String> tags = []
		tokens.each { token ->
			if(token.getAllFeaturesArray()[0] == "名詞" &&
			( token.getAllFeaturesArray()[1] == "一般" ||
			token.getAllFeaturesArray()[1] == "固有名詞"	)) {
				String tag = token.getSurfaceForm()
				if(!isInsignificantTag(tag)) tags << tag
			}
		}
		result.setTags(tags)


		return result
	}
}

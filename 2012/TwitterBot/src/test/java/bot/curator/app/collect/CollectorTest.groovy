package bot.curator.app.collect

	Boolean isSignificantConditions(String str){
		if(!str) return Boolean.FALSE
		return str.length()!=1 &&
		!(str.matches(/[0-9a-zA-Z]{1,2}/)) &&
		!(str.matches(/@[0-9a-zA-Z_]*/)) &&
		!(str.matches(/^(https?|ftp)(:\\/\\/[-_.!~*\\'()a-zA-Z0-9;\\/?:\\@&=+\\$,%#]+)$/))
	}

	println isSignificantConditions("n")
package com.drzoeylong.markdowntohtml.logic.converter;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegexMatchConverter {
	private Pattern p;
	private String replace;
	
	public RegexMatchConverter(String match, String replace)
	{
		p=Pattern.compile(match);
		this.replace=replace;
	}
	
	public String matchAndReplace(String in)
	{
		Matcher m=p.matcher(in);
		if(!m.matches())
			return null;
		else
			return m.replaceAll(replace);
	}
	
}

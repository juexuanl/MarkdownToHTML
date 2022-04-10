package com.drzoeylong.markdowntohtml.logic;

import java.util.ArrayList;

import com.drzoeylong.markdowntohtml.logic.converter.*;

public class MarkdownToHTMLConverter {
	private ArrayList<RegexMatchConverter> converters;
	private RegexMatchConverter linkConverter;
	
	public MarkdownToHTMLConverter()
	{
		converters=new ArrayList<RegexMatchConverter>();
		
		converters.add(new RegexMatchConverter("^#\\s+(.*)$","<h1>$1</h1>"));
		converters.add(new RegexMatchConverter("^##\\s+(.*)$","<h2>$1</h2>"));
		converters.add(new RegexMatchConverter("^###\\s+(.*)$","<h3>$1</h3>"));
		converters.add(new RegexMatchConverter("^####\\s+(.*)$","<h4>$1</h4>"));
		converters.add(new RegexMatchConverter("^#####\\s+(.*)$","<h5>$1</h5>"));
		converters.add(new RegexMatchConverter("^######\\s+(.*)$","<h6>$1</h6>"));
		converters.add(new RegexMatchConverter("^(.*)$","<p>$1</p>"));
		linkConverter = new RegexMatchConverter("(.*)\\[(.*)\\]\\((.*)\\)(.*)", "$1<a href=\"$3\">$2</a>$4");
	}
	
	
	public String convert(String markdown)throws ConvertFailedException
	{
		String[] inputs = markdown.split("\n");
		String output="";
		for(String in : inputs)
		{	if(in.matches("^\\s*$")) {
				continue;
			}
			for(RegexMatchConverter rmc:converters)
			{
				String out=rmc.matchAndReplace(in);
				if(out!=null) {
					String newStr = linkConverter.matchAndReplace(out);
					if(newStr==null)
						output+=out+"\n";
					else
						output+=newStr+"\n";
					break;
				}					
			}
		}
		return output;
		
	}
	
	
	public static void main(String[] args)throws Exception
	{
		MarkdownToHTMLConverter test=new MarkdownToHTMLConverter();
		System.out.println(test.convert("# Sample test\n## case1\n##example:We would like to reach [Google Link](https://google.com) in the browser.\n\n## Sample test2\n## case 2:\nDo you like to use the following link?\n[Google Link](https://google.com)"));
	}
}

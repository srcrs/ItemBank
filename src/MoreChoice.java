import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MoreChoice implements Mold{
	public static MoreChoice moreChoice = new MoreChoice();
	String str = null;
	String ID = null;
	public static String out = "";
	String T = "";
	int position;
	Pattern pattern = null;
	Matcher matcher = null;
	
	@Override
	public String run(String s) {
		out = "";
		out += this.TitleNumber(s) + "\n";
		s = this.Result(s);
		out += this.Subject(s) + "\n<br />\n";
		out += this.Option(s.substring(position)) + "\n";
		out += this.onClick();
		// System.out.println(out);
		return out;
	}
	/*
	 * 获取题号
	 */
	@Override
	public String TitleNumber(String s) {
		String regex_T = "(\\d{1,}[.|、|．]{1}[\\D|^A-Z|a-z]{1})";
		pattern = Pattern.compile(regex_T);
		matcher = pattern.matcher(s);
		String cache = "";
		if (matcher.find()) {
			ID = "3-" + matcher.group().substring(matcher.start(), matcher.end() - 2);
			// 3-代表多选题
		}
		cache += "\n<form id=\"" + ID + "\">";
		// System.out.println(cache);
		return cache;
	}
	/*
	 * 获取题目
	 */
	@Override
	public String Subject(String s) {
		String regex_T = "([ＡＢＣＤABCDabcd]{1}[.|、|．]{1})";
		Pattern pattern = Pattern.compile(regex_T);
		Matcher matcher = pattern.matcher(s);
		int index = -1;
		if (matcher.find()) {
			// System.out.println(matcher.group());
			index = matcher.start();
		}
		// System.out.println(index);
		position = index;
		String cache = s.substring(0, index);
		return cache;
	}
	/*
	 * 获取选项
	 */
	@Override
	public String Option(String s) {
		String regex_X = "([ＡＢＣＤABCDabcd]{1}[.|、|．]{1})";
		Pattern pattern = Pattern.compile(regex_X);
		Matcher matcher = pattern.matcher(s);
		int index = -1;
		String cache = "";
		int end = -1;
		while (matcher.find()) {
			end = matcher.start();
			if (index != -1) {
				cache += "<input type=\"checkbox\" id=\"" + ID + "-" + s.charAt(index) + "\" name=\"xxx\" />";
				cache += s.substring(index, end);
				cache += "\n<br />\n";
			}
			index = end;
		}
		end = s.length();
		cache += "<input type=\"checkbox\" id=\"" + ID + "-" + s.charAt(index) + "\" name=\"xxx\" />";
		cache += s.substring(index, end);
		cache += "\n<br />\n";
		cache += "</form>\n";
		// System.out.println(cache);
		return cache;
	}
	/*
	 * 获取答案
	 */
	@Override
	public String Result(String s) {
		String regex_R = "(\\([ＡＢＣＤABCDabcd]{2,}\\))";
		Pattern pattern = Pattern.compile(regex_R);
		Matcher matcher = pattern.matcher(s);
		if (matcher.find()) {
			int endIndex = matcher.group().length();
			T += matcher.group().substring(1, endIndex-1);
		}
		s = s.replaceAll(regex_R, "");
		return s;
	}

	@Override
	public String onClick() {
		String cache = "<button onClick=\"javascript:if(";
		for (char t : T.toCharArray()) {
			cache += "document.getElementById(\'" + ID + "-" + t + "\').checked&&";
		}
		cache = cache.substring(0, cache.length()-2);
//		cache += "<button onClick=\"javascript:if(document.getElementById(\'" + ID + "-";
		cache += "){document.getElementById(\'" + ID + "\').style.color=\'#3eaf7c\'}";
		cache += "else{document.getElementById(\'" + ID + "\').style.color=\'#F4606C\'}\">确定</button>\n";
		return cache;
	}

	@Override
	public String rule(String s) {
		this.Result(s);
		switch(T) {
		case "AB":
		case "AC":
		case "AD":
		case "BC":
		case "BD":
		case "CD":
		case "ABC":
		case "ABD":
		case "BCD":
		case "ABCD":{
			T = "";
			return "true";
		}
		}
		T = "";
		return "false";
	}
	
} 
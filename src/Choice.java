import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Choice implements Mold {
	public static Choice choice = new Choice();
	String str = null;
	String ID = null;
	public static String out = "";
	String T = "";
	int position;
	Pattern pattern = null;
	Matcher matcher = null;

	/*
	 * 获取题号
	 */
	public String TitleNumber(String s) {
		String regex_T = "(\\d{1,}[.|、|．]{1}[\\D|^A-Z|a-z]{1})";
		pattern = Pattern.compile(regex_T);
		matcher = pattern.matcher(s);
		String cache = "";
		if (matcher.find()) {
			ID = "2-" + matcher.group().substring(matcher.start(), matcher.end() - 2);
			// 2-代表选择题
		}
		cache += "\n<form id=\"" + ID + "\">";
		return cache;
	}

	/*
	 * 获取题目
	 */
	public String Subject(String s) {
		String regex_T = "([ＡＢＣＤABCDabcd]{1}[.|、|．]{1})";
		Pattern pattern = Pattern.compile(regex_T);
		Matcher matcher = pattern.matcher(s);
		int index = -1;
		if (matcher.find()) {
			index = matcher.start();
		}
		position = index;
		String cache = s.substring(0, index);
		return cache;
	}

	/*
	 * 获取选项
	 */
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
				cache += "<input type=\"radio\" id=\"" + ID + "-" + s.charAt(index) + "\" name=\"xxx\" />";
				cache += s.substring(index, end);
				cache += "\n<br />\n";
			}
			index = end;
		}
		end = s.length();
		cache += "<input type=\"radio\" id=\"" + ID + "-" + s.charAt(index) + "\" name=\"xxx\" />";
		cache += s.substring(index, end);
		cache += "\n<br />\n";
		cache += "</form>\n";
		return cache;
	}

	/*
	 * 获取答案
	 */
	public String Result(String s) {
		String regex_R = "(\\([ＡＢＣＤABCDabcd]\\))";
		Pattern pattern = Pattern.compile(regex_R);
		Matcher matcher = pattern.matcher(s);
		if (matcher.find()) {
			T += matcher.group().toString().charAt(1);
		}
		s = s.replaceAll(regex_R, "");
		return s;
	}

	/*
	 * 当点击按钮发生的事件
	 */
	public String onClick() {
		String cache = "";
		cache += "<button onClick=\"javascript:if(document.getElementById(\'" + ID + "-";
		cache += T + "\').checked){document.getElementById(\'" + ID + "\').style.color=\'#3eaf7c\'}";
		cache += "else{document.getElementById(\'" + ID + "\').style.color=\'#F4606C\'}\">确定</button>\n";
		return cache;
	}

	@Override
	public String run(String s) {
		out = "";
		out += this.TitleNumber(s) + "\n";
		s = this.Result(s);
		out += this.Subject(s) + "\n<br />\n";
		out += this.Option(s.substring(position)) + "\n";
		out += this.onClick();
		return out;
	}

	@Override
	public String rule(String s) {
		this.Result(s);
		switch (T) {
		case "Ａ":
		case "Ｂ":
		case "Ｃ":
		case "Ｄ":
		case "A":
		case "B":
		case "C":
		case "D":
		case "a":
		case "b":
		case "c":
		case "d": {
			T = "";
			return "true";
		}
		}
		T = "";
		return "false";
	}
}

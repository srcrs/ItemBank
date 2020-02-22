import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Fill implements Mold{
	ArrayList<String> T = new ArrayList<String>();
	public static String out = "";
	Pattern pattern=null;
	Matcher matcher=null;
	String ID="";
	@Override
	public String run(String s) {
		out = "";
		out += this.TitleNumber(s) + "\n";
		s=this.Result(s);
		out += this.Subject(s) + "\n<br />\n";
		out += this.Option(s) + "\n";
		out += this.onClick();
		T.clear();
		return out;
	}

	@Override
	public String TitleNumber(String s) {
		String regex_T = "(\\d{1,}[.|、|．]{1}[\\D|^A-Z|a-z]{1})";
		pattern = Pattern.compile(regex_T);
		matcher = pattern.matcher(s);
		String cache = "";
		if (matcher.find()) {
			ID = "4-" + matcher.group().substring(matcher.start(), matcher.end() - 2);
			// 4-代表填空题
		}
		cache += "\n<form id=\"" + ID + "\">";
		return cache;
	}

	@Override
	public String Subject(String s) {
		return s;
	}

	@Override
	public String Option(String s) {
		String cache = "";
		int t=1;
		for(@SuppressWarnings("unused") String f : T) {
			cache += "[" + t + "]:" + "<input type=\"text\" id=\""+ID+"-"+ t++ +"\" name=\"xxx\" />"+"\n<br/>\n";
		}
		cache += "</form>\n";
		return cache;
	}

	@Override
	public String Result(String s) {
		String regex_R = "(\\([0-9|a-z|A-Z|、|.|\\u4E00-\\u9FA5]+\\))";
		pattern = Pattern.compile(regex_R);
		matcher = pattern.matcher(s);
		int t=1;
		while(matcher.find()) {
			T.add(matcher.group().substring(1, matcher.group().length()-1));
			s = s.replace(matcher.group(), "[" + t++ + "]");
		}
		return s;
	}

	@Override
	public String onClick() {
		String cache="";
		int t=1;
		cache += "<button onClick=\"javascript:if(";
		for(String f : T) {
			cache += "document.getElementById(\'"+ ID + "-" + t++ +"\').value==\'"+ f +"\'&&";
		}
		cache = cache.substring(0, cache.length()-2);
		cache += "){document.getElementById(\'" + ID + "\').style.color=\'#3eaf7c\'}";
		cache += "else{document.getElementById(\'" + ID + "\').style.color=\'#F4606C\'}\">确定</button>\n";
		return cache;
	}

	@Override
	public String getType() {
		return "填空";
	}

}

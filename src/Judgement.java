import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Judgement implements Mold{
	public static Judgement judgement = new Judgement();
	String str = null;
	String ID=null;
	public static String out="";
	char T;
	Pattern pattern = null;
	Matcher matcher = null;
	@Override
	public String TitleNumber(String s) {
		String regex_T = "(\\d+\\.\\D)";
		pattern = Pattern.compile(regex_T);
	    matcher = pattern.matcher(s);
	    String cache = "";
	    if(matcher.find()) {
	    	ID = "1-"+matcher.group().substring(matcher.start(), matcher.end()-2);
	    	//1-代表判断题
	    }
	    cache += "\n<form id=\""+ID+"\">"; 
	   // System.out.println(cache);
		return cache;
	}

	@Override
	public String Subject(String s) {
		// TODO Auto-generated method stub
		return s;
	}

	@Override
	public String Option(String s) {
		// TODO Auto-generated method stub
		String cache="";
		cache += "<input type=\"radio\" id=\""+ID+"-"+"T"+"\" name=\"xxx\" />";
		cache += "T";
		cache += "\n<input type=\"radio\" id=\""+ID+"-"+"F"+"\" name=\"xxx\" />";
		cache += "F";
		cache += "\n<br />\n";
		cache += "</form>\n";
		return cache;
	}

	@Override
	public String Result(String s) {
		 String regex_R = "(\\([ABCDabcdTFtf]\\))";
		Pattern pattern = Pattern.compile(regex_R);
		Matcher matcher = pattern.matcher(s);
		if(matcher.find()) {
			T = matcher.group().toString().charAt(1);

		}
		s=s.replaceAll(regex_R, "");
		//System.out.println(s);
		return s;
	}

	@Override
	public String onClick() {
		String cache="";
		cache += "<button onClick=\"javascript:if(document.getElementById(\'"+ID+"-";
		cache += T+"\').checked){document.getElementById(\'"+ID+"\').style.color=\'#3eaf7c\'}";
		cache += "else{document.getElementById(\'"+ID+"\').style.color=\'#F4606C\'}\">确定</button>\n";
		return cache;
	}


	@Override
	public String run(String s) {
	//	System.out.println(s);
		out = "";
		out += this.TitleNumber(s)+"\n";
		s = this.Result(s);
		out += this.Subject(s)+"\n<br />\n";
		out += this.Option(s)+"\n";
		out += this.onClick();
		System.out.println(out);
		return out;
	}
}

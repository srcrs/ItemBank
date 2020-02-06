import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Classification {
	static char T;
	static String cache="";
	/*
	 * 获取答案
	 */
	public static void Result(String s) {
        String regex_R = "(\\([ABCDabcdTFtf]\\))";
		Pattern pattern = Pattern.compile(regex_R);
		Matcher matcher = pattern.matcher(s);
		if(matcher.find()) {
			T = matcher.group().toString().charAt(1);
		}
	}
	public static String Function(String s) {
		cache = "";
		Result(s);
	//	System.out.println(s);
		switch(T) {
		case 'A' :
		case 'B' :
		case 'C' :
		case 'D' :
		case 'a' :
		case 'b' :
		case 'c' :
		case 'd' :{
		   cache += Choice.choice.run(s);
		};break;
		case 'T' :
		case 'F' :
		case 't' :
		case 'f' :{
			System.out.println(s);
			cache += Judgement.judgement.run(s);
		};break;
		}
		return cache;
	}
}

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Handle {
	public String str="";
	public static String out="";
	Pattern pattern = null;
	Matcher matcher = null;
	ArrayList<String> whole = new ArrayList<String>();
	public Handle(String s) {
		// TODO Auto-generated constructor stub
		this.str = s;
		this.Init();
	}
	public void Init() {
		str = str.replace("（", "(");
		str = str.replace('）', ')');
		str = str.replace("\u0020", "");//消除英文空格
		str = str.replace("\u3000", "");//消除中文空格
		this.Divition();
		this.merge();
	}
	/*
	 * 分割
	 */
	public void Divition() {
		String regex = "(\\d+\\.\\D)";
		//System.out.println(str);
		pattern = Pattern.compile(regex);
	    matcher = pattern.matcher(str);
		int index=-1;
		int end=-1;
		while(matcher.find()) {
			end=matcher.start();
	//		System.out.println(index+"----"+end);
			if(index!=-1) {
				whole.add(str.substring(index, end));
			}
			index = end;
		}
		end = str.length();
		whole.add(str.substring(index,end));
	}
	public void merge() {
		for (String sqw : whole) {
			out += Classification.Function(sqw);
		}
	}
}

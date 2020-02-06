import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.sun.org.apache.xalan.internal.utils.XMLSecurityManager.Limit;

public class Handle {
	public int flag=0;
	public String str="";
	public String [] limit = {"9","99","999","9999","99999"};
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
		flag = 0;
		String regex = "(\\d{1,}[.|、|．]{1}\\D)";
		//System.out.println(str);
		pattern = Pattern.compile(regex);
	    matcher = pattern.matcher(str);
		int index=-1;
		int end=-1;
		while(matcher.find()) {
			System.out.println(matcher.group());
			end=matcher.end()-2;//代表点的位置
	//		System.out.println(index+"----"+end);
			if(str.substring(end-flag-1, end).compareTo(limit[flag])>=0) {
				flag++;
			}
			if(index!=-1) {
				whole.add(str.substring(index, end-flag-1));
			}
			index = end-flag-1;
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

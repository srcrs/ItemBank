import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Handle {
	public int flag=0;
	public String str="";
	public String [] limit = {"9","99","999","9999","99999"};
	public static String out="";
	Pattern pattern = null;
	Matcher matcher = null;
	ArrayList<String> whole = new ArrayList<String>();//每一道题
	public static ArrayList<String> classname = new ArrayList<String>();//每一种题型类的名字
	public Handle(String s) {
		// TODO Auto-generated constructor stub
		this.str = s;
		this.Init();
	}
	/*
	 * 增加题型的类名称
	 */
	public static void Increase(String s) {
		classname.add(s);
	}
	/*
	 * 初始化
	 */
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
		String regex = "((\\d{1,}|[一二三])[.|、|．]{1}\\D)";
		pattern = Pattern.compile(regex);
	    matcher = pattern.matcher(str);
		int index=-1;
		int end=-1;
		while(matcher.find()) {
			String s = matcher.group();
			end=matcher.end()-2;//代表点的位置
			if(s.charAt(0)=='一'||s.charAt(0)=='二'||s.charAt(0)=='三') {
				flag=0;
			}
			else {
					if(str.substring(end-flag-(s.length()-2), end).compareTo(limit[flag])>0) {
						flag++;
					}
				
			}
			
			if(index!=-1) {
				whole.add(str.substring(index, end-flag-(s.length()-2)));
			}
			index = end-flag-(s.length()-2);//解决第一道题不是个位数只取一位问题
		}
		end = str.length();
		whole.add(str.substring(index,end));
	}
	public void merge() {
//		int num=0;
		for (String sqw : whole) {
			if(sqw.charAt(0)=='一'||sqw.charAt(0)=='二'||sqw.charAt(0)=='三') {
				out += "\n## " + sqw + "\n";
			}
//			else {
//				num++;
//			}
//			if(num%10==1) {
//				if((num+9) < whole.size()) {
//					out += "\n### "+num+"-"+(num+9)+"\n";
//				}
//				else {
//					out += "\n### "+num+"-"+whole.size()+"\n";
//				}
//			}
			for(String name : classname) {
				try {
					Object obj = Class.forName(name).newInstance();
					Class<? extends Object> clazz = obj.getClass();//不知道为什么加上这个就没有警告了
					Method md = clazz.getMethod("Rule", String.class);
					if("true".equals(md.invoke(obj, sqw))) {
						Method m = clazz.getMethod("run", String.class);
						out += m.invoke(obj,sqw);
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
	}
}

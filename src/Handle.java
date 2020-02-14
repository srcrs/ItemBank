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
		String regex = "(\\d{1,}[.|、|．]{1}\\D)";
		//System.out.println(str);
		pattern = Pattern.compile(regex);
	    matcher = pattern.matcher(str);
		int index=-1;
		int end=-1;
		while(matcher.find()) {
			end=matcher.end()-2;//代表点的位置
	//		System.out.println(index+"----"+end);
			if(str.substring(end-flag-1, end).compareTo(limit[flag])>=0) {
				flag++;
			}
			if(index!=-1) {
				whole.add(str.substring(index, end-flag-1));
			}
			index = end-flag-(matcher.group().length()-2);//解决第一道题不是个位数只取一位问题
		}
		end = str.length();
		whole.add(str.substring(index,end));
	}
	public void merge() {
		for (String sqw : whole) {
			//out += Classification.Function(sqw);
			for(String name : classname) {
				try {
					Object obj = Class.forName(name).newInstance();
					Class clazz = obj.getClass();
					Method md = clazz.getMethod("rule", String.class);
					if("true".equals(md.invoke(obj, sqw))) {
						Method m = clazz.getMethod("run", String.class);
						out += m.invoke(obj,sqw);
						//System.out.println(m.invoke(obj,sqw));
					}
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	}
}

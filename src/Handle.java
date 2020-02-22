import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Handle {
	public int flag=0;
	public String str="";
	public String [] limit = {"9","99","999","9999","99999"};
	public String [] head = {"一","二","三","四","五","六","七","八","九","十"};
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
		try {
			flag = 0;
			String regex = "((\\d{1,}|[一二三四])[.|、|．]{1}\\D)";
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
		} catch (Exception e){
			System.out.println("检查文本的格式！！！\n");
		}
	}
	public void merge() {
		@SuppressWarnings("unused")
		Method md=null;
		Method m=null;
		Object obj=null;
		int t=-1;
		for (String sqw : whole) {
			if((t=isok(sqw)) != -1) {
				out += "\n## " + sqw + "\n";
				for(String name : classname) {
					try {
						obj = Class.forName(name).newInstance();
						Class<? extends Object> clazz = obj.getClass();//不知道为什么加上这个就没有警告了
						md = clazz.getMethod("getType");
						if(sqw.substring(t+1).equals(md.invoke(obj, null))) {
							m = clazz.getMethod("run", String.class);
							break;
						}
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			}
			else {
				try {
					String cache="";
					if("".equals(cache=(String) m.invoke(obj,sqw))) {
						System.out.println("有点小毛病！！！----"+sqw+"\n");
					}
					else {
						out += cache;
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
	}
	public int isok(String s) {
		int t=0;
		boolean flag = false;
		for(int i=0;i<10;i++) {
			if(head[i].equals(s.substring(t, t+1))) {
				i=0;
				t++;
				flag = true;
			}
		}
		if(flag) {
			return t;
		}
		else {
			return -1;
		}
	}
}

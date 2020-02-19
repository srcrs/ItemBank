import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

public class Connect {
	String file=null;
	BufferedReader reader = null;
	public Connect(String file) {
		this.file = file;
	}
	public void con() {
		try {
			reader = new BufferedReader(new FileReader(new File(file)));
			String s = "";
			String str = "";
			while((str=reader.readLine())!=null) {
				s += str;
			}
			reader.close();
			new Handle(s);
		} catch (Exception e) {
			System.out.println("没有找到文本文件！！！\n");
		}
	}
}

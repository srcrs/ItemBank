import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;

public class Print {
	String file=null;
	public Print(String file) {
		this.file = file;
		this.file = Remove(file) + "out.txt";
		
	}
	public String Remove(String file) {
		int in=-1;
		for(int i=file.length()-1;i>=0;i--) {
			if(file.charAt(i)=='\\'||file.charAt(i)=='\\') {
				in = i;
				break;
			}
		}
		return file.substring(0, in+1);
	}
	public void out() {
		if(!"".equals(Handle.out)) {
			try {
				BufferedWriter writer = new BufferedWriter(new FileWriter(new File(file)));
				writer.append(Handle.out);
				writer.flush();
				writer.close();
				System.out.println("写入成功！！");
				System.out.println("输出位置:"+file+"\n");
			} catch (Exception e) {
				System.out.println("写入失败");
			}
		}
	}
}

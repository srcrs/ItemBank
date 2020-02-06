import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;

public class Print {
	String file=null;
	public Print(String file) {
		// TODO Auto-generated constructor stub
		this.file = file;
	}
	public void out() {
		try {
			BufferedWriter writer = new BufferedWriter(new FileWriter(new File(file)));
			writer.append(Handle.out);
			writer.flush();
			writer.close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			System.out.println("写入失败");
		}
	}
}

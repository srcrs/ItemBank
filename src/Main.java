
public class Main {
	public static void main(String[] args) {
		Handle.Increase("Choice");
		Handle.Increase("Judgement");
		Handle.Increase("MoreChoice");
		new Connect("C:\\Users\\srcrs\\Desktop\\in.txt").con();//输入文件的路径
		new Print("C:\\Users\\srcrs\\Desktop\\out.txt").out();//输出文件的路径
	}
}

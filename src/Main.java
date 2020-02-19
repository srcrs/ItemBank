import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		Handle.Increase("Choice");
		Handle.Increase("Judgement");
		Handle.Increase("MoreChoice");
		while(true) {
			Handle.out = "";
			System.out.println("请输入源文件路径：");
			@SuppressWarnings("resource")
			Scanner sc = new Scanner(System.in);
			String file = sc.next();
			new Connect(file).con();//输入文件的路径
			new Print(file).out();//输出文件的路径
		}
	}
}

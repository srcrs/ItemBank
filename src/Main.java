import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		Handle.Increase("Choice");
		Handle.Increase("Judgement");
		Handle.Increase("MoreChoice");
		Handle.Increase("Fill");
		Handle.Increase("New");
		System.out.println("仓库地址：https://github.com/srcrs/ItemBank\n说明：");
		System.out.println("1. 现在只支持单选题，多选题，判断题和填空题。");
		System.out.println("2. 必须包含大标题，否则将无法运行。关于大标题写法可参考仓库地址。");
		System.out.println("3. 答案必须在括号中。");
		System.out.println("4. 题号不可缺少(一般下载的题都包括题号，应该不是大问题)。");
		System.out.println("5. 若对格式还有疑问请参考仓库地址，有例子。\n");
		System.out.println("-------------------------------------------------------------------------------------");
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

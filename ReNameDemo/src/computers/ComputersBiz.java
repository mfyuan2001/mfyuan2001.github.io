package computers;

import java.util.ArrayList;
import java.util.Arrays;

public class ComputersBiz {
	public static double calculate(String lambae) {
		if (lambae.startsWith("-")) {
			lambae = "0"+lambae;
		}
		// 利用正则表达式 来得到除了数字跟.之外的所有数
		String[] number = lambae.split("[^0-9.]");
		// 保留结果
		double temp = 0;
		// 再字符串数组转换成List方便得到跟跟删除
		ArrayList<String> al = new ArrayList<String>(Arrays.asList(number));
		System.out.println("========================");
		for (String string : al) {
			System.out.println(string);
		}
		

		// 存放运算符到StringBuilder中(添加删除快)
		StringBuilder operator = new StringBuilder();
		for (int i = 0; i < lambae.length(); i++) {
			if ((lambae.charAt(i) < '0' || lambae.charAt(i) > '9') && lambae.charAt(i) != '.') {
				operator.append(lambae.charAt(i));
			}
		}

		
		// 遍历运算符数组
		// 先乘除后加减
		for (int i = 0; i < operator.length(); i++) {
			if (operator.charAt(i) == '*' || operator.charAt(i) == '/'|| operator.charAt(i) == '%') {
				switch (operator.charAt(i)) {
				// 转换成double再进行运算运算
				case '*':
					temp = Double.parseDouble(al.get(i)) * Double.parseDouble(al.get(i + 1));
					break;
				case '/':
					temp = Double.parseDouble(al.get(i)) / Double.parseDouble(al.get(i + 1));
					break;
				case '%':
					temp = (int)Double.parseDouble(al.get(i)) % (int)Double.parseDouble(al.get(i + 1));
					break;
				default:
					break;
				}
				// !!!!!!!!
				/**
				 * 1 * 2 * 3 value 1 2 3 key 0 1 2 如果 1 * 2运算了 哪就要去掉 1 跟 2了 也就是去掉 key 0 跟 1
				 * 因为是集合 删掉会自动补齐 先删掉对应i 的小标 i and i + 1 这样因为是list的缘故所以 i删了之后i+1 就变成了 i
				 */
				al.remove(i);
				al.remove(i);
				// 然后再把得数放到运算结果哪个位置
				al.add(i, temp + "");
				
				System.out.println(operator.toString());
				System.out.println("========================");
				// 再去删除掉因为删掉了 所以又需要从第0个坐标开始运算
				operator.deleteCharAt(i);
				i--;
			} else {
				continue;
			}
			
			for (String string : al) {
				System.out.println(string);
			}
			
		}
		for (int i = 0; i < operator.length(); i++) {
			
			if (operator.charAt(i) == '+' || operator.charAt(i) == '-') {
				switch (operator.charAt(i)) {
				case '+':
					temp = Double.parseDouble(al.get(i)) + Double.parseDouble(al.get(i + 1));
					break;
				case '-':
					temp = Double.parseDouble(al.get(i)) - Double.parseDouble(al.get(i + 1));
					break;
				default:
					break;
				}
				al.remove(i);
				al.remove(i);
				al.add(i, temp + "");
				
				System.out.println(operator.toString());
				System.out.println("========================");
				operator.deleteCharAt(i);
				i--;
			} else {
				continue;
			}
			
			for (String string : al) {
				System.out.println(string);
			}
			
		}
		return temp;
	}

	public static boolean isin_Start_(String str) {
		return str.startsWith("-");
	}

	public static void main(String[] args) {
		double size = ComputersBiz.calculate("-16*2*20*30-1241-2+24*2");
	}
}

package Puzzle;

import java.util.Random;

import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

/**
 * 
 * @author yuanmengfan 业务类 完成程序所需的方法
 */
public class PuzzleBiz {
	/**
	 * 
	 */
	public void randomValuetion() {
		Random random = new Random();
		// 用来得到图片名以为我的图片名是1~9.jpg 因为要对字符串进行删除所有使用StringBuffer
		StringBuffer buffer = new StringBuffer(new String("123456789"));
		// 存放得到1~9随机打乱的顺序
		char[] array = new char[9];
		int length = buffer.length(); // buffer的当前长度
		// 循环截取buffer的元素
		for (int i = 0; i < length; i++) {
			int index = random.nextInt(length - i); // 使用Random类来生成一个随机数是 0 ~ length - i 之间的数
			array[i] = buffer.charAt(index); // 把生成的随机数index 作为下标取出buffer对应的字符
			buffer.deleteCharAt(index); // 然后再去删除已经存放到数组里的元素 避免下次遍历再次得到这个元素
		}
		/**
		 * 拼图如果是随机图片位置的话 每次都只会有50%的几率成功拼好
		 * 逆序数：即在一个数列中，按照从小到大的顺序排列则是顺序(0,1),(0,2)，如果有两个数违反了这一规则，
		 * 即左边的数大于右边的数，那么这两个数就是一个逆序(1,0)(2,1) 只要逆序数是偶数的话那么拼图就可以被拼好
		 */
		if (WinSize(array) % 2 == 0) {
			/**
			 * 如果逆序数是偶数的话那么 Dates.list.get(i)来获取里面的值 就给按钮加上图片跟id属性
			 */
			for (int i = 0; i < array.length; i++) {
				Dates.list.get(i).getjButton().setIcon(new ImageIcon("images/" + array[i] + ".png"));
				Dates.list.get(i).setId(array[i] - '0');
			}
		} else {
			// 否则就重新执行此方法让他执行到可以逆序数是偶数的时候再进行给按钮加上图片
			randomValuetion();
		}
	}

	/**
	 * 交换图片的位置
	 * 
	 * @param click 是有点击的哪个按钮
	 * @param index 图片是空白的那个按钮
	 */
	public void TempJButton(int click, int index) {
		/**
		 * 如果你点击图片按钮图片不是空白图片的话 那么就用temp来存放当前点击当前你点击图片按钮里的id属性 用来作业后面的 交换
		 */
		if (Dates.list.get(index).getId() == 9) {
			int temp = Dates.list.get(click).getId();
			// 把点击图片按钮的id值改变成空白图片的id值
			Dates.list.get(click).setId(Dates.list.get(index).getId());
			// 把空白图片的id值改成点击图片的按钮的id值
			Dates.list.get(index).setId(temp);
			// 直接把点击图片按钮的图片设置成空白图片
			Dates.list.get(click).getjButton().setIcon(new ImageIcon("images/9.png"));
			// 然后把空白图片按钮的图片设置成点击图片按钮的图片
			Dates.list.get(index).getjButton().setIcon(new ImageIcon("images/" + temp + ".png"));
			// 然后把当前这一组下标值存放到al用ActionandEmpty来存放
			Dates.al.add(new ActionandEmpty(click, index));
		}
	}

	/**
	 * 只是在TempJButton就只剩没有添加到重置的al里去
	 * 
	 * @param i
	 * @param index
	 */
	public void RecallJButton(int i, int index) {
		int temp = Dates.list.get(i).getId();
		Dates.list.get(i).setId(Dates.list.get(index).getId());
		Dates.list.get(index).setId(temp);
		Dates.list.get(i).getjButton().setIcon(new ImageIcon("images/9.png"));
		Dates.list.get(index).getjButton().setIcon(new ImageIcon("images/" + temp + ".png"));
	}

	/**
	 * 查找空白图片 遍历一下list集合 如果id属性是9那么就是空白图片 就然后返回当前的下标
	 * 
	 * @return 空白图片的下标
	 */
	public int Findempty() {
		int index = -1;
		for (int i = 0; i < 9; i++) {
			if (Dates.list.get(i).getId() == 9) {
				index = i;
				break;
			}
		}
		return index;
	}

	/**
	 * 因为list集合中存放的id属性是1~9 然而按钮的下标是0~8 所以遍历集合如果每一次的id-1 = i （下标时） 那么就复原成功
	 * 
	 * @return
	 */
	public boolean isWin() {
		boolean flag = true;
		for (int i = 0; i < 9; i++) {
			// 如果下标跟id-1的值不一样的话那么就是没有复原成功就直接返回false
			if (Dates.list.get(i).getId() - 1 != i) {
				flag = false;
				break;
			}
		}
		return flag;
	}

	/**
	 * 计时 返回格式化之后的字符串
	 * 
	 * @return
	 */
	public String RTiem() {
		int h = Dates.time / 3600; // 小时
		int f = Dates.time / 60 % 60; // 分
		int s = Dates.time % 60; // 秒
		return String.format("%02d:%02d:%02d", h, f, s);
	}

	/**
	 * 查找字符数组中的逆序数
	 * 
	 * @param str 就是当前随机存放图片的对应位置
	 * @return 返回逆序的数量
	 */
	public int WinSize(char[] str) {
		// 逆序数的数量
		int size = 0;
		// 先得到空白图片的位置空白图片不进行逆序运算
		int index = new String(str).indexOf("9");
		// 把每一个字符取出来跟后面的每一位进行判断大小如果大则是一个逆序数
		for (int i = 0; i < str.length - 1; i++) {
			if (i == index) {
				continue;
			}
			for (int j = i + 1; j < str.length; j++) {
				if (str[i] > str[j]) {
					size++;
				}
			}
		}
		return size;
	}

	/**
	 * 结束时提醒已经复原成功 得到RTime的当前时间然后用split(":")分割字符串然后得到时分秒 再转换成int型添加的time中去
	 */
	public void EndTime() {
		String[] str = RTiem().split(":");
		StringBuffer time = new StringBuffer(new String("恭喜复原成功！\n"));
		time.append(Integer.parseInt(str[0]) + "时:");
		time.append(Integer.parseInt(str[1]) + "分:");
		time.append(Integer.parseInt(str[2]) + "秒!");
		JOptionPane.showMessageDialog(null, time.toString());
	}
}

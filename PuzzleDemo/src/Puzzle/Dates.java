package Puzzle;
import java.util.ArrayList;
import java.util.List;

/**
 * 这是仓库类
 * 用来存在整个程序所需要用到的数据
 * @author yuanmengfan
 */
public class Dates {
	/**
	 * list 放的是创建的一个jButtons类型的元素 
	 * 用来存放按钮跟图片的位置
	 * 最后用来判断是否拼好
	 */
	public static List<JButtons> list = new ArrayList<JButtons>();
	/**
	 * al 放的是创建的一个ActionandEmpty类的元素 
	 * 用来放置我每一次点击时空白图片跟我点击图片的位置
	 */
	public static List<ActionandEmpty> al = new  ArrayList<ActionandEmpty>();
	
	/**
	 * 这就是一个时间 
	 * 记录完成用时多少
	 */
	public static int time = 0;

}

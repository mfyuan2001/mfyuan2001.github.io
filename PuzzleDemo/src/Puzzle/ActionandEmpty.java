package Puzzle;

/**
 * 存放一组点击时空白图片跟点击图片的位置
 * 方便恢复上一步操作
 * @author yuanmengfan
 *
 */
public class ActionandEmpty {
	/**
	 * action  点击图片按钮的 下标
	 * empty   空白图片按钮的下标
	 */
	private int action;	 	
	private int empty;
	public int getAction() {
		return action;
	}
	public void setAction(int action) {
		this.action = action;
	}
	public int getEmpty() {
		return empty;
	}
	public ActionandEmpty(int action, int empty) {
		this.action = action;
		this.empty = empty;
	}
	public void setEmpty(int empty) {
		this.empty = empty;
	}
	
}

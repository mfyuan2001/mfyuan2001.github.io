package Puzzle;
import javax.swing.JButton;
/**
 * @author yuanmengfan
 *	类似给JButton加了一个id属性
 *	方便得到按钮的位置
 */
public class JButtons {
	private JButton jButton = null;
	private int id;

	public JButtons(JButton jButton) {
		super();
		this.jButton = jButton;
	}

	public JButtons(JButton jButton, int id) {
		super();
		this.jButton = jButton;
		this.id = id;
	}

	public JButton getjButton() {
		return jButton;
	}

	public void setjButton(JButton jButton) {
		this.jButton = jButton;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
}

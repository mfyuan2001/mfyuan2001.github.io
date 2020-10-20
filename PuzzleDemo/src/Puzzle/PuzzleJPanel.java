package Puzzle;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;

/**
 * 界面的实现类
 * @author yuanmengfan
 *
 */
public class PuzzleJPanel extends JFrame {
	/**
	 * 有界面的业务类利用构造方法来实例化这个
	 */
	private PuzzleBiz puzzleBiz = null;
	private JPanel contentPane;
	int buttonindex = -1;

	/**
	 * Create the frame.
	 */
	public PuzzleJPanel() {
		puzzleBiz = new PuzzleBiz();
		// 得到屏幕的分辨率
//		int width = Toolkit.getDefaultToolkit().getScreenSize().width;
//		int height = Toolkit.getDefaultToolkit().getScreenSize().height;
		setTitle("拼图小游戏");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//		setBounds((width - 450) / 2, (height - 570) / 2, 450, 570);
		setSize(450, 570);
		setLocationRelativeTo(null);
		// 窗口大小不能被改变 把窗口的最大大小跟最小大小设置成一个值
//		setMinimumSize(this.getSize());
//		setMaximumSize(this.getSize());
		setResizable(false);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		// 图片的按钮的1~9
		JButton button1 = new JButton();
		button1.setBounds(0, 95, 150, 150);
		contentPane.add(button1);

		JButton button2 = new JButton();
		button2.setBounds(150, 95, 150, 150);
		contentPane.add(button2);

		JButton button3 = new JButton();
		button3.setBounds(300, 95, 150, 150);
		contentPane.add(button3);

		JButton button4 = new JButton();
		button4.setBounds(0, 245, 150, 150);
		contentPane.add(button4);

		JButton button5 = new JButton();
		button5.setBounds(150, 245, 150, 150);
		contentPane.add(button5);

		JButton button6 = new JButton();
		button6.setBounds(300, 245, 150, 150);
		contentPane.add(button6);

		JButton button7 = new JButton();
		button7.setBounds(0, 395, 150, 150);
		contentPane.add(button7);

		JButton button8 = new JButton();
		button8.setBounds(150, 395, 150, 150);
		contentPane.add(button8);

		JButton button9 = new JButton();
		button9.setBounds(300, 395, 150, 150);
		contentPane.add(button9);

		// 把9个按钮依次以JButtons的类型添加到list中去
		Dates.list.add(new JButtons(button1));
		Dates.list.add(new JButtons(button2));
		Dates.list.add(new JButtons(button3));
		Dates.list.add(new JButtons(button4));
		Dates.list.add(new JButtons(button5));
		Dates.list.add(new JButtons(button6));
		Dates.list.add(new JButtons(button7));
		Dates.list.add(new JButtons(button8));
		Dates.list.add(new JButtons(button9));

		// 上一步的按钮
		JButton btnNewButton_1 = new JButton("上一步");
		btnNewButton_1.setBounds(337, 66, 94, 24);
		contentPane.add(btnNewButton_1);
		// 鼠标点击事件
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// 如果al有数据则恢复最后一步的操作 把之前空白的位置（也就是现在有图片的位置）
				// 与现在有图片（之前是空白图片的位置）进行交换
				// 然后删除al里的最后一组数据 也就是删除刚刚恢复的哪一步
				if (Dates.al.size() > 0) {
					puzzleBiz.RecallJButton(Dates.al.get(Dates.al.size() - 1).getEmpty(),
							Dates.al.get(Dates.al.size() - 1).getAction());
					Dates.al.remove(Dates.al.size() - 1);
				}
			}
		});

		// 计时器
		JLabel lblNewLabel = new JLabel("00:00:00");
		lblNewLabel.setBounds(25, 39, 61, 16);
		contentPane.add(lblNewLabel);

		// 记时线程 每运行一次循环就sleep（1000）也就是休眠一秒
		// 然后时间加1
		Thread t2 = new Thread(new Runnable() {
			@Override
			public void run() {
				while (true) {
					lblNewLabel.setText(puzzleBiz.RTiem());
					try {
						Thread.sleep(1000);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					Dates.time++;
				}
			}
		});

		// 开始按钮
		JButton btnNewButton = new JButton("开始");
		btnNewButton.setFont(new Font("Lucida Grande", Font.PLAIN, 17));
		btnNewButton.setBounds(150, 21, 120, 50);
		contentPane.add(btnNewButton);
		//执行randomValuation方法使游戏开始
		//如果不是第一次点击开始 那么开始判断计时线程是否在运行
		//如果不是则运行计时线程 如果是则time 重新用0开始计时
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				puzzleBiz.randomValuetion();
				if (t2.isAlive() == false) {
					t2.start();
				} else {
					Dates.time = -1;
				}
			}
		});
		/**
		 * 下标为2的按钮
		 * 判断是否周边空白图片如果有的话就执行交换动作
		 * 每一次交换后判断是否已经拼好
		 */
		button1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				puzzleBiz.TempJButton(0, 1);
				puzzleBiz.TempJButton(0, 3);
				if (puzzleBiz.isWin()) {
					puzzleBiz.EndTime();
				}
			}
		});

		button2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				buttonindex = puzzleBiz.Findempty();
				puzzleBiz.TempJButton(1, 0);
				puzzleBiz.TempJButton(1, 2);
				puzzleBiz.TempJButton(1, 4);
				if (puzzleBiz.isWin()) {
					puzzleBiz.EndTime();
				}
			}
		});
		button3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				buttonindex = puzzleBiz.Findempty();
				puzzleBiz.TempJButton(2, 1);
				puzzleBiz.TempJButton(2, 5);
				if (puzzleBiz.isWin()) {
					puzzleBiz.EndTime();
				}
			}
		});

		button4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				buttonindex = puzzleBiz.Findempty();
				puzzleBiz.TempJButton(3, 0);
				puzzleBiz.TempJButton(3, 4);
				puzzleBiz.TempJButton(3, 6);
				if (puzzleBiz.isWin()) {
					puzzleBiz.EndTime();
				}
			}
		});
		button5.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				buttonindex = puzzleBiz.Findempty();
				puzzleBiz.TempJButton(4, 1);
				puzzleBiz.TempJButton(4, 3);
				puzzleBiz.TempJButton(4, 5);
				puzzleBiz.TempJButton(4, 7);
				if (puzzleBiz.isWin()) {
					puzzleBiz.EndTime();
				}
			}
		});
		button6.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				buttonindex = puzzleBiz.Findempty();
				puzzleBiz.TempJButton(5, 2);
				puzzleBiz.TempJButton(5, 4);
				puzzleBiz.TempJButton(5, 8);
				if (puzzleBiz.isWin()) {
					puzzleBiz.EndTime();
				}
			}
		});
		button7.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				buttonindex = puzzleBiz.Findempty();
				puzzleBiz.TempJButton(6, 3);
				puzzleBiz.TempJButton(6, 7);
				if (puzzleBiz.isWin()) {
					puzzleBiz.EndTime();
				}
			}
		});
		button8.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				buttonindex = puzzleBiz.Findempty();
				puzzleBiz.TempJButton(7, 6);
				puzzleBiz.TempJButton(7, 8);
				puzzleBiz.TempJButton(7, 4);
				if (puzzleBiz.isWin()) {
					puzzleBiz.EndTime();
				}
			}
		});

		button9.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				buttonindex = puzzleBiz.Findempty();
				puzzleBiz.TempJButton(8, 7);
				puzzleBiz.TempJButton(8, 5);
				if (puzzleBiz.isWin()) {
					puzzleBiz.EndTime();
				}
			}
		});
	}

}

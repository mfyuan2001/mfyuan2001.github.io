package computers;

import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * @version 1.0
 * @author yuanmengfan
 *
 */
public class ComputersViewVersion2 extends JFrame implements ActionListener {
	/**
	 * stringBuffer 便于操作lable中的text属性
	 */
	StringBuffer stringBuffer = new StringBuffer(new String("0"));
	private JPanel contentPane;
	private JButton button_1, button_2, button_3, button_4, button_5, button_6, button_7, button_8, button_9, button_0;
	private JButton result, add, delete, ride, divide, delivery, empty, negation, dian;
	private JLabel resultView;

	/**
	 * Create the frame.
	 */
	public ComputersViewVersion2() {
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 310, 481);
		this.setTitle("计算机");
		// 无法缩放
		this.setResizable(false);
		// 设置图标
		this.setIconImage(Toolkit.getDefaultToolkit().getImage("images/1.png"));

		contentPane = new JPanel();
		contentPane.setBackground(new Color(0x5B5B5D));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		/**
		 * 显示结果跟 式子的组件
		 */
		resultView = new JLabel(stringBuffer.toString());
		resultView.setForeground(new Color(0xfaa755));
		resultView.setFont(new Font("Lucida Grande", Font.PLAIN, 30));
		
		//文字水平靠右
		resultView.setHorizontalAlignment(SwingConstants.RIGHT);
		//文件垂直靠下
		resultView.setVerticalAlignment(SwingConstants.BOTTOM);
		//也就是左下
		resultView.setBounds(0, 0, 310, 80);
		contentPane.add(resultView);
		
		button_0 = new JButton("0");
		button_0.setFont(new Font("Lucida Grande", Font.PLAIN, 30));
		button_0.setBounds(-2, 380, 157, 80);
		button_0.addActionListener(this);
		contentPane.add(button_0);

		button_1 = new JButton("1");
		button_1.setFont(new Font("Lucida Grande", Font.PLAIN, 30));
		button_1.setBounds(-2, 156, 80, 80);
		button_1.addActionListener(this);
		contentPane.add(button_1);

		button_2 = new JButton("2");
		button_2.setFont(new Font("Lucida Grande", Font.PLAIN, 30));
		button_2.setBounds(74, 156, 80, 80);
		button_2.addActionListener(this);
		contentPane.add(button_2);

		button_3 = new JButton("3");
		button_3.setFont(new Font("Lucida Grande", Font.PLAIN, 30));
		button_3.setBounds(152, 156, 80, 80);
		button_3.addActionListener(this);
		contentPane.add(button_3);

		button_4 = new JButton("4");
		button_4.setFont(new Font("Lucida Grande", Font.PLAIN, 30));
		button_4.setBounds(-2, 232, 80, 80);
		button_4.addActionListener(this);
		contentPane.add(button_4);

		button_5 = new JButton("5");
		button_5.setFont(new Font("Lucida Grande", Font.PLAIN, 30));
		button_5.setBounds(74, 232, 80, 80);
		button_5.addActionListener(this);
		contentPane.add(button_5);

		button_6 = new JButton("6");
		button_6.setFont(new Font("Lucida Grande", Font.PLAIN, 30));
		button_6.setBounds(152, 232, 80, 80);
		button_6.addActionListener(this);
		contentPane.add(button_6);

		button_7 = new JButton("7");
		button_7.setFont(new Font("Lucida Grande", Font.PLAIN, 30));
		button_7.setBounds(-2, 306, 80, 80);
		button_7.addActionListener(this);
		contentPane.add(button_7);

		button_8 = new JButton("8");
		button_8.setFont(new Font("Lucida Grande", Font.PLAIN, 30));
		button_8.setBounds(74, 306, 80, 80);
		button_8.addActionListener(this);
		contentPane.add(button_8);

		button_9 = new JButton("9");
		button_9.setFont(new Font("Lucida Grande", Font.PLAIN, 30));
		button_9.setBounds(152, 306, 80, 80);
		button_9.addActionListener(this);
		contentPane.add(button_9);

		dian = new JButton(".");
		dian.setFont(new Font("Lucida Grande", Font.PLAIN, 30));
		dian.setBounds(152, 380, 80, 80);
		dian.addActionListener(this);
		contentPane.add(dian);
		
		add = new JButton("+");
		add.setFont(new Font("Lucida Grande", Font.PLAIN, 30));
		add.setBounds(230, 306, 80, 80);
		add.setOpaque(true);
		add.setBackground(new Color(0xf3715c));
		add.addActionListener(this);
		contentPane.add(add);

		delete = new JButton("-");
		delete.setFont(new Font("Lucida Grande", Font.PLAIN, 30));
		delete.setBounds(230, 232, 80, 80);
		delete.setOpaque(true);
		delete.setBackground(new Color(0xf3715c));
		delete.addActionListener(this);
		contentPane.add(delete);

		ride = new JButton("*");
		ride.setFont(new Font("Lucida Grande", Font.PLAIN, 30));
		ride.setBounds(230, 156, 80, 80);
		ride.setOpaque(true);
		ride.setBackground(new Color(0xf3715c));
		ride.addActionListener(this);
		contentPane.add(ride);

		divide = new JButton("÷");
		divide.setOpaque(true);
		divide.setBackground(new Color(0xf3715c));
		divide.setFont(new Font("Lucida Grande", Font.PLAIN, 30));
		divide.setBounds(230, 80, 80, 80);
		divide.addActionListener(this);
		contentPane.add(divide);
		
		delivery = new JButton("%");
		delivery.setFont(new Font("Lucida Grande", Font.PLAIN, 30));
		delivery.setBounds(152, 80, 80, 80);
		delivery.addActionListener(this);
		contentPane.add(delivery);
		/**
		 * 清空label里的所有
		 */
		empty = new JButton("A/C");
		empty.setFont(new Font("Lucida Grande", Font.PLAIN, 30));
		empty.setBounds(-2, 80, 80, 80);
		empty.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				stringBuffer = new StringBuffer("0");
				resultView.setText(stringBuffer.toString());
			}
		});
		contentPane.add(empty);
		/**
		 * 如果当的值为不为0的正数
		 * 则向当前数值添加  -
		 * 
		 * 如果为0则 
		 * 不操作
		 * 
		 * 如果 为负数
		 * 则修改为正数
		 * 也就是 去除-号
		 */
		negation = new JButton("+/-");
		negation.setFont(new Font("Lucida Grande", Font.PLAIN, 30));
		negation.setBounds(74, 80, 80, 80);
		negation.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (ComputersBiz.isin_Start_(stringBuffer.toString())) {
					stringBuffer.deleteCharAt(0);
				} else if (!stringBuffer.toString().startsWith("0")) {
					stringBuffer.insert(0, "-");
				}
				resultView.setText(stringBuffer.toString());
			}
		});
		contentPane.add(negation);
		
		/**
		 * 运算字符串的结果 最后显示到lable上
		 */
		result = new JButton("=");
		result.setFont(new Font("Lucida Grande", Font.PLAIN, 30));
		result.setBounds(230, 380, 80, 80);
		result.setBackground(new Color(0xf3715c));
		result.setOpaque(true);
		result.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					/**
					 * 调用计算方法 先乘除后加减的规律来计算字符串
					 * 然后清空字符串
					 * 再把结果显示到label上
					 */
					double result = ComputersBiz.calculate(stringBuffer.toString());
					stringBuffer.delete(0, stringBuffer.length());
					stringBuffer.append(result);
					resultView.setText(stringBuffer.toString());
				} catch (Exception ex) {
					//如果报错，不退出程序 而是清空字符串 也就是给0  
					System.err.println(ex);
					stringBuffer = new StringBuffer("0");
					resultView.setText(stringBuffer.toString());
				}
			}
		});
		contentPane.add(result);
	}

	/**
	 * 需求 如果resultView的字符串只有一个0那么接下来 输入的是数字0~9的话就会显示相应数字 如果输入的是点就会在0后面加一个点，而不是直接替换掉0
	 * 如果输入的是加减哪个也是如此
	 * 
	 * @param number
	 */
	private void addStringBuffer(char c) {
		if (resultView.getText().equals("0") && Character.isDigit(c)) {
			stringBuffer = new StringBuffer(c + "");
		} else {
			stringBuffer.append(c);
		}
		resultView.setText(stringBuffer.toString());
	}

	
	/**
	 * 自己实现的实现的触发事件
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		//e.getSource  可以返回触发时间段 jbutton
		JButton button = (JButton) e.getSource();
		addStringBuffer(button.getText().charAt(0));
	}
}

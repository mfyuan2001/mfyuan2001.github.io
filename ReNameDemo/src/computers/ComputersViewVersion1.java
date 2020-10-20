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
public class ComputersViewVersion1 extends JFrame{
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
	public ComputersViewVersion1() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 310, 481);
		this.setTitle("计算机");
		//无法缩放
		this.setResizable(false);
		//设置图标
		this.setIconImage(Toolkit.getDefaultToolkit().getImage("images/1.png"));
		
		contentPane = new JPanel();
		contentPane.setBackground(new Color(0x5B5B5D));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		button_9 = new JButton("9");
		button_9.setFont(new Font("Lucida Grande", Font.PLAIN, 30));
		button_9.setBounds(152, 306, 80, 80);
		button_9.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				addStringBuffer(button_9.getText().charAt(0));
			}
		});
		contentPane.add(button_9);

		button_2 = new JButton("2");
		button_2.setFont(new Font("Lucida Grande", Font.PLAIN, 30));
		button_2.setBounds(74, 156, 80, 80);
		button_2.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				addStringBuffer(button_2.getText().charAt(0));
			}
		});
		contentPane.add(button_2);

		button_7 = new JButton("7");
		button_7.setFont(new Font("Lucida Grande", Font.PLAIN, 30));
		button_7.setBounds(-2, 306, 80, 80);
		button_7.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				addStringBuffer(button_7.getText().charAt(0));
			}
		});
		contentPane.add(button_7);

		button_4 = new JButton("4");
		button_4.setFont(new Font("Lucida Grande", Font.PLAIN, 30));
		button_4.setBounds(-2, 232, 80, 80);
		button_4.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				addStringBuffer(button_4.getText().charAt(0));
			}
		});
		contentPane.add(button_4);

		button_6 = new JButton("6");
		button_6.setFont(new Font("Lucida Grande", Font.PLAIN, 30));
		button_6.setBounds(152, 232, 80, 80);
		contentPane.add(button_6);
		button_6.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				addStringBuffer(button_6.getText().charAt(0));
			}
		});

		button_0 = new JButton("0");
		button_0.setFont(new Font("Lucida Grande", Font.PLAIN, 30));
		button_0.setBounds(-2, 380, 157, 80);
		button_0.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				addStringBuffer(button_0.getText().charAt(0));
			}
		});
		contentPane.add(button_0);

		button_8 = new JButton("8");
		button_8.setFont(new Font("Lucida Grande", Font.PLAIN, 30));
		button_8.setBounds(74, 306, 80, 80);
		button_8.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				addStringBuffer(button_8.getText().charAt(0));
			}
		});
		contentPane.add(button_8);

		dian = new JButton(".");
		dian.setFont(new Font("Lucida Grande", Font.PLAIN, 30));
		dian.setBounds(152, 380, 80, 80);
		dian.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				addStringBuffer(dian.getText().charAt(0));
			}
		});
		contentPane.add(dian);

		button_5 = new JButton("5");
		button_5.setFont(new Font("Lucida Grande", Font.PLAIN, 30));
		button_5.setBounds(74, 232, 80, 80);
		button_5.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				addStringBuffer(button_5.getText().charAt(0));
			}
		});
		contentPane.add(button_5);

		button_3 = new JButton("3");
		button_3.setFont(new Font("Lucida Grande", Font.PLAIN, 30));
		button_3.setBounds(152, 156, 80, 80);
		button_3.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				addStringBuffer(button_3.getText().charAt(0));
			}
		});
		contentPane.add(button_3);

		result = new JButton("=");
		result.setFont(new Font("Lucida Grande", Font.PLAIN, 30));
		result.setBounds(230, 380, 80, 80);
		result.setBackground(new Color(0xf3715c));
		result.setOpaque(true);
		result.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					double result = ComputersBiz.calculate(stringBuffer.toString());
					System.out.println(result);
					stringBuffer.delete(0, stringBuffer.length());
					stringBuffer.append(result);
					resultView.setText(stringBuffer.toString());
				}catch (Exception ex) {
					System.err.println(ex);
					stringBuffer = new StringBuffer("0");
					resultView.setText(stringBuffer.toString());
				}
			}
		});
		contentPane.add(result);

		add = new JButton("+");
		add.setFont(new Font("Lucida Grande", Font.PLAIN, 30));
		add.setBounds(230, 306, 80, 80);
		add.setOpaque(true);
		add.setBackground(new Color(0xf3715c));
		add.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				addStringBuffer(add.getText().charAt(0));
			}
		});
		contentPane.add(add);

		delete = new JButton("-");
		delete.setFont(new Font("Lucida Grande", Font.PLAIN, 30));
		delete.setBounds(230, 232, 80, 80);
		delete.setOpaque(true);
		delete.setBackground(new Color(0xf3715c));
		delete.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				addStringBuffer(delete.getText().charAt(0));
			}
		});
		contentPane.add(delete);

		ride = new JButton("*");
		ride.setFont(new Font("Lucida Grande", Font.PLAIN, 30));
		ride.setBounds(230, 156, 80, 80);
		ride.setOpaque(true);
		ride.setBackground(new Color(0xf3715c));
		ride.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				addStringBuffer(ride.getText().charAt(0));
			}
		});
		contentPane.add(ride);

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

		negation = new JButton("+/-");
		negation.setFont(new Font("Lucida Grande", Font.PLAIN, 30));
		negation.setBounds(74, 80, 80, 80);
		negation.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (ComputersBiz.isin_Start_(stringBuffer.toString())) {
					stringBuffer.deleteCharAt(0);
				}else if(!stringBuffer.toString().startsWith("0")){
					stringBuffer.insert(0, "-");
				}
				resultView.setText(stringBuffer.toString());
			}
		});
		contentPane.add(negation);

		delivery = new JButton("%");
		delivery.setFont(new Font("Lucida Grande", Font.PLAIN, 30));
		delivery.setBounds(152, 80, 80, 80);
		delivery.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				addStringBuffer(delivery.getText().charAt(0));
			}
		});
		contentPane.add(delivery);

		divide = new JButton("÷");
		divide.setOpaque(true);
		divide.setBackground(new Color(0xf3715c));
		divide.setFont(new Font("Lucida Grande", Font.PLAIN, 30));
		divide.setBounds(230, 80, 80, 80);
		divide.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				addStringBuffer(divide.getText().charAt(0));
			}
		});
		contentPane.add(divide);

		button_1 = new JButton("1");
		button_1.setFont(new Font("Lucida Grande", Font.PLAIN, 30));
		button_1.setBounds(-2, 156, 80, 80);
		button_1.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				addStringBuffer(button_1.getText().charAt(0));
			}
		});
		contentPane.add(button_1);

		resultView = new JLabel("0");
		resultView.setForeground(new Color(0xfaa755));
		resultView.setFont(new Font("Lucida Grande", Font.PLAIN, 30));
		resultView.setHorizontalAlignment(SwingConstants.RIGHT);
		resultView.setVerticalAlignment(SwingConstants.BOTTOM);
		resultView.setBounds(0, 0, 310, 80);
		contentPane.add(resultView);
	}

	/**
	 * 需求 如果resultView的字符串只有一个0那么接下来 输入的是数字0~9的话就会显示相应数字 如果输入的是点就会在0后面加一个点，而不是直接替换掉0
	 * 如果输入的是加减哪个也是如此
	 * 
	 * @param number
	 */
	private void addStringBuffer(char c) {
		if (resultView.getText().equals("0") && isNumber(c)) {
			stringBuffer = new StringBuffer(c + "");
		} else {
			stringBuffer.append(c);
		}
		resultView.setText(stringBuffer.toString());
	}

	private boolean isNumber(char c) {
		if (c >= '0' && c <= '9') {
			return true;
		}
		return false;
	}
}

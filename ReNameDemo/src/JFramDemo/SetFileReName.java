package JFramDemo;

import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Color;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JSpinner;
import javax.swing.JTable;
import java.awt.Font;
import javax.swing.JTextArea;
import javax.swing.JFileChooser;
import java.awt.event.ActionListener;
import java.io.File;
import java.awt.event.ActionEvent;
import javax.swing.table.DefaultTableModel;
/**
 * 单文件夹重命名
 * @author yuanmengfan
 *
 */
public class SetFileReName extends JFrame {
	public Object[][] objects;
	private JPanel contentPane;
	private JTable table;


	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					SetFileReName frame = new SetFileReName();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public SetFileReName() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 561, 649);
		contentPane = new JPanel();
		contentPane.setFont(new Font("微软雅黑 Light", Font.PLAIN, 16));
		contentPane.setBackground(Color.LIGHT_GRAY);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JTextArea panel = new JTextArea();
		panel.setBounds(108, 41, 329, 24);
		contentPane.add(panel);
		panel.setLayout(null);

		JLabel label = new JLabel("\u6587\u4EF6\u6279\u91CF\u91CD\u547D\u540D\u6A21\u5757\uFF1A");
		label.setFont(new Font("方正粗黑宋简体", Font.PLAIN, 18));
		label.setBounds(192, 10, 193, 21);
		contentPane.add(label);

		JLabel label_1 = new JLabel("\u6587\u4EF6\u8DEF\u7ECF\uFF1A");
		label_1.setFont(new Font("方正粗黑宋简体", Font.PLAIN, 16));
		label_1.setBounds(24, 41, 82, 24);
		contentPane.add(label_1);

		JButton button = new JButton("\u6D4F\u89C8");

		button.setFont(new Font("方正粗黑宋简体", Font.PLAIN, 16));
		button.setBounds(447, 30, 93, 46);
		contentPane.add(button);

		JSpinner spinner = new JSpinner();
		spinner.setBounds(108, 147, 60, 26);
		contentPane.add(spinner);

		JLabel label_2 = new JLabel(
				"\u4F7F\u7528#\u53EF\u4EE5\u6307\u5B9A\u6570\u5B57\u8BA1\u6570\u6240\u5360\u4F4D\u7F6E\uFF1A");
		label_2.setFont(new Font("方正粗黑宋简体", Font.PLAIN, 15));
		label_2.setBounds(148, 71, 237, 24);
		contentPane.add(label_2);

		JLabel label_3 = new JLabel("\u6A21\u677F\uFF1A");
		label_3.setFont(new Font("方正粗黑宋简体", Font.PLAIN, 15));
		label_3.setBounds(60, 105, 46, 24);
		contentPane.add(label_3);

		JTextArea panel_1 = new JTextArea();
		panel_1.setBounds(108, 105, 329, 24);
		contentPane.add(panel_1);

		JButton btnNewButton = new JButton("\u5F00\u59CB");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
			}
		});
		btnNewButton.setFont(new Font("方正粗黑宋简体", Font.PLAIN, 15));
		btnNewButton.setBounds(447, 135, 93, 46);
		contentPane.add(btnNewButton);

		JLabel lblNewLabel = new JLabel("\u5F00\u59CB\u4E8E\uFF1A");
		lblNewLabel.setFont(new Font("方正粗黑宋简体", Font.PLAIN, 15));
		lblNewLabel.setBounds(35, 141, 74, 34);
		contentPane.add(lblNewLabel);

		JLabel lblNewLabel_1 = new JLabel("\u6269\u5C55\u540D\uFF1A");
		lblNewLabel_1.setFont(new Font("方正粗黑宋简体", Font.PLAIN, 15));
		lblNewLabel_1.setBounds(216, 147, 69, 23);
		contentPane.add(lblNewLabel_1);

		JTextArea panel_2 = new JTextArea();
		panel_2.setBounds(274, 151, 163, 24);
		contentPane.add(panel_2);

		JLabel lblNewLabel_2 = new JLabel("\u65E7\u6587\u4EF6\u540D");
		lblNewLabel_2.setFont(new Font("方正粗黑宋简体", Font.PLAIN, 16));
		lblNewLabel_2.setBounds(108, 200, 74, 24);
		contentPane.add(lblNewLabel_2);

		JLabel lblNewLabel_3 = new JLabel("\u65B0\u6587\u4EF6\u540D");
		lblNewLabel_3.setFont(new Font("方正粗黑宋简体", Font.PLAIN, 16));
		lblNewLabel_3.setBounds(363, 200, 74, 24);
		contentPane.add(lblNewLabel_3);
		table = new JTable(15, 2);
		table.setFont(new Font("宋体", Font.PLAIN, 14));
		table.setBounds(60, 259, 414, 281);
		table.setRowHeight(28);
		contentPane.add(table);
		/**
		 * 这个地方应该只实现得到文件夹
		 */
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				/**
				 * 选择文件夹
				 * 利用JFileChooser
				 */
				JFileChooser jf = new JFileChooser("/Users/yuanmengfan/Desktop");
				jf.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
				jf.showOpenDialog(null);
				File mkdir = jf.getSelectedFile();
				
				//得到文件夹中的所有文件
				File[] files = mkdir.listFiles();
				
				//建立一个可存放原名称 和 修改后的名称的object二维数组
				objects = new Object[files.length][2];
				
				for (int i = 0; i <files.length; i++) {
					
					System.out.println(files[i].getName());
					objects[i][0] = new String(files[i].getName());
					
					//得到文件的绝对路径 和名字 然后得到名字在路径出现的位置
					int index = files[i].getPath().indexOf(files[i].getName());
					System.out.println(files[i].getPath());
					System.out.println(files[i].getName());
					System.out.println(index);
					
					//用StringBuffer来得到他的绝对路径
					StringBuffer path = new StringBuffer(new String(files[i].getPath()));
					System.out.println(path.length());
					
					//利用刚刚index来删掉之后的字符
					path.delete(index, path.length());
					//再拼接上重命名的名称
					path.append(i + ".jpg");
					
					//然后给到renameTo 来实现重命名
					files[i].renameTo(new File(path.toString()));
					//然后添加到objects的二维数组中
					objects[i][1] = new String((i + 1) + ".jpg");
				}
				table.setModel(new DefaultTableModel(objects, new String[] { "A", "B" }));
				panel.setText(mkdir.getPath());
			}
		});
	}
}

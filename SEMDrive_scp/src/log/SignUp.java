package log;
import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import java.awt.Color;
import javax.swing.Box;
import java.awt.Component;
import java.awt.Dimension;

import com.jgoodies.forms.layout.FormLayout;
import com.TestScp;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.RowSpec;
import com.jgoodies.forms.layout.FormSpecs;
 
public class SignUp extends JFrame implements ActionListener
{
	JButton signup = new JButton("Sign Up");
	JLabel  name = new JLabel("Username: ");
	JLabel password = new JLabel("Password: "); 
	JTextField JName = new JTextField(10); //明文账号输入
	JPasswordField JPassword = new JPasswordField(10); // 非明文密码输入；
	private final JButton mainwin = new JButton("Return");
	private final Component verticalStrut_1 = Box.createVerticalStrut(20);
	private final Component verticalStrut_3 = Box.createVerticalStrut(20);
	private final Component verticalStrut = Box.createVerticalStrut(20);
	private final Component verticalStrut_2 = Box.createVerticalStrut(20);
	private final Component horizontalStrut = Box.createHorizontalStrut(20);
	private final Component horizontalStrut_1 = Box.createHorizontalStrut(20);
	
	public SignUp() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		JPanel jp = new JPanel();
		getContentPane().add(jp,BorderLayout.CENTER);
		jp.setLayout(new FormLayout(new ColumnSpec[] {
				FormSpecs.RELATED_GAP_COLSPEC,
				FormSpecs.DEFAULT_COLSPEC,
				ColumnSpec.decode("130px"),
				ColumnSpec.decode("130px"),
				FormSpecs.RELATED_GAP_COLSPEC,
				FormSpecs.DEFAULT_COLSPEC,
				FormSpecs.RELATED_GAP_COLSPEC,
				FormSpecs.DEFAULT_COLSPEC,},
			new RowSpec[] {
				RowSpec.decode("30px"),
				RowSpec.decode("30px"),
				RowSpec.decode("30px"),
				RowSpec.decode("30px"),
				RowSpec.decode("30px"),}));
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		Dimension frameSize = jp.getSize();
		if (frameSize.height > screenSize.height)
		    frameSize.height = screenSize.height;       
		if (frameSize.width > screenSize.width)
		    frameSize.width = screenSize.width;       
		jp.setLocation((screenSize.width-frameSize.width)/2,(screenSize.height-frameSize.height)/2);
		
		jp.add(verticalStrut_1, "3, 1, fill, fill");
		
		jp.add(verticalStrut_3, "4, 1, fill, fill");
		
		name.setHorizontalAlignment(SwingConstants.RIGHT);  //设置该组件的对齐方式为向右对齐
		name.setFont(new Font("Arial Rounded MT Bold", Font.PLAIN, 15));
		
		jp.add(name, "3, 2, fill, fill");   //将内容加到面板jp上
		JName.setFont(new Font("Arial", Font.PLAIN, 15));
		jp.add(JName, "4, 2, fill, fill");
		password.setHorizontalAlignment(SwingConstants.RIGHT);
		password.setFont(new Font("Arial Rounded MT Bold", Font.PLAIN, 15));
		
		jp.add(password, "3, 3, fill, fill");
		JPassword.setFont(new Font("Arial", Font.PLAIN, 15));
		jp.add(JPassword, "4, 3, fill, fill");
		
		jp.add(horizontalStrut, "2, 4");
		
		jp.add(signup, "3, 4, fill, fill");
		signup.addActionListener(this); //登录按钮：增加事件监听
		signup.setFont(new Font("Arial Black", Font.PLAIN, 15));
		
		jp.add(mainwin, "4, 4, fill, fill");
		mainwin.addActionListener(this);	//返回主界面按钮：增加事件监听
		mainwin.setFont(new Font("Arial Black", Font.PLAIN, 15));
		
		jp.add(horizontalStrut_1, "6, 4");
		
		jp.add(verticalStrut, "3, 5, fill, fill");
		
		jp.add(verticalStrut_2, "4, 5, fill, fill");
		
		this.setTitle("Sign Up Window");
		this.setLocation(500,300);	//设置初始位置
		this.pack();  		//表示随着面板自动调整大小
		this.setVisible(true);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	public void actionPerformed(ActionEvent e)  // 对时间进行处理
	{
		if(e.getSource() == signup)	
		{
			Log test = new Log(DBManager.getConnection(), "users");
			Bean testuser = new Bean (JName.getText(), String.valueOf(JPassword.getPassword()));
	        if (test.addUser(testuser))
			{
				JOptionPane.showMessageDialog(null, "Successfully sign up!");
				//显示信息提示框
				String path = testuser.getPath();
				TestScp testscp = new TestScp();
				System.out.println(path);
				try {
					testscp.createDir(path);
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				//System.exit(0);
				this.dispose();
				Main window = new Main();
				window.frame.setVisible(true);
			}
			else 
			{
				JOptionPane.showMessageDialog(null, "Failed to sign up. Please try again.");
				//显示信息提示框
				JName.setText(""); 
				JPassword.setText("");
			}
		}
		else {
			this.dispose();
			Main window = new Main();
			window.frame.setVisible(true);
			
		}
	}
	
	public static void main(String[] args)
	{
		JFrame.setDefaultLookAndFeelDecorated(true);
		SignUp j1;
		j1 = new SignUp();
		j1.setLocationRelativeTo(null);
	}
}
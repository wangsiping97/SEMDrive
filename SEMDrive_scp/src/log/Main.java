package log;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JButton;
import java.awt.Font;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.BorderLayout;
import java.awt.Color;
import javax.swing.ImageIcon;

public class Main extends JFrame implements ActionListener{

	public JFrame frame;
	JButton btnLogin = new JButton("Login");
	JButton btnExit = new JButton("Exit");
	JButton btnSignUp = new JButton("Sign Up");

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Main window = new Main();
					window.frame.setVisible(true);
					
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Main() {
		
		frame = new JFrame();
		ImageIcon icon=new ImageIcon("./src/icon.gif");
		frame.setIconImage(icon.getImage());
		
		
		frame.getContentPane().setBackground(new Color(135, 206, 250));
		frame.setBounds(100, 100, 685, 470);
		frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);	

		
		btnLogin.addActionListener(this);
		btnLogin.setFont(new Font("Arial Black", Font.PLAIN, 15));
		btnLogin.setBounds(523, 122, 97, 28);
		frame.getContentPane().add(btnLogin);

		btnSignUp.addActionListener(this);
		btnSignUp.setFont(new Font("Arial Black", Font.PLAIN, 15));
		btnSignUp.setBounds(523, 186, 97, 28);
		frame.getContentPane().add(btnSignUp);
		
		btnExit.setFont(new Font("Arial Black", Font.PLAIN, 15));
		btnExit.setBounds(523, 250, 97, 28);
		frame.getContentPane().add(btnExit);
		btnExit.addActionListener(this);
		
		JLabel lblCopyright = new JLabel("Copyright: 2019 - Infinity");
		lblCopyright.setFont(new Font("SimSun-ExtB", Font.PLAIN, 13));
		lblCopyright.setBounds(454, 405, 192, 15);
		frame.getContentPane().add(lblCopyright);
		
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(new ImageIcon("./src/log/blue.jpg"));
		lblNewLabel.setBounds(0, 25, 495, 370);
		frame.getContentPane().add(lblNewLabel);
		frame.setTitle("Welcome!");
	}

	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == btnExit)
		{
			int i = JOptionPane.showConfirmDialog(null,"Are you sure you want to exit?", "Yes", JOptionPane.YES_NO_OPTION);
			// 显示选择对话框
			if(i == JOptionPane.YES_OPTION) {
			System.exit(0);
			}
		}
		
		else if(e.getSource() == btnSignUp)	
		{
			frame.dispose();
			new SignUp();
			
		}
		
		else if(e.getSource() == btnLogin){
			frame.dispose();
			new Login();		
		}
		else {}
		
	}
}

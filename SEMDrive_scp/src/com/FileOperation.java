package com;

import java.awt.EventQueue;

import javax.swing.JFrame;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.awt.event.ActionEvent;
import javax.swing.SwingConstants;

import ch.ethz.ssh2.SCPClient;
import log.DBManager;
import log.Log;
import log.Login;
import log.Main;
import log.SignUp;

public class FileOperation extends JFrame implements ActionListener{

	public JFrame frame;
	JButton btnLogout = new JButton("LogOut");
	JButton btnUpload = new JButton("UpLoad Local Files");
	JButton btnDownload = new JButton("View / Download");
	String remoteTargetDirectory;
	String localFile;
	
	
	/**
	 * Launch the application.
	 */
	/*public static void main(String[] args, String path) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FileOperation window = new FileOperation(path);
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});}*/


	/**
	 * Create the application.
	 */
	public FileOperation(String path) {
		this.remoteTargetDirectory = path;
		initialize();
		System.out.println(path);
	}
	
	/*public FileOperation() {
		initialize();
	}*/

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.getContentPane().setBackground(new Color(135, 206, 250));
		frame.getContentPane().setLayout(null);
		
		JLabel label = new JLabel("Copyright: 2019 - Infinity");
		label.setFont(new Font("SimSun-ExtB", Font.PLAIN, 13));
		label.setBounds(454, 405, 192, 15);
		frame.getContentPane().add(label);
		
		btnLogout.addActionListener(this);
		btnLogout.setFont(new Font("Arial Black", Font.PLAIN, 15));
		btnLogout.setBounds(304, 322, 93, 28);
		frame.getContentPane().add(btnLogout);
		
		btnUpload.addActionListener(this);
		btnUpload.setFont(new Font("Arial Black", Font.PLAIN, 22));
		btnUpload.setBounds(204, 148, 275, 53);
		frame.getContentPane().add(btnUpload);
		
		btnDownload.addActionListener(this);
		btnDownload.setFont(new Font("Arial Black", Font.PLAIN, 22));
		btnDownload.setBounds(204, 230, 275, 53);
		frame.getContentPane().add(btnDownload);
		
		JLabel lblPlease = new JLabel("Congrats! You've connected to the Server.");
		lblPlease.setHorizontalAlignment(SwingConstants.CENTER);
		lblPlease.setBounds(204, 36, 275, 41);
		frame.getContentPane().add(lblPlease);
		
		JLabel label_1 = new JLabel("Please Specify Your Operation.");
		label_1.setHorizontalAlignment(SwingConstants.CENTER);
		label_1.setBounds(204, 89, 275, 41);
		frame.getContentPane().add(label_1);
		frame.setBounds(100, 100, 687, 471);
		frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(e.getSource() == btnLogout)
		{
			int i = JOptionPane.showConfirmDialog(null,"Are you sure you want to logout?", "Yes", JOptionPane.YES_NO_OPTION);
			// 显示选择对话框
			if(i == JOptionPane.YES_OPTION) {
			System.exit(0);
			}
		}
		
		else if(e.getSource() == btnUpload)	
		{
			//frame.dispose();
			awtest fo = new awtest();
			
			TestScp test = new TestScp();
			//test.putFile(localFile, remoteTargetDirectory);
			try {
				test.putFile(fo.openDic(), remoteTargetDirectory);
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			//System.out.println(fo.openDic());
			frame.setVisible(true);
		}
		
		else if(e.getSource() == btnDownload){
			frame.dispose();
			TestScp test = new TestScp();
			Object[][] rows = null;
			try {
				rows = test.getFileProperties(remoteTargetDirectory);
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			new RemoteFiles(rows, remoteTargetDirectory);
			
		}
		else {}
		
	}
}


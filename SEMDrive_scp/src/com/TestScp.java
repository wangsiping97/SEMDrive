package com;

import java.io.File;
import java.io.IOException;
import java.util.Vector;

import javax.swing.JOptionPane;

import ch.ethz.ssh2.Connection;
import ch.ethz.ssh2.SCPClient;
import ch.ethz.ssh2.SFTPv3Client;
import ch.ethz.ssh2.SFTPv3DirectoryEntry;
 


public class TestScp {
	private static String IP = "47.94.98.103";
	private static int PORT = 22;
	private static String USER = "root";//登录用户名
	private static String PASSWORD = "KpAjBx3ewa"; //登录密码
	//private static Connection connection = new Connection(IP, PORT);
	public static Connection connection = new Connection(IP, PORT);
	
	
    public TestScp () {
        super();
    }
 
	/**
	 * ssh用户登录验证，使用用户名和密码来认证
	 * 
	 * @param user
	 * @param password
	 * @return
	 */
	public static boolean isAuthedWithPassword(String user, String password) {
		try {
			return connection.authenticateWithPassword(user, password);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return false;
	}
 
	/**
	 * ssh用户登录验证，使用用户名、私钥、密码来认证 其中密码如果没有可以为null，生成私钥的时候如果没有输入密码，则密码参数为null
	 * 
	 * @param user
	 * @param privateKey
	 * @param password
	 * @return
	 */
	
 
	public static boolean isAuth() {
		return isAuthedWithPassword(USER, PASSWORD);
	}
	
	
	public static void delFile(String remoteFile, String prefix) throws IOException
	{
		try {
		connection.connect();
		connection.authenticateWithPassword(USER,PASSWORD);
		//建立一个SFTP客户端        
		SFTPv3Client sftpClient = new SFTPv3Client(connection);
		sftpClient.rm(prefix + remoteFile);
		JOptionPane.showMessageDialog(null, "Successfully Remove " + remoteFile + '!');
		}catch (IOException e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "Failed to Remove " + remoteFile + '!');
		} finally {
			connection.close();
			}
	}
 
	public static void getFile(String remoteFile, String path, String prefix) throws IOException {			
		try {
			connection.connect();
			boolean isAuthed = isAuth();
			if (isAuthed) {
				System.out.println("认证成功!");
				SCPClient scpClient = connection.createSCPClient();
				System.out.println(prefix + remoteFile);
				scpClient.get(prefix + remoteFile, path);
				JOptionPane.showMessageDialog(null, "Successfully Download " + remoteFile + '!');
			} else {
				System.out.println("认证失败!");
				JOptionPane.showMessageDialog(null, "Failed to Download " + remoteFile + '!');
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			connection.close();
		}
	}
 
	public static void putFile(String localFile, String remoteTargetDirectory) throws IOException {
		try {
			connection.connect();
			boolean isAuthed = isAuth();
			if (isAuthed) {
				System.out.println("认证成功!");
				SCPClient scpClient = connection.createSCPClient();
				scpClient.put(localFile, remoteTargetDirectory);
				JOptionPane.showMessageDialog(null, "Successfully upload " + localFile + '!');
			} else {
				System.out.println("认证失败!");
				JOptionPane.showMessageDialog(null, "Failed to upload " + localFile + '!' );
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			connection.close();
		}
	}
	
	public static void createDir(String path) throws IOException
	{
		//连接
		connection.connect();
		//远程服务器的用户名密码
		connection.authenticateWithPassword(USER,PASSWORD);
		//建立一个SFTP客户端        
		SFTPv3Client sftpClient = new SFTPv3Client(connection);
		//远程新建目录
		sftpClient.mkdir(path,0);
		sftpClient.close();
		connection.close();
	}
	
	
	public Object[][] getFileProperties(String remotePath) throws IOException{	
		try {
			connection.connect();
			connection.authenticateWithPassword(USER,PASSWORD);
			SFTPv3Client sft = new SFTPv3Client(connection);
			Vector<?> v = sft.ls(remotePath);
			int vlen = v.size();
			Object files[][] = new Object[vlen][2];
			
			for(int i=0;i<v.size();i++){
				SFTPv3DirectoryEntry s = new SFTPv3DirectoryEntry();
				s = (SFTPv3DirectoryEntry) v.get(i);
				//文件名
				String filename = s.filename;
				files[i][0] = filename;
				//文件的大小
				/*Long fileSize = s.attributes.size;*/
				String fileSize;
				Long temp =  s.attributes.size;
				java.text.DecimalFormat df =new java.text.DecimalFormat("#.00");  
				
				if(temp >= 1024 && temp < 1024 * 1024) {
					fileSize =df.format( temp.doubleValue() / 1024);
					fileSize += "KB";
				}
				else if(temp >= 1024 * 1024 && temp < 1024 * 1024 * 1024){
					fileSize =df.format( temp.doubleValue() / 1024 / 1024);
					fileSize += "MB";
				}
				else {
					fileSize =df.format( temp.doubleValue() / 1024 / 1024 / 1024);
					fileSize += "GB";
				}
				files[i][1] = fileSize;
			}
				
			sft.close();
			connection.close();
			return files;
	       
		} catch (Exception e1) {
			e1.printStackTrace();
		}
		return null;
	}
 
	/*public static void main(String[] args) {
		try {
			//getFile("/www/wwwroot/yzc16/1.pdf", "/Users/apple/Desktop");
			createDir("/www/wwwroot/bye");
			//putFile("/Users/apple/Downloads/test.pdf", "/www/wwwroot/hahah");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}*/
}
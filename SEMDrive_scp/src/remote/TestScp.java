package remote;

import java.io.File;
import java.io.IOException;

import javax.swing.JOptionPane;

import ch.ethz.ssh2.Connection;
import ch.ethz.ssh2.SCPClient;
import ch.ethz.ssh2.SFTPv3Client;
 


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
 
	public static void getFile(String remoteFile, String path) {
		try {
			connection.connect();
			boolean isAuthed = isAuth();
			if (isAuthed) {
				System.out.println("认证成功!");
				SCPClient scpClient = connection.createSCPClient();
				scpClient.get(remoteFile, path);
			} else {
				System.out.println("认证失败!");
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			connection.close();
		}
	}
 
	public static void putFile(String localFile, String remoteTargetDirectory) {
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
				JOptionPane.showMessageDialog(null, "Failed to upload!");
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			connection.close();
		}
	}
	
	public static void createDir(String path) throws IOException
	{
		Connection con = new Connection(IP, PORT);
		//连接
		con.connect();
		//远程服务器的用户名密码
		con.authenticateWithPassword(USER,PASSWORD);
		//建立一个SFTP客户端        
		SFTPv3Client sftpClient = new SFTPv3Client(con);
		//远程新建目录
		sftpClient.mkdir(path,0);
		con.close();
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
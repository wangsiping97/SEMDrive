package log;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.io.File;

public class Log {
    public Connection mConnect;
    private String tableName;

    public Log (Connection connection, String _tableName) {
        super();
        this.mConnect = connection;
        tableName = _tableName;
    }

    /**
     * 创建表
     */
    public boolean createTable () {
        boolean result = false;
        String sql = "CREATE TABLE IF NOT EXISTS  `" + tableName +"` (`userid` int(10) unsigned NOT NULL AUTO_INCREMENT, `username` varchar(100) NOT NULL, `pwd` varchar(100) NOT NULL, `filepath` varchar(100) NOT NULL, PRIMARY KEY (`userid`)) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4";
        try {
            Statement statement = mConnect.createStatement();
            statement.execute(sql);
            result = true;
            statement.close();
        } catch (SQLException e) {
            System.err.println("创建表异常:"+e.getMessage());
        }
        return result;
    }

    /**
     * 清空表
     */
    public void clearTable () {
        try {
            Statement statement = mConnect.createStatement();
            String sql = "TRUNCATE `" + tableName + "`";
            statement.executeUpdate(sql);
            statement.close();
        } catch (SQLException e) {
            System.err.println("清空异常:"+e.getMessage());
        }
    }

    /**
     * 提取用户信息
     * @param userName
     */
    public Bean getUserInfo (String userName) {
        String sql ="SELECT * FROM `" + tableName + "` WHERE username='" +userName+"';";
        try {
            Statement statement =mConnect.createStatement();
            ResultSet result = statement.executeQuery(sql);
            if(result.first()) {
                Bean target =new Bean(
                        result.getString("username"), 
                        result.getString("pwd"));
                return target;
            }
            statement.close();
            
        } catch (SQLException e) {
            e.printStackTrace();
        } finally { }
        return null;
    }

    /**
     * 用户注册
     * @param user
     */
    public boolean addUser(Bean user) {
        boolean result = false;
        /**
         * 避免重复注册
         */
        if (getUserInfo(user.getName()) != null) {
            return false;
        }
        try {
            Statement statement =mConnect.createStatement();
            String sql ="INSERT INTO `" + tableName + "`(`username`, `pwd`, `filepath`) VALUES ('"+
            user.getName()+"','"+user.getPwd()+"','"+user.getPath()+"')";
            statement.executeUpdate(sql);
            result = true;
            statement.close();
            File dir = new File(user.getPath());
            /**
             * 在这里添加文件夹
             * ..
             * ..
             * ..
             * ..
             */
        } catch (SQLException e) {
            System.err.println("添加异常:"+e.getMessage());
        }
        return result;
    }

    /**
     * 用户登录
     */
    public boolean logIn (String name, String pwd) {
        Bean target = getUserInfo(name);
        if (target == null)  {
            System.out.println("用户名不存在");
            return false;
        }
        if (pwd.equals(target.getPwd())) return true;
        System.out.println("密码错误");
        return false;
    }

    public static void main (String[] arguments) {
        /**
         * 检查连接
         */
        Log test = new Log(DBManager.getConnection(), "users");

        /**
         * 检查数据库创建
         */
        // if (test.createTable()) System.out.println("表格创建成功");
        // else System.out.println("表格创建失败");

        // test.clearTable(); // 清空数据库

        /**
         * 检查创建用户
         */
        Bean testuser = new Bean ("apple", "123456");
        if (test.addUser(testuser)) System.out.println("用户创建成功");
        else System.out.println("用户创建失败");
        /**
         * 检查登录
         */
        if (test.logIn("apple", "123456")) System.out.println("用户登录成功");
        else System.out.println("用户登录失败");
    }
}

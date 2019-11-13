package log;

public class Bean {
    private String name;
    private String pwd;
    private String path;
    Bean (String _name, String _pwd) {
        name = _name;
        pwd = _pwd;
        /**
         * path 在这里自动创建
         */
        path = "/home/www/wwwroot/" + name;
    }
    /**
     * 更改用户名和密码
     * @param _name
     */
    public void setName (String _name) {
        name = _name;
    }
    public void setPwd (String _pwd) {
        pwd = _pwd;
    }
    /**
     * 提取用户信息
     */
    public String getName () {
        return name;
    }
    public String getPwd () {
        return pwd;
    }
    public String getPath () {
        return path;
    }
}
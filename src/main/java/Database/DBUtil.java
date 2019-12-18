package Database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * 基本数据库连接、执行语句、关闭操作
 * @author overlord
 *
 */
public class  DBUtil {
	private Connection conn;
	private Statement st;
	private ResultSet rs;
	
	/**
	 * 创建数据库连接，初始化对象时便调用创建方法
	 */
	public DBUtil() {
		try {
			createConn();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 创建一个到数据库的连接
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	private void createConn() throws ClassNotFoundException, SQLException{
        final String URL= "jdbc:sqlite:atm_account.db";
        Class.forName("org.sqlite.JDBC");
		conn=DriverManager.getConnection(URL);
		st=conn.createStatement();
	}
	
	/**
	 * 执行sql语句，无返回
	 * @param sql	输入的sql语句
	 */
	public void voidExecute(String sql){
		try {
			st.execute(sql);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 更新的内容
	 * @param sql	输入更新sql语句
	 * @return	返回更新成功条目数
	 */
	public int intExecute(String sql){
		int i=0;
		try {
			i = st.executeUpdate(sql);
		} catch (SQLException e) {
			e.printStackTrace();
		}
//		close();
		return i;
	}
	
	/**
	 * 获取查询的结果
	 * @param sql	输入查询语句
	 * @return	返回ResultSet类型结果
	 */
	public ResultSet rsExecute(String sql){
		try {
			rs=st.executeQuery(sql);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return rs;
	}
	
	/**
	 * 关闭数据库连接
	 */
	public void close(){
			try {
				if(rs!=null)rs.close();
				if(st!=null)st.close();
				if(conn!=null)conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
	}
}

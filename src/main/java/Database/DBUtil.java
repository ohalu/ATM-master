package Database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * �������ݿ����ӡ�ִ����䡢�رղ���
 * @author overlord
 *
 */
public class  DBUtil {
	private Connection conn;
	private Statement st;
	private ResultSet rs;
	
	/**
	 * �������ݿ����ӣ���ʼ������ʱ����ô�������
	 */
	public DBUtil() {
		try {
			createConn();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * ����һ�������ݿ������
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
	 * ִ��sql��䣬�޷���
	 * @param sql	�����sql���
	 */
	public void voidExecute(String sql){
		try {
			st.execute(sql);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * ���µ�����
	 * @param sql	�������sql���
	 * @return	���ظ��³ɹ���Ŀ��
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
	 * ��ȡ��ѯ�Ľ��
	 * @param sql	�����ѯ���
	 * @return	����ResultSet���ͽ��
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
	 * �ر����ݿ�����
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

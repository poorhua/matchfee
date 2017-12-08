package org.wxjs.matchfee.modules.base.jdbc;

import java.util.Iterator;
import java.util.List;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

import javax.sql.DataSource;

import org.apache.log4j.Logger;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class SmsDAOHelper{
	
    private static final Logger logger = Logger.getLogger(SmsDAOHelper.class);
    
	/**
	 * use this method to run a single sql clause
	 * @param sql
	 * @return
	 * @throws SQLException
	 */
	public static int executeSQL(String sql) throws SQLException {
		int rst = 0;
	    ApplicationContext ctx = new ClassPathXmlApplicationContext("spring-context.xml");  
	    DataSource dataSource = ctx.getBean("dataSourceSms",DataSource.class); 
	    
		Connection conn = null;
		Statement st = null;
		try{
			conn = dataSource.getConnection();
			st = conn.createStatement();
			rst = st.executeUpdate(sql);
		}catch(SQLException ex){
			logger.error("executeSQL(String sql) errr, [SQL]:"+sql, ex);
			throw ex;
		}finally{
			if(st!=null){
				try{
					st.close();
				}catch(SQLException ex1){
					logger.error("", ex1);
				}
			}
			if(conn!=null){
				try{
					conn.close();
				}catch(SQLException ex1){
					logger.error("", ex1);
				}
			}
		}
		return rst;
	}
	
	public static int executeSQLQuietly(String sql) {
		int effects = 0;
		try {
			effects = executeSQL(sql);
		} catch (SQLException e) {
			logger.error("", e);
		}
		return effects;
	}
	
	/**
	 * use this method to run multiple sql clauses
	 * @param sqls
	 * @return
	 * @throws SQLException
	 */
	public static int executeSQL(List<String> sqls) throws SQLException {
		int rst = 0;
		if(sqls==null || sqls.size()==0){
			return rst;
		}
		Connection conn = null;
		Statement st = null;
		String sql = "";
		try{
			conn = getConnection();
			conn.setAutoCommit(false);
			st = conn.createStatement();
			for(Iterator<String> it=sqls.iterator();it.hasNext();){
				sql = it.next();
				rst += st.executeUpdate(sql);
			}
			conn.commit();
		}catch(SQLException ex){
			if(conn!=null){
				try{
					conn.rollback();
				}catch(SQLException ex1){
					logger.error("", ex1);
				}
			}
			logger.error("executeSQL(List) errr, [SQL]:"+sql, ex);
			throw ex;
		}finally{
			if(st!=null){
				try{
					st.close();
				}catch(SQLException ex1){
					logger.error("", ex1);
				}
			}
			if(conn!=null){
				try{
					conn.close();
				}catch(SQLException ex1){
					logger.error("", ex1);
				}
			}
		}
		return rst;
	}
	
	private static Connection getConnection() throws SQLException{

	    ApplicationContext ctx = new ClassPathXmlApplicationContext("spring-context.xml");  
	    
	    DataSource dataSource = ctx.getBean("dataSourceSms",DataSource.class);  
	  
	    return dataSource.getConnection(); 
		
	}

}



package org.djjs.dao;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.log4j.Logger;
import org.djjs.model.AdminVO;
import org.djjs.util.DBUtil;



public class AdminDao {
	private static final Logger log = Logger.getLogger(AdminDao.class);	
	
	public AdminVO login(String uName, String pass) {
		String sql = "select * from pi_admins where (userName=? and paswd=password(?) and is_active='Y')";
		Connection con = DBUtil.getConnection();
		AdminVO vo = null;
		int id = 0;
		try{
			PreparedStatement pstmt = con.prepareStatement(sql);
			pstmt.setString(1, uName);
			pstmt.setString(2, pass);
			ResultSet rs = pstmt.executeQuery();
			while(rs.next()) {
				id = rs.getInt("id");
			}// while
			if(id>0) {
				ConsoleAdminDAO dao = new ConsoleAdminDAO();
				vo = dao.getAdminDetails(id);
			}
		}catch(SQLException sq) {
			log.error("Admin failed to login " + sq.getMessage());
		}
		return vo;		
	}
	
	public boolean changePassword(int id, String pass) {
		boolean flag = false;
		String sql = "update pi_admins set paswd=password(?) where id=?";
		Connection con = DBUtil.getConnection();
		try {
			PreparedStatement pstmt = con.prepareStatement(sql);
			pstmt.setString(1, pass);
			pstmt.setInt(2, id);
			log.debug("Going to execute " + sql);
			pstmt.executeUpdate();
			con.commit();
			flag = true;
		} catch(SQLException sqle) {
			log.error("error occured in changing admins password " + sqle.getMessage());
			sqle.printStackTrace();
		} finally {
			DBUtil.closeConnection(con);
		}		
		return flag;
	}
	
	
	
}

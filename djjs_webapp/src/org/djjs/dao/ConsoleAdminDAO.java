/**
 * Oct 18, 2009
 * @author Prateek Jain
 */
package org.djjs.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

import org.djjs.model.AdminVO;
import org.djjs.model.SuperUserVO;
import org.djjs.util.DBUtil;

import org.apache.log4j.Logger;

public class ConsoleAdminDAO {
	private static Logger log = Logger.getLogger(ConsoleAdminDAO.class);
	
	public boolean chnageActiveStatus(int aid, String statusToSet) {
		boolean flag = false;
		String sql = "update pi_admins set is_active=? where id=?";
		Connection con = DBUtil.getConnection();
		try{
			PreparedStatement pstmt = con.prepareStatement(sql);
			pstmt.setString(1, statusToSet);
			pstmt.setInt(2, aid);
			pstmt.executeUpdate();
			con.commit();
			flag = true;
		} catch(SQLException sq) {
			log.error("Failed to update admin status");
		} finally {
			DBUtil.closeConnection(con);
		}
		
		
		return flag;
	}
	
	
	public SuperUserVO validateAdmin(String name, String passwd) {
		String sql = "select * from pi_super_user where (userName=? and passwd=password(?))";
		Connection con = DBUtil.getConnection();
		SuperUserVO vo = null;
		try {
			PreparedStatement pstmt = con.prepareStatement(sql);
			pstmt.setString(1, name);
			pstmt.setString(2, passwd);
			ResultSet rs = pstmt.executeQuery();
			log.debug("Executing " + sql);
			vo = new SuperUserVO();
			while (rs.next()) {
				vo.setId(rs.getInt("id"));
				vo.setName(rs.getString("userName"));
				vo.setEmail(rs.getString("email"));

			}
		} catch (SQLException e) {
			log.error("Error occured while validating super user "
					+ e.getMessage());
			e.printStackTrace();
		} finally {
			DBUtil.closeConnection(con);
		}

		return vo;
	}

	public List<AdminVO> listAdminsBySuperAdmin(int id) {
		String sql = "select * from pi_admins where createdBy=?";
		Connection con = DBUtil.getConnection();
		List<AdminVO> lst = new ArrayList<AdminVO>();
		try {
			PreparedStatement pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, id);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				AdminVO vo = new AdminVO();
				Date date = rs.getDate("createdDate");
				java.util.Date d = new java.util.Date(date.getTime());
				vo.setCreatedDate(d);
				vo.setEmail(rs.getString("email"));
				vo.setId(rs.getInt("id"));
				vo.setName(rs.getString("name"));
				vo.setUserName(rs.getString("userName"));
				vo.setSuperAdminID(id);
				lst.add(vo);
			}

		} catch (SQLException e) {
			log.error("Error occured while retriving list of admins "
					+ e.getMessage());
		} finally {
			DBUtil.closeConnection(con);
		}
		return lst;

	}

	public AdminVO addAdmin(AdminVO vo) {
		String sql1 = "insert into pi_admins(userName, name, paswd, email, createdBy, createdDate) values (?, ?, password(?),?,?,curdate())";
		String sql2 = "select * from pi_admins where (userName=? and name=? and email=? and createdBy=?)";
		PreparedStatement pstmt = null;
		Connection con = DBUtil.getConnection();
		try {
			log.debug("Trying to add new admin");
			log.error("Executing sql " + sql1);
			pstmt = con.prepareStatement(sql1);
			pstmt.setString(1, vo.getUserName());
			pstmt.setString(2, vo.getName());
			pstmt.setString(3, vo.getPasswd());
			pstmt.setString(4, vo.getEmail());
			pstmt.setInt(5, vo.getSuperAdminID());
			int r = pstmt.executeUpdate();
			if (r == 1) {
				pstmt = con.prepareStatement(sql2);
				pstmt.setString(1, vo.getUserName());
				pstmt.setString(2, vo.getName());
				pstmt.setString(3, vo.getEmail());
				pstmt.setInt(4, vo.getSuperAdminID());
				ResultSet rs = pstmt.executeQuery();
				while (rs.next()) {
					int id = rs.getInt("id");
					vo.setId(id);
					log.info("Admin added with id=" + id);
				}
				con.commit();
			}

		} catch (SQLException sqle) {
			log.error("Error occured while adding admins " + sqle.getMessage());
		} finally {
			DBUtil.closeConnection(con);
		}

		return vo;
	}

	public boolean swapAdmins(int oldAid, int newAid) {
		boolean flag = false;
		String sqlSelect = "select id, aid from pi_allowed_countries where aid IN (?,?)";
		String sqlUpdate = "update pi_allowed_countries set aid=?, updated_date=CURDATE() where id=?";
		PreparedStatement pstmt = null;
		Connection con = DBUtil.getConnection();
		try {
			pstmt = con.prepareStatement(sqlSelect);
			pstmt.setInt(1, newAid);
			pstmt.setInt(2, oldAid);
			log.debug("Executing query " + sqlSelect);
			ResultSet rs = pstmt.executeQuery();
			int[][] idz = new int[2][2];
			int i=0;
			while (rs.next()) {
				idz[i][0] = rs.getInt("id");
				idz[i][1] = rs.getInt("aid");
				i++;
			}	
			pstmt = con.prepareStatement(sqlUpdate);
			pstmt.setInt(1, idz[0][1]);
			pstmt.setInt(2, idz[1][0]);
			pstmt.executeUpdate();
			
			pstmt.setInt(1, idz[1][1]);
			pstmt.setInt(2, idz[0][0]);
			pstmt.executeUpdate();
			
			con.commit();
			
			flag = true;
		} catch (SQLException sq) {
			log.error("Error occured while swapping admins " + sq.getMessage());
		} finally {
			DBUtil.closeConnection(con);
		}

		return flag;
	}

	
	
	
	/**
	 * Activate/Deactivate IDs
	 * 
	 * @return
	 */
	public boolean changeAdminStatus(int adminID, String newStatus) {
		boolean flag = false;
		String sql = "update pi_admins set is_active=? where id=?";
		PreparedStatement pstmt = null;
		Connection con = DBUtil.getConnection();
		try {
			pstmt = con.prepareStatement(sql);
			if (newStatus.equalsIgnoreCase("Y")) {
				pstmt.setString(1, "Y");
			} else {
				pstmt.setString(1, "N");
			}
			pstmt.setInt(2, adminID);
			pstmt.executeUpdate();
			con.commit();
			flag = true;
		} catch (SQLException sq) {
			log.error("Error occured while changing status of admins "
					+ sq.getMessage());
		} finally {
			DBUtil.closeConnection(con);
		}

		return flag;
	}

	public AdminVO getAdminDetails(int adminID) {
		String sql = "select * from pi_admins where id=?";
		PreparedStatement pstmt = null;
		Connection con = DBUtil.getConnection();
		AdminVO vo = null;
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, adminID);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				vo = new AdminVO();
				vo.setUserName(rs.getString("userName"));
				vo.setName(rs.getString("name"));
				vo.setEmail(rs.getString("email"));
				java.util.Date d = new java.util.Date(rs.getDate("createdDate")
						.getTime());
				vo.setCreatedDate(d);
				vo.setIsActive(rs.getString("is_active"));
				vo.setId(adminID);
			}

		} catch (SQLException se) {
			log.error("Error occured while retrieving details of admin ="
					+ adminID + " " + se.getMessage());
		} finally {
			DBUtil.closeConnection(con);
		}
		return vo;
	}

}

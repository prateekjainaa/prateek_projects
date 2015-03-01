/**
 * Aug 11, 2009
 * @author Prateek Jain
 */
package org.djjs.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.log4j.Logger;
import org.djjs.model.MemberVO;
import org.djjs.model.SearchVO;
import org.djjs.util.DBUtil;

/**
 * /**
 * 
 * @author pjain
 * 
 */
public class EditUserDao {

	private static final Logger log = Logger.getLogger(EditUserDao.class);

	private String convertToSqlDate(String date) {
		SimpleDateFormat format = new SimpleDateFormat("MM/dd/yyyy");
		Date d = null;
		try {
			d = format.parse(date);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		SimpleDateFormat fmt = new SimpleDateFormat("yyyy/MM/dd");
		String sqlDate = fmt.format(d);

		return sqlDate.replaceAll("/", "-");

	}

	public boolean updateUser(SearchVO vo) {
		boolean flag = false;
		/*String isvip = "N";
		if (vo.getIsvip().equals("1")) {
			isvip = "Y";
		}*/

		StringBuilder b = new StringBuilder();
		b.append("update members set ");
		b.append(" first_name=upper('" + vo.getFirstNameText() + "')");
		b.append(" ,last_name=upper('" + vo.getLastNameText() + "')");
		b.append(" ,guardian_name=upper('" + vo.getGaurdianNameText() + "')");
		String sDOB = convertToSqlDate(vo.getDateOfBirth());
		b.append(" ,dob=DATE_FORMAT('" + sDOB + "','%Y-%m-%d')");
		b.append(" ,Sex='" + vo.getSex() + "'");
		b.append(" ,phone_landline='" + vo.getPhoneLandText() + "'");
		b.append(" ,phone_mobile='" + vo.getPhoneMobileText() + "'");
		b.append(" ,is_vip='" + vo.getIsvip() + "'");
		b.append(" ,email=upper('" + vo.getEmailText() + "')");
		b.append(" ,relatedTO=" + vo.getRelatedToText());
		b.append(" ,work_category='" + vo.getWorkCategory() + "'");
		b.append(" ,sewa_ids='" + vo.getSewaIds() + "'");
		String selectSwami = vo.getSelectSwami();
		String isswami = "N";
		String ispremi = "N";
		String isdeekshit = "Y";
		if (selectSwami.equals("1")) {
			isswami = "Y";
		}
		if (selectSwami.equals("2")) {
			ispremi = "Y";
		}
		if (selectSwami.equals("3")) {
			isdeekshit = "N";
		}
		b.append(" ,is_swami='" + isswami + "'");
		b.append(" ,is_premi='" + ispremi + "'");
		b.append(" ,is_deekshit='" + isdeekshit + "'");
		b.append(" where mid=" + vo.getMemberID());

		Connection conn = DBUtil.getConnection();
		try {
			conn.setAutoCommit(false);
			conn.setTransactionIsolation(Connection.TRANSACTION_READ_COMMITTED);
			String query = b.toString();
			log.debug("Update user Query \n" + query);
			Statement stmt = conn.createStatement();
			int update = stmt.executeUpdate(query);
			conn.commit();
			flag = true;
		} catch (SQLException e) {
			e.printStackTrace();
			log.error("database error " + e.getMessage());

		} finally {
			DBUtil.closeConnection(conn);
		}
		return flag;
	}

	public boolean updateUserAddress(MemberVO vo) {
		boolean flag = false;
		StringBuilder b = new StringBuilder();
		b.append("update address set ");
		b.append(" cid=" + vo.getCountryCodes());
		b.append(" ,sid=" + vo.getStateCodes());
		b.append(" ,did=" + vo.getDistrictCodes());
		b.append(" ,town=upper('" + vo.getTownNameText() + "')");
		b.append(" ,description=upper('" + vo.getFullAddText() + "')");
		b.append(" where mid=" + vo.getMemberID());
		Connection conn = DBUtil.getConnection();
		try {
			conn.setAutoCommit(false);
			conn.setTransactionIsolation(Connection.TRANSACTION_READ_COMMITTED);
			String query = b.toString();
			log.debug("Update user Query \n" + query);
			Statement stmt = conn.createStatement();
			int update = stmt.executeUpdate(query);
			conn.commit();
			flag = true;
		} catch (SQLException e) {
			e.printStackTrace();
			log.error("database error " + e.getMessage());

		} finally {
			DBUtil.closeConnection(conn);
		}

		return flag;
	}

	private boolean issuedInsert(MemberVO vo) {
		boolean result = false;
		int mid = Integer.parseInt(vo.getMemberID());
		String checkQuery = "Select count(*) from deeksha where mid=" + mid;
		Connection conn = DBUtil.getConnection();
		try {
			conn.setAutoCommit(false);
			conn.setTransactionIsolation(Connection.TRANSACTION_READ_COMMITTED);
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(checkQuery);
			int count = 0;
			while (rs.next()) {
				count = rs.getInt(1);
			}
			if (count == 0) {
				result = new MemberDAO().storeDeekshaDetails(vo, conn);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			log.error("database error " + e.getMessage());
			result = false;
		} finally {
			DBUtil.closeConnection(conn);
		}

		return result;
	}

	public boolean updateUserDeekshaDetails(MemberVO vo) {
		String dd = vo.getDeekshaDate();
		String db = vo.getDeekshaAshramText();
		boolean flag = false;
		if (null == dd || dd.length() == 0 || dd.equalsIgnoreCase("null")) {
			return true;
		}
		if ((!vo.getSelectSwami().equals("3")) && issuedInsert(vo)) {
			return true;
		}
		StringBuilder b = new StringBuilder();
		b.append("update deeksha set ");
		String sqlDate = convertToSqlDate(dd);
		b.append(" deeksha_date=DATE_FORMAT('" + sqlDate + "','%Y-%m-%d')");
		b.append(" ,deeksha_branch=upper('" + db + "')");
		b.append(" where mid=" + vo.getMemberID());
		Connection conn = DBUtil.getConnection();
		try {
			conn.setAutoCommit(false);
			conn.setTransactionIsolation(Connection.TRANSACTION_READ_COMMITTED);
			String query = b.toString();
			log.debug("Update user Query \n" + query);
			Statement stmt = conn.createStatement();
			int update = stmt.executeUpdate(query);
			conn.commit();
			flag = true;
		} catch (SQLException e) {
			e.printStackTrace();
			log.error("database error " + e.getMessage());
			flag = false;
		} finally {
			DBUtil.closeConnection(conn);
		}
		return flag;
	}

	public boolean updateQualification(MemberVO vo) {
		String s1 = vo.getQualificationText();
		String s2 = vo.getOccupationText();
		String s3 = vo.getOtherProfText();
		StringBuilder b = new StringBuilder();
		b.append("update qualification set ");
		b.append(" education=upper('" + s1 + "')");
		b.append(" ,occupation=upper('" + s2 + "')");
		b.append(" ,other=upper('" + s3 + "')");
		b.append(" where mid=" + vo.getMemberID());
		boolean flag = false;
		Connection conn = DBUtil.getConnection();
		try {
			conn.setAutoCommit(false);
			conn.setTransactionIsolation(Connection.TRANSACTION_READ_COMMITTED);
			String query = b.toString();
			log.debug("Update user Query \n" + query);
			Statement stmt = conn.createStatement();
			int update = stmt.executeUpdate(query);
			conn.commit();
			flag = true;
		} catch (SQLException e) {
			e.printStackTrace();
			log.error("database error " + e.getMessage());
			flag = false;
		} finally {
			DBUtil.closeConnection(conn);
		}
		return flag;

	}

}

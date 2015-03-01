/**
 * Jul 4, 2009
 * @author Prateek Jain
 */
package org.djjs.dao;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;
import org.djjs.model.MemberVO;
import org.djjs.model.Qualification;
import org.djjs.model.Sewa;
import org.djjs.util.DBUtil;

public class MemberDAO {
	private static final Logger log = Logger.getLogger(MemberDAO.class);
	private final String IMAGE_STORE = System.getenv("image_store");
	
	
	/**
	 * 
	 * @param vo
	 * @return
	 */
	public int storeMember(MemberVO vo, Connection con) throws RuntimeException {
		String sql = "insert into members values ( ?,upper(?),upper(?),upper(?),?,?,?,?,?,?,?,?,?,upper(?),?, ?, ?)";
		int ID = getMaxMemberID(con);
		int tempID = ID + 1;
		PreparedStatement pstmt;
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, tempID);
			pstmt.setString(2, vo.getFirstNameText());
			pstmt.setString(3, vo.getLastNameText());
			pstmt.setString(4, vo.getGaurdianNameText());
			pstmt.setDate(5, convertToSqlDate(vo.getDateOfBirth()));
			pstmt.setString(6, vo.getSex());
			pstmt.setString(7, IMAGE_STORE + "/" + tempID + ".jpg");
			pstmt.setString(8, vo.getPhoneLandText());
			pstmt.setString(9, vo.getPhoneMobileText());
			if (vo.getSelectSwami().equals("1")) {
				pstmt.setString(10, "Y");
			} else {
				pstmt.setString(10, "N");
			}
			if (vo.getSelectSwami().equals("2")) {
				pstmt.setString(11, "Y");
			} else {
				pstmt.setString(11, "N");
			}
			if (vo.getSelectSwami().equals("3")) {
				pstmt.setString(12, "N");
			} else {
				pstmt.setString(12, "Y");
			}
			pstmt.setString(13, vo.getIsvip());
			pstmt.setString(14, vo.getEmailText());
			pstmt.setInt(15, Integer.parseInt(vo.getRelatedToText()));
			pstmt.setString(16, vo.getSewaIds());
			pstmt.setString(17, vo.getWorkCategory());
			pstmt.executeUpdate();

		} catch (SQLException e) {
			ID = -1;
			log.error("Failed to enter member");
			e.printStackTrace();
			throw new RuntimeException(
					"Some sql exception occured in inserting data");
		} finally {

		}

		return ID + 1;
	}

	public int storeMemberAddress(MemberVO vo, Connection con) {

		int id = getMaxAddressID(con);
		PreparedStatement pstmt;
		String sql = "insert into address values(?,?,?,?,upper(?),?,upper(?),?)";
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, id);
			pstmt.setInt(2, Integer.parseInt(vo.getCountryCodes()));
			pstmt.setInt(3, Integer.parseInt(vo.getStateCodes()));
			pstmt.setInt(4, Integer.parseInt(vo.getDistrictCodes()));
			pstmt.setString(5, vo.getTownNameText());
			pstmt.setInt(6, Integer.parseInt(vo.getMemberID()));
			pstmt.setString(7, vo.getFullAddText());
			pstmt.setString(8, "Y");
			pstmt.executeUpdate();

		} catch (SQLException e) {
			log.error("failed to enter addresses for " + vo.getMemberID());
			e.printStackTrace();
		} finally {

		}
		return id;

	}

	public boolean storeQualification(MemberVO vo, Connection con) {
		boolean flag = false;
		String sql = "insert into qualification values(upper(?),upper(?),upper(?),?)";
		PreparedStatement pstmt;
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, vo.getQualificationText());
			pstmt.setString(2, vo.getOccupationText());
			pstmt.setString(3, vo.getOtherProfText());
			pstmt.setInt(4, Integer.parseInt(vo.getMemberID()));
			pstmt.executeUpdate();

			flag = true;

		} catch (SQLException e) {
			log.error("Failed to enter qualification for " + vo.getMemberID());
			e.printStackTrace();
		} finally {

		}

		return flag;
	}

	public boolean storeDeekshaDetails(MemberVO vo, Connection con) {
		boolean flag = false;
		String sql = "insert into deeksha values(?,upper(?),?)";
		PreparedStatement pstmt;
		try {

			pstmt = con.prepareStatement(sql);
			pstmt.setDate(1, convertToSqlDate(vo.getDeekshaDate()));
			pstmt.setString(2, vo.getDeekshaAshramText());
			pstmt.setInt(3, Integer.parseInt(vo.getMemberID()));
			pstmt.executeUpdate();

			flag = true;

		} catch (SQLException e) {
			log.error("Failed to enter deeksha details for " + vo.getMemberID());
			e.printStackTrace();
		} finally {

		}

		return flag;

	}

	private int getMaxMemberID(Connection con) {
		String sql = "select max(mid) as id from members";
		int id = 0;
		try {
			PreparedStatement pstmt = con.prepareStatement(sql);
			ResultSet rs = pstmt.executeQuery();
			id = 0;
			while (rs.next()) {
				id = rs.getInt("id");
			}
		} catch (SQLException e) {
			log.error("Failed to get Member ID");
		} finally {
		}
		return id;
	}

	private int getMaxAddressID(Connection con) {
		String sql = "select max(id) as id from address";
		int id = 0;
		try {
			PreparedStatement pstmt = con.prepareStatement(sql);
			ResultSet rs = pstmt.executeQuery();
			id = 0;
			while (rs.next()) {
				id = rs.getInt("id");
			}
		} catch (SQLException e) {
			log.error("Failed to get Address ID");
		}
		return id + 1;
	}

	private java.sql.Date convertToSqlDate(String date) {
		SimpleDateFormat format = new SimpleDateFormat("MM/dd/yyyy");
		Date d = null;
		try {
			d = format.parse(date);
		} catch (ParseException e) {
			log.error("Failed to parse date");
			e.printStackTrace();
		}
		java.sql.Date sdate = new java.sql.Date(d.getTime());
		return sdate;

	}

	public MemberVO getUserData(int mid) {
		MemberVO vo = new MemberVO();
		Connection con = DBUtil.getConnection();
		String relatedTo = null;
		java.sql.Date dob = null;
		java.sql.Date deeksha = null;
		String imagePath = null;
		try {
			PreparedStatement pstmt = con
					.prepareStatement("Select * from members where mid=?");
			pstmt.setInt(1, mid);
			ResultSet rs = pstmt.executeQuery();

			while (rs.next()) {
				vo.setFirstNameText(rs.getString("first_name"));
				vo.setLastNameText(rs.getString("last_name"));
				vo.setGaurdianNameText(rs.getString("guardian_name"));
				dob = rs.getDate("dob");
				vo.setSewaIds(rs.getString("sewa_ids"));
				vo.setSex(rs.getString("Sex"));
				imagePath = rs.getString("img_path");
				vo.setPhoneLandText(rs.getString("phone_landline"));
				vo.setPhoneMobileText(rs.getString("phone_mobile"));
				String swami = rs.getString("is_swami");
				String premi = rs.getString("is_premi");
				String deekshit = rs.getString("is_deekshit");
				if (swami.equals("Y")) {
					vo.setSelectSwami("1");
				} else if (premi.equals("Y")) {
					vo.setSelectSwami("2");
				} else if (deekshit.equals("N")) {
					vo.setSelectSwami("3");
				}
				vo.setIsvip(rs.getString("is_vip"));
				vo.setWorkCategory(rs.getString("work_category"));
				vo.setEmailText(rs.getString("email"));
				relatedTo = Integer.toString(rs.getInt("relatedTO"));
				vo.setRelatedToText(relatedTo);

			}
		} catch (SQLException e) {
			e.printStackTrace();
			log.error("Failed in DB interaction " + e.getLocalizedMessage());
		} finally {
			DBUtil.closeConnection(con);
		}
		vo.setDateOfBirth(getStringDate(dob));
		vo.setImagePath(readimage(imagePath));
		vo.setMemberID(Integer.toString(mid));
		return vo;
	}

	private String getStringDate(java.sql.Date date) {
		SimpleDateFormat fm = new SimpleDateFormat("MM/dd/yyyy");
		Date d = new Date(date.getTime());
		String sDate = fm.format(d);
		return sDate;

	}

	private byte[] readimage(String path) {
		byte[] b = null;
		FileInputStream fis = null;
		try {
			File file = new File(path);
			if (!file.exists()) {
				return b;
			}
			fis = new FileInputStream(file);
			long size = file.length();
			b = new byte[(int) size];

			// Read in the bytes
			int offset = 0;
			int numRead = 0;
			while (offset < b.length
					&& (numRead = fis.read(b, offset, b.length - offset)) >= 0) {
				offset += numRead;
			}

			// Ensure all the bytes have been read in
			if (offset < b.length) {
				throw new IOException("Could not completely read file "
						+ file.getName());
			}
			fis.close();
		} catch (FileNotFoundException e) {
			log.error("file IO error, while reading image " + e.getMessage());

		} catch (IOException e) {
			// TODO Auto-generated catch block
			log.error("Failed to read file " + path);
		}
		return b;
	}

	public MemberVO getAddress(MemberVO vo) {
		int mid = Integer.parseInt(vo.getMemberID());
		String query = "Select * from address where mid=?";
		Connection conn = DBUtil.getConnection();
		try {
			conn.setAutoCommit(false);
			conn.setTransactionIsolation(Connection.TRANSACTION_READ_COMMITTED);
			PreparedStatement pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, mid);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				int cid = rs.getInt("cid");
				int sid = rs.getInt("sid");
				int did = rs.getInt("did");
				vo.setCountryCodes(Integer.toString(cid));
				vo.setStateCodes(Integer.toString(sid));
				vo.setDistrictCodes(Integer.toString(did));
				vo.setFullAddText(rs.getString("description"));
				vo.setTownNameText(rs.getString("town"));
			}

		} catch (SQLException e) {
			log.error("failed to retrive address " + e.getMessage());
			e.printStackTrace();
		} finally {
			DBUtil.closeConnection(conn);
		}

		return vo;
	}

	public MemberVO getQualification(MemberVO vo) {
		int mid = Integer.parseInt(vo.getMemberID());
		String query = "Select * from qualification where mid=?";
		Connection conn = DBUtil.getConnection();
		try {
			conn.setAutoCommit(false);
			conn.setTransactionIsolation(Connection.TRANSACTION_READ_COMMITTED);
			PreparedStatement pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, mid);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				vo.setQualificationText(rs.getString("education"));
				vo.setOccupationText(rs.getString("occupation"));
				vo.setOtherProfText(rs.getString("other"));
			}

		} catch (SQLException e) {
			log.error("failed to retrive qualification " + e.getMessage());
			e.printStackTrace();
		} finally {
			DBUtil.closeConnection(conn);
		}
		return vo;
	}

	public MemberVO getDeekshaDetails(MemberVO vo) {
		int mid = Integer.parseInt(vo.getMemberID());
		String query = "Select * from deeksha where mid=?";
		Connection conn = DBUtil.getConnection();
		try {
			conn.setAutoCommit(false);
			conn.setTransactionIsolation(Connection.TRANSACTION_READ_COMMITTED);
			PreparedStatement pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, mid);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				vo.setDeekshaAshramText(rs.getString("deeksha_branch"));
				java.sql.Date date = rs.getDate("deeksha_date");
				vo.setDeekshaDate(getStringDate(date));
			}
		} catch (SQLException e) {
			log.error("failed to retrive qualification " + e.getMessage());
			e.printStackTrace();
		} finally {
			DBUtil.closeConnection(conn);
		}
		return vo;
	}

	public boolean updateDBForImage(int mid) {
		boolean flag = false;
		String sql = "update members set img_path=? where mid=?";
		String path = IMAGE_STORE + "/" + mid + ".jpg";
		Connection con = DBUtil.getConnection();
		try {
			PreparedStatement pstmt = con.prepareStatement(sql);
			pstmt.setString(1, path);
			pstmt.setInt(2, mid);
			pstmt.executeUpdate();
			con.commit();
			flag = true;
		} catch (SQLException e) {
			log.error("failed to update image path for user " + mid + " "
					+ e.getMessage());
		} finally {
			DBUtil.closeConnection(con);
		}
		return flag;
	}

	public List<Sewa> getSewaList() {
		String sql = "select * from sewa";
		List<Sewa> list = new ArrayList<Sewa>();
		Connection con = DBUtil.getConnection();
		try {
			PreparedStatement pstmt = con.prepareStatement(sql);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				Sewa s = new Sewa();
				s.setName(rs.getString("display_label"));
				s.setValue(rs.getInt("id"));
				list.add(s);
			}

		} catch (SQLException e) {
			log.error("Exception occured while getting sewa table"
					+ e.getMessage());
		} finally {
			DBUtil.closeConnection(con);
		}

		return list;
	}

	public List<Qualification> getQualificationList() {
		String sql = "select * from qualificationList";
		List<Qualification> list = new ArrayList<Qualification>();
		Connection con = DBUtil.getConnection();
		try {
			PreparedStatement pstmt = con.prepareStatement(sql);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				Qualification s = new Qualification();
				s.setName(rs.getString("display_label"));
				s.setValue(rs.getInt("id"));
				list.add(s);
			}

		} catch (SQLException e) {
			log.error("Exception occured while getting qualificationList table"
					+ e.getMessage());
		} finally {
			DBUtil.closeConnection(con);
		}

		return list;
	}
	
	
}

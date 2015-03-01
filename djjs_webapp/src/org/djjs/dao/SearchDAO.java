/**
 * Aug 11, 2009
 * @author Prateek Jain
 */
package org.djjs.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.log4j.Logger;
import org.djjs.model.MemberVO;
import org.djjs.util.DBUtil;

/**
 * /**
 * 
 * @author pjain
 * 
 */
public class SearchDAO {

    private static final Logger log = Logger.getLogger(SearchDAO.class);

    public Set<Integer> executeQueryForMemberID(String query) {

	Set<Integer> lst = new HashSet<Integer>();
	Connection conn = DBUtil.getConnection();
	try {
	    conn.setAutoCommit(false);
	    conn.setTransactionIsolation(Connection.TRANSACTION_READ_COMMITTED);
	    Statement stmt = conn.createStatement();
	    log.debug("Executing query \n" + query);
	    ResultSet rs = stmt.executeQuery(query);
	    while (rs.next()) {
		lst.add(rs.getInt("mid"));
	    }

	} catch (SQLException e) {
	    log.error("database error " + e.getMessage());
	    e.printStackTrace();
	} finally {
	    DBUtil.closeConnection(conn);
	}
	return lst;

    }

    public List<MemberVO> getSearchedUsersData(Set<Integer> set) {
	List<MemberVO> lst = new ArrayList<MemberVO>();
	Connection con = DBUtil.getConnection();
	String relatedTo = null;
	java.sql.Date dob = null;
	java.sql.Date deeksha = null;
	StringBuilder b = new StringBuilder();
	b.append("Select * from members where mid IN (  ");
	for(Integer in : set) {
	    b.append(in + ",");
	}
	String query = b.substring(0, b.length()-1);
	query += ")";
	
	try {
	    con.setAutoCommit(false);
	    con.setTransactionIsolation(Connection.TRANSACTION_READ_COMMITTED);
	    Statement stmt = con.createStatement();
	    log.debug("Executing query \n" + query);
	    ResultSet rs = stmt.executeQuery(query);
	    while (rs.next()) {
		MemberVO vo = new MemberVO();
		vo.setFirstNameText(rs.getString("first_name"));
		vo.setLastNameText(rs.getString("last_name"));
		vo.setGaurdianNameText(rs.getString("guardian_name"));
		dob = rs.getDate("dob");
		vo.setSex(rs.getString("Sex"));
		//imagePath = rs.getString("img_path");
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
		vo.setDateOfBirth(getStringDate(dob));
		vo.setMemberID(Integer.toString(rs.getInt("mid")));
		lst.add(vo);
	    }
	} catch (SQLException e) {
	    e.printStackTrace();
	    log.error("Failed in DB interaction " + e.getLocalizedMessage());
	} finally {
	    DBUtil.closeConnection(con);
	}

	return lst;
    }

    private String getStringDate(java.sql.Date date) {
	SimpleDateFormat fm = new SimpleDateFormat("MM/dd/yyyy");
	Date d = new Date(date.getTime());
	String sDate = fm.format(d);
	return sDate;

    }

}

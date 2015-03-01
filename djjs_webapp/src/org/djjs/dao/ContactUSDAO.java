/**
 * Jun 15, 2009
 * @author Prateek Jain
 */
package org.djjs.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.djjs.model.AddressVO;
import org.djjs.model.Container;
import org.djjs.util.DBUtil;

import org.apache.log4j.Logger;

public class ContactUSDAO {
    private static Logger log = Logger.getLogger(ContactUSDAO.class);

    public List<Container> getCountries() {
	List<Container> list = null;
	String sql = "select * from country";
	Connection con = DBUtil.getConnection();
	try {
	    PreparedStatement pstmt = con.prepareStatement(sql);
	    ResultSet rs = pstmt.executeQuery();
	    log.debug("Executing " + sql);
	    list = new ArrayList<Container>();
	    while (rs.next()) {

		Container cont = new Container();
		String name = rs.getString("name");
		String value = Integer.toString(rs.getInt("id"));
		cont.setName(name);
		cont.setValue(value);
		list.add(cont);
	    }
	} catch (SQLException e) {
	    log.error("Error occured while retriving countries "
		    + e.getMessage());
	    e.printStackTrace();
	} finally {
	    DBUtil.closeConnection(con);
	}

	return list;
    }

    public List<Container> getStates(int cid) {
	List<Container> list = null;
	String sql = "select * from states where cid=?";
	Connection con = DBUtil.getConnection();
	try {
	    PreparedStatement pstmt = con.prepareStatement(sql);
	    pstmt.setInt(1, cid);
	    ResultSet rs = pstmt.executeQuery();
	    log.debug("Executing " + sql);
	    list = new ArrayList<Container>();
	    while (rs.next()) {

		Container cont = new Container();
		String name = rs.getString("name");
		String value = Integer.toString(rs.getInt("id"));
		cont.setName(name);
		cont.setValue(value);
		list.add(cont);
	    }
	} catch (SQLException e) {
	    log.error("Error occured while retriving countries "
		    + e.getMessage());
	    e.printStackTrace();
	} finally {
	    DBUtil.closeConnection(con);
	}

	return list;
    }

    public List<AddressVO> getAddreses(int sid) {
	List<AddressVO> list = null;
	String sql = "select * from ashram_address where sid=?";
	Connection con = DBUtil.getConnection();
	try {
	    PreparedStatement pstmt = con.prepareStatement(sql);
	    pstmt.setInt(1, sid);
	    ResultSet rs = pstmt.executeQuery();
	    log.debug("Executing " + sql);
	    list = new ArrayList<AddressVO>();
	    while (rs.next()) {

		AddressVO vo = new AddressVO();
		String name = rs.getString("name");
		String aid = Integer.toString(rs.getInt("aid"));
		String desc = rs.getString("description");
		String stateid = Integer.toString(rs.getInt("sid"));
		String phone = rs.getString("phone");
		String zip = rs.getString("zip");
		String head = rs.getString("headed_by");
		vo.setAshramID(aid);
		vo.setDescription(desc);
		vo.setName(name);
		vo.setPhone(phone);
		vo.setStateID(stateid);
		vo.setZip(zip);
		vo.setHeadedBy(head);

		list.add(vo);
	    }
	} catch (SQLException e) {
	    log.error("Error occured while retriving countries "
		    + e.getMessage());
	    e.printStackTrace();
	} finally {
	    DBUtil.closeConnection(con);
	}

	return list;
    }

    public List<Container> getDistricts(int sid) {
	List<Container> list = null;
	String sql = "select * from districts where sid=?";
	Connection con = DBUtil.getConnection();
	try {
	    PreparedStatement pstmt = con.prepareStatement(sql);
	    pstmt.setInt(1, sid);
	    ResultSet rs = pstmt.executeQuery();
	    log.debug("Executing " + sql);
	    list = new ArrayList<Container>();
	    while (rs.next()) {

		Container cont = new Container();
		String name = rs.getString("name");
		String value = Integer.toString(rs.getInt("id"));
		cont.setName(name);
		cont.setValue(value);
		list.add(cont);
	    }
	} catch (SQLException e) {
	    log.error("Error occured while retriving districts "
		    + e.getMessage());
	    e.printStackTrace();
	} finally {
	    DBUtil.closeConnection(con);
	}

	return list;
    }

}

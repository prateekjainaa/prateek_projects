package org.djjs.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.List;
import java.util.StringTokenizer;

import org.djjs.model.Country;
import org.djjs.model.District;
import org.djjs.model.State;
import org.djjs.util.DBUtil;

import org.apache.log4j.Logger;

public class AreaDao {
	private static Logger log = Logger.getLogger(AreaDao.class);
	
	public boolean assignArea(int cid, int sid, String did, int adminID) {
		boolean flag = false;
		int r1 = assignCountry(cid, adminID);
		int r2 = assignState(r1, cid, sid);
		int r3 = assignDistricts(r2, sid, did);
		if(r3>0) {
			flag = true;
		}
		return flag;
	}
	
	
	/**
	 * 
	 * @param asid
	 *            -- allowed state id
	 * @param sid
	 *            -- state id
	 * @param did
	 *            -- district ID
	 * @return
	 */
	private int assignDistricts(int asid, int sid, String did) {

		int flag = 0;
		did = filterOutAlreadyExistingDistricts(asid, sid, did);
		String arr1[] = did.split(",");
		for(String dd : arr1) {
		flag = isDistrictAlreadyAdded(sid, asid, Integer.parseInt(dd));
		
		PreparedStatement pstmt = null;
		if (flag == -1) {
			String sql = "insert into pi_allowed_districts (allowed_states_id, state_id, allowed_districts, updated_date) values (?,?,?, CURDATE())";
			Connection con = DBUtil.getConnection();
			try {
				pstmt = con.prepareStatement(sql);
				pstmt.setInt(1, asid);
				pstmt.setInt(2, sid);
				pstmt.setString(3, dd+",");
				pstmt.executeUpdate();
				con.commit();
			} catch (SQLException e) {
				log.error("Error occured while adding districts "
						+ e.getMessage());
			} finally {
				DBUtil.closeConnection(con);
			}

		} else if (flag == 0) {
			String sql1 = "select * from pi_allowed_districts where allowed_states_id=?";
			String sql2 = "update pi_allowed_districts set allowed_districts=? , updated_date=CURDATE() where state_id=? and allowed_states_id=?";
			Connection con = DBUtil.getConnection();
			try {
				log.error("finding already assigned districts ");
				pstmt = con.prepareStatement(sql1);
				pstmt.setInt(1, asid);
				ResultSet rs = pstmt.executeQuery();
				String id = null;
				String allowed_districts = null;
				while (rs.next()) {
					id = rs.getString("id");
					allowed_districts = rs.getString("allowed_districts");
				}
				rs = null;
				pstmt = null;
				allowed_districts += "," + dd;
				pstmt = con.prepareStatement(sql2);
				pstmt.setString(1, allowed_districts);
				pstmt.setInt(2, sid);
				pstmt.setInt(3, asid);
				int rows = pstmt.executeUpdate();
				con.commit();
				log.error("districts " + did + " successfully added.");
				
			} catch (SQLException e) {
				log.error("Error occured while assigning districts "
						+ e.getMessage());
			} finally {
				DBUtil.closeConnection(con);
			}
		}// if
		}//if
		String arr[] = did.split(",");
		flag = isDistrictAlreadyAdded(sid, asid, Integer
				.parseInt(arr[arr.length - 1]));
		return flag;

	}

	private String filterOutAlreadyExistingDistricts(int asid, int sid,
			String did) {
		String tempDid = "";
		StringTokenizer token = new StringTokenizer(did, ",");
		while (token.hasMoreElements()) {
			String temp = (String) token.nextElement();
			if (null != temp && temp.length() > 0) {
				int tdid = Integer.parseInt(temp);
				int result = isDistrictAlreadyAdded(sid, asid, tdid);
				if (result == 0 || result == -1) {
					tempDid += tdid + ",";
				}

			}// if
		}
		/*if (tempDid.length() > 1) {
			tempDid = tempDid.substring(0, tempDid.length() - 1);
		}*/
		return tempDid;
	}

	/**
	 * 
	 * @param acid
	 *            -- allowed country ID
	 * @param cid
	 *            -- country id
	 * @param sid
	 *            -- state ID
	 * @return
	 */
	private int assignState(int acid, int cid, int sid) {
		int flag = 0;
		flag = isStateAlreadyAdded(sid, cid, acid);
		PreparedStatement pstmt = null;
		if (flag == -1) {
			String sql = "insert into pi_allowed_states (all_countries_id, country_id,allowed_states,updated_date) values (?,?,?,CURDATE())";
			Connection con = DBUtil.getConnection();
			try {
				pstmt = con.prepareStatement(sql);
				pstmt.setInt(1, acid);
				pstmt.setInt(2, cid);
				pstmt.setString(3, Integer.toString(sid) + ",");
				pstmt.executeUpdate();
				con.commit();
			} catch (SQLException e) {
				log
						.error("Error occured while adding states "
								+ e.getMessage());
			} finally {
				DBUtil.closeConnection(con);
			}

		} else if (flag == 0) {
			String sql1 = "select * from pi_allowed_states where all_countries_id=?";
			String sql2 = "update pi_allowed_states set allowed_states=? , updated_date=CURDATE() where all_countries_id=?";
			Connection con = DBUtil.getConnection();
			try {
				log.debug("finding already assigned states ");
				pstmt = con.prepareStatement(sql1);
				pstmt.setInt(1, acid);
				ResultSet rs = pstmt.executeQuery();
				String allowedStates = null;
				while (rs.next()) {
					allowedStates = rs.getString("allowed_states");
				}
				rs = null;
				pstmt = null;
				allowedStates += Integer.toString(sid) + ",";
				pstmt = con.prepareStatement(sql2);
				pstmt.setString(1, allowedStates);
				pstmt.setInt(2, acid);
				pstmt.executeUpdate();
				con.commit();
				log.debug("State " + sid + " successfully added.");
				
			} catch (SQLException e) {
				log.error("Error occured while assigning state "
						+ e.getMessage());
			} finally {
				DBUtil.closeConnection(con);
			}
		}// if
		flag = isStateAlreadyAdded(sid, cid, acid);
		return flag;
	}

	private int assignCountry(int cid, int aid) {
		int flag = 0;
		flag = isCountryAlreadyAdded(cid, aid);
		PreparedStatement pstmt = null;
		if (flag == -1) {
			// new record is created for this user
			String sql = "insert into pi_allowed_countries (aid,allowed_countries,updated_date) values (?,?,CURDATE())";
			Connection con = DBUtil.getConnection();
			try {
				log.error("trying to assign country to user " + aid);
				pstmt = con.prepareStatement(sql);
				pstmt.setInt(1, aid);
				pstmt.setString(2, Integer.toString(cid) + ",");
				pstmt.executeUpdate();
				con.commit();
			} catch (SQLException e) {
				log.error("Error occured while adding countries "
						+ e.getMessage());
			} finally {
				DBUtil.closeConnection(con);
			}
		} else if (flag == 0) {
			String sql = "update table pi_allowed_countries set allowed_countries=? ,updated_date=CURDATE() where (aid=?)";
			String sql1 = "select * from pi_allowed_countries where aid=?";
			Connection con = DBUtil.getConnection();
			try {
				log.error("trying to assign country to user " + aid);
				pstmt = con.prepareStatement(sql1);
				ResultSet rs = pstmt.executeQuery();
				String allowedCountries = "";
				while (rs.next()) {
					allowedCountries = rs.getString("allowed_countries");
				}
				rs = null;
				pstmt = null;
				allowedCountries += (Integer.toString(cid) + ",");
				pstmt = con.prepareStatement(sql);
				int rows = pstmt.executeUpdate();
				if (rows > 0) {
					con.commit();
				}
			} catch (SQLException e) {
				log.error("Error occured while adding countries "
						+ e.getMessage());
			} finally {
				DBUtil.closeConnection(con);
			}
		}// else if
		flag = isCountryAlreadyAdded(cid, aid);
		return flag;
	}

	/**
	 * 
	 * @param cid
	 * @param aid
	 * @return flag value -1 indicates no entry for this aid is present. flag
	 *         value 0 indicates entry for aid is there but this country is not
	 *         present.
	 */

	private int isCountryAlreadyAdded(int cid, int aid) {
		int flag = -1;
		String sql1 = "select * from pi_allowed_countries where aid=?";
		PreparedStatement pstmt = null;
		Connection con = DBUtil.getConnection();
		try {
			log.debug("isCountryAlreadyAdded\n " + sql1);
			pstmt = con.prepareStatement(sql1);
			pstmt.setInt(1, aid);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				flag = 0;
				String id = Integer.toString(cid);
				String ac = rs.getString("allowed_countries");
				if (ac.contains(id)) {
					flag = rs.getInt("id");
					break;
				}// if
			}

		} catch (SQLException e) {
			log.error("Error occured while isCountryAlreadyAdded "
					+ e.getMessage());
		} finally {
			DBUtil.closeConnection(con);
		}

		return flag;
	}

	/**
	 * @return flag value -1 indicates no entry for this aid is present. flag
	 *         value 0 indicates entry for aid is there but this country is not
	 *         present.
	 */
	private int isStateAlreadyAdded(int sid, int cid, int id) {
		int flag = -1;
		String sql1 = "select * from pi_allowed_states where all_countries_id=? and country_id=?";
		PreparedStatement pstmt = null;
		Connection con = DBUtil.getConnection();
		try {
			log.debug("isStateAlreadyAdded\n " + sql1);
			pstmt = con.prepareStatement(sql1);
			pstmt.setInt(1, id);
			pstmt.setInt(2, cid);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				flag = 0;
				String stateID = Integer.toString(sid);
				String ac = rs.getString("allowed_states");
				if (ac.contains(stateID)) {
					flag = rs.getInt("id");
					break;
				}// if
			}

		} catch (SQLException e) {
			log.error("Error occured while isCountryAlreadyAdded "
					+ e.getMessage());
		} finally {
			DBUtil.closeConnection(con);
		}

		return flag;
	}

	private int isDistrictAlreadyAdded(int sid, int asid, int did) {
		int flag = -1;
		String sql1 = "select * from pi_allowed_districts where allowed_states_id=? and state_id=?";
		PreparedStatement pstmt = null;
		Connection con = DBUtil.getConnection();
		try {
			log.debug("isDistrictAlreadyAdded\n " + sql1);
			pstmt = con.prepareStatement(sql1);
			pstmt.setInt(1, asid);
			pstmt.setInt(2, sid);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				flag = 0;
				String districtID = Integer.toString(did);
				String ac = rs.getString("allowed_districts");
				if (ac.contains(districtID)) {
					flag = rs.getInt("id");
					break;
				}// if
			}// while
		} catch (SQLException e) {
			log.error("Error occured while isDistrictAlreadyAdded "
					+ e.getMessage());
		} finally {
			DBUtil.closeConnection(con);
		}
		return flag;
	}

	/**
	 * did is comma separated list of removed districts.
	 * 
	 * @param adminId
	 * @param did
	 * @return
	 */
	public boolean removeDistricts(int adminId, String did, int sid) {
		boolean flag = false;
		String sql = "select id, allowed_districts from pi_allowed_districts where allowed_states_id=(select id from pi_allowed_states where all_countries_id=(select id from pi_allowed_countries where aid=?)) and state_id=?";
		PreparedStatement pstmt = null;
		Connection con = DBUtil.getConnection();
		int id = 0;
		String oriDistricts = "";
		// trying to retrieve already allowed districts.
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, adminId);
			pstmt.setInt(2, sid);
			log.error("retriving list of all allowed districts");
			log.error(sql);
			ResultSet rs = pstmt.executeQuery();

			while (rs.next()) {
				id = rs.getInt("id");
				oriDistricts = rs.getString("allowed_districts");
			}
			rs = null;
			pstmt = null;
		} catch (SQLException e) {
			log.error("Error occured while removeDistricts " + e.getMessage());
		}
		// trying to make new list of districts.
		String newDistricts = prepareNewDistricList(oriDistricts, did);
		sql = "update pi_allowed_districts set allowed_districts=?, updated_date=CURDATE() where id=?";
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, newDistricts);
			pstmt.setInt(2, id);
			pstmt.executeUpdate();
			con.commit();
			flag = true;
		} catch (SQLException se) {
			log.error("Error occured while updating new list of districts "
					+ se.getMessage());
		} finally {
			DBUtil.closeConnection(con);
		}
		return flag;
	}

	private String prepareNewDistricList(String oldList, String removedDisttList) {
		String newList = "";
		String[] old = oldList.split(",");
		String[] rem = removedDisttList.split(",");
		//find and remove dist.
		for(int i=0; i<old.length;i++) {
			for(int j=0;j<rem.length;j++) {
				String old0 = old[i];				
				String rem0 = rem[j];
				if(rem0.equals(old0)) {
					old[i] = "";
				}				
			}//for					
		}//for
				
		for(String s : old) {
			if(s.length()>0) {
				newList += s +",";
			}
		}
		log.debug("New list of districts are: " + newList);
		return newList;

	}

	public Set<Country> getCountriesOfAdmin(int adminID) {
		Set<Country> count = new HashSet<Country>();
		Connection con = null;
		String sql = "select * from pi_allowed_countries where aid=?";
		String all_count = null;
		try {
			con = DBUtil.getConnection();
			PreparedStatement pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, adminID);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				all_count = rs.getString("allowed_countries");
				String[] cid = all_count.split(",");
				for (int i = 0; i < cid.length; i++) {
					if (cid[i].length() > 0) {
						Country c = new Country();
						int temp = Integer.parseInt(cid[i]);
						c.setId(temp);
						c.setName(getCountryName(temp));
						count.add(c);
					}// if
				}// for
			}
			pstmt = null;
		} catch (SQLException e) {
			log.error("error occured while retrieving countries "
					+ e.getMessage());
		}finally {
			DBUtil.closeConnection(con);
		}

		return count;
	}
	
	
	
	

	public String getCountryName(int id) {
		String sql = "select name from country where id=?";
		Connection con = null;
		String name = null;
		try {
			con = DBUtil.getConnection();
			PreparedStatement pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, id);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				name = rs.getString("name");
			}
		} catch (SQLException e) {
			log.error("error occured while retreiving country names "
					+ e.getMessage());
		} finally {
			DBUtil.closeConnection(con);
		}
		return name;
	}

	public String getStateName(int id) {
		String sql = "select name from states where id=?";
		Connection con = null;
		String name = null;
		try {
			con = DBUtil.getConnection();
			PreparedStatement pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, id);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				name = rs.getString("name");
			}
		} catch (SQLException e) {
			log.error("error occured while retreiving state names "
					+ e.getMessage());
		} finally {
			DBUtil.closeConnection(con);
		}
		return name;
	}

	public String getDistrictName(int id) {
		String sql = "select name from districts where id=?";
		Connection con = null;
		String name = null;
		try {
			con = DBUtil.getConnection();
			PreparedStatement pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, id);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				name = rs.getString("name");
			}
		} catch (SQLException e) {
			log.error("error occured while retreiving district names "
					+ e.getMessage());
		} finally {
			DBUtil.closeConnection(con);
		}
		return name;
	}

	public Set<State> getAdminCountryStates(int aid, int cid) {
		String sql = "select * from pi_allowed_states where all_countries_id = (select id from pi_allowed_countries where aid=?) and country_id=?";
		Connection con = null;
		Set<State> st = new HashSet<State>();
		try {
			con = DBUtil.getConnection();
			PreparedStatement pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, aid);
			pstmt.setInt(2, cid);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				String all_st = rs.getString("allowed_states");

				String[] sid = all_st.split(",");
				for (int i = 0; i < sid.length; i++) {
					if (sid[i].length() > 0) {
						State c = new State();
						int temp = Integer.parseInt(sid[i]);
						c.setId(temp);
						c.setName(getStateName(temp));
						st.add(c);
					}// if
				}// for

			}
		} catch (SQLException e) {
			log.error("error occured while retreiving district names "
					+ e.getMessage());
		} finally {
			DBUtil.closeConnection(con);
		}

		return st;

	}

	public Set<State> getAdminAssignedDistricts(Set<State> state, int adminID) {

		String sql = "select id, allowed_districts from pi_allowed_districts where "
				+ "allowed_states_id=(select id from pi_allowed_states where "
				+ "all_countries_id=(select id from pi_allowed_countries where aid=?)) and state_id=?";

		Connection con = DBUtil.getConnection();
		for (State s : state) {
			int sid = s.getId();

			try {
				PreparedStatement pstmt = con.prepareStatement(sql);
				pstmt.setInt(1, adminID);
				pstmt.setInt(2, sid);
				ResultSet rs = pstmt.executeQuery();
				String[] dids = null;
				while (rs.next()) {
					String temp = rs.getString("allowed_districts");
					dids = temp.split(",");

					for (int i = 0; i < dids.length; i++) {
						if (dids[i].length() > 0) {
							District d = new District();
							int temp0 = Integer.parseInt(dids[i]);
							d.setId(temp0);
							d.setName(getDistrictName(temp0));
							s.addDistrict(d);
						}// if
					}// for
				}
			} catch (SQLException e) {
				log
						.error("error occured while reyreiving state specific assigned districts "
								+ e.getMessage());
				DBUtil.closeConnection(con);
				return state;
			}

		}// for
		DBUtil.closeConnection(con);
		return state;
	}

}

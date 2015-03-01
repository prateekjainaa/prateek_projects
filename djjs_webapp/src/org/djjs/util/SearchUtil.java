
package org.djjs.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.djjs.dao.AreaDao;
import org.djjs.model.District;
import org.djjs.model.SearchVO;
import org.djjs.model.State;

/**
 * /**
 * 
 * @author pjain
 * 
 */
public class SearchUtil {

    public SearchVO fillSearchVO(HttpServletRequest req, SearchVO vo) {

	Enumeration<?> names = req.getParameterNames();

	while (names.hasMoreElements()) {
	    String name = (String) names.nextElement();
	    
	    if(name.equals("sewaSelectionName")) {
	    vo.setSewaIds(req.getParameter("sewaSelectionName"));
	    } else if (name.equals("firstNameText")) {
		vo.setFirstNameText(req.getParameter("firstNameText"));
	    } else if (name.equals("lastNameText")) {
		vo.setLastNameText(req.getParameter("lastNameText"));
	    } else if (name.equals("gaurdianNameText")) {
		vo.setGaurdianNameText(req.getParameter("gaurdianNameText"));
	    } else if (name.equals("townNameText")) {
		vo.setTownNameText(req.getParameter("townNameText"));
	    } else if (name.equals("fullAddText")) {
		vo.setFullAddText(req.getParameter("fullAddText"));
	    } else if (name.equals("dateOfBirth")) {
		vo.setDateOfBirth(req.getParameter("dateOfBirth"));
		if (vo.getDateOfBirth().equalsIgnoreCase("undefined")) {
		    vo.setDateOfBirth(null);
		}
	    } else if (name.equals("country_hidden_name")) {
		vo.setCountryCodes(req.getParameter("country_hidden_name"));
	    } else if (name.equals("state_hidden_name")) {
		vo.setStateCodes(req.getParameter("state_hidden_name"));
	    } else if (name.equals("district_hidden_name")) {
		vo.setDistrictCodes(req.getParameter("district_hidden_name"));
	    } else if (name.equals("sex")) {
		String temp = req.getParameter("sex");
		if (temp.equals("1")) {
		    temp = "M";
		} else {
		    temp = "F";
		}
		vo.setSex(temp);
	    } else if (name.equals("swami")) {
		vo.setSelectSwami(req.getParameter("swami"));
	    } else if (name.equals("sam")) {
		String temp = req.getParameter("sam");
		vo.setIsvip(temp);
	    } else if (name.equals("sam2")) {
			vo.setWorkCategory(req.getParameter("sam2"));
	    } else if (name.equals("phoneMobileText")) {
		vo.setPhoneMobileText(req.getParameter("phoneMobileText"));
	    } else if (name.equals("emailText")) {
		vo.setEmailText(req.getParameter("emailText"));
	    } else if (name.equals("phoneLandText")) {
		vo.setPhoneLandText(req.getParameter("phoneLandText"));
	    } else if (name.equals("relatedToText")) {
		vo.setRelatedToText(req.getParameter("relatedToText"));
	    } else if (name.equals("qualificationText")) {
		vo.setQualificationText(req.getParameter("qualificationText"));
	    } else if (name.equals("occupationText")) {
		vo.setOccupationText(req.getParameter("occupationText"));
	    } else if (name.equals("otherProfText")) {
		vo.setOtherProfText(req.getParameter("otherProfText"));
	    } else if (name.equals("deekshaAshramText")) {
		vo.setDeekshaAshramText(req.getParameter("deekshaAshramText"));
	    } else if (name.equals("deekshaDate")) {
		vo.setDeekshaDate(req.getParameter("deekshaDate"));
		if (vo.getDeekshaDate().equalsIgnoreCase("undefined")) {
		    vo.setDeekshaDate(null);
		}
	    } else if (name.equals("maxddate")) {
		vo.setMaxDeekshaDate(req.getParameter("maxddate"));
		if (vo.getMaxDeekshaDate().equalsIgnoreCase("undefined")) {
		    vo.setMaxDeekshaDate(null);
		}
	    } else if (name.equals("maxdob")) {
		vo.setMaxDateOfBirth(req.getParameter("maxdob"));
		if (vo.getMaxDateOfBirth().equalsIgnoreCase("undefined")) {
		    vo.setMaxDateOfBirth(null);
		}
	    }
	}

	return vo;
    }

    public Map<String, Object> makeQueryFromMemberTable(SearchVO vo) {
	boolean useAnd = false;
	Map<String, Object> map = new HashMap<String, Object>();
	if (!isNull(vo.getDateOfBirth())) {
	    map.put("dob", vo.getDateOfBirth());
	}
	if (!isNull(vo.getMaxDateOfBirth())) {
	    map.put("maxdob", vo.getMaxDateOfBirth());
	}
	if (!isNull(vo.getEmailText())) {
	    map.put("email", vo.getEmailText());
	}
	if (!isNull(vo.getFirstNameText())) {
	    map.put("first_name", vo.getFirstNameText());
	}
	if (!isNull(vo.getLastNameText())) {
	    map.put("last_name", vo.getLastNameText());
	}
	if (!isNull(vo.getPhoneMobileText())) {
	    map.put("phone_mobile", vo.getPhoneMobileText());
	}
	if (!isNull(vo.getPhoneLandText())) {
	    map.put("phone_landline", vo.getPhoneLandText());
	}
	if (!isNull(vo.getSex())) {
	    map.put("Sex", vo.getSex());
	}
	if (!isNull(vo.getSelectSwami())) {
	    String tempStatus = vo.getSelectSwami();
	    if (tempStatus.equals("1")) {
		map.put("is_swami", "Y");
	    } else if (tempStatus.equals("2")) {
		map.put("is_premi", "Y");
	    } else if (tempStatus.equals("3")) {
		map.put("is_deekshit", "N");
	    } else {
		map.put("is_deekshit", "Y");
	    }
	}// if
	if (!isNull(vo.getIsvip())) {
	    map.put("is_vip", vo.getIsvip());
	}
	if (!isNull(vo.getRelatedToText())) {
	    map.put("relatedTO", vo.getRelatedToText());
	}
	if(!isNull(vo.getSewaIds())) {
		map.put("sewa_ids", vo.getSewaIds());
	} 
	if(!isNull(vo.getWorkCategory())) {
		map.put("work_category", vo.getWorkCategory());
	} 
	// TODO fill up filter for address, qualification and deeksha.
	return map;
    }

    private boolean isNull(String s) {
	if ((s == null) || (s.length() == 0)) {
	    return true;
	}
	return false;
    }

    public String makeQuery1(Map<String, Object> map) {
	StringBuilder query = new StringBuilder();
	query.append("Select mid from members where (");
	Set<String> set = map.keySet();
	query.append(" Sex='" + map.get("Sex").toString() + "' ");
	set.remove("Sex");
	for (String s : set) {
	    if (s.equals("maxdob")) {
		String sqlDate = convertToSqlDate(map.get("maxdob").toString());
		query.append(" and dob <= DATE_FORMAT('" + sqlDate
			+ "','%Y-%m-%d') ");
	    } else if (s.equals("dob")) {
		String sqlDate = convertToSqlDate(map.get("dob").toString());
		query.append(" and dob >= DATE_FORMAT('" + sqlDate
			+ "','%Y-%m-%d') ");
	    } else if (s.equals("relatedTO")) {
		query.append(" and relatedTO="
			+ map.get("relatedTO").toString() + " ");
	    } else {
		query.append(" and " + s + " like '%" + map.get(s).toString().toUpperCase()
			+ "%'");
	    }

	}// for
	query.append(") ");
	return query.toString();

    }

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

    public String makeQuery2(SearchVO vo) {
	String c = vo.getCountryCodes();
	String s = vo.getStateCodes();
	String d = vo.getDistrictCodes();
	String t = vo.getTownNameText();
	if (s.contains("Select") || StringUtils.isEmpty(s)) {
		s = null;
	}
	if (d.contains("Select") || StringUtils.isEmpty(d)) {
		d = null;
	}

	StringBuilder b = new StringBuilder();
	boolean and = false;
	b.append("select mid from address where (");
	if ((null == c || c.length() == 0) && (null == t || t.length() == 0)) {
		return null;
	}
	if (null != c && c.length() > 0) {
		b.append(" cid=" + vo.getCountryCodes());
		and = true;
	}

	// /
	if (null == d) {
		AreaDao dao1 = new AreaDao();
		d = "";
		for (String cid : c.split("~")) {

			Set<State> st = dao1.getAdminCountryStates(vo.getAdminId(),
					Integer.parseInt(cid));
			st = dao1.getAdminAssignedDistricts(st, vo.getAdminId());
			for (State state : st) {
				Set<District> dists = state.getDistricts();
				for(District dist : dists) {
					d = d.concat(dist.getId()+"~");
				}
			}// for

		}// for
	}
	// /

	if (null != s && s.length() > 0) {
		b.append(" and sid IN (" + s.replaceAll("~", ",") + ")");
	}
	if (null != d && d.length() > 0) {
	    	d = d.replaceAll("~", ",");
	    	if(d.endsWith(",")) {
	    	    d = d.substring(0, d.length()-1);
	    	}
		b.append(" and did IN (" + d + ")");
	}
	if (null != t && t.length() > 0) {
		if (and) {
			b.append(" and ");
		}
		b.append(" town='" + t + "'");
	}
	b.append(")");

	StringBuilder upper = new StringBuilder();
	upper.append("select mid from members where mid IN (");
	upper.append(b.toString());
	upper.append(")");

	return upper.toString();
    }

    public String makeQuery3(SearchVO vo) {
	String d1 = vo.getDeekshaDate();
	String d2 = vo.getMaxDeekshaDate();
	StringBuilder b = new StringBuilder();
	b.append("select mid from deeksha where (");
	String sqlDate = convertToSqlDate(d1);
	String sqlDate2 = convertToSqlDate(d2);
	b.append(" deeksha_date >= DATE_FORMAT('" + sqlDate + "','%Y-%m-%d') ");
	b.append(" and deeksha_date < DATE_FORMAT('" + sqlDate2 + "','%Y-%m-%d') ");
	b.append(")");
	StringBuilder upper = new StringBuilder();
	upper.append("select mid from members where mid IN (");
	upper.append(b.toString());
	upper.append(")");

	return upper.toString();
    }

    public String makeQuery4(SearchVO vo) {
	String s1 = vo.getQualificationText();
	String s2 = vo.getOccupationText();
	String s3 = vo.getOtherProfText();
	boolean useAnd = false;
	if (null != s1 && s1.length() > 0) {
	    useAnd = true;
	}
	StringBuilder b = new StringBuilder();
	b.append("select mid from qualification where (");
	if (useAnd) {
	    b.append(" education like '%"+s1.toUpperCase()+"%'");
	}
	boolean useAnd2 = false;
	if (null != s2 && s2.length() > 0) {
	    useAnd2 = true;
	}
	if (useAnd && useAnd2) {
	    b.append(" , ");
	}
	if (useAnd2) {
	    useAnd = true;
	    b.append(" occupation like '%"+s2.toUpperCase()+"%'");
	}

	boolean useAnd3 = false;
	if (null != s3 && s3.length() > 0) {
	    useAnd3 = true;
	}
	if (useAnd3 && (useAnd2 || useAnd)) {
	    b.append(" , ");
	}
	if (useAnd3) {
	    b.append(" other like '%"+s3.toUpperCase()+"%'");
	}
	b.append(")");
	
	StringBuilder upper = new StringBuilder();
	upper.append("select mid from members where mid IN (");
	upper.append(b.toString());
	upper.append(")");
		
	return upper.toString();
    }

}

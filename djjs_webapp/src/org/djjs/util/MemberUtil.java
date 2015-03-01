/**
 * Jul 4, 2009
 * @author Prateek Jain
 */
package org.djjs.util;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.fileupload.FileItem;
import org.djjs.model.MemberVO;
import org.djjs.model.SearchVO;

public class MemberUtil {
	private final String IMAGE_STORE;
	private final String DOC_STORE;
	private MemberUtil() {
		IMAGE_STORE = System.getenv("image_store");
		DOC_STORE = System.getenv("doc_store");
	}

	
	private static MemberUtil mu = null;

	public static MemberUtil getInstance() {
		if (null != mu) {
			return mu;
		}
		return new MemberUtil();
	}

	public MemberVO extractFieldsFromForm(MemberVO vo, FileItem item) {
		String name = item.getFieldName();
		if (name.equals("firstNameText")) {
			vo.setFirstNameText(item.getString());
		} else if (name.equals("lastNameText")) {
			vo.setLastNameText(item.getString());
		} else if (name.equals("gaurdianNameText")) {
			vo.setGaurdianNameText(item.getString());
		} else if (name.equals("townNameText")) {
			vo.setTownNameText(item.getString());
		} else if (name.equals("fullAddText")) {
			vo.setFullAddText(item.getString());
		} else if (name.equals("dateOfBirth")) {
			vo.setDateOfBirth(item.getString());
		} else if (name.equals("country_hidden_name")) {
			vo.setCountryCodes(item.getString());
		} else if (name.equals("state_hidden_name")) {
			vo.setStateCodes(item.getString());
		} else if (name.equals("district_hidden_name")) {
			vo.setDistrictCodes(item.getString());
		} else if("sewaSelectionName".equals(name)) {
			vo.setSewaIds(item.getString());
		} else if (name.equals("sex")) {
			String temp = item.getString();
			if (temp.equals("1")) {
				temp = "M";
			} else {
				temp = "F";
			}
			vo.setSex(temp);
		} else if (name.equals("swami")) {
			vo.setSelectSwami(item.getString());
		} else if (name.equals("sam")) {
			String temp = item.getString();
			/*if (temp.equals("1")) {
				temp = "Y";
			} else {
				temp = "N";
			}*/
			vo.setIsvip(temp);
		} else if (name.equals("sam2")) { 
			vo.setWorkCategory(item.getString());
		} else if (name.equals("phoneMobileText")) {
			vo.setPhoneMobileText(item.getString());
		} else if (name.equals("emailText")) {
			vo.setEmailText(item.getString());
		} else if (name.equals("phoneLandText")) {
			vo.setPhoneLandText(item.getString());
		} else if (name.equals("relatedToText")) {
			vo.setRelatedToText(item.getString());
		} else if (name.equals("qualificationText")) {
			vo.setQualificationText(item.getString());
		} else if (name.equals("occupationText")) {
			vo.setOccupationText(item.getString());
		} else if (name.equals("otherProfText")) {
			vo.setOtherProfText(item.getString());
		} else if (name.equals("deekshaAshramText")) {
			vo.setDeekshaAshramText(item.getString());
		} else if (name.equals("deekshaDate")) {
			vo.setDeekshaDate(item.getString());
		} else if (name.equals("imagePath")) {
			vo.setImagePath(item.get());
		}
		return vo;
	}

	public boolean storeImage(byte[] data, String mID) {

		String pathname = IMAGE_STORE + "/" + mID + ".jpg";
		if (data == null || data.length == 0) {
			return true;
		}
		return writeFileInStore(data, pathname);
	}

	public boolean storeCSV(byte[] data, String adminIdWithTimestamp) {

		String pathname = IMAGE_STORE + "/" + adminIdWithTimestamp + ".csv";
		if (data == null || data.length == 0) {
			return true;
		}
		return writeFileInStore(data, pathname);
	}

	private boolean writeFileInStore(byte[] data, String pathname) {
		boolean flag = true;
		File f = new File(pathname);
		FileOutputStream fos = null;
		try {
			fos = new FileOutputStream(f);
			fos.write(data);
		} catch (FileNotFoundException e) {
			flag = false;
			e.printStackTrace();
		} catch (IOException e) {
			flag = false;
			e.printStackTrace();
		} finally {
			try {
				fos.flush();
				fos.close();
			} catch (IOException e) {
				flag = false;
				e.printStackTrace();
			}
		}
		return flag;
	}

	public boolean copyImageForDisplay(byte[] data, String mid) {
		boolean flag = true;
		if (data == null || data.length == 0) {
			return flag;
		}
		File f = new File(DOC_STORE + "/100988798789/members/faces/" + mid
				+ ".jpg");
		FileOutputStream fos = null;
		try {
			fos = new FileOutputStream(f);
			fos.write(data);
		} catch (FileNotFoundException e) {
			flag = false;
			e.printStackTrace();
		} catch (IOException e) {
			flag = false;
			e.printStackTrace();
		} finally {
			try {
				fos.flush();
				fos.close();
			} catch (IOException e) {
				flag = false;
				e.printStackTrace();
			}
		}
		return flag;
	}

	public SearchVO extractMemberInfoFromUI(HttpServletRequest req) {

		SearchVO vo = new SearchVO();
		vo = new SearchUtil().fillSearchVO(req, vo);
		vo.setMemberID(req.getParameter("mid"));
		vo.setStateCodes(req.getParameter("stateCodes"));
		vo.setSelectSwami(req.getParameter("swami"));
		vo.setRelatedToText(req.getParameter("relatedToText"));
		vo.setDistrictCodes(req.getParameter("districtCodes"));
		// vo.setDateOfBirth(dateOfBirth)
		vo.setCountryCodes(req.getParameter("countryCodes"));

		return vo;

	}

}

/**
 * Jul 4, 2009
 * @author Prateek Jain
 */
package org.djjs.model;

import java.io.Serializable;

public class MemberVO implements Serializable {
    private String memberID, firstNameText, lastNameText, townNameText,
	    fullAddText, gaurdianNameText, dateOfBirth, countryCodes,
	    stateCodes, districtCodes, sex, selectSwami, isvip,
	    phoneMobileText, emailText, phoneLandText, relatedToText,
	    qualificationText, occupationText, otherProfText,
	    deekshaAshramText, deekshaDate, sewaIds, workCategory;
    private byte[] imagePath;

    /**
     * @return the memberID
     */
    public String getMemberID() {
	return memberID;
    }

    /**
     * @param memberID
     *            the memberID to set
     */
    public void setMemberID(String memberID) {
	this.memberID = memberID;
    }

    /**
     * @return the firstNameText
     */
    public String getFirstNameText() {
	return firstNameText;
    }

    public String getWorkCategory() {
		return workCategory;
	}

	public void setWorkCategory(String workCategory) {
		this.workCategory = workCategory;
	}

	/**
     * @param firstNameText
     *            the firstNameText to set
     */
    public void setFirstNameText(String firstNameText) {
	this.firstNameText = firstNameText;
    }

    public String getSewaIds() {
		return sewaIds;
	}

	public void setSewaIds(String sewaIds) {
		this.sewaIds = sewaIds;
	}

	/**
     * @return the lastNameText
     */
    public String getLastNameText() {
	return lastNameText;
    }

    /**
     * @param lastNameText
     *            the lastNameText to set
     */
    public void setLastNameText(String lastNameText) {
	this.lastNameText = lastNameText;
    }

    /**
     * @return the townNameText
     */
    public String getTownNameText() {
	return townNameText;
    }

    /**
     * @param townNameText
     *            the townNameText to set
     */
    public void setTownNameText(String townNameText) {
	this.townNameText = townNameText;
    }

    /**
     * @return the fullAddText
     */
    public String getFullAddText() {
	return fullAddText;
    }

    /**
     * @param fullAddText
     *            the fullAddText to set
     */
    public void setFullAddText(String fullAddText) {
	this.fullAddText = fullAddText;
    }

    /**
     * @return the gaurdianNameText
     */
    public String getGaurdianNameText() {
	return gaurdianNameText;
    }

    /**
     * @param gaurdianNameText
     *            the gaurdianNameText to set
     */
    public void setGaurdianNameText(String gaurdianNameText) {
	this.gaurdianNameText = gaurdianNameText;
    }

    /**
     * @return the dateOfBirth
     */
    public String getDateOfBirth() {
	return dateOfBirth;
    }

    /**
     * @param dateOfBirth
     *            the dateOfBirth to set
     */
    public void setDateOfBirth(String dateOfBirth) {
	this.dateOfBirth = dateOfBirth;
    }

    /**
     * @return the countryCodes
     */
    public String getCountryCodes() {
	return countryCodes;
    }

    /**
     * @param countryCodes
     *            the countryCodes to set
     */
    public void setCountryCodes(String countryCodes) {
	this.countryCodes = countryCodes;
    }

    /**
     * @return the stateCodes
     */
    public String getStateCodes() {
	return stateCodes;
    }

    /**
     * @param stateCodes
     *            the stateCodes to set
     */
    public void setStateCodes(String stateCodes) {
	this.stateCodes = stateCodes;
    }

    /**
     * @return the districtCodes
     */
    public String getDistrictCodes() {
	return districtCodes;
    }

    /**
     * @param districtCodes
     *            the districtCodes to set
     */
    public void setDistrictCodes(String districtCodes) {
	this.districtCodes = districtCodes;
    }

    /**
     * @return the sex
     */
    public String getSex() {
	return sex;
    }

    /**
     * @param sex
     *            the sex to set
     */
    public void setSex(String sex) {
	this.sex = sex;
    }

    /**
     * @return the imagePath
     */
    public byte[] getImagePath() {
	return imagePath;
    }

    /**
     * @param imagePath
     *            the imagePath to set
     */
    public void setImagePath(byte[] imagePath) {
	this.imagePath = imagePath;
    }

    /**
     * @return the selectSwami
     */
    public String getSelectSwami() {
	return selectSwami;
    }

    /**
     * @param selectSwami
     *            the selectSwami to set
     */
    public void setSelectSwami(String selectSwami) {
	this.selectSwami = selectSwami;
    }

    /**
     * @return the isvip
     */
    public String getIsvip() {
	return isvip;
    }

    /**
     * @param isvip
     *            the isvip to set
     */
    public void setIsvip(String isvip) {
	this.isvip = isvip;
    }

    /**
     * @return the phoneMobileText
     */
    public String getPhoneMobileText() {
	return phoneMobileText;
    }

    /**
     * @param phoneMobileText
     *            the phoneMobileText to set
     */
    public void setPhoneMobileText(String phoneMobileText) {
	this.phoneMobileText = phoneMobileText;
    }

    /**
     * @return the emailText
     */
    public String getEmailText() {
	return emailText;
    }

    /**
     * @param emailText
     *            the emailText to set
     */
    public void setEmailText(String emailText) {
	this.emailText = emailText;
    }

    /**
     * @return the phoneLandText
     */
    public String getPhoneLandText() {
	return phoneLandText;
    }

    /**
     * @param phoneLandText
     *            the phoneLandText to set
     */
    public void setPhoneLandText(String phoneLandText) {
	this.phoneLandText = phoneLandText;
    }

    /**
     * @return the relatedToText
     */
    public String getRelatedToText() {
	return relatedToText;
    }

    /**
     * @param relatedToText
     *            the relatedToText to set
     */
    public void setRelatedToText(String relatedToText) {
	this.relatedToText = relatedToText;
    }

    /**
     * @return the qualificationText
     */
    public String getQualificationText() {
	return qualificationText;
    }

    /**
     * @param qualificationText
     *            the qualificationText to set
     */
    public void setQualificationText(String qualificationText) {
	this.qualificationText = qualificationText;
    }

    /**
     * @return the occupationText
     */
    public String getOccupationText() {
	return occupationText;
    }

    /**
     * @param occupationText
     *            the occupationText to set
     */
    public void setOccupationText(String occupationText) {
	this.occupationText = occupationText;
    }

    /**
     * @return the otherProfText
     */
    public String getOtherProfText() {
	return otherProfText;
    }

    /**
     * @param otherProfText
     *            the otherProfText to set
     */
    public void setOtherProfText(String otherProfText) {
	this.otherProfText = otherProfText;
    }

    /**
     * @return the deekshaAshramText
     */
    public String getDeekshaAshramText() {
	return deekshaAshramText;
    }

    /**
     * @param deekshaAshramText
     *            the deekshaAshramText to set
     */
    public void setDeekshaAshramText(String deekshaAshramText) {
	this.deekshaAshramText = deekshaAshramText;
    }

    /**
     * @return the deekshaDate
     */
    public String getDeekshaDate() {
	return deekshaDate;
    }

    /**
     * @param deekshaDate
     *            the deekshaDate to set
     */
    public void setDeekshaDate(String deekshaDate) {
	this.deekshaDate = deekshaDate;
    }

}
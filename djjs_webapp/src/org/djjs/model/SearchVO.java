/**
 * Aug 9, 2009
 * @author Prateek Jain
 */
package org.djjs.model;

/**
 * @author pjain
 *
 */
public class SearchVO extends MemberVO {
    
    String maxDateOfBirth, maxDeekshaDate;
    int adminId;

    /**
     * @return the maxDateOfBirth
     */
    public String getMaxDateOfBirth() {
        return maxDateOfBirth;
    }

    /**
     * @param maxDateOfBirth the maxDateOfBirth to set
     */
    public void setMaxDateOfBirth(String maxDateOfBirth) {
        this.maxDateOfBirth = maxDateOfBirth;
    }

    /**
     * @return the maxDeekshaDate
     */
    public String getMaxDeekshaDate() {
        return maxDeekshaDate;
    }

    /**
     * @param maxDeekshaDate the maxDeekshaDate to set
     */
    public void setMaxDeekshaDate(String maxDeekshaDate) {
        this.maxDeekshaDate = maxDeekshaDate;
    }

    /**
     * @return the adminId
     */
    public int getAdminId() {
        return adminId;
    }

    /**
     * @param adminId the adminId to set
     */
    public void setAdminId(int adminId) {
        this.adminId = adminId;
    }
    

}


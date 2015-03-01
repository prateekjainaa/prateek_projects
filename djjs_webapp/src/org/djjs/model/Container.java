/**
 * Jun 15, 2009
 * @author Prateek Jain
 */
package org.djjs.model;

import java.io.Serializable;

public class Container implements Serializable {
    private static final long serialVersionUID = 1L;
    private String name;
    private String value;
    private boolean defaultSelect;

    /**
     * Get the defaultSelect.
     * 
     * @return Returns the defaultSelect.
     */
    public boolean isDefaultSelect() {
	return defaultSelect;
    }

    /**
     * Get the property.
     * 
     * @return Returns the property.
     */
    public String getName() {
	return name;
    }

    /**
     * Get the value.
     * 
     * @return Returns the value.
     */
    public String getValue() {
	return value;
    }

    /**
     * set the defaultSelect.
     * 
     * @param defaultSelect
     *            The defaultSelect to set.
     */
    public void setDefaultSelect(String defaultSelect) {
	this.defaultSelect = Boolean.getBoolean(defaultSelect);
    }

    /**
     * set the property.
     * 
     * @param property
     *            The property to set.
     */
    public void setName(String property) {
	this.name = property;
    }

    /* (non-Javadoc)
     * @see java.lang.Object#hashCode()
     */
    @Override
    public int hashCode() {
	final int prime = 31;
	int result = 1;
	result = prime * result + ((name == null) ? 0 : name.hashCode());
	result = prime * result + ((value == null) ? 0 : value.hashCode());
	return result;
    }

    /* (non-Javadoc)
     * @see java.lang.Object#equals(java.lang.Object)
     */
    @Override
    public boolean equals(Object obj) {
	if (this == obj)
	    return true;
	if (obj == null)
	    return false;
	if (getClass() != obj.getClass())
	    return false;
	Container other = (Container) obj;
	if (name == null) {
	    if (other.name != null)
		return false;
	} else if (!name.equals(other.name))
	    return false;
	if (value == null) {
	    if (other.value != null)
		return false;
	} else if (!value.equals(other.value))
	    return false;
	return true;
    }

    /**
     * set the value.
     * 
     * @param value
     *            The value to set.
     */
    public void setValue(String value) {
	this.value = value;
    }

    /**
     * @return string
     */
    public String toString() {
	StringBuffer sb = new StringBuffer();
	sb.append("\n Name:" + this.getName() + "  Value:" + this.getValue());

	return sb.toString();
    }
}
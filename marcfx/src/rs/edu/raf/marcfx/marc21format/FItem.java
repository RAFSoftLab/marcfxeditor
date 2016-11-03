/***********************************************************************
 * Module:  FItem.java
 * Author:  dimicb
 * Purpose: Defines the Class FItem
 ***********************************************************************/

package rs.edu.raf.marcfx.marc21format;


public class FItem {
	
   private String code;
   private String value;
   
   
   
   
	public FItem() {
		super();
	}
	public FItem(String code, String value) {
		super();
		this.code = code;
		this.value = value;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}
	
	@Override
	public String toString() {
		return code+" "+value;
	}

}
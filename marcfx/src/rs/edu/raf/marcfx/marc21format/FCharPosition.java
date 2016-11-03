/***********************************************************************
 * Module:  FCharPosition.java
 * Author:  dimicb
 * Purpose: Defines the Class FCharPosition
 ***********************************************************************/

package rs.edu.raf.marcfx.marc21format;



public class FCharPosition {
	
   private int start;
   private int end;
   private String description;
   private String defaultValue;
   private String isSystemGenerated;
   private String externlCodeListId;   
   private FInternalCodeList fInternalCodeList;
   private Object owner;
   
   //konstruktor sa svim obaveznim atributima
   
   public FCharPosition(int start, int end, String description) {
				super();
				this.start = start;
				this.end = end;
				this.description = description;
   }


   public FCharPosition(){
	   
   }
   
   
   
   public String getIsSystemGenerated() {
      return isSystemGenerated;
   }
   
   /** @param newIsSystemGenerated */
   public void setIsSystemGenerated(String newIsSystemGenerated) {
      isSystemGenerated = newIsSystemGenerated;
   }
   
   public String getExternlCodeListId() {
      return externlCodeListId;
   }
   
   /** @param newExternlCodeListId */
   public void setExternlCodeListId(String newExternlCodeListId) {
      externlCodeListId = newExternlCodeListId;
   }

	public String getDefaultValue() {
		return defaultValue;
	}
	
	public void setDefaultValue(String defaultValue) {
		this.defaultValue = defaultValue;
	}
	
	public String getDescription() {
		return description;
	}
	
	public void setDescription(String description) {
		this.description = description;
	}
	
	public int getEnd() {
		return end;
	}
	
	public void setEnd(int end) {
		this.end = end;
	}
	
	public FInternalCodeList getFInternalCodeList() {
		return fInternalCodeList;
	}
	
	public void setFInternalCodeList(FInternalCodeList internalCodeList) {
		fInternalCodeList = internalCodeList;
	}
	
	public int getStart() {
		return start;
	}
	
	public void setStart(int start) {
		this.start = start;
	}



	public Object getOwner() {
		return owner;
	}



	public void setOwner(Object owner) {
		this.owner = owner;
	}

}
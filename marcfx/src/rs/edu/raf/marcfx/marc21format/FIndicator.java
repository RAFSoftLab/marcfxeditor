/***********************************************************************
 * Module:  FIndicator.java
 * Author:  dimicb
 * Purpose: Defines the Class FIndicator
 ***********************************************************************/

package rs.edu.raf.marcfx.marc21format;



public class FIndicator {
   private int index;
   private String description;
   private String defaultValue;
   private FDataField owner;
   
   private FInternalCodeList codeList;
   
   
   
   public FIndicator(int index, String description) {
	super();
	this.index = index;
	this.description = description;
   }

   public int getIndex() {
      return index;
   }
   
   /** @param newIndex */
   public void setIndex(int newIndex) {
      index = newIndex;
   }
   
   public String getDescription() {
      return description;
   }
   
   /** @param newDescription */
   public void setDescription(String newDescription) {
      description = newDescription;
   }
   
   public String getDefaultValue() {
      return defaultValue;
   }
   
   /** @param newDefaultValue */
   public void setDefaultValue(String newDefaultValue) {
      defaultValue = newDefaultValue;
   }

public FDataField getOwner() {
	return owner;
}

public void setOwner(FDataField owner) {
	this.owner = owner;
}

public FInternalCodeList getCodeList() {
	return codeList;
}

public void setCodeList(FInternalCodeList codeList) {
	this.codeList = codeList;
}

}
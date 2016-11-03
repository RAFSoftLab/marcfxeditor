/***********************************************************************
 * Module:  FSubfield.java
 * Author:  dimicb
 * Purpose: Defines the Class FSubfield
 ***********************************************************************/

package rs.edu.raf.marcfx.marc21format;

import java.util.*;

public class FSubfield {
	
   private char name;
   private String description;
   private boolean repeatable;
   private boolean mandatory;   
   private String defaultValue;
   private FInternalCodeList fInternalCodeList;
   private String externalCodeListId;
   private FDataField owner;  
   private List<FCharPosition> charPositions = new ArrayList<FCharPosition>();
   
   
   
   
   public FSubfield(char name, String description, boolean repeatable) {
				super();
				this.name = name;
				this.description = description;
				this.repeatable = repeatable;
   }

   public char getName() {
      return name;
   }
   
   /** @param newName */
   public void setName(char newName) {
      name = newName;
   }
   
   public String getDescription() {
      return description;
   }
   
   /** @param newDescription */
   public void setDescription(String newDescription) {
      description = newDescription;
   }
   
   public boolean getRepeatable() {
      return repeatable;
   }
   
   /** @param newRepeatable */
   public void setRepeatable(boolean newRepeatable) {
      repeatable = newRepeatable;
   }
   
   public boolean getMandatory() {
      return mandatory;
   }
   
   /** @param newMandatory */
   public void setMandatory(boolean newMandatory) {
      mandatory = newMandatory;
   }

	public FInternalCodeList getFInternalCodeList() {
		return fInternalCodeList;
	}
	
	public void setFInternalCodeList(FInternalCodeList internalCodeList) {
		fInternalCodeList = internalCodeList;
	}
	
	   
	   /** @pdGenerated default getter */
	   public List<FCharPosition> getCharPositions() {
	      if (charPositions == null)
	         charPositions = new ArrayList<FCharPosition>();
	      return charPositions;
	   }  
	   
	   /** @pdGenerated default setter
	     * @param newFCharPosition */
	   public void setCharPositions(List<FCharPosition> newFCharPosition) {
	      removeAllFCharPosition();
	      for (java.util.Iterator<FCharPosition> iter = newFCharPosition.iterator(); iter.hasNext();)
	         addFCharPosition((FCharPosition)iter.next());
	   }
	   
	   /** @pdGenerated default add
	     * @param newFCharPosition */
	   public void addFCharPosition(FCharPosition newFCharPosition) {
	      if (newFCharPosition == null)
	         return;
	      if (this.charPositions == null)
	         this.charPositions = new ArrayList<FCharPosition>();
	      if (!this.charPositions.contains(newFCharPosition))
	         this.charPositions.add(newFCharPosition);
	   }
	   
	   /** @pdGenerated default remove
	     * @param oldFCharPosition */
	   public void removeFCharPosition(FCharPosition oldFCharPosition) {
	      if (oldFCharPosition == null)
	         return;
	      if (this.charPositions != null)
	         if (this.charPositions.contains(oldFCharPosition))
	            this.charPositions.remove(oldFCharPosition);
	   }
	   
	   /** @pdGenerated default removeAll */
	   public void removeAllFCharPosition() {
	      if (charPositions != null)
	         charPositions.clear();
	   }

				public String getDefaultValue() {
					return defaultValue;
				}

				public void setDefaultValue(String defaultValue) {
					this.defaultValue = defaultValue;
				}

				public String getExternalCodeListId() {
					return externalCodeListId;
				}

				public void setExternalCodeListId(String externalCodeListId) {
					this.externalCodeListId = externalCodeListId;
				}
				
				public FDataField getOwner() {
					return owner;
				}

				public void setOwner(FDataField owner) {
					this.owner = owner;
				}
	
	@Override
	public String toString() {
		return "$"+name+" - "+description;
	}
	
	public String write(){
		return "$"+name;
	}

}
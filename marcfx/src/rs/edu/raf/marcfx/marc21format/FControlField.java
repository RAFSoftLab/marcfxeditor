/***********************************************************************
 * Module:  FControlField.java
 * Author:  dimicb
 * Purpose: Defines the Class FControlField
 ***********************************************************************/

package rs.edu.raf.marcfx.marc21format;

import java.util.ArrayList;
import java.util.List;



public class FControlField extends FField {
	
  
   private List<FCharPosition> fCharPositions = new ArrayList<FCharPosition>();
   private String materialType;
   private String materialCategory;
   private String isSystemGenerated;
   
   
   public FControlField(String name, String description, boolean repeatable) {
	super(name, description, repeatable);
   }

   /** @pdGenerated default getter */
   public List<FCharPosition> getFCharPosition() {
      if (fCharPositions == null)
         fCharPositions = new ArrayList<FCharPosition>();
      return fCharPositions;
   }   
  
   /** @pdGenerated default setter
     * @param newFCharPosition */
   public void setFCharPosition(List<FCharPosition> newFCharPosition) {
      removeAllFCharPosition();
      for (java.util.Iterator<FCharPosition> iter = newFCharPosition.iterator(); iter.hasNext();)
         addFCharPosition((FCharPosition)iter.next());
   }
   
   /** @pdGenerated default add
     * @param newFCharPosition */
   public void addFCharPosition(FCharPosition newFCharPosition) {
      if (newFCharPosition == null)
         return;
      if (this.fCharPositions == null)
         this.fCharPositions = new ArrayList<FCharPosition>();
      if (!this.fCharPositions.contains(newFCharPosition))
         this.fCharPositions.add(newFCharPosition);
   }
   
   /** @pdGenerated default remove
     * @param oldFCharPosition */
   public void removeFCharPosition(FCharPosition oldFCharPosition) {
      if (oldFCharPosition == null)
         return;
      if (this.fCharPositions != null)
         if (this.fCharPositions.contains(oldFCharPosition))
            this.fCharPositions.remove(oldFCharPosition);
   }
   
   /** @pdGenerated default removeAll */
   public void removeAllFCharPosition() {
      if (fCharPositions != null)
         fCharPositions.clear();
   }

			public String getMaterialCategory() {
				return materialCategory;
			}

			public void setMaterialCategory(String materialCategory) {
				this.materialCategory = materialCategory;
			}

			public String getMaterialType() {
				return materialType;
			}

			public void setMaterialType(String materialType) {
				this.materialType = materialType;
			}

			public String getIsSystemGenerated() {
				return isSystemGenerated;
			}

			public void setIsSystemGenerated(String isSystemGenerated) {
				this.isSystemGenerated = isSystemGenerated;
			}

}
/***********************************************************************
 * Module:  FDirectory.java
 * Author:  dimicb
 * Purpose: Defines the Class FDirectory
 ***********************************************************************/

package  rs.edu.raf.marcfx.marc21format;

import java.util.List;
import java.util.ArrayList;

public class FDirectory {
	
   private final boolean repeatable = false;   
   private List<FCharPosition> charPositions = new ArrayList<FCharPosition>();
   
   public FDirectory() {
	super();
   }

/** @pdGenerated default getter */
   public List<FCharPosition> getFCharPositions() {
      if (charPositions == null)
    	  charPositions = new ArrayList<FCharPosition>();
      return charPositions;
   }   
   
   /** @pdGenerated default setter
     * @param newFCharPosition */
   public void setFCharPositions(List<FCharPosition> newFCharPosition) {
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

}
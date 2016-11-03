/***********************************************************************
 * Module:  FLeader.java
 * Author:  dimicb
 * Purpose: Defines the Class FLeader
 ***********************************************************************/

package rs.edu.raf.marcfx.marc21format;

import java.util.*;

public class FLeader {
	
   private String name;
   private final boolean repeatable = false;
   private final boolean mandatory = true;
   
   private List<FCharPosition> charPositions;
   
   
   
   public FLeader() {
	super();
   }

public String getName() {
      return name;
   }
   
   /** @param newName */
   public void setName(String newName) {
      name = newName;
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
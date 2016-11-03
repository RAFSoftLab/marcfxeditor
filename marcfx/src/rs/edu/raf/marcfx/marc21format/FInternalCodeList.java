/***********************************************************************
 * Module:  FInternalCodeList.java
 * Author:  dimicb
 * Purpose: Defines the Class FInternalCodeList
 ***********************************************************************/

package rs.edu.raf.marcfx.marc21format;

import java.util.*;

public class FInternalCodeList {
   public List<FItem> items;
   
    
   
   public FInternalCodeList() {
	super();
   }


/** @pdGenerated default getter */
   public List<FItem> getItems() {
      if (items == null)
         items = new ArrayList<FItem>();
      return items;
   }
   
  
   /** @pdGenerated default setter
     * @param newFItem */
   public void setItems(List<FItem> newFItem) {
      removeAllFItem();
      for (java.util.Iterator<FItem> iter = newFItem.iterator(); iter.hasNext();)
         addFItem((FItem)iter.next());
   }
   
   /** @pdGenerated default add
     * @param newFItem */
   public void addFItem(FItem newFItem) {
      if (newFItem == null)
         return;
      if (this.items == null)
         this.items = new ArrayList<FItem>();
      if (!this.items.contains(newFItem))
         this.items.add(newFItem);
   }
   
   /** @pdGenerated default remove
     * @param oldFItem */
   public void removeFItem(FItem oldFItem) {
      if (oldFItem == null)
         return;
      if (this.items != null)
         if (this.items.contains(oldFItem))
            this.items.remove(oldFItem);
   }
   
   /** @pdGenerated default removeAll */
   public void removeAllFItem() {
      if (items != null)
         items.clear();
   }

}
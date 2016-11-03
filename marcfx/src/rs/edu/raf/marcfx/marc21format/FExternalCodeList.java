/***********************************************************************
 * Module:  FExternalCodeList.java
 * Author:  dimicb
 * Purpose: Defines the Class FExternalCodeList
 ***********************************************************************/

package rs.edu.raf.marcfx.marc21format;

import java.util.ArrayList;
import java.util.List;


public class FExternalCodeList {
	
   private String id;
   private String name;
   private String description;
   
   private List<FItem> items = new ArrayList<FItem>();
   
   
   
   public FExternalCodeList(String id) {
				super();
				this.id = id;
   }

   public String getDescription() {
      return description;
   }
   
   /** @param newDescription */
   public void setDescription(String newDescription) {
      description = newDescription;
   }
   
   public String getName() {
      return name;
   }
   
   /** @param newName */
   public void setName(String newName) {
      name = newName;
   }
   
   public String getId() {
      return id;
   }
   
   /** @param newId */
   public void setId(String newId) {
      id = newId;
   }

			public List<FItem> getItems() {
				return items;
			}

			public void setItems(List<FItem> items) {
				this.items = items;
			}
			
		 public void addFItem(FItem newFItem) {
    if (newFItem == null)
       return;
    if (this.items == null)
       this.items = new ArrayList<FItem>();
    if (!this.items.contains(newFItem))
       this.items.add(newFItem);
 }

}
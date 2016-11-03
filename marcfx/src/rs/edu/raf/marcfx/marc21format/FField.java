/***********************************************************************
 * Module:  FField.java
 * Author:  dimicb
 * Purpose: Defines the Class FField
 ***********************************************************************/

package rs.edu.raf.marcfx.marc21format;

import java.util.*;

public abstract class FField {
	
   protected String name;
   protected String description;
   protected boolean repeatable;
   protected boolean mandatory;
   
   
   
   public FField(String name, String description, boolean repeatable) {
	super();
	this.name = name;
	this.description = description;
	this.repeatable = repeatable;
   }

   public String getName() {
      return name;
   }
   
   /** @param newName */
   public void setName(String newName) {
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
   
   

}
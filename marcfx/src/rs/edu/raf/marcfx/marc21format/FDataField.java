/***********************************************************************
 * Module:  FDataField.java
 * Author:  dimicb
 * Purpose: Defines the Class FDataField
 ***********************************************************************/

package rs.edu.raf.marcfx.marc21format;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class FDataField extends FField {
	
   
   private FIndicator ind1;
   private FIndicator ind2;
   private List<FSubfield> subfields = new ArrayList<FSubfield>();
   
   public FDataField(String name, String description, boolean repeatable) {
   		super(name, description, repeatable);		
   }  
   
   /** @pdGenerated default getter */
   public List<FSubfield> getFSubfields() {
      if (subfields == null)
         subfields = new ArrayList<FSubfield>();
      return subfields;
   }  
   
   /** @pdGenerated default setter
     * @param newFSubfield */
   public void setFSubfield(List<FSubfield> newFSubfield) {
      removeAllFSubfield();
      for (java.util.Iterator<FSubfield> iter = newFSubfield.iterator(); iter.hasNext();)
         addFSubfield((FSubfield)iter.next());
   }
   
   /** @pdGenerated default add
     * @param newFSubfield */
   public void addFSubfield(FSubfield newFSubfield) {
      if (newFSubfield == null)
         return;
      if (this.subfields == null)
         this.subfields = new ArrayList<FSubfield>();
      if (!this.subfields.contains(newFSubfield))
         this.subfields.add(newFSubfield);
   }
   
   /** @pdGenerated default remove
     * @param oldFSubfield */
   public void removeFSubfield(FSubfield oldFSubfield) {
      if (oldFSubfield == null)
         return;
      if (this.subfields != null)
         if (this.subfields.contains(oldFSubfield))
            this.subfields.remove(oldFSubfield);
   }
   
   /** @pdGenerated default removeAll */
   public void removeAllFSubfield() {
      if (subfields != null)
    	  subfields.clear();
   }

			public FIndicator getInd1() {
				return ind1;
			}
			
			public void setInd1(FIndicator ind1) {
				this.ind1 = ind1;
			}
			
			public FIndicator getInd2() {
				return ind2;
			}
			
			public void setInd2(FIndicator ind2) {
				this.ind2 = ind2;
			}
			
			public FSubfield getFSubfield(char name){
			 Iterator<FSubfield> it = subfields.iterator();
			 while (it.hasNext()) {
			   FSubfield sf = (FSubfield)it.next();
			   if (sf.getName() == name)
			     return sf;
			 }
			 return null;
			}
			
			}
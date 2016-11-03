/***********************************************************************
 * Module:  FFormat.java
 * Author:  dimicb
 * Purpose: Defines the Class FFormat
 ***********************************************************************/

package rs.edu.raf.marcfx.marc21format;

import java.util.*;

import rs.edu.raf.marcfx.marc21records.DataField;


public class Format {
   private String name;
   private String description;
   
   private List<FField> fields = new ArrayList<FField>();
   private FLeader fLeader;
   private List<FExternalCodeList> externalCodeLists = new ArrayList<FExternalCodeList>();
   private FDirectory fDirectory;
   
   
   
   public Format() {
   	super();
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
   
   
   /** @pdGenerated default getter */
   public List<FField> getFields() {
      if (fields == null)
         fields = new ArrayList<FField>();
      return fields;
   }  
   
   public List<FDataField> getDataFields(){
	   List<FDataField> list = new LinkedList<>();
	   for(FField ff: fields){
		   if(ff instanceof FDataField){
			   FDataField fd = (FDataField)ff;
			   list.add(fd);
		   }
	   }
	   return list;
   }
   
   /** @pdGenerated default setter
     * @param newFField */
   public void setFields(List<FField> newFField) {
      removeAllFields();
      for (java.util.Iterator<FField> iter = newFField.iterator(); iter.hasNext();)
         addFField((FField)iter.next());
   }
   
   /** @pdGenerated default add
     * @param newFField */
   public void addFField(FField newFField) {
      if (newFField == null)
         return;
      if (this.fields == null)
         this.fields = new ArrayList<FField>();
      if (!this.fields.contains(newFField))
         this.fields.add(newFField);
   }
   
   /** @pdGenerated default remove
     * @param oldfields */
   public void removefields(FField oldfields) {
      if (oldfields == null)
         return;
      if (this.fields != null)
         if (this.fields.contains(oldfields))
            this.fields.remove(oldfields);
   }
   
   /** @pdGenerated default removeAll */
   public void removeAllFields() {
      if (fields != null)
         fields.clear();
   }
   /** @pdGenerated default getter */
   public List<FExternalCodeList> getExternalCodeLists() {
      if (externalCodeLists == null)
         externalCodeLists = new ArrayList<FExternalCodeList>();
      return externalCodeLists;
   }
   
   
   /** @pdGenerated default setter
     * @param newFExternalCodeList */
   public void setExternalCodeLists(List<FExternalCodeList> newFExternalCodeList) {
      removeAllFExternalCodeList();
      for (java.util.Iterator<FExternalCodeList> iter = newFExternalCodeList.iterator(); iter.hasNext();)
         addFExternalCodeList((FExternalCodeList)iter.next());
   }
   
   /** @pdGenerated default add
     * @param newFExternalCodeList */
   public void addFExternalCodeList(FExternalCodeList newFExternalCodeList) {
      if (newFExternalCodeList == null)
         return;
      if (this.externalCodeLists == null)
         this.externalCodeLists = new ArrayList<FExternalCodeList>();
      if (!this.externalCodeLists.contains(newFExternalCodeList))
         this.externalCodeLists.add(newFExternalCodeList);
   }
   
   /** @pdGenerated default remove
     * @param oldFExternalCodeList */
   public void removeExternalCodeLists(FExternalCodeList oldFExternalCodeList) {
      if (oldFExternalCodeList == null)
         return;
      if (this.externalCodeLists != null)
         if (this.externalCodeLists.contains(oldFExternalCodeList))
            this.externalCodeLists.remove(oldFExternalCodeList);
   }
   
   /** @pdGenerated default removeAll */
   public void removeAllFExternalCodeList() {
      if (externalCodeLists != null)
         externalCodeLists.clear();
   }

			public FLeader getFLeader() {
				return fLeader;
			}
			
			public void setFLeader(FLeader leader) {
				fLeader = leader;
			}
			
			public FDirectory getFDirectory() {
				return fDirectory;
			}
			
			public void setFDirectory(FDirectory directory) {
				fDirectory = directory;
			}
			
			public FField getFField(String name){
			 for (int i = 0; i < fields.size(); i++) {
			  FField field = (FField)fields.get(i);
			  if (field.getName().equals(name)) {
			    return field;
			  }
			}
			return null;
			}
			
			public FDataField getFDataField(String name){
				for (int i = 0; i < fields.size(); i++) {
			  FField field = (FField)fields.get(i);
			  if (field.getName().equals(name)) {
			  	if(field instanceof FDataField)
			    	return (FDataField)field;
			  }
			}
			return null;
			}
			
			public FSubfield getFSubfield (String name){
					 if (name.length() != 4)
					  return null;
						String fieldName = name.substring(0, 3);
						FField f = getFField(fieldName);
						if (f == null)
						  return null;
						if(!(f instanceof FDataField))
							return null;
						return ((FDataField)f).getFSubfield(name.charAt(3));
			}
			
			
			public List<String> getFieldsNames(){
				List<String> allFields = new ArrayList<String>();
				for(FField f:getFields()){
					allFields.add(f.getName());
				}
				return allFields;
					
			}
			
			public List<String> getSubfieldNames(){
				List<String> allSubfields = new ArrayList<String>();
				for(FField f:getFields()){
					if(f instanceof FDataField){
						FDataField df = (FDataField)f;
						for(FSubfield sf:df.getFSubfields())
							allSubfields.add(""+sf.getName());
					}
				}
				return allSubfields;					
			}
   

}
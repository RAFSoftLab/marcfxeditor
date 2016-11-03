package rs.edu.raf.marcfx.marc21format;


import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;



public class FormatHandler extends DefaultHandler {
	
	private Format format = null;
	
	
	public Format getFormat(){
		return format;
	}
	
	public void startDocument() throws SAXException {
 }
   
 public void endDocument() throws SAXException {
 }

 public void startElement(String namespaceURI, String lName, String qName, Attributes attrs) throws SAXException {
   if (qName.equals("format")) {
     String name = attrs.getValue("name");
     String desc = attrs.getValue("description");     
     format = new Format();
     format.setName(name);
     format.setDescription(desc);     
   }else if(qName.equals("leader")){
   		String name = attrs.getValue("name");
   		leader = new FLeader();
   		leader.setName(name);
   		format.setFLeader(leader);
   }else if(qName.equals("directory")){
   	 directory = new FDirectory();
   	 format.setFDirectory(directory);
   } else if (qName.equals("controlfield")) {
   	// required attributes
     String name = attrs.getValue("name");
     String desc = attrs.getValue("description");
     boolean repeatable = Boolean.valueOf(attrs.getValue("repeatable")).booleanValue();
     currControlField = new FControlField(name,desc,repeatable);
    // optional attributes
     if(attrs.getValue("mandatory")!=null){
     	boolean mandatory = Boolean.valueOf(attrs.getValue("mandatory")).booleanValue();
     	currControlField.setMandatory(mandatory);
     }
     if(attrs.getValue("materialType")!=null){
     	String materialType = attrs.getValue("materialType");
     	currControlField.setMaterialType(materialType);
     }
     if(attrs.getValue("materialCategory")!=null){
     	String materialCategory = attrs.getValue("materialCategory");
     	currControlField.setMaterialType(materialCategory);
     }
     if(attrs.getValue("isSystemGenerated")!=null){
     	String isSystemGenerated = attrs.getValue("isSystemGenerated");
     	currControlField.setIsSystemGenerated(isSystemGenerated);
     }    
     format.addFField(currControlField);
   }else if(qName.equals("datafield")){
	   	String name = attrs.getValue("name");
	    String desc = attrs.getValue("description");
	    boolean repeatable = Boolean.valueOf(attrs.getValue("repeatable")).booleanValue();
	    currDataField = new FDataField(name,desc,repeatable);
	    if(attrs.getValue("mandatory")!=null){
     	boolean mandatory = Boolean.valueOf(attrs.getValue("mandatory")).booleanValue();
     	currDataField.setMandatory(mandatory);
     }
	    format.addFField(currDataField);
   }else if(qName.equals("externalCodeList")){
   		String id = attrs.getValue("id");
   		currExternalCodeList = new FExternalCodeList(id);
   		if(attrs.getValue("name")!=null){
   			currExternalCodeList.setName(attrs.getValue("name"));   			
   		}
   		if(attrs.getValue("source")!=null){
   			currExternalCodeList.setDescription(attrs.getValue("source"));
   		}
   		format.addFExternalCodeList(currExternalCodeList);
   }else if (qName.equals("charposition")){
   		int start = Integer.valueOf(attrs.getValue("start")).intValue();
   		int end = Integer.valueOf(attrs.getValue("end")).intValue();
   		String description = attrs.getValue("description");
   		currCharPosition = new FCharPosition(start,end,description);
   		if(attrs.getValue("defaultValue")!=null){
   			currCharPosition.setDefaultValue(attrs.getValue("defaultValue"));
   		}
   		if(attrs.getValue("isSystemGenerated")!=null){
   			currCharPosition.setIsSystemGenerated(attrs.getValue("isSystemGenerated"));
   		}
   		if(leader!=null){
   			currCharPosition.setOwner(leader);
   			leader.addFCharPosition(currCharPosition);
   		}else if(directory!=null){
   			currCharPosition.setOwner(directory);
   			directory.addFCharPosition(currCharPosition);
   		}else if(currControlField!=null){
   			currCharPosition.setOwner(currControlField);
   			currControlField.addFCharPosition(currCharPosition);
   		}else if(currSubfield!=null){
   			currCharPosition.setOwner(currSubfield);
   			currSubfield.addFCharPosition(currCharPosition);
   		}
   }else if(qName.equals("subfield")){
   	char name = attrs.getValue("name").charAt(0);
    String desc = attrs.getValue("description");
    boolean repeatable = Boolean.valueOf(attrs.getValue("repeatable")).booleanValue();
    currSubfield = new FSubfield(name, desc, repeatable);
    currSubfield.setDefaultValue(attrs.getValue("defaultValue"));
    currDataField.addFSubfield(currSubfield);
    currSubfield.setOwner(currDataField);
   } else if (qName.equals("indicator")) {
     int index = Integer.valueOf(attrs.getValue("index")).intValue();
     String desc = attrs.getValue("description");
     String defVal = attrs.getValue("defaultValue");
     currInd = new FIndicator(index,desc);
     currInd.setDefaultValue(defVal);
     if (index == 1)
       currDataField.setInd1(currInd); 
     else
       currDataField.setInd2(currInd);
   }else if(qName.equals("internalCodeList")){
   			currInternalCodelist = new FInternalCodeList();
   			if(currCharPosition!=null)
   				currCharPosition.setFInternalCodeList(currInternalCodelist);
   			else if(currSubfield!=null)
   				currSubfield.setFInternalCodeList(currInternalCodelist);
   			else if(currInd!=null)
   				currInd.setCodeList(currInternalCodelist);
   }else if(qName.equals("externalCodeListId")){
   	
   
   } else if (qName.equals("item")) {
     String code = attrs.getValue("code");
     String value = attrs.getValue("value");
     if (currInternalCodelist != null)
     	currInternalCodelist.addFItem(new FItem(code, value));
     else
      currExternalCodeList.addFItem(new FItem(code, value));
   }
 }
   
 public void endElement(String namespaceURI, String lName, String qName) throws SAXException {
   if (qName.equals("format")) {
   	
   }else if (qName.equals("leader")){
   		leader = null;
   }else if (qName.equals("directory")){
   		directory = null;     
   }else if (qName.equals("controlfield")) {
     currControlField = null;
   }else if (qName.equals("datafield")){
   		currDataField = null;
   }else if (qName.equals("externalcodeList")){
   		currExternalCodeList = null;
   } else if (qName.equals("indicator")) {
     currInd = null;
   } else if (qName.equals("subfield")) {
     currSubfield = null;
   }else if (qName.equals("charposition")) {
   		currCharPosition = null;
   }else if (qName.equals("internalCodeList")){
   	 currInternalCodelist = null;
   } else if (qName.equals("item")) {
     // do nothing
   }
 }

 public void characters(char[] buf, int offset, int len) throws SAXException {
  try {
   String cnt = new String(buf, offset, len).trim().replace('\r', ' ').replace('\n', ' ');
   if (cnt.equals(""))
     return;
   if (currCharPosition != null)
    currCharPosition.setExternlCodeListId(currCharPosition.getExternlCodeListId() + cnt);
   else if (currSubfield != null)
   	currSubfield.setExternalCodeListId(currSubfield.getExternalCodeListId() + cnt);	
 } catch (NullPointerException ex) {
   ex.printStackTrace();
 }
   
 }
 


 
 private FLeader leader = null;
 private FDirectory directory = null;
 private FControlField currControlField = null;
 private FDataField currDataField = null; 
 private FIndicator currInd = null;
 private FSubfield currSubfield = null;
 private FCharPosition currCharPosition = null; 
 private FExternalCodeList currExternalCodeList = null;
 private FInternalCodeList currInternalCodelist = null;
 
	

}

/**
 * 
 */
package rs.edu.raf.marcfx.marc21records.serializers;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import rs.edu.raf.marcfx.marc21records.ControlField;
import rs.edu.raf.marcfx.marc21records.DataField;
import rs.edu.raf.marcfx.marc21records.Leader;
import rs.edu.raf.marcfx.marc21records.Record;
import rs.edu.raf.marcfx.marc21records.Subfield;



/**
 * @author bojana
 *
 */
public class OaiMarcSAXHandler extends DefaultHandler {

	/**
	 * 
	 */
	private Record currRecord;
	private Leader currLeader;
	private ControlField currControlField;
	private DataField currDataField;
	private Subfield currSubfield;

	/**
	 * Default constructor
	 */
	public OaiMarcSAXHandler() {		
	}
	
	public Record getRecord(){
		return currRecord;
	}
	
	public void startDocument() throws SAXException {
	    
	}

	public void endDocument() throws SAXException {
	}
	
	public void startElement(String namespaceURI, String lName, String qName, Attributes attrs) throws SAXException {
    try {
    	if(qName.equals("oai_marc")){
    		currRecord = new Record();
    		char atr = returnCharFromString(attrs.getValue("status"));    		
    		char status = returnCharFromString(attrs.getValue("status"));
    		char type = returnCharFromString(attrs.getValue("type"));
    		char level = returnCharFromString(attrs.getValue("level"));
    		char ctlType = returnCharFromString(attrs.getValue("ctlType"));
    		char charEnc = returnCharFromString(attrs.getValue("charEnc"));    		
    		char encLvl = returnCharFromString(attrs.getValue("encLvl"));
    		char catForm = returnCharFromString(attrs.getValue("catForm"));
    		char lrRqrd = returnCharFromString(attrs.getValue("lrRqrd"));
    		currLeader = new Leader(status,type,level,ctlType,charEnc,encLvl,catForm,lrRqrd);
    		currRecord.setLeader(currLeader);
    		currLeader = null;
    	}else if(qName.equals("fixfield")){    		
    		String name = attrs.getValue("id");    		
    	    currControlField = new ControlField(name);
    	    currRecord.add(currControlField);
    	}else if(qName.equals("varfield")){
    		String name = attrs.getValue("id");   
    		char ind1 = attrs.getValue("i1").charAt(0);
    	    char ind2 = attrs.getValue("i2").charAt(0);
    	    currDataField = new DataField(name,ind1,ind2);
    	    currRecord.add(currDataField);
    	}else if (qName.equals("subfield")) {
    	    char name = attrs.getValue("label").charAt(0);
    	    currSubfield = new Subfield(name);
    	    currDataField.addSubfield(currSubfield);    		
    	}    	
    }catch(NullPointerException e){
    	e.printStackTrace();
    }
	}
	
	public void endElement(String namespaceURI, String lName, String qName) throws SAXException {
    try {
      if (qName.equals("oai_marc")){
    	  
      }else if (qName.equals("fixfield")) {    	 
    	  currControlField = null;
      } else if (qName.equals("varfield")) {
	        currDataField = null;		      	
      } else if (qName.equals("subfield")) {
    	  currSubfield = null;
      }  
     
    } catch (NullPointerException ex) {
      ex.printStackTrace();
    }    
    }
	
	public void characters(char[] buf, int offset, int len) throws SAXException {
    try {
      String cnt = new String(buf, offset, len).trim().replace('\r', ' ').replace('\n', ' ').replaceAll("&","&amp;");     
      if (cnt.equals(""))
        return;
      if (currLeader != null)
        currLeader.setContent(cnt);
      else if (currControlField != null)
    	  currControlField.setContent(currControlField.getContent() + cnt);
      else if (currSubfield != null)
        currSubfield.setContent(currSubfield.getContent() + cnt);
    } catch (NullPointerException ex) {
      ex.printStackTrace();
    }		    
	}
	
	private char returnCharFromString(String str){
		if (str == null)
			return ' ';
		else return str.charAt(0);
	}

}

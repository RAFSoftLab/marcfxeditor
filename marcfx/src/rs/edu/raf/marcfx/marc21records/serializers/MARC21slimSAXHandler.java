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
public class MARC21slimSAXHandler extends DefaultHandler {
	
	private Record currRecord;
	private Leader currLeader;
	private ControlField currControlField;
	private DataField currDataField;
	private Subfield currSubfield;

	/**
	 * Default constructor
	 */
	public MARC21slimSAXHandler() {		
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
    	if(qName.equals("record")){
    		currRecord = new Record();
    	}else if(qName.equals("leader")){
    		currLeader = new Leader();
    		currRecord.setLeader(currLeader);
    	}else if(qName.equals("controlfield")){
    		String name = attrs.getValue("tag");    		
    	    currControlField = new ControlField(name);
    	    currRecord.add(currControlField);
    	}else if(qName.equals("datafield")){
    		String name = attrs.getValue("tag");   
    		char ind1 = attrs.getValue("ind1").charAt(0);
    	    char ind2 = attrs.getValue("ind2").charAt(0);
    	    currDataField = new DataField(name,ind1,ind2);
    	    currRecord.add(currDataField);
    	}else if (qName.equals("subfield")) {
    	    char name = attrs.getValue("code").charAt(0);
    	    currSubfield = new Subfield(name);
    	    currDataField.addSubfield(currSubfield);    		
    	}    	
    }catch(NullPointerException e){
    	e.printStackTrace();
    }
	}
	
	public void endElement(String namespaceURI, String lName, String qName) throws SAXException {
    try {
     
     if(qName.equals("leader")){
    	 currLeader = null;    	  
      } else if (qName.equals("controlfield")) {
    	currControlField = null;
      } else if (qName.equals("datafield")) {
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
      String cnt = new String(buf, offset, len).trim().replace('\r', ' ').replace('\n', ' ');      
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
	
	
	
	

	
}

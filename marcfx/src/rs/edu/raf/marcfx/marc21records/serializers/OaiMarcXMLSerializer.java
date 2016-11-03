
package rs.edu.raf.marcfx.marc21records.serializers;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import rs.edu.raf.marcfx.marc21records.Record;



public class OaiMarcXMLSerializer {
	
	static SAXParserFactory factory;
	  
	static {
	   factory = SAXParserFactory.newInstance();
	}
	
	public static Record fromOaiMarcXML(String xml) {
	    try {
	      SAXParser parser = factory.newSAXParser();
	      OaiMarcSAXHandler handler = new OaiMarcSAXHandler();
	      parser.parse(xml, handler);
	      return handler.getRecord();
	    } catch (Exception ex) {    	
	    	
	    	return null;
	    }
	  }

}

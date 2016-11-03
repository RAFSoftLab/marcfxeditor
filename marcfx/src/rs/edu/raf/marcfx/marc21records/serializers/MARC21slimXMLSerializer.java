/**
 * 
 */
package rs.edu.raf.marcfx.marc21records.serializers;
import java.io.StringReader;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.InputSource;

import rs.edu.raf.marcfx.marc21records.ControlField;
import rs.edu.raf.marcfx.marc21records.DataField;
import rs.edu.raf.marcfx.marc21records.Field;
import rs.edu.raf.marcfx.marc21records.Record;
import rs.edu.raf.marcfx.marc21records.Subfield;







public class MARC21slimXMLSerializer {
	
	static SAXParserFactory factory;
	  
	static {
	   factory = SAXParserFactory.newInstance();
	}
	
	public static String toMARC21slimXML(Record rec){
		StringBuffer buff = new StringBuffer(1024);
		buff.append("<record>\n");
		buff.append("  <leader>"+rec.getLeader().getContent()+"</leader>\n");
		for (int i = 0;i<rec.getFieldCount();i++){
			Field cf = (Field) rec.getField(i);
			if(cf instanceof ControlField)
				buff.append(controlFieldToMARC21slimXML((ControlField) cf));
			else buff.append(dataFieldToMARC21slimXML((DataField) cf));
		}
		
		buff.append("</record>");
		return buff.toString();
	}
	
	private static String controlFieldToMARC21slimXML (ControlField cf){
		return "  <controlfield tag=\""+cf.getName()+"\">"
					+cf.getContent()+"</controlfield>\n";	
	}
	
	private static String dataFieldToMARC21slimXML (DataField df){
		StringBuffer buff = new StringBuffer(1024);		
	    buff.append("  <datafield tag=\"");
	    buff.append(df.getName());
	    buff.append("\" ind1=\"");
	    buff.append(df.getInd1());
	    buff.append("\" ind2=\"");
	    buff.append(df.getInd2());
	    buff.append("\">\n");
	    for (int i = 0; i < df.getSubfieldCount(); i++) {
	      Subfield subfield = df.getSubfield(i);		      
	      buff.append("    <subfield code=\"");
	      buff.append(subfield.getName());
	      buff.append("\">");
	      buff.append(subfield.getContent());
	      buff.append("</subfield>\n");		     
	    }		   
	    buff.append("  </datafield>\n");   
		return buff.toString();
	}
	
	
	/**
	 * Reads record from the MARC21slim XML representation
	 * @param xml   
	 * @return created Record
	 * 
	 * */
	public static Record fromMARC21slimXML(String xml) {
	    try {
	      SAXParser parser = factory.newSAXParser();
	      MARC21slimSAXHandler handler = new MARC21slimSAXHandler();	     
	      parser.parse(xml, handler);
	      return handler.getRecord();
	    } catch (Exception ex) {
	    	 ex.printStackTrace();
	    	 return null;
	    }
	  }
	
	

}

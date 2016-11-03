package rs.edu.raf.marcfx.marc21format;

import java.io.InputStream;
import java.util.List;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;






public class FormatFactory {
	
	static SAXParserFactory factory = SAXParserFactory.newInstance();
	
	public static final String BIBLIOGRAPHIC_FORMAT_ID = "bibliographic";
	public static final String HOLDINGS_FORMAT_ID = "holdings";
	
	
	
	
	
	public static Format getFormat(InputStream xml){
	 try {
	   SAXParser parser = factory.newSAXParser();
	   FormatHandler handler = new FormatHandler();
	   parser.parse(xml, handler);
	   return handler.getFormat();
	 } catch (Exception ex) {
	 		ex.printStackTrace();
   return null;
 }
	}
	
	public static Format getMARC21Format(String id){
		if(!id.equals(BIBLIOGRAPHIC_FORMAT_ID) && !id.equals(HOLDINGS_FORMAT_ID))
			return null;
		return getFormat(FormatFactory.class
				.getResourceAsStream("/rs/edu/raf/marcfx/marc21format/xmlfiles/MARC21_format-"+id+".xml"));		
	}
	
	public static Format getMARC21BibliographicFormat(){
		return getFormat(FormatFactory.class
				.getResourceAsStream("/rs/edu/raf/marcfx/marc21format/xmlfiles/MARC21_format-"+BIBLIOGRAPHIC_FORMAT_ID+".xml"));		
	}
	
	
	public static Format getMARC21HoldingsFormat(){
		return getFormat(FormatFactory.class
				.getResourceAsStream("/rs/edu/raf/marcfx/marc21format/xmlfiles/MARC21_format-"+HOLDINGS_FORMAT_ID+".xml"));	
		
	}
	
	public static List<FField> getAllFields(){
		List<FField> retList = getMARC21BibliographicFormat().getFields();
		for(FField f:getMARC21HoldingsFormat().getFields()){
			if(getMARC21BibliographicFormat().getFField(f.getName())==null)
				retList.add(f);
		}
		//retList.addAll(getMARC21HoldingsFormat().getFields());
		return retList;
	}

}

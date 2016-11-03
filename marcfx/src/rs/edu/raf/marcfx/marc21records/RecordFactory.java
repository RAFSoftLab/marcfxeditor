/**
 * 
 */
package rs.edu.raf.marcfx.marc21records;

import java.io.InputStream;

import rs.edu.raf.marcfx.marc21records.serializers.MARC21slimXMLSerializer;
import rs.edu.raf.marcfx.marc21records.serializers.OaiMarcXMLSerializer;



/**
 * MARC21 record factory 
 * contain operations for serialization of MARC21 records in different formats
 * 
 * Format types that are supported by now:
 * 
 * XML format according to http://www.loc.gov/MARC21/slim XMLSchema
 * XML format according to http://www.openarchives.org/OAI/oai_marc XMLSchema
 * Full format
 *  
 *  
 * @author bojana
 *
 */
public class RecordFactory {

	public static Record fromMARC21slimXML(String str){
		
		return MARC21slimXMLSerializer.fromMARC21slimXML(str);
	}
	
	public static String toMARC21slimXML(Record rec){
	 rec.sortFields();
		return MARC21slimXMLSerializer.toMARC21slimXML(rec);
		
	}
	
	public static Record fromOaiMarcXML(String str){
		return OaiMarcXMLSerializer.fromOaiMarcXML(str);
	}
	
	public static String toOaiMarcXML(Record rec){
		return null;
		
	}
	
	public static String toFullFormat(Record rec){
		StringBuffer sb = new StringBuffer();
		rec.sortFields();
		sb.append(rec.getLeader().toFullFormatString()+"\n");
		for (int i = 0;i<rec.getFields().size();i++){
			sb.append(rec.getField(i).toFullFormatString()+"\n");
		}		
		return sb.toString();
	}
		


}

/**
 * 
 */
package rs.edu.raf.marcfx.marc21records;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents a control field in MARC21 record
 * 
 * @author bojana
 * 
 */
public class ControlField extends Field implements java.io.Serializable {
	

	/** field content 
	 *  sadrzaj se unosi sa razmacima izmedju pozicija karaktera  */
	private String content;
	
	/** definition 007 & 008 control fields depends on type of publication
	 * 	Integer constaints for these types should be generated 
	 * */
	private int type;	
	
	

	public ControlField() {
	}

	/**
	 * @param name
	 */
	public ControlField(String name) {
		super(name);
		this.content = "";
	}

	/**
	 * @param content
	 * @param name
	 */
	public ControlField(String name, String content) {
		super(name);
		this.content = content;		
	}
	
	/** Returns a printable string representation of this controlfield */
	public String toFullFormatString() {
		return name+"    "+content.replace(" ", "");
	}
	
	/** Returns a Xtext string representation of this controlfield */
	public String toXTextString() {
		return name+" "+content+"\r";
	}


	/**
	 * @return Returns the content.
	 */
	public String getContent() {
		return content;
	}

	/**
	 * @param content The content to set.
	 */
	public void setContent(String content) {
		this.content = content;
	}
	
	/** char positions methods*/
	
	public String getCharPosition(int start, int length){
		// obrajam i razmake
		return content.substring(start+start-1,start+length);
	}
	
	public void addCharPosition(int start, String value){
		 content = content.substring(0,start)+" "+value+" "+content.substring(start+value.length());
	}
	
	
	
	
}

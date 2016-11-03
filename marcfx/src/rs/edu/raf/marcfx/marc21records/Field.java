/**
 * 
 */
package rs.edu.raf.marcfx.marc21records;

import java.io.Serializable;

/**
 * @author bojana
 *
 */
public abstract class Field implements Serializable {
	
	protected String name;
	
	/**
	 * 
	 */
	public Field() {		
	}

	/**
	 * @param name
	 */
	public Field(String name) {		
		this.name = name;
	}

	/**
	 * @return Returns the name.
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name The name to set.
	 */
	public void setName(String name) {
		this.name = name;
	}
	
	public void sort(){};
	
	abstract String toFullFormatString();

	
	
	
	
	

}

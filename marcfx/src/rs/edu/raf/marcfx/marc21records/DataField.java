/**
 * 
 */
package rs.edu.raf.marcfx.marc21records;
import java.util.ArrayList;
import java.util.List;



/**
 * @author bojana
 * 
 */
public class DataField extends Field implements java.io.Serializable {
	

	/** the value of the first indicator */
	private char ind1;

	/** the value of the second indicator */
	private char ind2;

	/** the list of subfields */
	private List<Subfield> subfields = new ArrayList<Subfield>();

	/**
	 * 
	 */
	public DataField() {
		super();		
	}

	/**
	 * Constructs a field with the given name.
	 * 
	 * @param name The name of the field
	 */
	public DataField(String name) {
		this(name, ' ', ' ');
	}

	/**
	 * Constructs a field with the given name and indicators.
	 * 
	 * @param name The name of the field
	 * @param ind1 The value of the first indicator
	 * @param ind2 The value of the second indicator
	 */
	public DataField(String name, char ind1, char ind2) {
		super(name);
		this.ind1 = ind1;
		this.ind2 = ind2;
		subfields = new ArrayList<Subfield>();
	}

	/**
	 * @return Returns the ind1.
	 */
	public char getInd1() {
		return ind1;
	}

	/**
	 * @param ind1 The ind1 to set.
	 */
	public void setInd1(char ind1) {
		this.ind1 = ind1;
	}

	/**
	 * @return Returns the ind2.
	 */
	public char getInd2() {
		return ind2;
	}

	/**
	 * @param ind2 The ind2 to set.
	 */
	public void setInd2(char ind2) {
		this.ind2 = ind2;
	}
	/**
	 * @return Returns the indicator with a given index (1 or 2)
	 * */
	public char getInd(int index){
		  if(index==1) return this.getInd1();
		  else return this.getInd2();
	}

	/**
	 * @return Returns the subfields.
	 */
	public List<Subfield> getSubfields() {
		return subfields;
	}

	/**
	 * @param subfields The subfields to set.
	 */
	public void setSubfields(List<Subfield> subfields) {
		this.subfields = subfields;
	}

	/**
	 * Adds a subfield to this field.
	 * 
	 * @param sf The subfield to be added
	 */
	public boolean addSubfield(Subfield sf) {
		return subfields.add(sf);
	}
	
	public int getSubfieldCount(){
		return subfields.size();
	}
	
	/* (non-Javadoc)
	 * @see java.util.List#get(int)
	 */
	public Subfield getSubfield(int index) {
		return (Subfield) subfields.get(index);
	}
	
	public Subfield getSubfield(char name){
		for(Subfield sf:subfields)
			if(sf.getName()==name)
				return sf;
		return null;
	}
	
	public String getSubfieldContent(char name){
		if(getSubfield(name)==null) return null;
		return getSubfield(name).getContent();
	}
	
	/** sorts subfields by their names*/
	public void sort(){
		for (int i = 1; i < subfields.size(); i++) {
		      for (int j = 0; j < subfields.size() - i; j++) {
		        Subfield sf1 = (Subfield)subfields.get(j);
		        Subfield sf2 = (Subfield)subfields.get(j+1);
		        if (sf1.getName() > sf2.getName()) {
		          subfields.set(j, sf2);
		          subfields.set(j+1, sf1);
		        }
		      }
		    }		
	}

	/** Returns a printable string representation of this datafield */
	public String toFullFormatString() {
		StringBuffer retVal = new StringBuffer();
		retVal.append(name);
		retVal.append(' ');
		retVal.append(getInd1() == ' ' ? '#' : getInd1());
		retVal.append(getInd2() == ' ' ? '#' : getInd2());
		retVal.append(' ');
		for (int i = 0; i < subfields.size(); i++) {
			retVal.append(subfields.get(i).toString());
		}
	//	retVal.append('\n');
		return retVal.toString();
	}
	
	public String toXtextString(){		
		StringBuffer retVal = new StringBuffer();		
		retVal.append(name);		
		retVal.append(' ');
		retVal.append(getInd1() == ' ' ? '#' : getInd1());
		retVal.append(' ');
		retVal.append(getInd2() == ' ' ? '#' : getInd2());
		retVal.append(' ');
		for (int i = 0; i < subfields.size(); i++) {
			retVal.append("$"+subfields.get(i).getName());
			retVal.append(" \""+subfields.get(i).getContent()+"\"");
			if(i<subfields.size()-1)
				retVal.append(" ");			
		}		
		retVal.append("\r");
		return retVal.toString();
	}

	

}

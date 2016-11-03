package rs.edu.raf.marcfx.marc21records;

import java.util.*;



/**
 * @author bojana
 *
 */
public class Record implements java.io.Serializable{

	private Leader leader;
	private List<Field> fields = new ArrayList<Field>();
	
	
	public Record() {
		leader = new Leader();
		fields = new ArrayList<Field>();		
	}
	
	/**
	 * @return Returns the leader.
	 */
	public Leader getLeader() {
		return leader;
	}

	/**
	 * @param leader The leader to set.
	 */
	public void setLeader(Leader leader) {
		this.leader = leader;
	}
	
	/**
	 * @return Returns the controlFields.
	 */
	public List<Field> getFields() {
		return fields;
	}

	/**
	 * @param controlFields The controlFields to set.
	 */
	public void setFields(List<Field> fields) {
		this.fields = fields;
	}

	/* (non-Javadoc)
	 * @see java.util.List#add(java.lang.Object)
	 */
	public boolean add(Field f) {
		return fields.add(f);
	}

	/* (non-Javadoc)
	 * @see java.util.List#get(int)
	 */
	public Field getField(int index) {
		if (index >= fields.size() || index < 0)
			return null;
		return (Field) fields.get(index);
	}
	
	public int getFieldCount(){
		return fields.size();
	}
	
	public Field getField(String name){		
		for (int i = 0; i < fields.size(); i++) {
		      Field field = (Field)fields.get(i);
		      if (field.getName().equals(name)) {
		        return field;
		      }
		    }
		    return null;
	}
	
	public boolean hasField(String name){
		return getField(name)!=null;
	}
	
	
	public void sortFields(){
		for (int i = 1; i < fields.size(); i++) {
		      for (int j = 0; j < fields.size() - i; j++) {
		        Field f1 = (Field)fields.get(j);
		        Field f2 = (Field)fields.get(j+1);
		        if (f1.getName().compareTo(f2.getName()) > 0) {
		          fields.set(j, f2);
		          fields.set(j+1, f1);
		        }
		      }
		    }
		    for (int i = 0; i < fields.size(); i++) {
		      Field f = (Field)fields.get(i);
		      f.sort();
		    }
	}
	
	public ControlField getControlField(String name){
		if(getField(name)==null) return null;
		if(getField(name) instanceof ControlField) return (ControlField)getField(name);
		else return null;
	}
	
	public DataField getDataField(String name){
		if(getField(name)==null) return null;
		if(getField(name) instanceof DataField) return (DataField)getField(name);
		else return null;
	}
	
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		
		for(Field f: fields){
			DataField df = (DataField)f;
			sb.append(df.getName()+" "+df.getInd1()+" "+df.getInd2()+"\n");
		}
		
		return sb.toString();
	}
	

}

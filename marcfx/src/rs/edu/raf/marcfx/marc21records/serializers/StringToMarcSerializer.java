package rs.edu.raf.marcfx.marc21records.serializers;

import java.util.List;

import org.reactfx.Indicator;

import rs.edu.raf.marcfx.marc21format.FDataField;
import rs.edu.raf.marcfx.marc21format.Format;
import rs.edu.raf.marcfx.marc21format.FormatFactory;
import rs.edu.raf.marcfx.marc21records.DataField;
import rs.edu.raf.marcfx.marc21records.Field;
import rs.edu.raf.marcfx.marc21records.Record;
import rs.edu.raf.marcfx.marc21records.Subfield;

public class StringToMarcSerializer {
	
	public StringToMarcSerializer(){
		
	}
	
	public Record stringToMarc(String text) throws Exception{
		String rows[] = text.split("\n");
		Record record = new Record();
		
		for(int i=0; i<rows.length; i++){
			Field field = pomMet(rows[i]);
			record.add(field);
		}
		
		return record;
	}
	
	private Field pomMet(String row) throws Exception{
		String fieldsRow[] = row.split("\\$");
		String fr1[] = fieldsRow[0].split(" ");
		DataField df = new DataField();
		df.setName(fr1[0]);
		char ind1 = fr1[1].charAt(0);
		char ind2 = fr1[2].charAt(0);
		df.setInd1(ind1);
		df.setInd2(ind2);
		for(int i=1; i<fieldsRow.length; i++){
			Subfield sf = new Subfield(fieldsRow[i].charAt(0));
			String content = fieldsRow[i].substring(1, fieldsRow[i].length());
			sf.setContent(content);
			df.addSubfield(sf);
		}
		return df;
	}
	
	public String marcToString(Record record) throws Exception{
		StringBuilder sb = new StringBuilder();
		
		for(Field f: record.getFields()){
			DataField df = (DataField)f;
			sb.append(df.getName()+" "+df.getInd1()+" "+df.getInd2()+" ");
			for(Subfield sf: df.getSubfields()){
				sb.append(sf.toString()+" ");
			}
			sb.append("\n");
		}
		
		return sb.toString();
	}
	
}

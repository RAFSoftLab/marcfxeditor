package rs.edu.raf.marcfx.marc21records.serializers;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.io.RandomAccessFile;

import rs.edu.raf.marcfx.marc21records.ControlField;
import rs.edu.raf.marcfx.marc21records.DataField;
import rs.edu.raf.marcfx.marc21records.Field;
import rs.edu.raf.marcfx.marc21records.Record;


/*
 * serijalizicaja objektnog modela u 
 * format u kom se unosi u Xtext editoru
 *  
 * 
 */


public class XTextFormatSerializer {
	
	public static RandomAccessFile toXtextFile(Record rec, String path){
		RandomAccessFile file = null;
		try {
			file = new RandomAccessFile(path,"rw");
			file.writeBytes(rec.getLeader().toXtextString());
		 for(Field f:rec.getFields()){
		 	if(f instanceof ControlField)
		 		file.writeBytes(((ControlField)f).toXTextString());
		 	else if(f instanceof DataField)
		 		file.writeBytes(((DataField)f).toXtextString());
		 }
		 file.close();			
		} catch (Exception e) {		
			e.printStackTrace();
		}		
		return file;		
	}
	
	public static File convertToXtextFile(Record rec, String path){
		File file = null;
		FileOutputStream stream;
		OutputStreamWriter writer;
		try {
			file = new File(path);
			stream = new FileOutputStream(file);
			writer = new OutputStreamWriter(stream,"UTF-8"); 
			writer.append(rec.getLeader().toXtextString());
		 for(Field f:rec.getFields()){
		 	if(f instanceof ControlField)
		 		writer.append(((ControlField)f).toXTextString());
		 	else if(f instanceof DataField)
		 		writer.append(((DataField)f).toXtextString());
		 }
		 writer.close();			
		} catch (Exception e) {		
			e.printStackTrace();
		}		
		return file;		
	}	

}

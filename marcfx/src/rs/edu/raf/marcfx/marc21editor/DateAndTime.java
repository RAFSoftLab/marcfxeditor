package rs.edu.raf.marcfx.marc21editor;

import java.text.SimpleDateFormat;
import java.util.Date;

import javafx.scene.control.TextField;

public class DateAndTime extends TextField implements Runnable {
	private Date date;
	private SimpleDateFormat sf = new SimpleDateFormat("dd.MM.yyyy. hh:mm:ss");
	private Thread thread;
	
	public DateAndTime() {
		setEditable(false);
		
		thread = new Thread(this);
		thread.start();
	}

	@Override
	public void run() {
		try{
			while(1!=0){
				date = new Date();
				setText(sf.format(date));
				Thread.sleep(1000);
			}
		}catch (Exception e) {
			e.printStackTrace();
		}
	}

}

package rs.edu.raf.marcfx.marc21editor;


import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.fxmisc.richtext.CodeArea;
import org.fxmisc.richtext.LineNumberFactory;
import org.fxmisc.richtext.StyleSpans;
import org.fxmisc.richtext.StyleSpansBuilder;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.EventHandler;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;
import rs.edu.raf.marcfx.marc21format.FDataField;
import rs.edu.raf.marcfx.marc21format.FIndicator;
import rs.edu.raf.marcfx.marc21format.FInternalCodeList;
import rs.edu.raf.marcfx.marc21format.FItem;
import rs.edu.raf.marcfx.marc21format.FSubfield;
import rs.edu.raf.marcfx.marc21format.Format;
import rs.edu.raf.marcfx.marc21format.FormatFactory;


/**
 * 
 * komponenta editora u kom se unosi marc 21 zapis
 *  
 * komponenta prosiruje gotovu open source komponentu dostupnu na https://github.com/TomasMikula/RichTextFX 
 * 
 * funkcionalnsoti koje treba dodati 
 * 
 * - prepoznavanje kljucnih reci (nazivi polja i potpolja) - uradjeno
 * 
 * - content assist, kao u Eclipse, kada korisnik pritisne neku precicu na tastaturi, 
 * 	  recimo Ctrl+Space da mu se otvori padajuci meni za izbor naziva potpolja ili sifre
 * 
 * TODO:  Prvi zadatak za Igora
 * 
 * Kada korisnik unese polje 245 u posle toga prazan karakter, pritisne Ctrl+Space otvara se padajuci meni sa spiskom svih potpolja koje ima polja 245 
 * ovo treba iscitati iz objektnog modela u paketu marc21format, sledecom naredbom 
 * 
 *  ((FDataField)bibliographicFormat.getFField("245")).getFSubfields();  
 *  
 */

public class MARC21EditorArea extends CodeArea {
	
	private static Pattern PATTERN;
	private Stage stage;
	private ContentAssist ca;
	
	
	// podaci o MARC21 formatu
	private Format bibliographicFormat = null;
	
	public void setStage(Stage stage) {
		this.stage = stage;
	}
	
	public MARC21EditorArea(){
		bibliographicFormat = FormatFactory.getMARC21BibliographicFormat();
		initializeKeywords();
		initializeStyles();		
		
		textProperty().addListener(new ChangeListener<String>() {

			@Override
			public void changed(ObservableValue<? extends String> arg0, String arg1, String arg2) {
				//System.out.println("text property arg0="+arg0+" arg1="+arg1);
			}
		});
		setOnKeyPressed(new EventHandler<KeyEvent>() {
			@Override
			public void handle(KeyEvent event) {
				
				if(event.isControlDown() && event.getCode() == KeyCode.SPACE){
					
					try{
						ca = new ContentAssist(stage);
						String myRow;
						String[] fieldMyRow;
						String text = getText().substring(0, getCaretPosition());
						String[] rows = text.split("\n");
						if(rows.length>getCurrentParagraph()){
							myRow = rows[getCurrentParagraph()];
							fieldMyRow = myRow.split("\\$");
							if(fieldMyRow.length==1){
								myMetod(fieldMyRow[0]);
							}
							else{
								HashMap<String, String> map = new HashMap<>();
								String[] hf = fieldMyRow[0].split(" ");
								String currField = hf[0].substring(0, hf[0].length());
								FDataField currentField = ((FDataField)bibliographicFormat.getFField(currField));
								List<FSubfield> listSubField = currentField.getFSubfields();
								for(FSubfield sf: listSubField){
									map.put(sf.write(), sf.toString());
								}
								ca.show(map);
							}
						}
						else{
							myMetod("");
						}
						
					}catch(Exception e){
						Alert a = new Alert(AlertType.ERROR);
						a.setHeaderText("GRESKA!");
						a.show();
						e.printStackTrace();
					}
					
				}
			}
		});
	
		
	}
	
	public void myMetod(String text){
		try{
			HashMap<String, String> map = new HashMap<>();
			List<FDataField> listField = bibliographicFormat.getDataFields();
			if(text.equals("")){
				for(FDataField f: listField){
					map.put(f.getName(), f.getName());
				}
				ca.show(map);
				return;
			}
			String[] fieldMyRow = text.split(" ");
			FDataField currentField = ((FDataField)bibliographicFormat.getFField(fieldMyRow[0]));
			if(currentField == null){
				for(FDataField f: listField){
					if(f.getName().substring(0, text.length()).equals(text))
						map.put(f.getName(), f.getName());
				}
				ca.show(map);
				return;
			}
			int noField = fieldMyRow.length;

			if(noField == 1 && !fieldMyRow[0].equals("")){
				FIndicator ind1 = currentField.getInd1();
				if(ind1 == null) addText("#");
				else{
					FInternalCodeList indCodList = ind1.getCodeList();
					for(FItem fi: indCodList.getItems()){
						map.put(fi.getCode(), fi.toString());
					}
					ca.show(map);
				}
			}
			if(noField == 2){
				FIndicator ind2 = currentField.getInd2();
				if(ind2 == null) addText("#");
				else{
					FInternalCodeList indCodList = ind2.getCodeList();
					for(FItem fi: indCodList.getItems()){
						map.put(fi.getCode(), fi.toString());
					}
					ca.show(map);
				}
			}
			if(noField == 3){
				List<FSubfield> listSubField = currentField.getFSubfields();
				for(FSubfield sf: listSubField){
					map.put(sf.write(), sf.toString());
				}
				ca.show(map);
			}
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	public void addText(String text){
		String s = getText(getCaretPosition(), getCaretPosition()+1);
		@SuppressWarnings("unused")
		int temp = getCurrentParagraph();
		if(!s.equals("") || s.equals(" ")){
			while(1!=0){
				deleteNextChar();
				
				s = getText(getCaretPosition(), getCaretPosition()+1);
				if(s.equals(" ")||s.equals("")) break;
			}
		}
		insertText(getCaretPosition(), text);
	}
	
	private void initializeStyles(){
		getStylesheets().add(getClass().getResource("marc21-style.css").toExternalForm());
		setParagraphGraphicFactory(LineNumberFactory.get(this));
		this.richChanges()
        .filter(ch -> !ch.getInserted().equals(ch.getRemoved())) // XXX
        .subscribe(change -> {
            this.setStyleSpans(0, computeHighlighting(this.getText()));
        });		
	}
	
	
	private void initializeKeywords(){
		StringBuffer keyword_pattern = new StringBuffer();
		keyword_pattern.append("(");
		
		
		List<String> fieldNames = bibliographicFormat.getFieldsNames();
		keyword_pattern.append(fieldNames.get(0));
		for(String str:fieldNames.subList(1, fieldNames.size())){
			keyword_pattern.append("|");
			keyword_pattern.append(str);		
			//System.out.println(str);
		}	
		List<String> subieldNames = bibliographicFormat.getSubfieldNames();
		for(String sf:subieldNames){
			keyword_pattern.append("|");
			keyword_pattern.append("\\$"+sf);		
			//System.out.println(sf);
		}
		keyword_pattern.append(")");		
		PATTERN = Pattern.compile("(?<KEYWORD>" + keyword_pattern + ")");		
	}
	
	
	private static StyleSpans<Collection<String>> computeHighlighting(String text) {
        Matcher matcher = PATTERN.matcher(text);
        int lastKwEnd = 0;
        StyleSpansBuilder<Collection<String>> spansBuilder
                = new StyleSpansBuilder<>();
        while(matcher.find()) {
            String styleClass =
                    matcher.group("KEYWORD") != null ? "keyword" :	                   
                    null; /* never happens */ assert styleClass != null;
            spansBuilder.add(Collections.emptyList(), matcher.start() - lastKwEnd);
            spansBuilder.add(Collections.singleton(styleClass), matcher.end() - matcher.start());
            lastKwEnd = matcher.end();
        }
        spansBuilder.add(Collections.emptyList(), text.length() - lastKwEnd);
        return spansBuilder.create();
    }

}

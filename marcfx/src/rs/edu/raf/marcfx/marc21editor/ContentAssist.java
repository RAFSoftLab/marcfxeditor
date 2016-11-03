package rs.edu.raf.marcfx.marc21editor;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.MenuItem;
import javafx.scene.control.Tab;
import javafx.stage.Stage;

public class ContentAssist extends ContextMenu {
	private Stage stage;
	
	
	public ContentAssist(Stage stage){
		this.stage = stage;
		
	}
	
	public void initialize(HashMap<String, String> mySet){
		getItems().removeAll(getItems());
		@SuppressWarnings("rawtypes")
		Set set = mySet.entrySet();
		@SuppressWarnings("rawtypes")
		Iterator it = set.iterator();
		while(it.hasNext()){
			@SuppressWarnings("rawtypes")
			Map.Entry me = (Map.Entry) it.next();
			MenuItem mn = new MenuItem(me.getValue()+"");
			getItems().add(mn);
			mn.setOnAction(new EventHandler<ActionEvent>() {
				@Override
				public void handle(ActionEvent event) {
					Tab t = Main.form.tabPane.getSelectionModel().getSelectedItem();
					MARC21EditorArea mea = (MARC21EditorArea)t.getContent();
					mea.addText(me.getKey()+"");			
				}
			});
		}
	}
	
	public void show(HashMap<String, String> mySet){
		initialize(mySet);
		show(stage);
	}
	
}

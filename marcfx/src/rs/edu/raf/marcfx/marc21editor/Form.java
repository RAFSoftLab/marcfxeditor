package rs.edu.raf.marcfx.marc21editor;

import java.io.File;
import java.io.PrintWriter;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextInputDialog;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyCodeCombination;
import javafx.scene.input.KeyCombination;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import javafx.stage.Stage;
import rs.edu.raf.marcfx.marc21format.FDataField;
import rs.edu.raf.marcfx.marc21format.FSubfield;
import rs.edu.raf.marcfx.marc21format.Format;
import rs.edu.raf.marcfx.marc21format.FormatFactory;
import rs.edu.raf.marcfx.marc21records.Record;
import rs.edu.raf.marcfx.marc21records.serializers.StringToMarcSerializer;

public class Form extends Stage {
	private static Form instance = null;
	private TextArea taValidate;
	private StringToMarcSerializer stms = new StringToMarcSerializer();
	private Button btNewRecord, btOpenRecord, btSaveRecord, btValidate, btSerializer;
	public TabPane tabPane = new TabPane();
	
	private Form(){
		Scene sc = new Scene(root());
		this.setTitle("Mark 21 editor");
		this.setScene(sc);
		this.show();
	}
	
	private Region root(){
		VBox root = new VBox();
		HBox pRoot = new HBox();
		pRoot.getChildren().addAll(tree(), vBoxRoot());
		root.getChildren().addAll(menu(), pRoot);
		return root;
	}
	
	private MenuBar menu(){
		MenuBar menu = new MenuBar();
		Menu mFile = new Menu("File");
		MenuItem mfExit = new MenuItem("Exit", new ImageView(new Image("/img/Exit.png")));
		mfExit.setAccelerator(new KeyCodeCombination(KeyCode.E, KeyCombination.CONTROL_DOWN));
		mfExit.setOnAction(exitEvent);
		mFile.getItems().add(mfExit);
		Menu mEdit = new Menu("Edit");
		Menu mHelp = new Menu("Help");
		menu.getMenus().addAll(mFile, mEdit, mHelp);
		return menu;
	}
	
	private VBox vBoxRoot(){
		VBox root = new VBox();
		//TOP line
		HBox pp = new HBox(15);
		btNewRecord = new Button("New record", new ImageView(new Image("/img/New document.png")));
		btNewRecord.setOnAction(newEvent);
		btOpenRecord = new Button("Open record...", new ImageView(new Image("/img/Folder.png")));
		btOpenRecord.setOnAction(openEvent);
		btSaveRecord = new Button("Save", new ImageView(new Image("/img/Save.png")));
		btSaveRecord.setOnAction(saveEvent);
		btValidate = new Button("Validate", new ImageView(new Image("/img/Info.png")));
		btSerializer = new Button("Serializer");
		btSerializer.setOnAction(serializerAction);
		pp.setPadding(new Insets(10));
		pp.getChildren().addAll(btNewRecord, btOpenRecord, btSaveRecord, btValidate, btSerializer);
		
		taValidate = new TextArea();
		taValidate.setEditable(false);
		taValidate.setMaxHeight(100);
		tabPane.setMinHeight(300);
		
		
		root.setPadding(new Insets(8));
		root.getChildren().addAll(pp, tabPane, taValidate);
		return root;
	}
	
	private VBox tree(){
		VBox root = new VBox(10);
		Format bibliographicFormat = FormatFactory.getMARC21BibliographicFormat();
		List<FDataField> listField = bibliographicFormat.getDataFields();
		TreeView<String> treeView = new TreeView<>();
		TreeItem<String> tcRoot = new TreeItem<>();
		for(FDataField fdf: listField){
			TreeItem<String> tcLevel1 = new TreeItem<>(fdf.getName()+" "+fdf.getDescription());
			tcRoot.getChildren().add(tcLevel1);
			for(FSubfield sf: fdf.getFSubfields()){
				TreeItem<String> tcLevel2 = new TreeItem<>(sf.toString());
				tcLevel1.getChildren().add(tcLevel2);
			}
		}
		treeView.setRoot(tcRoot);
		treeView.setShowRoot(false);
		root.getChildren().add(treeView);
		return root;
	}
	
	EventHandler<ActionEvent> serializerAction = new EventHandler<ActionEvent>() {
		@Override
		public void handle(ActionEvent event) {
			try{
				Record r = stms.stringToMarc(getActiveArea().getText());
				taValidate.setText(stms.marcToString(r));
			}catch(Exception e){
				Alert a = new Alert(AlertType.ERROR);
				a.setHeaderText("GRESKA!!!");
				a.show();
			}
		}
	};
	
	EventHandler<ActionEvent> openEvent = new EventHandler<ActionEvent>(){
		@Override
		public void handle(ActionEvent event){
			try {
				FileChooser fc = new FileChooser();
				fc.setTitle("Open File");
				fc.getExtensionFilters().addAll(
					new ExtensionFilter("TEXT files (*.txt)", "*.txt")
				);
				File f = fc.showOpenDialog(Form.this);
				if(f == null) return;
				Scanner sc = new Scanner(f);
				StringBuilder sb = new StringBuilder();
				while(sc.hasNextLine()){
					sb.append(sc.nextLine()+"\n");
				}
				sc.close();
				Record r = stms.stringToMarc(sb.toString());
				getActiveArea().insertText(0, stms.marcToString(r));
			} catch (Exception e) {
				taValidate.setText("GRESKA!!!");
			}
		}
	};
	
	EventHandler<ActionEvent> saveEvent = new EventHandler<ActionEvent>() {
		public void handle(ActionEvent event) {
			try{
				FileChooser fc = new FileChooser();
				fc.setTitle("Save File");
				fc.getExtensionFilters().addAll(
					new ExtensionFilter("TEXT files (*.txt)", "*.txt")
				);
				File f = fc.showSaveDialog(Form.this);
				if(f == null) return;
				Record r = stms.stringToMarc(getActiveArea().getText());
				getActiveArea().setUserData(true);
				PrintWriter pw = new PrintWriter(f);
				pw.print(stms.marcToString(r));
				pw.close();
			}catch (Exception e) {
				taValidate.setText("GRESKA!!!");
			}
		};
	};
	
	EventHandler<ActionEvent> exitEvent = new EventHandler<ActionEvent>() {
		public void handle(ActionEvent event) {
			Main.form.close();
		};
	};
	
	EventHandler<ActionEvent> newEvent = new EventHandler<ActionEvent>() {
		public void handle(ActionEvent event) {
			try {
				TextInputDialog dialog = new TextInputDialog("");
				dialog.setTitle("Name for Tap!");
				dialog.setHeaderText("");
				dialog.setContentText("Please enter name:");

				Optional<String> result = dialog.showAndWait();
				if (result.isPresent()){
				    System.out.println("Your name: " + result.get());
				}
				if(result == null) return;
				String nameResult = result.get();
				String name = !nameResult.equals("") ? nameResult : tabPane.getTabs().size()+1+". tab";
				MARC21EditorArea newArea = new MARC21EditorArea();
				newArea.setStage(Form.this);
				newArea.setUserData(false);
				Tab tab = new Tab(name);
				tab.setContent(newArea);
				tabPane.getTabs().add(tab);
				tabPane.getSelectionModel().select(tab);
				
				//Tab setOnCloseRequest
				tab.setOnCloseRequest(new EventHandler<Event>() {
					public void handle(Event event) {
						MARC21EditorArea mea = (MARC21EditorArea)tab.getContent();
						if((boolean)mea.getUserData() == true || mea.getText().trim().equals("")) return;
						Alert alert = new Alert(AlertType.CONFIRMATION);
						alert.setTitle("SAVE Dialog");
						alert.setHeaderText("You don't save data!");
						alert.setContentText("Do you want to save your data?");
						
						ButtonType buttonTypeYes = new ButtonType("Yes");
						ButtonType buttonTypeNo = new ButtonType("No");
						alert.getButtonTypes().setAll(buttonTypeYes, buttonTypeNo);

						Optional<ButtonType> result = alert.showAndWait();
						if (result.get() == buttonTypeYes){
						    btSaveRecord.fire();
						} else if (result.get() == buttonTypeNo) {
						    // TODO: ... user chose "Two"
						} else {
						    // TODO: ... user chose CANCEL or closed the dialog
						}
					};
				});
			} catch (Exception e) {
				// TODO: handle exception
			}
		};
	};
	
	public MARC21EditorArea getActiveArea(){
		Tab t = Main.form.tabPane.getSelectionModel().getSelectedItem();
		MARC21EditorArea mea = (MARC21EditorArea)t.getContent();
		return mea;
	}
	
	public static Form getInstance() {
		if(instance == null) instance = new Form();
		return instance;
	}

}

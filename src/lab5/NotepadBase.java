package lab5;

import com.sun.javafx.property.adapter.PropertyDescriptor;
import com.sun.org.apache.bcel.internal.generic.GOTO;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Desktop;
import java.awt.Dialog;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.Image;
import static java.awt.PageAttributes.ColorType.COLOR;
import java.awt.image.ImageObserver;
import java.awt.image.ImageProducer;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.Cursor;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
import static javafx.scene.control.ButtonType.FINISH;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextArea;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCodeCombination;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import static lab5.Lab5.stage;

public class NotepadBase extends BorderPane {

    int status = 0;
    boolean flag = false;
    boolean save_flag=false;
   boolean delete=false,cut=false;
    String undo_Text="";
    public static String pasteItem;
    protected final MenuBar menuBar;
    protected final Menu menu;
    protected final MenuItem menuItem;
    protected final MenuItem menuItem0;
    protected final MenuItem menuItem1;
    protected final MenuItem menuItem2;
    protected final Menu menu0;
    protected final MenuItem menuItem3;
    protected final MenuItem menuItem4;
    protected final MenuItem menuItem5;
    protected final MenuItem menuItem6;
    protected final MenuItem menuItem7;
    protected final MenuItem menuItem8;
    protected final Menu menu1;
    protected final MenuItem menuItem9;
    protected final TextArea textArea;
    FileChooser fileChooser;
    // Container pane;
    JButton save = new JButton("Save");
    String data;
    Alert alert;
    private Desktop desktop = Desktop.getDesktop();
   int counter=0;
    public NotepadBase() {

        menuBar = new MenuBar();
        menu = new Menu();
        menuItem = new MenuItem();
        menuItem0 = new MenuItem();
        menuItem1 = new MenuItem();
        menuItem2 = new MenuItem();
        menu0 = new Menu();
        menuItem3 = new MenuItem();
        menuItem4 = new MenuItem();
        menuItem5 = new MenuItem();
        menuItem6 = new MenuItem();
        menuItem7 = new MenuItem();
        menuItem8 = new MenuItem();
        menu1 = new Menu();
        menuItem9 = new MenuItem();
        textArea = new TextArea();
        setMaxHeight(USE_PREF_SIZE);
        setMaxWidth(USE_PREF_SIZE);
        setMinHeight(USE_PREF_SIZE);
        setMinWidth(USE_PREF_SIZE);
        setPrefHeight(616.0);
        setPrefWidth(761.0);
        BorderPane.setAlignment(menuBar, javafx.geometry.Pos.CENTER);
        menu.setMnemonicParsing(false);
        menu.setText("File");
        menuItem.setMnemonicParsing(false);
        menuItem.setText("New");
        menuItem.setAccelerator(KeyCodeCombination.keyCombination("CTRL+N"));
        menuItem0.setMnemonicParsing(false);
        menuItem0.setText("Open");
         menuItem0.setAccelerator(KeyCodeCombination.keyCombination("CTRL+O"));
        menuItem1.setMnemonicParsing(false);
        menuItem1.setText("Save");
         menuItem1.setAccelerator(KeyCodeCombination.keyCombination("CTRL+S"));
        menuItem2.setMnemonicParsing(false);
        menuItem2.setText("Exit");
        
        menu0.setMnemonicParsing(false);
        menu0.setText("Edit");
        menuItem3.setMnemonicParsing(false);
        menuItem3.setText("Undo");
        menuItem4.setMnemonicParsing(false);
        menuItem4.setText("Cut");
        menuItem5.setMnemonicParsing(false);
        menuItem5.setText("Copy");
        menuItem6.setMnemonicParsing(false);
        menuItem6.setText("Paste");
        menuItem7.setMnemonicParsing(false);
        menuItem7.setText("Delete");
        menuItem8.setMnemonicParsing(false);
        menuItem8.setText("Select All");
        menu1.setMnemonicParsing(false);
        menu1.setText("Help");
        menuItem9.setMnemonicParsing(false);
        menuItem9.setText("About Notepad");
        setTop(menuBar);
        BorderPane.setAlignment(textArea, javafx.geometry.Pos.CENTER);
        textArea.setPrefHeight(200.0);
        textArea.setPrefWidth(200.0);
        setCenter(textArea);
        menu.getItems().add(menuItem);
        menu.getItems().add(menuItem0);
        menu.getItems().add(menuItem1);
        menu.getItems().add(menuItem2);
        menuBar.getMenus().add(menu);
        menu0.getItems().add(menuItem3);
        menu0.getItems().add(menuItem4);
        menu0.getItems().add(menuItem5);
        menu0.getItems().add(menuItem6);
        menu0.getItems().add(menuItem7);
        menu0.getItems().add(menuItem8);
        menuBar.getMenus().add(menu0);
        menu1.getItems().add(menuItem9);
        menuBar.getMenus().add(menu1);
        data = textArea.getText();
        textArea.textProperty().addListener((observable, oldValue, newValue) -> {
            status = 1;
            ++counter;
        });
        menu0.setOnShowing(new EventHandler<Event>() {
            @Override
            public void handle(Event event) {
                boolean flagTwo=textArea.getSelectedText().isEmpty();
                if(flagTwo){
                    
                    //menuItem3.setDisable(true);
                    
                     menuItem4.setDisable(true);
                      menuItem5.setDisable(true);
                       menuItem7.setDisable(true);
                        menuItem8.setDisable(true);
                }else{
                     menuItem3.setDisable(false);
                     menuItem4.setDisable(false);
                     menuItem5.setDisable(false);
                     menuItem7.setDisable(false);
                     menuItem8.setDisable(false);
                }
            }
        });
        menuItem.setOnAction((ActionEvent event) -> {
           /* if (flag == false ) {
                save();
            } else*/ if(status==1){
                save();
                status = 0;
                textArea.clear();
            }else{
                textArea.clear();
            }

        });

        menuItem0.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                if (status == 1) {
                    save();
                    status = 0;
                }else {
                    fileChooser = new FileChooser();
                    File file = fileChooser.showOpenDialog(stage);
                    if (file != null) {
                        try {
                            configureFileChooser(fileChooser);
                            openFile(file);
                        }catch (IOException ex) {
                            Logger.getLogger(NotepadBase.class.getName()).log(Level.SEVERE, null, ex);
                        }

                    }
                }

            }
        });
        menuItem1.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                if (status == 1) {
                    save();
                    status = 0;
                }else if (textArea.getText().isEmpty()){
                     save();
            }
            }
        });
        menuItem2.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                System.exit(0);
            }
        });

        menuItem9.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {

                Alert alert = new Alert(AlertType.INFORMATION);
                alert.setTitle("Notepade Help");
                alert.setHeaderText("\n");
                Stage stage5 = (Stage) alert.getDialogPane().getScene().getWindow();
                stage5.getIcons().add(new javafx.scene.image.Image("Images/contract.png"));
                alert.setContentText("Notepade is helping to edit and has more functionality, produced by iti./n Notepad a new, comfortable and simple notepad right on your Android. Notepad is always at your hand, you just have to write down what you want to do");
                alert.setGraphic(new ImageView(("Images/Capture.png")));
                alert.showAndWait();
            }

        });
        menuItem8.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                textArea.selectAll();
            }
        });
     
        menuItem7.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                
                delete=true;
                undo_Text=textArea.getText();
                textArea.replaceSelection("");

            }
        });

        menuItem6.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                if (!pasteItem.isEmpty()) {
                    int index = textArea.getCaretPosition();
                    //int end=pasteItem
                    
                    textArea.replaceText(textArea.getSelection(), pasteItem);

                }

            }
        });
        menuItem5.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                copy();
            }
        });
        menuItem4.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                
                pasteItem=textArea.getText(textArea.getSelection().getStart(), textArea.getSelection().getEnd());
                 cut=true;
                 undo_Text=textArea.getSelectedText();
                textArea.deleteText(textArea.getSelection());
            }
        });
       
        menuItem3.setOnAction((ActionEvent event) -> {
             if(cut==true && !undo_Text.equals("")){
                textArea.setText(undo_Text);
                cut=false;
                
            }else if(delete==true && !undo_Text.equals("")){
                textArea.setText(undo_Text);
                delete=false;
            }else if(textArea.getText().equals("") && !undo_Text.equals("")){
                textArea.setText(undo_Text);
            }else{
                textArea.selectAll();
                undo_Text=textArea.getText();
                textArea.deleteText(textArea.getSelection());
            }
        });
    }

    private String copy() {
        pasteItem = textArea.getSelectedText();
        return pasteItem;
    }

    private static void configureFileChooser(final FileChooser fileChooser) {
        fileChooser.setTitle("View File");
        fileChooser.setInitialDirectory(
                new File(System.getProperty("user.home"))
        );
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("All Files", "*.*"),
                new FileChooser.ExtensionFilter("TXT", "*.txt"),
                new FileChooser.ExtensionFilter("JAVA", "*.java")
        );
    }

    private void openFile(File file) throws IOException {
        FileInputStream in = null;
        FileOutputStream out = null;
        in = new FileInputStream(file);
        byte bt[] = new byte[(int) file.length()];
        in.read(bt);
        List<String> content = Files.readAllLines(Paths.get(file.toURI()), StandardCharsets.UTF_8);
        for (String s : content) {
            textArea.setText(s);
        }
        in.close();
    }
    public void save() //throws FileNotFoundException
    {      
        alert = new Alert(Alert.AlertType.CONFIRMATION, "Do You want to Save your change " + " ?",
                ButtonType.YES, ButtonType.NO, ButtonType.CANCEL);

        alert.showAndWait();
        if (alert.getResult() == ButtonType.YES) {
            //if (textArea.getText().equals("")) {
                fileChooser = new FileChooser();
                FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("TXT files (*.txt)", "*.txt");
                fileChooser.getExtensionFilters().add(extFilter);
                File file = fileChooser.showSaveDialog(Lab5.stage);
                if (file != null) {
                    fileWriter(file, textArea);
                }

                alert.hide();
                if (status == 0) {
                    textArea.clear();
                }
                flag = true;
            //}
        } else if (alert.getResult() == ButtonType.NO) {
            textArea.clear();
        } else {
            alert.onHiddenProperty();
        }
       

    }

    public void fileWriter(File savePath, TextArea textArea) {
        try {
            BufferedWriter bf = new BufferedWriter(new FileWriter(savePath));
            bf.write(textArea.getText());
            bf.flush();
            bf.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}


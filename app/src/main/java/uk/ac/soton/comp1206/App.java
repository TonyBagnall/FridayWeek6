package uk.ac.soton.comp1206;

import javafx.application.Application;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.shape.Box;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;
import javafx.stage.Stage;

import java.util.ArrayList;

//import org.apache.logging.log4j.LogManager;


/**
 * JavaFX App
Lecture 4: binding in JavaFX

 Topic 1: enum types example and var
 Topic 2: JavaFX bindings
 Topic 3: Custom components
 Topic 4: Adding images
 */
public class App extends Application {

    enum Status {STOP, START, PAUSE}
    Status currentStatus = Status.PAUSE;
    Text startText = new Text("Start");

    /**
     * Examples for topic 1: enum types
     * @param primaryStage
     */
    public void topic1(Stage primaryStage) {
        // Text to display the selected action

        // Create buttons for each action
        Button startButton = new Button("Start/Stop");
        Button pauseButton = new Button("Pause");
        Status s = Status.STOP;

        // Set button actions using the enum, example with function reference
        startButton.setOnAction(this::startStop);
        // Pause button with lambda
        pauseButton.setOnAction(e -> {
            if(currentStatus!=Status.PAUSE) {
                startText.setText(Status.PAUSE.toString());
                currentStatus = Status.START;
            }
            else{
                startText.setText(Status.START.toString());
                currentStatus = Status.PAUSE;

                }

        });

        // Layout
        VBox layout = new VBox(10, startText, startButton, pauseButton);
        layout.setAlignment(Pos.CENTER);

        // Scene and stage setup
        Scene scene = new Scene(layout, 300, 200);
        primaryStage.setTitle("Enum Demo");
        primaryStage.setScene(scene);
        primaryStage.show();
    }
    public void startStop(ActionEvent e){
        if(currentStatus==Status.PAUSE || currentStatus==Status.STOP)
            currentStatus = Status.START;
        else
            currentStatus = Status.STOP;
        startText.setText(currentStatus.toString());
    }

    /**
     * Topic 2: bindings
     * Using single binding for two labels bound to a text box
     * Use double binding for two text boxes
     * @param primaryStage
     */
    public void topic2(Stage primaryStage) {
        // TextField for input
        TextField inputTextField = new TextField();
        inputTextField.setFont(new Font(24));

        // Labels that will bind to the text field input
        Label label1 = new Label("A label");
        Label label2 = new Label("Another label");
        label2.setFont(new Font(24));
        // Bind label text properties to the input text field's text property
        label1.textProperty().bind(inputTextField.textProperty());
        label2.textProperty().bind(inputTextField.textProperty());

        // Two text fields for demonstrating double binding
        TextField tf1 = new TextField();
        tf1.setFont(new Font(24));
        TextField tf2 = new TextField();
        tf2.setFont(new Font(36));

        // Double bind the text properties of the two text fields
        tf1.textProperty().bindBidirectional(tf2.textProperty());

        // Layout setup
        VBox vbox = new VBox(20, inputTextField, label1, label2, tf1, tf2);
        vbox.setAlignment(Pos.CENTER);

        Scene scene = new Scene(vbox, 600, 400);
        primaryStage.setTitle("JavaFX Binding Demo");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    /**
     * Custom components example
     * @param primaryStage
     */
    public void topic3(Stage primaryStage) {
            // TextField for input
        int[] pressCount = new int[6];
        var aList = new ArrayList<RedHoverButton>();
        ArrayList<TextField> text = new ArrayList<>();
        for (int i = 0; i < 6; i++) {
            var b = new RedHoverButton("Button " + i);
            var t = new TextField();
            final int index = i;
            b.setOnAction(event -> {
                pressCount[index]++;
                t.setText(" Pressed " + pressCount[index] + " times");
            });
            aList.add(b);
            text.add(t);

        }
        GridPane gp = new GridPane();
        // Set padding and spacing
        gp.setPadding(new Insets(10, 10, 10, 10));
        gp.setHgap(10);
        gp.setVgap(10);
        for(int i=0;i<6;i++) {
            gp.add(aList.get(i), 0, i);
            gp.add(text.get(i), 1, i);
        }
        Scene scene = new Scene(gp, 600, 400);
        primaryStage.setTitle("JavaFX custom buttons");
        primaryStage.setScene(scene);
        primaryStage.show();
    }
    int pos=0;


    public void topic4(Stage primaryStage) {
        Image image = new Image("getmecoat.gif");
        ImageView imageView = new ImageView(image);
        var button = new Button("I'll get my coat");
        button.setGraphic(imageView);
        button.getStyleClass().add("button-red");
        Image i2 = new Image("upforgrabs.gif");
        ImageView iv2 = new ImageView(i2);
        ImageView[] images = new ImageView[2];
        images[0] = iv2;
        iv2=new ImageView(new Image("bournmouth.gif"));
        images[1] = iv2;
        Label p = new Label();
        pos=0;
        button.setOnAction(event -> {
            p.setGraphic(images[pos]);
            pos=(pos+1)%2;
        });
        p.setGraphic(iv2);
        HBox box = new HBox();
        box.getChildren().add(button);
        box.getChildren().add(p);

        Scene scene = new Scene(box, 600, 400);
        primaryStage.setTitle("JavaFX animated buttons");
        primaryStage.setScene(scene);
        primaryStage.show();
    }



    public void start(Stage primaryStage) {
        topic1(primaryStage);
//        topic2(primaryStage);
//        topic3(primaryStage);
//        topic4(primaryStage);
    }

    /*
    public void startXXX(Stage primaryStage) {
    // Create the text field for input (box 1)
    DoubleProperty dp = new SimpleDoubleProperty(0);
    DoubleProperty dp2 = new SimpleDoubleProperty(0);
    StringProperty sp = new SimpleStringProperty("FOO");
        dp.bindBidirectional(dp2);
    // Create the text area for displaying entered texts (box 2)
    TextArea displayTextArea = new TextArea();
        displayTextArea.setEditable(false); // Make it non-editable
        displayTextArea.setWrapText(true); // Enable text wrapping

    // Add an action event to the text field to handle Enter key press
        inputTextField.setOnAction(event ->

    {
        String inputText = inputTextField.getText();
        if (!inputText.isEmpty()) {
            // Append the input text to the text area and add a newline
            displayTextArea.appendText(inputText + "\n");

            // Clear the input text field for new text
            inputTextField.clear();
        }
    });

    // Layout setup
    VBox root = new VBox(10, inputTextField, displayTextArea);
        root.setPadding(new

    Insets(10));

    // Scene and stage setup
    Scene scene = new Scene(root, 300, 200);
        primaryStage.setTitle("TextField Example");
        primaryStage.setScene(scene);
        primaryStage.show();
}

 /*
        @Override
    public void start(Stage stage) {
        Image image = new Image("getmecoat.gif");
        ImageView imageView = new ImageView(image);
        var button = new Button("I'll get my coat");
        button.setGraphic(imageView);
        button.getStyleClass().add("button-red");


        Image i2 = new Image("upforgrabs.gif");
        ImageView iv2 = new ImageView(i2);
        var button2 = new RedHoverButton("Its up for grabs now!");
        button2.setGraphic(iv2);


        TextField text= new TextField("System Status: Green");
        text.setFont(new Font(28));
        RedHoverButton b = new RedHoverButton("turns red when mouse over");
        RedHoverButton b2 = new RedHoverButton("A second red hover button");

        TextArea area = new TextArea(text.getText());

        area.setMaxHeight(10);
        b.setOnAction(ActionEvent->{
            text.setText("RED ALERT!!");
            text.setStyle("-fx-text-fill: red;");
        });
        area.textProperty().bind(text.textProperty());
        area.styleProperty().bind(text.styleProperty());
        Label l = new Label(text.getText());
        l.textProperty().bind(text.textProperty());
        area.setFont(new Font(44));
        l.setFont(new Font(36));

//        box.getChildren().add(text);

        VBox box = new VBox();
        for( int i=0;i<6;i++)
            box.getChildren().add(new RedHoverButton("Button number "+i));
 //       box.getChildren().add(l);
        HBox box2 = new HBox();
        box2.getChildren().add(button);
        button2.setPrefHeight(40);
        button2.setPrefWidth(100);
        box2.getChildren().add(button2);

        Scene scene = new Scene(box2, 640, 480);
        var sheet = getClass().getResource("/style.css").toExternalForm();
        scene.getStylesheets().add(sheet);
        stage.setScene(scene);
        stage.show();
    }
    */

    public static void main(String[] args) {
        launch();
    }

}
package uk.ac.soton.comp1206;
import javafx.application.Application;
import javafx.beans.property.StringProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.geometry.Orientation;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class SliderExample extends Application {
    enum SliderType{STATIC,INNER,LOCAL,ANON,LAMBDA,FUNCTION}
    static SliderType nested=SliderType.ANON;
    static String message="Set with "+nested;

    @Override
    public void start(Stage primaryStage) {
        // Create a slider with a range from 0 to 100 with an initial value of 50
        Slider slider = new Slider(0, 100, 50);
        slider.setShowTickLabels(true);
        slider.setShowTickMarks(true);
        slider.setMajorTickUnit(50);
        slider.setMinorTickCount(5);
        slider.setBlockIncrement(10);
        // Create a label that will display the value of the slider
        slider.setStyle("-fx-font-size: 24px;");
        // Lambda example


        VBox toggles = makeToggleButtons();
        // Arrange the slider and label vertically
        HBox root = new HBox();
        TextArea sliderValue = setSlider(slider);
        sliderValue.setPrefSize(400,10);
        VBox s = new VBox(10, slider, sliderValue);
        root.getChildren().addAll(s,toggles);
        Scene scene = new Scene(root, 600, 200);

        primaryStage.setTitle("Slider Example");
        primaryStage.setScene(scene);
        primaryStage.show();
    }
    private static VBox makeToggleButtons(){
        VBox container = new VBox();
        container.setSpacing(10); // Set spacing between elements
        // Create a ToggleGroup
        ToggleGroup group = new ToggleGroup();
        RadioButton[] buttons = new RadioButton[5];
        String[] names = {"Inner Class", "Static nested class", "anonymous class","Lambda","Function reference"};
        for (int i=0;i<5;i++) {
            buttons[i] = new RadioButton(names[i]);
            buttons[i].setToggleGroup(group);
            final int x = i;
            buttons[i].setOnAction(a -> nested = SliderType.values()[x]);
            container.getChildren().add(buttons[i]);
        }
        return container;

    }

    private static TextArea setSlider(Slider s){
        TextArea sliderValue = new TextArea(Double.toString(s.getValue()));
        sliderValue.setFont(new Font(24));
        switch(nested){
            case STATIC:
                break;
            case INNER:

                break;
            case ANON:
                //Anonymous
                s.valueProperty().addListener(new ChangeListener<Number>() {
                    @Override
                    public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
                        sliderValue.setText(String.format(message+" %.2f", newValue));
                        System.out.println(" Value of message = "+message+" value of nested = "+nested);
                    }
                });
                break;
            case LAMBDA:
                // Lambda example from chatGPT
//                s.valueProperty().addListener((ObservableValue<? extends Number> ov, Number old_val, Number new_val) -> {
//                    sliderValue.setText(String.format("%.2f", new_val));
//                });
                //Lambda without typing, which is inferred
                s.valueProperty().addListener((ov, old_val, new_val) -> {
                    sliderValue.setText(String.format(message+" = %.2f", new_val));
                });
                break;
        }
        return sliderValue;
    }

    public static void main(String[] args) {
        launch(args);
    }
    public class SliderInnerClass implements ChangeListener<Number>{

        @Override
        public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {

        }
    }
    public class SliderStaticNestedClass implements ChangeListener<Number>{

        @Override
        public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {

        }
    }

}

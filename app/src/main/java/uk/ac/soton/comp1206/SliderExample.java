package uk.ac.soton.comp1206;
import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.geometry.Orientation;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class SliderExample extends Application {
    enum SliderType{STATIC,INNER,LOCAL,ANON,LAMBDA}
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
        Label sliderValue = setSlider(slider, SliderType.ANON);


        // Arrange the slider and label vertically
        VBox root = new VBox(10, slider, sliderValue);

        Scene scene = new Scene(root, 400, 200);

        primaryStage.setTitle("Slider Example");
        primaryStage.setScene(scene);
        primaryStage.show();
    }
    private static Label setSlider(Slider s, SliderType t){
        Label sliderValue = new Label(Double.toString(s.getValue()));
        sliderValue.setFont(new Font(24));
        switch(t){
            case STATIC:
                break;
            case INNER:

                break;
            case ANON:
                //Anonymous
                s.valueProperty().addListener(new ChangeListener<Number>() {
                    @Override
                    public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
                        sliderValue.setText(String.format("Set with anon class %.2f", newValue));
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
                    sliderValue.setText(String.format("Set with Lambda = %.2f", new_val));
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

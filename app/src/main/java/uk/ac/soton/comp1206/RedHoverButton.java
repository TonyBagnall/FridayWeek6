package uk.ac.soton.comp1206;

import javafx.scene.control.Button;
import javafx.scene.text.Font;

// Custom button that changes to red when hovered over
class RedHoverButton extends Button {
    public RedHoverButton(String text) {
        super(text);
        this.initStyle();
    }
    private void initStyle() {
        // Set the initial style (optional, could also use a CSS file)
        this.setStyle("-fx-background-color: white; -fx-text-fill: black;");
        // Change the style when the mouse hovers over the button
        this.setOnMouseEntered(e -> this.setStyle("-fx-background-color: red; -fx-text-fill: white;"));
        // Revert to the original style when the mouse exits the button
        this.setOnMouseExited(e -> this.setStyle("-fx-background-color: white; -fx-text-fill: black;"));
        this.setFont(new Font(24));

    }
}
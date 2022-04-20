package com.company.src.customNodes;

import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.text.Font;

public class MyButton {

    public static Button getMyButton(String message, Font font, Insets insets) {
        Button button = new Button(message);
        button.setMaxWidth(Double.MAX_VALUE);
        button.setFont(font);
        if (insets != null)
            button.setPadding(insets);
        return button;
    }
}

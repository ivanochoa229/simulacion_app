package org.simulacion.utils;

import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public final class InputCleaner {

    public static void clearInputFields(TextField... fields) {
        for (TextField field : fields) {
            field.clear();
        }
    }

    public static void clearTextAreaOnFocus(TextArea textArea, TextField... fields) {
        for (TextField field : fields) {
            field.setOnMouseClicked(e -> textArea.clear());
        }
    }
}

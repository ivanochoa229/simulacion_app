package org.simulacion.utils;


import javafx.scene.control.TextField;

public class InputValidator {

    public static boolean areFieldsEmpty(TextField... fields) {
        for (TextField field : fields) {
            if (field.getText().isEmpty()) {
                return true;
            }
        }
        return false;
    }
}

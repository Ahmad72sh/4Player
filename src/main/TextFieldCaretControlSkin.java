package main;

import com.sun.javafx.scene.control.skin.TextFieldSkin;
import javafx.scene.control.TextField;

public class TextFieldCaretControlSkin extends TextFieldSkin {
    public TextFieldCaretControlSkin(TextField textField, double scale) {
        super(textField);

        setScale(scale);
    }

    private void setScale(double scale) {
        caretPath.setScaleX(scale);
        caretPath.setScaleY(scale);
    }
}

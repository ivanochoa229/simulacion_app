package org.simulacion.presentation.controller.generator;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import org.simulacion.configuration.AppConfig;
import org.simulacion.generator.Lehmer;
import org.simulacion.presentation.dto.LehmerRequest;
import org.simulacion.repository.GlobalRepository;
import org.simulacion.service.LehmerService;
import org.simulacion.utils.InputCleaner;
import org.simulacion.utils.InputValidator;
import org.simulacion.utils.Path;
import org.simulacion.utils.ViewUtils;

import java.util.List;

public class LehmerController {

    private final LehmerService lehmerService;

    public LehmerController() {
        this.lehmerService = new LehmerService(new Lehmer());
    }

    @FXML
    private TextArea txtFieldNumbers;

    @FXML
    private TextField txtQuantity;

    @FXML
    private TextField txtSead;

    @FXML
    private TextField txtT;

    @FXML
    void generateNumbers(ActionEvent event) {
        if (InputValidator.areFieldsEmpty(txtSead, txtT, txtQuantity)) {
            ViewUtils.showErrorAlert("Error", "Complete los campos");
            return;
        }
        try {
            LehmerRequest request = new LehmerRequest( Integer.parseInt(txtT.getText()),
                    Integer.parseInt(txtSead.getText()),
                    Integer.parseInt(txtQuantity.getText()));
            List<Double> numbers = lehmerService.generateNumbers(request);
            GlobalRepository.setSharedNumbers(numbers);
            txtFieldNumbers.setText(ViewUtils.formatNumbers(numbers));
            InputCleaner.clearInputFields(txtSead, txtT, txtQuantity);
        } catch (Exception e) {
            ViewUtils.showErrorAlert("Error", e.getMessage());
        }
    }

    @FXML
    void returnMain(ActionEvent event) {
        AppConfig.setScene(Path.PRINCIPAL_CONTROLLER);
    }

    @FXML
    void selectTest(ActionEvent event) {
        AppConfig.setScene(Path.SELECT_TEST_CONTROLLER);
    }

    @FXML
    public void initialize() {
        ViewUtils.setupIntegerTextField(txtSead);
        ViewUtils.setupIntegerTextField(txtT);
        ViewUtils.setupIntegerTextField(txtQuantity);

        InputCleaner.clearTextAreaOnFocus(txtFieldNumbers, txtSead, txtT, txtQuantity);
    }
}

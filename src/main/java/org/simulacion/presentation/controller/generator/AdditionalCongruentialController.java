package org.simulacion.presentation.controller.generator;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import org.simulacion.configuration.AppConfig;
import org.simulacion.generator.AdditionalCongruential;
import org.simulacion.presentation.dto.AdditionalCongruentialRequest;
import org.simulacion.repository.GlobalRepository;
import org.simulacion.service.AdditionalCongruentialService;
import org.simulacion.utils.InputCleaner;
import org.simulacion.utils.InputValidator;
import org.simulacion.utils.Path;
import org.simulacion.utils.ViewUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class AdditionalCongruentialController {
    private final AdditionalCongruentialService additionalCongruentialService;

    public AdditionalCongruentialController() {
        this.additionalCongruentialService = new AdditionalCongruentialService(new AdditionalCongruential());
    }

    @FXML
    private TextArea txtAreas;

    @FXML
    private TextField txtK;

    @FXML
    private TextField txtModule;

    @FXML
    private TextField txtQuantity;

    private List<Integer> seeds = new ArrayList<>();

    @FXML
    void generateNumbers(ActionEvent event) {
        if (InputValidator.areFieldsEmpty(txtK, txtQuantity, txtModule)) {
            ViewUtils.showErrorAlert("Error", "Complete los campos");
            return;
        }
        try {
            int k = Integer.parseInt(txtK.getText());
            int module = Integer.parseInt(txtModule.getText());
            int quantity = Integer.parseInt(txtQuantity.getText());

            if (k <= 0 || module <= 0 || quantity <= 0) {
                ViewUtils.showErrorAlert("Error", "Los valores deben ser positivos");
                return;
            }

            // 3. Pedir las k semillas al usuario
            seeds.clear(); // Limpiar lista anterior
            for (int i = 0; i < k+1; i++) {
                Optional<String> result = ViewUtils.showInputDialog(
                        "Semilla " + (-i),
                        "Ingrese el valor para la semilla " + (-i) + " (entero)"
                );

                if (result.isPresent()) {
                    try {
                        int seed = Integer.parseInt(result.get());
                        seeds.add(seed);
                    } catch (NumberFormatException e) {
                        ViewUtils.showErrorAlert("Error", "Debe ingresar un número entero");
                        i--; // Repetir esta iteración
                    }
                } else {
                    // Usuario canceló la operación
                    return;
                }
            }
            ViewUtils.showInfoAlert("Semillas ingresadas", "Semillas: " + seeds.toString());
            List<Double> numbers = additionalCongruentialService
                    .generateNumbers(
                            new AdditionalCongruentialRequest(k, module, quantity, seeds));
            GlobalRepository.setSharedNumbers(numbers);
            txtAreas.setText(ViewUtils.formatNumbers(numbers));
        } catch (NumberFormatException e) {
            ViewUtils.showErrorAlert("Error", "Ingrese valores numéricos válidos");
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
        ViewUtils.setupIntegerTextField(txtK);
        ViewUtils.setupIntegerTextField(txtModule);
        ViewUtils.setupIntegerTextField(txtQuantity);

        InputCleaner.clearTextAreaOnFocus(txtAreas, txtK, txtModule, txtQuantity);
    }
}

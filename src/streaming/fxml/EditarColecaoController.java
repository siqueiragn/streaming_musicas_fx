/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package streaming.fxml;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import streaming.model.Interprete;

/**
 * FXML Controller class
 *
 * @author Gabriel
 */
public class EditarColecaoController implements Initializable {

    @FXML
    private ListView<Interprete> listaCompositores;
    @FXML
    private ListView<Interprete> listaInterpretes;
    @FXML
    private TextField inputNomeColecao;
    @FXML
    private TextField inputAnoColecao;
    @FXML
    private ComboBox<?> inputTipoColecao;
    @FXML
    private TextField inputIdColecao;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void vincularCompositor(ActionEvent event) {
    }

    @FXML
    private void vincularInterprete(ActionEvent event) {
    }

    @FXML
    private void salvarColecao(ActionEvent event) {
    }
    
}

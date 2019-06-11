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
import streaming.fxml.StreamingMusica;

/**
 * FXML Controller class
 *
 * @author Gabriel
 */
public class FXMLDocumentController implements Initializable {

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void trocarTelaCadastrarMusica(ActionEvent event) {
        StreamingMusica.trocarTela("CadastrarMusica.FXML");
    }

    @FXML
    private void trocarTelaListarMusica(ActionEvent event) {
    }

    @FXML
    private void trocarTelaCadastrarColecoes(ActionEvent event) {
        StreamingMusica.trocarTela("CadastrarColecao.fxml");
    }

    @FXML
    private void trocarTelaListarColecoes(ActionEvent event) {
    }

    @FXML
    private void trocarTelaCadastrarPlaylists(ActionEvent event) {
    }

    @FXML
    private void trocarTelaListarPlaylists(ActionEvent event) {
    }

    @FXML
    private void trocarTelaCadastrarArtistas(ActionEvent event) {
    }
    
}

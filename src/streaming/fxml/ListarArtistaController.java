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
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import streaming.model.Artista;

/**
 * FXML Controller class
 *
 * @author Gabriel
 */
public class ListarArtistaController implements Initializable {

    @FXML
    private TableView<Artista> artistasTable;
    @FXML
    private TableColumn<Artista, ?> idArtistaCol;
    @FXML
    private TableColumn<Artista, ?> nomeArtistaCol;
    @FXML
    private TextField inputPesquisar;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        idArtistaCol.setCellValueFactory(new PropertyValueFactory("id"));
        nomeArtistaCol.setCellValueFactory(new PropertyValueFactory("nome"));
        
        for (Artista a : Artista.getArtistas("")) {
            artistasTable.getItems().add(a);
        }

        // TODO
    }    
    
    @FXML
    private void trocarTelaCadastrarMusica(ActionEvent event) {
        StreamingMusica.trocarTela("CadastrarMusica.fxml");
    }

    @FXML
    private void trocarTelaListarMusica(ActionEvent event) {
        StreamingMusica.trocarTela("ListarMusica.fxml");
    }

    @FXML
    private void trocarTelaCadastrarColecoes(ActionEvent event) {
        StreamingMusica.trocarTela("CadastrarColecao.fxml");
    }

    @FXML
    private void trocarTelaListarColecoes(ActionEvent event) {
        StreamingMusica.trocarTela("ListarColecao.fxml");
    }

    @FXML
    private void trocarTelaCadastrarPlaylists(ActionEvent event) {
        StreamingMusica.trocarTela("CadastrarPlaylist.fxml");
    }

    @FXML
    private void trocarTelaListarPlaylists(ActionEvent event) {
        StreamingMusica.trocarTela("ListarPlaylist.fxml");
    }

    @FXML
    private void trocarTelaCadastrarArtistas(ActionEvent event) {
        StreamingMusica.trocarTela("CadastrarArtista.fxml");
    }

    @FXML
    private void trocarTelaListarArtistas(ActionEvent event) {
        StreamingMusica.trocarTela("ListarArtista.fxml");
    }

    @FXML
    private void trocarTelaHome(ActionEvent event) {
        StreamingMusica.trocarTela("Home.fxml");
    }

    @FXML
    private void pesquisarArtistas(ActionEvent event) {
        
        artistasTable.getItems().clear();
        for (Artista a : Artista.getArtistas(inputPesquisar.getText())) {
            artistasTable.getItems().add(a);
        }
        
    }
    
}

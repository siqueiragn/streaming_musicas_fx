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
import javafx.scene.control.ListView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import oracle.net.aso.p;
import streaming.model.Colecao;
import streaming.model.Musica;
import streaming.model.Ouvinte;
import streaming.model.Playlist;
import streaming.model.RowMusica;

/**
 * FXML Controller class
 *
 * @author Gabriel
 */
public class CadastrarPlaylistController implements Initializable {

    @FXML
    private TextField inputNomeAutor;
    @FXML
    private TextField inputNomePlaylist;
    @FXML
    private TableView<RowMusica> tabelaMusicas;
    @FXML
    private TableColumn<RowMusica, String> musicaNomeCol;
    @FXML
    private TableColumn<RowMusica, Colecao> colecaoNomeCol;
    @FXML
    private ListView<Musica> listaMusicasAdicionadas;
    @FXML
    private TextField campoPesquisa;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
       
        musicaNomeCol.setCellValueFactory(new PropertyValueFactory("musica"));
        colecaoNomeCol.setCellValueFactory(new PropertyValueFactory("colecaoNome"));
        
        for (RowMusica rm : RowMusica.getMusicas("")) {
            tabelaMusicas.getItems().add(rm);
        }
    }    

    @FXML
    private void addMusicaPlaylist(ActionEvent event) {
        
        listaMusicasAdicionadas.getItems().add(tabelaMusicas.getSelectionModel().getSelectedItem().getMusica());
        
    }

    @FXML
    private void rmvMusicaPlaylist(ActionEvent event) {
        listaMusicasAdicionadas.getItems().remove(listaMusicasAdicionadas.getSelectionModel().getSelectedItem());
    }

    @FXML
    private void criarPlaylist(ActionEvent event) {
        
        Ouvinte o = new Ouvinte();
        o.setNome(inputNomeAutor.getText());
        o.insert();
        
        Playlist p = new Playlist();
        p.setAutor(o);
        p.setMusicas(listaMusicasAdicionadas.getItems());
        
        p.insert();
        
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
    private void pesquisarMusica(ActionEvent event) {
        String pesquisa = campoPesquisa.getText();
        
        tabelaMusicas.getItems().clear();
        for (RowMusica rm : RowMusica.getMusicas(pesquisa)) {
            tabelaMusicas.getItems().add(rm);
        }
    }
    
}

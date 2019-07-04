/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package streaming.fxml;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import streaming.model.Musica;
import streaming.model.Ouvinte;
import streaming.model.Playlist;
import streaming.model.RowMusica;

/**
 * FXML Controller class
 *
 * @author Gabriel
 */
public class ListarPlaylistController implements Initializable {

    @FXML
    private TableView<Musica> musicaTable;
    @FXML
    private TableColumn<Musica, ?> nomeMusicalCol;
    @FXML
    private TableColumn<Musica, ?> duracaoMusicaCol;
    @FXML
    private TableView<Playlist> playlistTable;
    @FXML
    private TableColumn<Playlist, ?> idPlaylistCol;
    @FXML
    private TableColumn<Playlist, ?> nomePlaylistCol;
    @FXML
    private TableColumn<Playlist, ?> autorPlaylistCol;
    @FXML
    private Button editarBtn;
    @FXML
    private TextField inputNomeAutor;
    @FXML
    private Label labelNomeAutor;
    @FXML
    private Label labelNomePlaylist;
    @FXML
    private TextField inputNomePlaylist;
    @FXML
    private TableView<RowMusica> tabelaMusicas;
    @FXML
    private TableColumn<RowMusica, ?> musicaNomeCol;
    @FXML
    private TableColumn<RowMusica, ?> colecaoNomeCol;
    @FXML
    private ListView<Musica> listaMusicasAdicionadas;
    @FXML
    private Button addMusicaPlaylist;
    @FXML
    private TextField campoPesquisa;
    @FXML
    private Button rmvMusicaPlaylist;
    @FXML
    private Button pesquisarMusica;
    @FXML
    private Button finalizarPlaylist;
   
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        nomeMusicalCol.setCellValueFactory(new PropertyValueFactory("nome"));
        duracaoMusicaCol.setCellValueFactory(new PropertyValueFactory("duracao"));
        
        idPlaylistCol.setCellValueFactory(new PropertyValueFactory("id"));
        nomePlaylistCol.setCellValueFactory(new PropertyValueFactory("nome"));
        autorPlaylistCol.setCellValueFactory(new PropertyValueFactory("nomeAutor"));
        
        musicaNomeCol.setCellValueFactory(new PropertyValueFactory("musica"));
        colecaoNomeCol.setCellValueFactory(new PropertyValueFactory("colecaoNome"));
        
        for (Playlist p : Playlist.getAll()) {
            playlistTable.getItems().add(p);
        }
        
        for (RowMusica rm : RowMusica.getMusicas("")) {
            tabelaMusicas.getItems().add(rm);
        }
        
        playlistTable.setOnMouseClicked( new EventHandler<MouseEvent>() {
            
            @Override
            public void handle(MouseEvent event) {
                Playlist p = playlistTable.getSelectionModel().getSelectedItem();
                
                musicaTable.getItems().clear();
                for (Musica m : p.getAllMusicas()) {
                        musicaTable.getItems().add(m);
                }
                
            }
          
        });
        
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
    private void habilitarEdicao(ActionEvent event) {
        
        Playlist aux =  playlistTable.getSelectionModel().getSelectedItem();
        
        if ( aux != null ) {
            
            editarBtn.setVisible(true);
            inputNomeAutor.setVisible(true);
            labelNomeAutor.setVisible(true);
            labelNomePlaylist.setVisible(true);
            inputNomePlaylist.setVisible(true);
            tabelaMusicas.setVisible(true);
            listaMusicasAdicionadas.setVisible(true);
            addMusicaPlaylist.setVisible(true);
            campoPesquisa.setVisible(true);
            rmvMusicaPlaylist.setVisible(true);
            pesquisarMusica.setVisible(true);
            finalizarPlaylist.setVisible(true);

            editarBtn.setVisible(false);
            musicaTable.setVisible(false);
            playlistTable.setVisible(false);
            
            inputNomeAutor.setText(aux.getAutor().getNome());
            inputNomePlaylist.setText(aux.getNome());
            
            for (Musica m : aux.getAllMusicas()) {
                listaMusicasAdicionadas.getItems().add(m);
            }
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
    private void finalizarPlaylist(ActionEvent event) {
        
            Playlist aux =  playlistTable.getSelectionModel().getSelectedItem();
        
            aux.setNome(inputNomePlaylist.getText());
            aux.setMusicas(listaMusicasAdicionadas.getItems());
            aux.update();
            
            Ouvinte oAux = aux.getAutor();
            oAux.setNome(inputNomeAutor.getText());
            oAux.update();
            
            editarBtn.setVisible(false);
            inputNomeAutor.setVisible(false);
            labelNomeAutor.setVisible(false);
            labelNomePlaylist.setVisible(false);
            inputNomePlaylist.setVisible(false);
            tabelaMusicas.setVisible(false);
            listaMusicasAdicionadas.setVisible(false);
            addMusicaPlaylist.setVisible(false);
            campoPesquisa.setVisible(false);
            rmvMusicaPlaylist.setVisible(false);
            pesquisarMusica.setVisible(false);
            finalizarPlaylist.setVisible(false);

            editarBtn.setVisible(true);
            musicaTable.setVisible(true);
            playlistTable.setVisible(true);
            
            playlistTable.getItems().clear();
            for (Playlist p : Playlist.getAll()) {
                playlistTable.getItems().add(p);
            }
        
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

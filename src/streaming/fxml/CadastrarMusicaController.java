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
import javafx.scene.control.ListView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import streaming.model.Colecao;
import streaming.model.Interprete;
import streaming.model.Letra;
import streaming.model.Musica;

/**
 * FXML Controller class
 *
 * @author Gabriel
 */
public class CadastrarMusicaController implements Initializable {


    @FXML
    private TextField nomeMusica;
    
    @FXML
    private TextField duracaoMusica;
    
    @FXML
    private TextArea letraMusica;
    
    @FXML
    private TableView<Colecao> album;
    
    @FXML
    private TableColumn<Integer, Colecao> idCol;
    
    @FXML
    private TableColumn<String, Colecao> nomeCol;
    
    @FXML
    private TableColumn<Integer, Colecao> anoCol;
    
    @FXML
    private ListView<Interprete> listaInterpretes;
    
    @FXML
    private ListView<Interprete> listaCompositores;
    
    @FXML
    private Button btnCadastrar;
    
    
    @FXML
    public void inserirMusica(ActionEvent e) {
        
        Musica m = new Musica();
        m.setNome(nomeMusica.getText());
        m.setDuracao(Double.parseDouble(duracaoMusica.getText()));
        //m.setColecao(album);
       
        
    }
    
    @FXML
    public void cadastrarMusica(ActionEvent e) {
        
       Musica m = new Musica();
       m.setColecao(album.getSelectionModel().getSelectedItem());
       m.setDuracao(Double.parseDouble(duracaoMusica.getText()));
       m.setNome(nomeMusica.getText());
       
       Letra l = new Letra();
       l.setTexto(letraMusica.getText());
       l.insert();
       
       m.setLetra(l);  
       m.insert();
              
    }
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        nomeCol.setCellValueFactory(new PropertyValueFactory("nome"));
        idCol.setCellValueFactory(new PropertyValueFactory("id"));
        anoCol.setCellValueFactory(new PropertyValueFactory("lancamento"));
        
        for (Colecao c : Colecao.getAll()) {
            album.getItems().add(c);
        }
        
        album.setOnMouseClicked( new EventHandler<MouseEvent>() {
            
            @Override
            public void handle(MouseEvent event) {
                Colecao c = album.getSelectionModel().getSelectedItem();
                
                listaCompositores.getItems().clear();
                for (Interprete comp : c.getCompositoresFromDB()) {
                        listaCompositores.getItems().add(comp);
                }
                
                listaInterpretes.getItems().clear();
                for (Interprete intp : c.getInterpretesFromDB()) {
                    listaInterpretes.getItems().add(intp);
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
    
}

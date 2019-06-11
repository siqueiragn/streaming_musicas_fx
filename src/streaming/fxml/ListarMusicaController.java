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
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import streaming.model.Colecao;
import streaming.model.Interprete;
import streaming.model.Musica;

/**
 * FXML Controller class
 *
 * @author Gabriel
 */
public class ListarMusicaController implements Initializable {

    @FXML
    private TextField inputNomeMusica;
    @FXML
    private TextField inputDuracaoMusica;
    @FXML
    private TextArea inputLetraMusica;
    @FXML
    private TextField inputIdLetraMusica;
    @FXML
    private TableView<Colecao> tableColecao;
    @FXML
    private TableColumn<Colecao, Integer> colIdColecao;
    @FXML
    private TableColumn<Colecao, String> colNomeColecao;
    @FXML
    private TableColumn<Colecao, Integer> colAnoColecao;
    @FXML
    private TableView<Musica> tableMusica;
    @FXML
    private TableColumn<Musica, Integer> colIdMusica;
    @FXML
    private TableColumn<Musica, String> colNomeMusica;
    @FXML
    private TableColumn<Musica, Colecao> colAlbumMusica;
    @FXML
    private TableColumn<Musica, Double> colDuracaoMusica;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
         // TODO
        colIdMusica.setCellValueFactory(new PropertyValueFactory("id"));
        colNomeMusica.setCellValueFactory(new PropertyValueFactory("nome"));
        colAlbumMusica.setCellValueFactory(new PropertyValueFactory("colecao"));
        colDuracaoMusica.setCellValueFactory(new PropertyValueFactory("duracao"));
        
        for (Musica m : Musica.getAll()) {
            tableMusica.getItems().add(m);
        }
        
        colIdColecao.setCellValueFactory(new PropertyValueFactory("id"));
        colNomeColecao.setCellValueFactory(new PropertyValueFactory("nome"));
        colAnoColecao.setCellValueFactory(new PropertyValueFactory("lancamento"));
       
        
        for (Colecao c : Colecao.getAll()) {
            tableColecao.getItems().add(c);
        }
        
        tableMusica.setOnMouseClicked( new EventHandler<MouseEvent>() {
            
            @Override
            public void handle(MouseEvent event) {
                Musica m = tableMusica.getSelectionModel().getSelectedItem();
                
                inputDuracaoMusica.setText(String.valueOf(m.getDuracao()));
                inputNomeMusica.setText(m.getNome());
                inputLetraMusica.setText(m.getLetra().getTexto());
                inputIdLetraMusica.setText(String.valueOf(m.getLetra().getId()));
            }
          
        });
        
        
        
        
    }    

    @FXML
    private void cadastrarMusica(ActionEvent event) {
    }

    @FXML
    private void visualizarMusica(ActionEvent event) {
    }
    
}

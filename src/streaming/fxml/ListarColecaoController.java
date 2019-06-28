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
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
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
public class ListarColecaoController implements Initializable {

    @FXML
    private ListView<Interprete> listaInterpretes;
    @FXML
    private ListView<Interprete> listaCompositores;
    @FXML
    private TableView<Colecao> colecaoTable;
    @FXML
    private TableColumn<Colecao, Integer> colIdColecao;
    @FXML
    private TableColumn<Colecao, String> colNomeColecao;
    @FXML
    private TableColumn<Colecao, Double> colLancamentoColecao;
    @FXML
    private Label labelCompositores;
    @FXML
    private Label labelInterpretes;
    @FXML
    private Button btnVisualizar;
    @FXML
    private TextField inputNomeColecao;
    @FXML
    private TextField inputAnoColecao;
    @FXML
    private ListView<Musica> listaMusicas;
    @FXML
    private TextField tipoColecao;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO        
        colIdColecao.setCellValueFactory(new PropertyValueFactory("id"));
        colNomeColecao.setCellValueFactory(new PropertyValueFactory("nome"));
        colLancamentoColecao.setCellValueFactory(new PropertyValueFactory("lancamento"));
       
        for (Colecao c : Colecao.getAll()) {
            colecaoTable.getItems().add(c);
        }
        
        colecaoTable.setOnMouseClicked( new EventHandler<MouseEvent>() {
            
            @Override
            public void handle(MouseEvent event) {
                Colecao c = colecaoTable.getSelectionModel().getSelectedItem();
                
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
    private void trocarTelaEditarColecao(ActionEvent event) {
        
        Colecao c = colecaoTable.getSelectionModel().getSelectedItem().getById();
        
        colecaoTable.setVisible(false);
        btnVisualizar.setVisible(false);
        
        inputAnoColecao.setVisible(true);
        inputAnoColecao.setText(String.valueOf(c.getLancamento()));
        inputNomeColecao.setVisible(true);
        inputNomeColecao.setText(c.getNome());
        tipoColecao.setVisible(true);
        tipoColecao.setText(String.valueOf(c.getClass().getSimpleName()));
        
        listaMusicas.setVisible(true);
        
        for (Musica m : c.getMusicasById()) {
            listaMusicas.getItems().add(m);
        }
        
    }

    @FXML
    private void editarColecao(ActionEvent event) {
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

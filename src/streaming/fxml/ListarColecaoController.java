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
    private ComboBox<String> inputTipoColecao;
    @FXML
    private ListView<Musica> listaMusicas;

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
        
        inputTipoColecao.getItems().clear();
       
        inputTipoColecao.getItems().add("Album");
        inputTipoColecao.getItems().add("Bootleg");
        inputTipoColecao.getItems().add("Compilação");
        inputTipoColecao.getItems().add("Single");
        
        colecaoTable.setVisible(false);
        btnVisualizar.setVisible(false);
        
        inputAnoColecao.setVisible(true);
        inputAnoColecao.setText(String.valueOf(c.getLancamento()));
        inputNomeColecao.setVisible(true);
        inputNomeColecao.setText(c.getNome());
        inputTipoColecao.setVisible(true);
        inputTipoColecao.setValue(String.valueOf(c.getClass()));
        
        listaMusicas.setVisible(true);
        
        for (Musica m : c.getMusicasById()) {
            listaMusicas.getItems().add(m);
        }
        
    }

    @FXML
    private void editarColecao(ActionEvent event) {
    }
    
}

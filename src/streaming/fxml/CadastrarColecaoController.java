/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package streaming.fxml;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import streaming.model.*;

/**
 * FXML Controller class
 *
 * @author Gabriel
 */
public class CadastrarColecaoController implements Initializable {

    @FXML
    private ListView<Interprete> listaCompositores;
    @FXML
    private ListView<Interprete> listaInterpretes;
    @FXML
    private TextField nomeColecao;
    @FXML
    private TextField anoColecao;
    @FXML
    private ComboBox<String> tiposColecoes;
    @FXML
    private Label listaInterpretesAdicionados;
    @FXML
    private Label listaCompositoresAdicionados;
    
    private List<Interprete> listaInterpretesInserir = new ArrayList<>();
    private List<Interprete> listaCompositoresInserir = new ArrayList<>();

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        tiposColecoes.getItems().clear();
        listaCompositores.getItems().clear();
        listaInterpretes.getItems().clear();
        
        tiposColecoes.getItems().add("Album");
        tiposColecoes.getItems().add("Bootleg");
        tiposColecoes.getItems().add("Compilação");
        tiposColecoes.getItems().add("Single");
        
        for(Interprete i : Interprete.getAll()) {
            listaCompositores.getItems().add(i);
            listaInterpretes.getItems().add(i);
        }
    }    

    @FXML
    private void vincularCompositor(ActionEvent event) {
        
        listaCompositoresInserir.add(listaCompositores.getSelectionModel().getSelectedItem());
        
        String aux = "Compositores: ";
        for (Interprete i : listaCompositoresInserir) {
            aux += i.toString() + ", ";
        }
        
        aux = aux.substring(0, aux.length() - 2);
        listaCompositoresAdicionados.setText(aux);
    }

    @FXML
    private void vincularInterprete(ActionEvent event) {
        
       listaInterpretesInserir.add(listaInterpretes.getSelectionModel().getSelectedItem());
        
        String aux = "Interpretes: ";
        for (Interprete i : listaInterpretesInserir) {
            aux += i.toString() + ", ";
        }
        
        aux = aux.substring(0, aux.length() - 2);
        listaInterpretesAdicionados.setText(aux);
        
    }

    @FXML
    private void salvarColecao(ActionEvent event) {
        
       // System.out.println(tiposColecoes.getSelectionModel().getSelectedItem());
        switch(tiposColecoes.getSelectionModel().getSelectedItem()) {
            case "Album":
                Album a = new Album();
                a.setNome(nomeColecao.getText());
                a.setLancamento(Integer.parseInt(anoColecao.getText()));
                a.insert();
                
            break;
            case "Bootleg":
                Bootleg b = new Bootleg();
                b.setNome(nomeColecao.getText());
                b.setLancamento(Integer.parseInt(anoColecao.getText()));
                b.insert();
                
            break;
            case "Compilação":
                Compilacao c = new Compilacao();
                c.setNome(nomeColecao.getText());
                c.setLancamento(Integer.parseInt(anoColecao.getText()));
                c.insert();
               
            break;
            case "Single":
                Single s = new Single();
                s.setNome(nomeColecao.getText());
                s.setLancamento(Integer.parseInt(anoColecao.getText()));
                s.insert();
                
            break;
        }
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

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package streaming.fxml;

import java.io.IOException;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 *
 * @author Gabriel
 */
public class StreamingMusica extends Application {
    
    private static Stage st;
    
    public static void trocarTela(String caminho) {
        
        try {
        Parent root = FXMLLoader.load(StreamingMusica.class.getResource(caminho));
        
        Scene scene = new Scene(root);
        
        st.setScene(scene);
        st.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    
    }
    
    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("ListarMusica.fxml"));
        //Parent root = FXMLLoader.load(getClass().getResource("FXMLDocument.fxml"));
        st = stage;
        Scene scene = new Scene(root);
        
        st.setScene(scene);
        st.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}

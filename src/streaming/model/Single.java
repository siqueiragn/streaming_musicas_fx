/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package streaming.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;
import streaming.database.Conector;

/**
 *
 * @author Gabriel
 */
public class Single extends Colecao{

    public Single(int id, String nome, List<Interprete> interpretes, List<Interprete> compositores, int lancamento) {
        super(id, nome, interpretes, compositores, lancamento);
    }

    public Single() {
    }
    
       public boolean insert() {
        
        Connection con = (new Conector()).getConexao();
        
        String query = "INSERT INTO POO1_COLECAO(id, nome, lancamento, tipo) VALUES(SEQ_COLECAO.NEXTVAL,?,?,?)";
        
        try {
            PreparedStatement ps = con.prepareStatement(query); 
            ps.setString(1, this.getNome());
            ps.setInt(2, this.getLancamento());
            ps.setString(3, "single");
            
            ps.executeUpdate();
            
        } catch (SQLException e ) {
            System.out.println("Ocorreu um erro ao inserir o bootleg!");
            return false;
        }
        
        return true;
    }
    
    
}

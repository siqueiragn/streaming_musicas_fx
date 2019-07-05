/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package streaming.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import streaming.database.Conector;

/**
 *
 * @author Gabriel
 */
public class Album extends Colecao{

    public Album(int id, String nome, List<Interprete> interpretes, List<Interprete> compositores, int lancamento) {
        super(id, nome, interpretes, compositores, lancamento);
    }

    public Album() {
    }
    
       public boolean insert() {
        
        Connection con = (new Conector()).getConexao();
        
        String query = "INSERT INTO POO1_COLECAO(id, nome, lancamento, tipo) VALUES(SEQ_COLECAO.NEXTVAL,?,?,?)";
        
        try {
            String generatedColumns[] = {"id"};
            PreparedStatement ps = con.prepareStatement(query, generatedColumns); 
            ps.setString(1, this.getNome());
            ps.setInt(2, this.getLancamento());
            ps.setString(3, "album");
 
            ps.executeUpdate();
            //ver qual codigo da tua venda
            ResultSet rs = ps.getGeneratedKeys();
            int chaveGerada = 0;//Inicia a variavel sem código

            while (rs.next()) {
                chaveGerada = rs.getInt(1);
            }
            this.setId(chaveGerada);
            
        } catch (SQLException e ) {
            e.printStackTrace();
            System.out.println("Ocorreu um erro ao inserir o album!");
            return false;
        }
        
        return true;
    }
    
    
}

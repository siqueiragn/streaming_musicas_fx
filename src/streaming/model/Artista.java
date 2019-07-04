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
import java.util.ArrayList;
import java.util.List;
import streaming.database.Conector;

/**
 *
 * @author Gabriel
 */
public class Artista extends Interprete {

    public Artista(int i, String nome) {
        super(i, nome);
    }

    public Artista() { }
    
    public boolean insert() {
        
        Connection con = (new Conector()).getConexao();
        
        String query = "INSERT INTO POO1_INTERPRETE(id, nome) VALUES(SEQ_INTERPRETE.NEXTVAL,?)";
        
        try {
            String generatedColumns[] = {"id"};
            PreparedStatement ps = con.prepareStatement(query, generatedColumns);
            ps.setString(1, this.getNome());
            
            ps.executeUpdate();
            
            ResultSet rs = ps.getGeneratedKeys();
            int chaveGerada = 0;//Inicia a variavel sem código

            while (rs.next()) {
                chaveGerada = rs.getInt(1);
            }
            
            this.setId(chaveGerada);
            
        } catch (SQLException e ) {
            e.printStackTrace();
            System.out.println("Ocorreu um erro ao inserir o interprete!");
            return false;
        }
        
        return true;
    }
    public static List<Artista> getArtistas(String pesquisa) {

        Connection con = (new Conector()).getConexao();
      
        ArrayList<Artista> artistas = new ArrayList<>();
        
        String query = "SELECT * FROM POO1_INTERPRETE ";
        
        try {
            
            if ( !pesquisa.isEmpty() ) {
                query += "WHERE UPPER(NOME) LIKE '%" + pesquisa.toUpperCase() + "%'";
            }
            PreparedStatement ps = con.prepareStatement(query);        
            ResultSet rs = ps.executeQuery();
        
            while (rs.next()) {
                
                Artista a = new Artista();
                a.setId(rs.getInt("id"));
                a.setNome(rs.getString("nome"));
                
                artistas.add(a);
            }

        } catch ( SQLException e ) {
            System.out.println("Ocorreu um problema ao recuperar os dados!");
            e.printStackTrace();
        }
        
        return artistas;

    }
    
}

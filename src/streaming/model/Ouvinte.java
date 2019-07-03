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
import streaming.database.Conector;

/**
 *
 * @author Gabriel
 */
public class Ouvinte {
    
    private int id;
    private String nome;

    public Ouvinte(int id, String nome) {
        this.id = id;
        this.nome = nome;
    }

    public Ouvinte() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
    
    public boolean insert() {
        
        Connection con = (new Conector()).getConexao();
        
        String query = "INSERT INTO POO1_OUVINTE(id, nome) VALUES(SEQ_OUVINTE.NEXTVAL,?)";
        
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
            System.out.println("Ocorreu um erro ao inserir o ouvinte!");
            return false;
        }
        
        return true;
    }
}

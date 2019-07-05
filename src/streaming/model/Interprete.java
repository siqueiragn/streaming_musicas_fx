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
public class Interprete {
    
    private int id;
    private String nome;

    public Interprete(int id, String nome) {
        this.id = id;
        this.nome = nome;
    }

    public Interprete() {    }

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
    
    public String toString() {
        return this.nome;
    }
    
    
    public static List<Interprete> getAll() {
        
        Connection con = (new Conector()).getConexao();
      
        ArrayList<Interprete> interpretes = new ArrayList<>();
        
        String query = "SELECT * FROM POO1_INTERPRETE ORDER BY NOME";
        try {
            PreparedStatement ps = con.prepareStatement(query);
            ResultSet rs = ps.executeQuery();
        
            while (rs.next()) {
                interpretes.add(new Interprete(rs.getInt("id"), rs.getString("nome")));
            }

        } catch ( SQLException e ) {
            System.out.println("Ocorreu um problema ao recuperar os dados!");
            e.printStackTrace();
        }
        
        return interpretes;
    }

    public boolean insertAsInterpreteIntoColecao(Colecao c) {
        
        Connection con = (new Conector()).getConexao();
        
        String query = "INSERT INTO poo1_interpretes_colecoes(id, interprete, colecao) VALUES(SEQ_INT_COL.NEXTVAL,?,?)";
        
        try {
           
            PreparedStatement ps = con.prepareStatement(query); 
            ps.setInt(1, this.getId());
            ps.setInt(2, c.getId());
           
            ps.executeUpdate();
            
        } catch (SQLException e ) {
            e.printStackTrace();
            System.out.println("Ocorreu um erro ao inserir!");
            return false;
        }
        
        return true;
    }
    
    public boolean insertAsCompositorIntoColecao(Colecao c) {
        
        Connection con = (new Conector()).getConexao();
        
        String query = "INSERT INTO poo1_compositores_colecoes(id, interprete, colecao) VALUES(SEQ_COMP_COL.NEXTVAL,?,?)";
        
        try {
            PreparedStatement ps = con.prepareStatement(query); 
            ps.setInt(1, this.getId());
            ps.setInt(2, c.getId());
            System.out.println("ID INTERPRETE->" + this.getId() + " Coleção ID - " + c.getId());
            ps.executeUpdate();
 
        } catch (SQLException e ) {
            e.printStackTrace();
            System.out.println("Ocorreu um erro ao inserir!");
            return false;
        }
        
        return true;
    }
    
}
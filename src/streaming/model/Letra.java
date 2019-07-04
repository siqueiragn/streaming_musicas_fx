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
public class Letra {
    
    private int id;
    private String texto;

    public Letra(int id, String texto) {
        this.id = id;
        this.texto = texto;
    }

    public Letra() {}

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTexto() {
        return texto;
    }

    public void setTexto(String texto) {
        this.texto = texto;
    }

    public boolean insert() {
        
        Connection con = (new Conector()).getConexao();
        
        String query = "INSERT INTO POO1_LETRA(id, texto) VALUES(LETRA_SEQ.NEXTVAL, ?)";
       
        try {
            
            String generatedColumns[] = {"id"};
            PreparedStatement ps = con.prepareStatement(query, generatedColumns); 
            con.createStatement();
            ps.setString(1, this.texto);
            
            ps.executeUpdate();
            
            ResultSet rs = ps.getGeneratedKeys();
            int chaveGerada = 0;//Inicia a variavel sem código

            while (rs.next()) {
                chaveGerada = rs.getInt(1);
            }
            this.setId(chaveGerada);
            
        } catch (SQLException e ) {
            e.printStackTrace();
            System.out.println("Ocorreu um problema ao inserir a letra!");
            return false;
        }
        
        return true;
    }

    public boolean update() {
        
        Connection con = (new Conector()).getConexao();
    
        String sql =  "UPDATE POO1_LETRA SET TEXTO = ? "   
                     + "WHERE ( id = ?)";
        try{
            PreparedStatement ps = con.prepareStatement(sql);
            
            ps.setInt(2, this.id); //Observe o indice do ID
            ps.setString(1, this.texto);
            
            ps.executeUpdate();           
            
            
        }catch(SQLException e){
            
            System.out.println("Ocorreu um problema ao realizar a alteração nos dados!");
            e.printStackTrace();
            return false;
        }
    
        return true;
    }
    
}

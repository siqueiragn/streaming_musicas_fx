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
public class Playlist {
    private int id;
    private List<Musica> musicas;
    private Ouvinte autor;
    private String nome;

    public Playlist(int id, List<Musica> musicas, Ouvinte autor, String nome) {
        this.id = id;
        this.musicas = musicas;
        this.autor = autor;
        this.nome = nome;
    }

    public Playlist() {}

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    
    public List<Musica> getMusicas() {
        return musicas;
    }
    
    public void setMusicas(List<Musica> musicas) {
        this.musicas = musicas;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Ouvinte getAutor() {
        return autor;
    }

    public void setAutor(Ouvinte autor) {
        this.autor = autor;
    }

    public boolean insert() {
        
        Connection con = (new Conector()).getConexao();
        
        String query = "INSERT INTO POO1_PLAYLIST(id, nome, autor) VALUES(SEQ_OUVINTE.NEXTVAL,?,?)";
        
        try {
            String generatedColumns[] = {"id"};
            PreparedStatement ps = con.prepareStatement(query, generatedColumns);
            ps.setString(1, this.getNome());
            ps.setInt(2, this.getAutor().getId());
            
            ps.executeUpdate();
            
            ResultSet rs = ps.getGeneratedKeys();
            int chaveGerada = 0;//Inicia a variavel sem código

            while (rs.next()) {
                chaveGerada = rs.getInt(1);
            }
            
            this.setId(chaveGerada);
            
            for (Musica m : musicas) {
                m.insertIntoPlaylist(this.getId());
            }
            
        } catch (SQLException e ) {
            System.out.println("Ocorreu um erro ao inserir o ouvinte!");
            return false;
        }
        
        return true;
    }
    
    
}

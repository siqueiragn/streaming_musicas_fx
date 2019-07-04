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
import java.sql.Statement;
import java.util.ArrayList;
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
    
    public String getNomeAutor() {
        return this.autor.getNome();
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
    
    public static List<Playlist> getAll() {
        
        Connection con = (new Conector()).getConexao();
      
        ArrayList<Playlist> playlists = new ArrayList<>();
        
        String query ="SELECT PL.ID, PL.NOME AS NOME_PLAYLIST, O.ID AS ID_OUVINTE, O.NOME AS NOME_OUVINTE FROM POO1_PLAYLIST PL INNER JOIN POO1_OUVINTE O ON PL.AUTOR = O.ID";
        
        try {
        Statement st = con.createStatement();
        ResultSet rs = st.executeQuery(query);
        
        while (rs.next()) {
            
            Ouvinte o = new Ouvinte();
            o.setId(rs.getInt("id_ouvinte"));
            o.setNome(rs.getString("nome_ouvinte"));
            
            Playlist p = new Playlist(); 
            p.setId(rs.getInt("id"));
            p.setNome(rs.getString("nome_playlist"));
            p.setAutor(o);
            
            playlists.add(p);
        }
        
        } catch ( SQLException e ) {
            System.out.println("Ocorreu um problema ao recuperar os dados!");
            e.printStackTrace();
        }
        
        return playlists;
    }
    
    public List<Musica> getAllMusicas() {

        Connection con = (new Conector()).getConexao();
      
        ArrayList<Musica> musicas = new ArrayList<>();
        
        String query = "SELECT MUS.ID AS ID_MUSICA, " +
              "                MUS.NOME AS NOME_MUSICA, " +
              "                MUS.DURACAO AS DURACAO_MUSICA, " +
              "                COL.ID AS ID_COLECAO, " +
              "                COL.NOME AS NOME_COLECAO, " +
              "                COL.LANCAMENTO AS LANCAMENTO_COLECAO, " +
              "                COL.TIPO AS TIPO_COLECAO, " +
              "                LET.ID AS ID_LETRA, " +
              "                LET.TEXTO AS TEXTO_LETRA " +
              "           FROM POO1_MUSICA MUS " +
              "     INNER JOIN POO1_LETRA LET ON LET.ID = MUS.LETRA " +
              "     INNER JOIN POO1_COLECAO COL ON MUS.COLECAO = COL.ID " +
              "     INNER JOIN POO1_MUSICA_PLAYLIST MP ON MP.ID_MUSICA = MUS.ID " +
              "          WHERE MP.ID_PLAYLIST = ?";
        try {
            PreparedStatement ps = con.prepareStatement(query); 
            ps.setInt(1, this.getId());       
        
            ResultSet rs = ps.executeQuery();
        
            while (rs.next()) {
                
                Musica m = new Musica();
                m.setId(rs.getInt("id_musica"));
                m.setNome(rs.getString("nome_musica"));
                m.setDuracao(rs.getDouble("duracao_musica"));

                Colecao c;
                switch(rs.getString("tipo_colecao")) {
                    case "Album": 
                        c = new Album();
                    break;
                    case "Bootleg":
                        c = new Bootleg();
                    break;
                    case "Compilacao":
                        c = new Compilacao();
                    break;
                    case "Single": 
                        c = new Single();
                    break;
                    default:
                        c = new Colecao();
                    break;
                }

                c.setId(rs.getInt("id_colecao"));
                c.setNome(rs.getString("nome_colecao"));
                c.setLancamento(rs.getInt("lancamento_colecao"));

                m.setColecao(c);

                Letra l = new Letra();
                l.setId(rs.getInt("id_letra"));
                l.setTexto(rs.getString("texto_letra"));

                m.setLetra(l);

                musicas.add(m);
                
            }

        } catch ( SQLException e ) {
            System.out.println("Ocorreu um problema ao recuperar os dados!");
            e.printStackTrace();
        }
        
        return musicas;

    }
    
    public boolean update() {
        
        Connection con = (new Conector()).getConexao();
    
        String sql =  "UPDATE POO1_PLAYLIST SET nome = ? "   
                     + "WHERE ( id = ?)";
        try{
            PreparedStatement ps = con.prepareStatement(sql);
            
            ps.setInt(2, this.id); //Observe o indice do ID
            
            ps.setString(1, this.nome);
            
            this.removeAllMusicas();
            
            for (Musica m : musicas) {
                m.insertIntoPlaylist(this.getId());
            }
            
            ps.executeUpdate();           
            
            
        }catch(SQLException e){
            
            System.out.println("Ocorreu um problema ao realizar a alteração nos dados!");
            e.printStackTrace();
            return false;
        }
    
        return true;
    }
    
    public boolean removeAllMusicas() {
        
        Connection con = (new Conector()).getConexao();
    
        String sql =  "DELETE FROM POO1_MUSICA_PLAYLIST WHERE ID_PLAYLIST = ? ";
        try{
            PreparedStatement ps = con.prepareStatement(sql);
            
            ps.setInt(1, this.id); //Observe o indice do ID            
            ps.executeUpdate();           
            
            
        }catch(SQLException e){
            
            System.out.println("Ocorreu um problema ao remover as músicas!");
            e.printStackTrace();
            return false;
        }
    
        return true;
    }
    
}

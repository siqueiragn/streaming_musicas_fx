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
public class Musica {
    
    private int id;
    private String nome;
    private double duracao;
    private Colecao colecao;
    private Letra letra;

    public Musica(int id, String nome, double duracao, Colecao colecao, Letra letra) {
        this.id = id;
        this.nome = nome;
        this.duracao = duracao;
        this.colecao = colecao;
        this.letra = letra;
    }

    public Musica() {}

    public int getId() {
        return id;
    }

    public double getDuracao() {
        return duracao;
    }

    public void setDuracao(double duracao) {
        this.duracao = duracao;
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

    public Colecao getColecao() {
        return colecao;
    }

    public void setColecao(Colecao colecao) {
        this.colecao = colecao;
    }

    public Letra getLetra() {
        return letra;
    }

    public void setLetra(Letra letra) {
        this.letra = letra;
    }

    public boolean insert() {
        
        Connection con = (new Conector()).getConexao();
        
        String query = "INSERT INTO POO1_MUSICA(id, nome, duracao, letra, colecao) VALUES(MUSICA_SEQ.NEXTVAL, ?, ?, ?, ?)";

        try {
            String generatedColumns[] = {"id"};
            PreparedStatement ps = con.prepareStatement(query, generatedColumns); 
            con.createStatement();            
            ps.setString(1, this.nome);
            ps.setDouble(2, this.duracao);
            ps.setInt(3, this.letra.getId());
            ps.setInt(4, this.colecao.getId());
            
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
            System.out.println("Ocorreu um erro ao inserir a música!");
            return false;
        }
        
        return true;
    }
    
    public static List<Musica> getAll() {
        
        Connection con = (new Conector()).getConexao();
      
        ArrayList<Musica> musicas = new ArrayList<>();
        
        String query = 
                "    SELECT MUS.ID AS ID_MUSICA," +
                "           MUS.NOME AS NOME_MUSICA," +
                "           MUS.DURACAO AS DURACAO_MUSICA," +
                "           COL.ID AS ID_COLECAO," +
                "           COL.NOME AS NOME_COLECAO," +
                "           COL.LANCAMENTO AS LANCAMENTO_COLECAO," +
                "           COL.TIPO AS TIPO_COLECAO," +
                "           LET.ID AS ID_LETRA," +
                "           LET.TEXTO AS TEXTO_LETRA " +
                "      FROM POO1_MUSICA MUS " +
                "INNER JOIN POO1_LETRA LET ON LET.ID = MUS.LETRA " +
                "INNER JOIN POO1_COLECAO COL ON MUS.COLECAO = COL.ID";
        
        try {
        Statement st = con.createStatement();
        ResultSet rs = st.executeQuery(query);
        
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
    
    public String toString() {
        return this.nome;
    }

    public boolean insertIntoPlaylist(int playlist) {
        Connection con = (new Conector()).getConexao();
        
        String query = "INSERT INTO POO1_MUSICA_PLAYLIST(id_musica, id_playlist) VALUES(?, ?)";

        try {
            PreparedStatement ps = con.prepareStatement(query); 
            con.createStatement();            
            ps.setInt(1, this.id);
            ps.setInt(2, playlist);
         
            ps.executeUpdate();
         
        } catch (SQLException e ) {
            e.printStackTrace();
            System.out.println("Ocorreu um erro ao inserir a música!");
            return false;
        }
        
        return true;
    }
    
}

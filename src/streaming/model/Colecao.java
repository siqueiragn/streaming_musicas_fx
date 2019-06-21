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
import javafx.collections.ObservableList;
import streaming.database.Conector;

/**
 *
 * @author Gabriel
 */
public class Colecao {
   
    private int id;
    private String nome;
    private List<Interprete> interpretes;
    private List<Interprete> compositores;
    private int lancamento;

    public Colecao(int id, String nome, List<Interprete> interpretes, List<Interprete> compositores, int lancamento) {
        this.id = id;
        this.nome = nome;
        this.interpretes = interpretes;
        this.compositores = compositores;
        this.lancamento = lancamento;
    }

    public Colecao() {}

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

    public List<Interprete> getInterpretes() {
        return interpretes;
    }

    public void setInterpretes(List<Interprete> interpretes) {
        this.interpretes = interpretes;
    }

    public List<Interprete> getCompositores() {
        return compositores;
    }

    public void setCompositores(List<Interprete> compositores) {
        this.compositores = compositores;
    }

    public int getLancamento() {
        return lancamento;
    }

    public void setLancamento(int lancamento) {
        this.lancamento = lancamento;
    }
    
    public void addCompositor(Interprete i) {
        this.compositores.add(i);
    }
    
    public void addInterprete(Interprete i) {
        this.interpretes.add(i);
    }
    
    public String toString() {
        return this.nome;
    }
    
    public static List<Colecao> getAll() {
        
        Connection con = (new Conector()).getConexao();
      
        ArrayList<Colecao> colecoes = new ArrayList<>();
        
        String query = "SELECT * FROM POO1_COLECAO";
        
        try {
        Statement st = con.createStatement();
        ResultSet rs = st.executeQuery(query);
        
        while (rs.next()) {
            Colecao c = new Colecao();
            c.setId(rs.getInt("id"));
            c.setNome(rs.getString("nome"));
            //c.setCompositores(c.getCompositoresFromDB());
            //c.setInterpretes(c.getInterpretesFromDB());
            c.setLancamento(rs.getInt("lancamento"));
            
            
            c.setId(rs.getInt("id"));
            colecoes.add(c);
        }
        
        } catch ( SQLException e ) {
            System.out.println("Ocorreu um problema ao recuperar os dados!");
            e.printStackTrace();
        }
        
        return colecoes;
    }
    
    public List<Interprete> getCompositoresFromDB() {
        
        Connection con = (new Conector()).getConexao();
      
        ArrayList<Interprete> compositores = new ArrayList<>();
        
        String query = "SELECT INTE.ID, INTE.NOME FROM POO1_INTERPRETE INTE INNER JOIN POO1_COMPOSITORES_COLECOES COMU ON INTE.ID = COMU.INTERPRETE WHERE COMU.COLECAO = ?";
        try {
            PreparedStatement ps = con.prepareStatement(query); 
            ps.setInt(1, this.getId());       
        
            ResultSet rs = ps.executeQuery();
        
            while (rs.next()) {
                compositores.add(new Interprete(rs.getInt("id"), rs.getString("nome")));
            }

        } catch ( SQLException e ) {
            System.out.println("Ocorreu um problema ao recuperar os dados!");
            e.printStackTrace();
        }
        
        return compositores;
    }

    public List<Interprete> getInterpretesFromDB() {

        Connection con = (new Conector()).getConexao();
      
        ArrayList<Interprete> interpretes = new ArrayList<>();
        
        String query = "SELECT INTE.ID, INTE.NOME FROM POO1_INTERPRETE INTE INNER JOIN POO1_INTERPRETES_COLECOES INCO ON INTE.ID = INCO.INTERPRETE WHERE INCO.COLECAO = ?";
        try {
            PreparedStatement ps = con.prepareStatement(query); 
            ps.setInt(1, this.getId());       
        
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

    public Colecao getById() {
        
        Connection con = (new Conector()).getConexao();
              
        String query = "SELECT * FROM POO1_COLECAO WHERE ID = ?";
        Colecao c = null;
        try {
            PreparedStatement ps = con.prepareStatement(query); 
            ps.setInt(1, this.getId());       
        
            ResultSet rs = ps.executeQuery();
        
            while (rs.next()) {
                
                switch(rs.getString("tipo")) {
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
                c.setId(rs.getInt("id"));
                c.setNome(rs.getString("nome"));
                c.setLancamento(rs.getInt("lancamento"));
            }

        } catch ( SQLException e ) {
            System.out.println("Ocorreu um problema ao recuperar os dados!");
            e.printStackTrace();
        }
        
        return c;
    }
    
    
    public List<Musica> getMusicasById() {
        
        Connection con = (new Conector()).getConexao();
              
        List<Musica> musicas = new ArrayList<>();
        String query = "SELECT PM.ID AS ID_MUSICA, " +
                       "       PM.NOME AS NOME_MUSICA, " +
                       "       PM.DURACAO AS DURACAO_MUSICA, " +
                       "       PM.LETRA AS LETRA_MUSICA, " +
                       "       PL.TEXTO AS TEXTO_LETRA " +
                       "     FROM POO1_MUSICA PM " +
                       "LEFT JOIN POO1_COLECAO PC ON PM.COLECAO = PC.ID " +
                       "LEFT JOIN POO1_LETRA PL ON PL.ID = PM.LETRA " +
                       "    WHERE PM.COLECAO = ? ";
        try {
            PreparedStatement ps = con.prepareStatement(query); 
            ps.setInt(1, this.getId());       
        
            ResultSet rs = ps.executeQuery();
        
            while (rs.next()) {
                Musica m = new Musica();
                m.setId(rs.getInt("id_musica"));
                m.setNome(rs.getString("nome_musica"));
                m.setDuracao(rs.getDouble("duracao_musica"));
                m.setLetra(new Letra(rs.getInt("letra_musica"), rs.getString("texto_letra") ));
                musicas.add(m);
            }

        } catch ( SQLException e ) {
            System.out.println("Ocorreu um problema ao recuperar os dados!");
            e.printStackTrace();
        }
        
        return musicas;
    }

    
}

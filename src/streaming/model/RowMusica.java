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
/** Model utilizada para obter resultados do banco de dados, cruzamento entre <b>Interpretes</b> e  <b>Musicas</b>. */

public class RowMusica {
    
    private Musica musica;
    private Colecao colecao;

    public RowMusica(Musica musica, Colecao colecao) {
        this.musica = musica;
        this.colecao = colecao;
    }

    private RowMusica() {}

    public Musica getMusica() {
        return musica;
    }

    public void setMusica(Musica musica) {
        this.musica = musica;
    }

    public Colecao getColecao() {
        return colecao;
    }

    public void setColecao(Colecao colecao) {
        this.colecao = colecao;
    }
 
    public String getColecaoNome() {
        return this.colecao.getNome();
    }
    
    public String getMusicaNome() {
        return this.musica.getNome();
    }
     
    public static List<RowMusica> getMusicas(String pesquisa) {

        Connection con = (new Conector()).getConexao();
      
        ArrayList<RowMusica> musicas = new ArrayList<>();
        
        String query = "SELECT M.ID, M.NOME AS MUSICA, M.DURACAO, C.NOME AS COLECAO, C.ID AS COLECAO_ID, C.LANCAMENTO, L.ID AS LETRA_ID, L.TEXTO AS LETRA_TEXTO FROM POO1_MUSICA M " +
"INNER JOIN POO1_COLECAO C ON C.ID = M.COLECAO " +
"INNER JOIN POO1_LETRA L ON L.ID = M.LETRA ";
        
        try {
            
            if ( !pesquisa.isEmpty() ) {
                query += "WHERE UPPER(M.NOME) LIKE '%" + pesquisa.toUpperCase() + "%'";
            }
            PreparedStatement ps = con.prepareStatement(query);        
            ResultSet rs = ps.executeQuery();
        
            while (rs.next()) {
                RowMusica r = new RowMusica();
                Letra letra = new Letra();
                letra.setId(rs.getInt("letra_id"));
                letra.setTexto(rs.getString("letra_texto"));
                
                Colecao colecao = new Colecao();
                colecao.setId(rs.getInt("colecao_id"));
                colecao.setNome(rs.getString("colecao"));
                colecao.setLancamento(rs.getInt("lancamento"));
                
                Musica musica = new Musica();
                musica.setId(rs.getInt("id"));
                musica.setNome(rs.getString("musica"));
                musica.setLetra(letra);
                musica.setColecao(colecao);
                
                r.setColecao(colecao);
                r.setMusica(musica);
                musicas.add(r);
            }

        } catch ( SQLException e ) {
            System.out.println("Ocorreu um problema ao recuperar os dados!");
            e.printStackTrace();
        }
        
        return musicas;

    }
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package streaming.model;

import java.util.List;

/**
 *
 * @author Gabriel
 */
public class Playlist {
    private List<Musica> musicas;
    private Ouvinte autor;

    public Playlist(List<Musica> musicas, Ouvinte autor) {
        this.musicas = musicas;
        this.autor = autor;
    }

    public List<Musica> getMusicas() {
        return musicas;
    }

    public void setMusicas(List<Musica> musicas) {
        this.musicas = musicas;
    }

    public Ouvinte getAutor() {
        return autor;
    }

    public void setAutor(Ouvinte autor) {
        this.autor = autor;
    }
    
    
}

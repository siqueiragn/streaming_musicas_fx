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
public class Banda extends Interprete {
    
    private List<Artista> artistas;

    public Banda(int id, String nome, List<Artista> artistas) {
        super(id, nome);
        this.artistas = artistas;
    }

    public Banda(int id, String nome) {
        super(id, nome);
    }

    public List<Artista> getArtistas() {
        return artistas;
    }

    public void setArtistas(List<Artista> artistas) {
        this.artistas = artistas;
    }

    public String toString() {
        return super.toString();
    }
    
    
    
    
}

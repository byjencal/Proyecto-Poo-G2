
package ec.edu.espol.proyectodomino;

import java.util.ArrayList;

public class Jugador {

    private String nombre;
    private ArrayList<Ficha> mano;

    public Jugador(String nombre, ArrayList<Ficha> mano) {
        this.nombre = nombre;
        this.mano = mano;
    }

    public Ficha getFicha(int indice) {
        Ficha fichaElegida;
        if (indice > mano.size() - 1 | indice < 0) {
            fichaElegida = mano.get(indice);
        } else {
            fichaElegida = null;
        }
        return fichaElegida;
    }
    public void removerFicha(Ficha f){
        mano.remove(f);
        
    }

    public String getNombre() {
        return nombre;
    }
    public void imprimirMano(){
        String mostrar = "";
        for(int i=0;i<mano.size();i++){
           if(i<mano.size()-1) {
               mostrar += mano.get(i)+"-";
           }
           else {
               mostrar += mano.get(i);
           }
        }
        System.out.println(mostrar);
    }
    
}

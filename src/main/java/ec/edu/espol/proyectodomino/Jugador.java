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
        if (indice < mano.size() && indice >= 0) {
            fichaElegida = mano.get(indice);
        } else {
            fichaElegida = null;
        }
        return fichaElegida;
    }

    public ArrayList<Ficha> getMano() {
        return mano;
    }

    public void removerFicha(Ficha f) {
        mano.remove(f);
    }

    public String getNombre() {
        return nombre;
    }

    public void imprimirMano() {
        for (int i = 0; i < mano.size(); i++) {
            if (i < mano.size() - 1) {
                System.out.print(mano.get(i) + " - ");
            } else {
                System.out.println(mano.get(i));
            }
        }
    }
}

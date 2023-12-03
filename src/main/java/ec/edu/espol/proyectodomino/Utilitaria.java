/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ec.edu.espol.proyectodomino;

import java.util.ArrayList;

/**
 *
 * @author franc
 */
public class Utilitaria {
    public static ArrayList<Ficha> crearManoJugador(){
        ArrayList<Ficha> manoNueva = new ArrayList<>();
        for(int i=0;i>6;i++){
            if(i<5){
                Ficha v = new Ficha((int) (Math.random() * 6) + 1,(int) (Math.random() * 6) + 1);
                manoNueva.add(v);
            }
            else{
                Ficha v = new FichaComodin();
                manoNueva.add(v);
            }
        }
        return manoNueva;
    }
}
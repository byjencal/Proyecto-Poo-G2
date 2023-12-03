
package ec.edu.espol.proyectodomino;

import java.util.ArrayList;
import java.util.Scanner;

public class Juego {

    private ArrayList<Ficha> lineaJuego;
    private ArrayList<Jugador> jugadores;

    public Juego() {
        lineaJuego = new ArrayList<>();
        jugadores = new ArrayList<>();
    }

    public void agregarJugador(String nombre) {
        Jugador v = new Jugador(nombre, Utilitaria.crearManoJugador());
        jugadores.add(v);
    }

    public int obtenerValorInicioLinea() {
        return lineaJuego.get(0).getLado1();
    }

    public int obtenerValorFinLinea() {
        return lineaJuego.get(lineaJuego.size() - 1).getLado2();
    }

    public void mostrarLinea() {
        System.out.println();
        for (int i = 0; i < lineaJuego.size(); i++) {
            if (i < lineaJuego.size() - 1) {
                if (lineaJuego.get(i) instanceof FichaComodin) {
                    FichaComodin j = (FichaComodin) lineaJuego.get(i);
                    System.out.print(j.toString() + "-");
                } else {
                    System.out.println(lineaJuego.get(i).toString() + "-");
                }

            } 
            else {
                if (lineaJuego.get(i) instanceof FichaComodin) {
                    FichaComodin j = (FichaComodin) lineaJuego.get(i);
                    System.out.print(j.toString() );
                } else {
                    System.out.println(lineaJuego.get(i).toString());
                }
            }
        }
    }
    public boolean agregarFichaLinea(Ficha f, Jugador j){
        boolean v = false;
        Scanner sc = new Scanner(System.in);
        if(f instanceof FichaComodin){
            if(lineaJuego.isEmpty()){
                lineaJuego.add(f);
                System.out.println("Ingresar valor Lado 1:");
                ((FichaComodin) f).setLado1(sc.nextInt());
                System.out.println("Ingresar valor Lado 2:");
                ((FichaComodin) f).setLado2(sc.nextInt());
            }
            else{
                System.out.println("Ingresar posicion :");
                String pos = sc.nextLine();
                if(pos.equals("Inicio")){
                    System.out.println("Ingresar valor Lado 1:");
                    ((FichaComodin) f).setLado1(sc.nextInt());
                    lineaJuego.add(f);
                }
                else if(pos.equals("Fin")){
                    System.out.println("Ingresar valor Lado 2:");
                    ((FichaComodin) f).setLado2(sc.nextInt());
                    lineaJuego.add(f);
                }
            }
        }
        else{
           if(lineaJuego.isEmpty()){
               lineaJuego.add(f);
           } 
        }
            
        
        return v;
    }
}

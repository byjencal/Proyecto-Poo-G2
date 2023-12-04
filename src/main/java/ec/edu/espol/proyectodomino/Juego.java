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

    public ArrayList<Ficha> getLineaJuego() {
        return lineaJuego;
    }

    public ArrayList<Jugador> getJugadores() {
        return jugadores;
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
        System.out.print("Línea de Juego -> ");
        if (!lineaJuego.isEmpty()) {
                       for (int i = 0; i < lineaJuego.size(); i++) {
                System.out.print(lineaJuego.get(i));
                if (i < lineaJuego.size() - 1) {
                    System.out.print(" - ");
                } else {
                    System.out.print("");
                }
            }
        }
        System.out.println("");
    }

    public boolean agregarFichaLinea(Ficha f, Jugador j) {
        Scanner sc = new Scanner(System.in);
        if (f instanceof FichaComodin) {
            FichaComodin other = (FichaComodin) f;
            if (lineaJuego.isEmpty()) {
                System.out.print("Ingresar valor Lado 1: ");
                other.setLado1(sc.nextInt());
                System.out.print("Ingresar valor Lado 2: ");
                other.setLado2(sc.nextInt());
                lineaJuego.add(f);
                j.removerFicha(f);
            } else {
                System.out.print("Ingresar posicion(Inicio/Fin): ");
                String pos = sc.next();
                if (pos.equals("Inicio")) {
                    System.out.print("Ingresar valor Lado 1 (1-6): ");
                    other.setLado1(sc.nextInt());
                    lineaJuego.add(0, f);
                    j.removerFicha(f);
                } else if (pos.equals("Fin")) {
                    System.out.print("Ingresar valor Lado 2 (1-6): ");
                    other.setLado2(sc.nextInt());
                    lineaJuego.add(f);
                    j.removerFicha(f);
                } else {
                    return false;
                }
            }
        } else {
            if (lineaJuego.isEmpty()) {
                lineaJuego.add(f);
                j.removerFicha(f);
            } else {
                int num_inicio = this.obtenerValorInicioLinea();
                int num_fin = this.obtenerValorFinLinea();
                if (f.getLado2() == num_inicio && f.getLado1() == num_fin) {
                    System.out.println("Tiene dos opciones en donde colocar la ficha");
                    System.out.print("Ingresar posicion(Inicio/Fin): ");
                    String pos = sc.next();
                    if (pos.equals("Inicio")) {
                        lineaJuego.add(0, f);
                        j.removerFicha(f);
                    } else if (pos.equals("Fin")) {
                        lineaJuego.add(f);
                        j.removerFicha(f);
                    } else {
                        return false;
                    }
                } else if (f.getLado2() == num_inicio) {
                    lineaJuego.add(0, f);
                    j.removerFicha(f);
                } else if (f.getLado1() == num_fin) {
                    lineaJuego.add(f);
                    j.removerFicha(f);
                } else {
                    return false;
                }
            }
        }
        return true;
    }
}

package ec.edu.espol.proyectodomino;

import java.util.Scanner;

public class ProyectoDomino {

    public static void main(String[] args) {
        int opc;
        Juego juego;
        int cont;
        Scanner sc = new Scanner(System.in);
        do {
            opc = ProyectoDomino.menu();
            System.out.println("");
            switch (opc) {
                case 1:
                    juego = new Juego();
                    System.out.print("Ingrese nombre de jugador 1: ");
                    juego.agregarJugador(sc.next());
                    System.out.print("Ingrese nombre de jugador 2: ");
                    juego.agregarJugador(sc.next());
                    System.out.println("");
                    cont = 0;
                    while (true) {
                        Jugador j = juego.getJugadores().get(cont);
                        System.out.print("Jugador ");
                        System.out.print(j.getNombre());
                        System.out.print(" Mano-> ");
                        j.imprimirMano();
                        juego.mostrarLinea();
                        if (jugadaPosible(j, juego)) {
                            ProyectoDomino.validacionTotal(sc, j, juego);
                        } else {
                            System.out.println("\n-----------------------------------");
                            System.out.println("Juego Terminado");
                            System.out.print("Mano " + juego.getJugadores().get(0).getNombre() + ": ");
                            juego.getJugadores().get(0).imprimirMano();
                            System.out.print("Mano " + juego.getJugadores().get(1).getNombre() + ": ");
                            juego.getJugadores().get(1).imprimirMano();
                            System.out.println("Linea de juego -> " + juego.getLineaJuego());
                            if (j.getMano().isEmpty()) {
                                System.out.print("\033[32mHa ganado el jugador: ");
                                System.out.println(j.getNombre());
                            } else {
                                System.out.println("Sin jugadas posibles");
                                System.out.print("Ha perdido el jugador: ");
                                System.out.println(j.getNombre());
                            }
                            break;
                        }
                        cont = Math.abs(cont - 1);
                    }
                    break;
                case 2:
                    juego = new Juego();
                    System.out.print("Ingrese nombre de jugador: ");
                    juego.agregarJugador(sc.next());
                    juego.agregarJugador("Computadora");
                    System.out.println("");
                    cont = 0;
                    while (true) {
                        Jugador j = juego.getJugadores().get(cont);
                        if (jugadaPosible(j, juego)) {
                            if (cont == 0) {
                                System.out.print("Jugador ");
                                System.out.print(j.getNombre());
                                System.out.print(" Mano-> ");
                                j.imprimirMano();
                                juego.mostrarLinea();
                                ProyectoDomino.validacionTotal(sc, j, juego);
                            } else {
                                System.out.println("Movimiento Realizado por la computadora\n");
                                ProyectoDomino.computadora(j, juego);
                            }
                        } else {
                            System.out.println("\n-----------------------------------");
                            System.out.println("Juego Terminado");
                            System.out.print("Mano " + juego.getJugadores().get(0).getNombre() + ": ");
                            juego.getJugadores().get(0).imprimirMano();
                            System.out.print("Mano " + juego.getJugadores().get(1).getNombre() + ": ");
                            juego.getJugadores().get(1).imprimirMano();
                            System.out.println("Linea de juego -> " + juego.getLineaJuego());
                            if (j.getMano().isEmpty()) {
                                System.out.print("\033[32mHa ganado: ");
                                System.out.println(j.getNombre());
                            } else {
                                System.out.println("Sin jugadas posibles");
                                System.out.print("Ha perdido: ");
                                System.out.println(j.getNombre());
                            }
                            break;
                        }
                        cont = Math.abs(cont - 1);
                    }
                    break;
                case 3:
                    break;
                default:
                    System.out.println("Opción no válida");
            }
        } while (opc != 3);
    }

    public static int menu() {
        Scanner sc = new Scanner(System.in);
        System.out.println("--------------------------");
        System.out.println("Elija una opción");
        System.out.println("1. Jugador1 vs Jugador2");
        System.out.println("2. Jugador vs computadora");
        System.out.println("3. Salir");
        System.out.println("--------------------------");
        System.out.print(">>> ");
        return sc.nextInt();
    }

    public static Ficha validacionNull(Scanner sc, Jugador j) {
        System.out.print("Índice de ficha para jugar (0 es el primero): ");
        Ficha f = j.getFicha(sc.nextInt());
        while (f == null) { // comprueba que el indice de la ficha existe
            System.out.println("Indice fuera de rango, Intentelo nuevamente");
            System.out.print("\n"+"Índice de ficha para jugar (0 es el primero): ");
            f = j.getFicha(sc.nextInt());
        }
        return f;
    }

    public static void validacionTotal(Scanner sc, Jugador j, Juego juego) {
        Ficha f = ProyectoDomino.validacionNull(sc, j);
        while (!juego.agregarFichaLinea(f, j)) {
            System.out.println("La Ficha " + f.toString() + " No puedo se puede, inténtalo de nuevo");
            f = ProyectoDomino.validacionNull(sc, j);
        }
        System.out.println("Movimiento Válido.");
        System.out.print("Nueva ");
        juego.mostrarLinea();
        System.out.println("");
    }

    public static boolean jugadaPosible(Jugador j, Juego juego) {
        if (!juego.getLineaJuego().isEmpty()) {
            int num_inicio = juego.obtenerValorInicioLinea();
            int num_fin = juego.obtenerValorFinLinea();
            for (int i = 0; i < j.getMano().size(); i++) {
                Ficha f = j.getFicha(i);
                if (f.getLado2() == num_inicio || f.getLado1() == num_fin || f instanceof FichaComodin) {
                    return true;
                }
            }
        } else {
            return true;
        }
        return false;
    }

    public static void computadora(Jugador j, Juego juego) {
        int num_inicio = juego.obtenerValorInicioLinea();
        int num_fin = juego.obtenerValorFinLinea();
        for (int i = 0; i < j.getMano().size(); i++) {
            Ficha f = j.getFicha(i);
            if (f instanceof FichaComodin) {
                ((FichaComodin) f).setLado2((int) (Math.random() * 6 + 1));
                juego.getLineaJuego().add(f);
                j.removerFicha(f);
                break;
            } else if (f.getLado2() == num_inicio) {
                juego.getLineaJuego().add(0, f);
                j.removerFicha(f);
                break;
            } else if (f.getLado1() == num_fin) {
                juego.getLineaJuego().add(f);
                j.removerFicha(f);
                break;
            }
        }
    }
}

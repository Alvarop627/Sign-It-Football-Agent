package com.alvarop627.signitfootballagent.classes;

import com.alvarop627.signitfootballagent.exceptions.MuyPocosJugadoresException;

import java.util.ArrayList;

public class Equipo {
    private String nombre;
    private int puntos, victorias, empates, derrotas;
    private ArrayList<Jugador> plantilla;
    private static PosicionJugador[] posiciones442 = { PosicionJugador.POR, PosicionJugador.LD, PosicionJugador.LI,
            PosicionJugador.DFCI, PosicionJugador.DFCD, PosicionJugador.MD, PosicionJugador.MI, PosicionJugador.MCD,
            PosicionJugador.MCI,PosicionJugador.DI, PosicionJugador.DD };

    public Equipo(String nombre, ArrayList<Jugador> plantilla) {
        super();
        this.nombre = nombre;
        this.setPuntos(0);
        this.plantilla = plantilla;
    }

    public Equipo(String nombre, int njugadores) throws MuyPocosJugadoresException {
        if (njugadores < 11) {
            throw new MuyPocosJugadoresException("No puedes tener menos de 11 jugadores");
        }
        this.nombre = nombre;
        this.setPuntos(0);
        this.plantilla = new ArrayList<Jugador>();
        for (int i = 0; i < njugadores; i++) {
            //System.out.println(nombre+" : "+posiciones442[i % 11]);
            plantilla.add(new Jugador(posiciones442[i % 11], (i < 12)));

        }
    }

    public Equipo(String nombre) {
        this.nombre = nombre;
        this.setPuntos(0);
        this.plantilla = new ArrayList<Jugador>();
        for (int i = 0; i < 12; i++) {
            plantilla.add(new Jugador(posiciones442[i % 12], (i < 12)));
        }
    }

    public ArrayList<Jugador> jugadoresEnPosicion(PosicionJugador pj){
        ArrayList<Jugador> todos=new ArrayList<Jugador>();
        for (int i = 0; i < this.plantilla.size(); i++) {
            if(this.plantilla.get(i).getPosicion().equals(pj)) {
                todos.add(this.plantilla.get(i));
            }
        }
        return todos;
    }

    protected void setPuntos(int puntos) {
        this.puntos = puntos;
    }
}

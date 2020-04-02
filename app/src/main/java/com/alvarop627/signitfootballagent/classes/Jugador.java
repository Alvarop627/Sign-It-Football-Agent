package com.alvarop627.signitfootballagent.classes;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class Jugador {
    private String nombre, apellidos;
    private int fuerza, velocidad, resistencia, tecnica;
    private boolean titular;
    private PosicionJugador posicion;

    private static final String[] grupoNom = { "Juan", "Paco", "Pepe", "Mateo", "Miguel", "Fabian", "Antonio", "Lolo",
            "Andres", "Ramon", "Cristiano", "Leo", "Karim", "Pedro", "Salvio", "Eden", "Lucas", "Bartolo", "Eustaquio",
            "Agustin", "Aleix", "Michel", "Joaquin", "Isco", "Luka", "Paul", "Raul", "Fernando", "Ter", "Luis",
            "Malcolm", "Javier", "Arturo", "Daniel", "Eusebio", "Alfredo", "Jesús", "Keylor", "Sergio", "Rafael",
            "Alvaro", "Jaime", "Xavier", "Timo", "Thibaut", "Vinicius", "David", "Manolo", "Teo", "Mario" };

    private static final String[] grupoAp = { "De Luiz", "Ramos", "Suarez", "Messi", "Ronaldo", "Kroos", "Courtois",
            "Navas", "Gonzalez", "Moura", "Varane", "De Gea", "Torres", "Hernandez", "Coutinho", "Dembele", "Hazard",
            "Sanchez", "Ontiveros", "Pogba", "Di Stefano", "Iniesta", "Salgado", "Gaitan", "Tellez", "Vidal", "Junior",
            "Morata", "Modric", "Aureola", "Benzema", "Alarcon", "Pacheco", "Caballero", "Perez", "Martin", "Fernandez",
            "Bravo", "Vargas", "Stegen", "Jimenez", "Valderrama", "Lozano", "Martinez", "Rabiot", "Lopez", "Verrati",
            "Casillas", "Reina", "Puskas" };

    public Jugador(String nombre, String apellidos, int precio, int sueldo, int fuerza, int velocidad, int resistencia,
                   int tecnica) {
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.fuerza = fuerza;
        this.velocidad = velocidad;
        this.resistencia = resistencia;
        this.tecnica = tecnica;
    }

    public Jugador(PosicionJugador pos, boolean titular) {
        Random r = new Random();
        this.setNombre(grupoNom[r.nextInt(50)]);
        this.setApellidos(grupoAp[r.nextInt(50)] + " " + grupoAp[r.nextInt(50)]);
        this.setFuerza(r.nextInt(100));
        this.setVelocidad(r.nextInt(100));
        this.setResistencia(r.nextInt(100));
        this.setTecnica(r.nextInt(100));
        this.setTitular(titular);
        this.posicion = pos;
    }

    public Jugador() {
        Random r = new Random();
        this.setNombre(grupoNom[r.nextInt(50)]);
        this.setApellidos(grupoAp[r.nextInt(50)] + " " + grupoAp[r.nextInt(50)]);
        this.setFuerza(r.nextInt(100));
        this.setVelocidad(r.nextInt(100));
        this.setResistencia(r.nextInt(100));
        this.setTecnica(r.nextInt(100));
        this.setPosicionJugadorAleatoriamente();
    }

    /**
     * @return el nombre
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * @param nombre
     *            modifica el nombre
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     * @return los apellidos
     */
    public String getApellidos() {
        return apellidos;
    }

    /**
     * @param apellidos
     *            modifica los apellidos
     */
    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    /**
     * @return el precio
     */
    public int getPrecio() {
        int sumCar = this.getFuerza() + this.getVelocidad() + this.getResistencia() + this.getTecnica();
        int precio;
        if (sumCar < 50) {
            precio = sumCar * 2500;
        } else if (50 <= sumCar && sumCar < 200) {
            precio = sumCar * 10000;
        } else if (200 <= sumCar && sumCar < 300) {
            precio = sumCar * 33333;
        } else {
            precio = sumCar * 250000;

        }
        return precio;
    }

    /**
     * @return el sueldo
     */
    public int getSueldo() {
        return this.getPrecio() / 100;
    }

    /**
     * @return la fuerza
     */
    public int getFuerza() {
        return fuerza;
    }

    /**
     * @param fuerza
     *            modifica la fuerza
     */
    public void setFuerza(int fuerza) {
        this.fuerza = fuerza;
    }

    /**
     * @return la velocidad
     */
    public int getVelocidad() {
        return velocidad;
    }

    /**
     * @param velocidad
     *            modifica la velocidad
     */
    public void setVelocidad(int velocidad) {
        this.velocidad = velocidad;
    }

    /**
     * @return la resistencia
     */
    public int getResistencia() {
        return resistencia;
    }

    /**
     * @param resistencia
     *            modifica la resistencia
     */
    public void setResistencia(int resistencia) {
        this.resistencia = resistencia;
    }

    /**
     * @return la tecnica
     */
    public int getTecnica() {
        return tecnica;
    }

    /**
     * @param tecnica
     *            modifica la tecnica
     */
    public void setTecnica(int tecnica) {
        this.tecnica = tecnica;
    }

    public boolean esTitular() {
        return titular;
    }

    public void setTitular(boolean titular) {
        this.titular = titular;
    }

    /**
     * @return the posicion
     */
    public PosicionJugador getPosicion() {
        return posicion;
    }

    public void setPosicionJugadorAleatoriamente() {
        final List<PosicionJugador> posiciones = Collections.unmodifiableList(Arrays.asList(PosicionJugador.values()));
        final int tamanyo = posiciones.size();
        final Random r = new Random();

        this.posicion = posiciones.get(r.nextInt(tamanyo));

    }

    @Override
    public String toString() {
        return nombre + " " + apellidos +" "+"F:"+fuerza+"V:"+velocidad+"R:"+resistencia+"T:"+tecnica;
    }

}
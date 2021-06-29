package juego;

import java.awt.Color;

import java.awt.Image;
import java.util.Random;

import entorno.Entorno;
import entorno.Herramientas;
import entorno.InterfaceJuego;

public class Juego extends InterfaceJuego {

	// El objeto Entorno que controla el tiempo y otros
	private Entorno entorno;
	private Calle[] calles;
	private Casa[] casas;
	private Sakura sakura;
	private Ninja[] ninjas;
	private Rasengan rasengan;
	private Casa casaDelPedido;
	private int numeroCasaDelPedido;
	private Random numeroRandom;
	private int puntos;
	private int ninjasEliminados;
	private Image fondo;
	private Image lineasCalle;
	private Image arboles;
	private Image imagenGameOver;
	private int cantidadDeRasengans;
	private double[][] ninjasReapariciones;
	private int ninjaQueReaparecera;
	private boolean finDelJuego;
	public Juego() {

		// Inicializa el objeto entorno
		this.entorno = new Entorno(this, "Sakura Ikebana Delivery - Grupo 7", 800, 600);
		sakura = new Sakura(entorno.ancho() / 2, entorno.alto() / 2, -Math.PI / 2);
		ninjas = new Ninja[6];
		ninjasReapariciones = new double[3][3];
		puntos = 0;
		ninjasEliminados = 0;
		cantidadDeRasengans = 3;
		ninjaQueReaparecera = 0;
		int i = 0;
		double x = entorno.ancho() / 8;
		double y = entorno.alto() / 2;
		double ang = Math.PI / 2;
		finDelJuego = false;
		// Creacion de ninjas
		for (int j = 0; j < ninjas.length; j++) {
			ninjas[i++] = new Ninja(x, y, ang);

			if (i == 4) {
				x = entorno.ancho() / 2;
				y = entorno.alto() / 5.2;
				ang = 0;
			}
			if (i == 5) {
				x = entorno.ancho() / 2;
				y = entorno.alto() - y - entorno.alto() / (entorno.alto() / 10);
				ang = 0;
			} else {
				x += 200;
				ang = ang * -1;
			}
		}

		// Creacion de calles y casas
		calles = new Calle[7];
		int k = 0;
		double xx = entorno.ancho() / 2;
		int yy = entorno.alto() / 5;
		int ancho = entorno.ancho();
		int alto = 40;
		casas = new Casa[41];
		int xCasa = 200;
		int yCasa = 40;
		int g = 0;
		for (int j = 0; j < calles.length; j++) {
			calles[k++] = new Calle(xx, yy, ancho, alto);

			if (k == 1) {
				yy = entorno.alto() / 2;
				for (int c = 0; c < 3; c++) {
					casas[g++] = new Casa(c, xCasa, yy - 230, Math.PI);
					xCasa += 200;
				}
				xCasa = 200;
				for (int d = 3; d < 6; d++) {
					casas[g++] = new Casa(d, xCasa, yy - 50, Math.PI);
					xCasa += 200;
				}
				xCasa = 200;
				for (int e = 6; e < 9; e++) {
					casas[g++] = new Casa(e, xCasa, yy + 130, Math.PI);
					xCasa += 200;
				}

			}
			if (k == 2) {
				yy = entorno.alto() - entorno.alto() / 5;
			}
			if (k == 3 || k == 4 || k == 5 || k == 6) {

				yy = entorno.alto() / 2;
				ancho = 40;
				alto = entorno.alto();

				if (k == 3) {
					xx = entorno.ancho() / 8;
					for (int f = 9; f < 13; f++) {
						casas[g++] = new Casa(f, xx - 50, yCasa, Math.PI / 2);
						yCasa += 170;
					}
					yCasa = 40;
					for (int h = 13; h < 17; h++) {
						casas[g++] = new Casa(h, xx + 50, yCasa, Math.PI / 2);
						yCasa += 170;
					}
					yCasa = 40;

				}
				if (k == 4) {
					xx = entorno.ancho() / 2.66;
					for (int l = 17; l < 21; l++) {
						casas[g++] = new Casa(l, xx - 50, yCasa, Math.PI / 2);
						yCasa += 165;
					}
					yCasa = 40;
					for (int m = 21; m < 25; m++) {
						casas[g++] = new Casa(m, xx + 50, yCasa, Math.PI / 2);
						yCasa += 165;
					}
					yCasa = 40;
				}
				if (k == 5) {
					xx = entorno.ancho() - entorno.ancho() / 2.66;
					for (int l = 25; l < 29; l++) {
						casas[g++] = new Casa(l, xx - 50, yCasa, Math.PI / 2);
						yCasa += 165;
					}
					yCasa = 40;
					for (int m = 29; m < 33; m++) {
						casas[g++] = new Casa(m, xx + 50, yCasa, Math.PI / 2);
						yCasa += 165;
					}
					yCasa = 40;
				}
				if (k == 6) {
					xx = entorno.ancho() - entorno.ancho() / 8;
					for (int q = 33; q < 37; q++) {
						casas[g++] = new Casa(q, xx - 50, yCasa, Math.PI / 2);
						yCasa += 165;
					}
					yCasa = 40;
					for (int w = 37; w < 41; w++) {
						casas[g++] = new Casa(w, xx + 50, yCasa, Math.PI / 2);
						yCasa += 165;
					}
				}
			}
			fondo = Herramientas.cargarImagen("fondo.png");
			lineasCalle = Herramientas.cargarImagen("lineasCalle.png");
			arboles = Herramientas.cargarImagen("arboles.png");
		}
		numeroRandom = new Random();
		numeroCasaDelPedido = numeroRandom.nextInt(casas.length);
		casaDelPedido = casas[numeroCasaDelPedido];
		imagenGameOver = Herramientas.cargarImagen("gameover.png");

		// Inicializar lo que haga falta para el juego
		// ...

		// Inicia el juego!
		this.entorno.iniciar();
	}

	/**
	 * Durante el juego, el método tick() será ejecutado en cada instante y por lo
	 * tanto es el método más importante de esta clase. Aquí se debe actualizar el
	 * estado interno del juego para simular el paso del tiempo (ver el enunciado
	 * del TP para mayor detalle).
	 */

	public void tick() {
		entorno.dibujarImagen(fondo, entorno.ancho() / 2, entorno.alto() / 2, 0);

		for (Calle c : calles) {
			c.dibujar(entorno);
		}

		entorno.dibujarImagen(lineasCalle, entorno.ancho() / 2, entorno.alto() / 2, 0);

		for (Ninja n : ninjas) {
			if (n != null) {
				n.dibujar(entorno);
				n.mover(entorno);
				if (sakura.colisionoConNinja(entorno, n)) {
					finDelJuego = true;
				}
			}
		}

		for (Casa c : casas) {
			if (c != null) {
				c.dibujar(entorno);
			}
		}

		sakura.dibujar(entorno);

		if (sakura.colisionoConElEntorno(entorno) || sakura.salioDeCalle(entorno)) {
			sakura.noAvanzar(entorno);
		}

		if (entorno.estaPresionada('a') || entorno.estaPresionada('d') || entorno.estaPresionada('w') || entorno.estaPresionada('s')) {
			sakura.mover(entorno);
		}

		if (entorno.estaPresionada('a')) {
			sakura.mirarIzquierda();
		} else {
			if (entorno.estaPresionada('d')) {
				sakura.mirarDerecha();
			} else {
				if (entorno.estaPresionada('w')) {
					sakura.mirarArriba();
				} else {
					if (entorno.estaPresionada('s')) {
						sakura.mirarAbajo();
					}
				}
			}
		}

		if (rasengan != null) {
			rasengan.dibujar(entorno);
			rasengan.mover();

			for (int i = 0; i < ninjas.length; i++) {
				if (rasengan != null && ninjas[i] != null && rasengan.colisionoConNinja(ninjas[i])) {
					Herramientas.cargarSonido("ninjaDie.wav").start();
					ninjasReapariciones[ninjaQueReaparecera][0] = ninjas[i].getX();
					ninjasReapariciones[ninjaQueReaparecera][1] = ninjas[i].getY();
					ninjasReapariciones[ninjaQueReaparecera][2] = ninjas[i].getDireccion();
					ninjas[i] = null;
					rasengan = null;
					ninjasEliminados++;
					ninjaQueReaparecera += 1;
				}
			}
			if (rasengan != null
					&& (rasengan.colisionoConLaCiudad(entorno) || rasengan.colisionoConElEntorno(entorno))) {
				rasengan = null;
			}
		}
		if (rasengan == null && cantidadDeRasengans != 0) {

			if (entorno.sePresiono(entorno.TECLA_ESPACIO)) {
				rasengan = sakura.lanzarRasengan(entorno);
				cantidadDeRasengans -= 1;
			}
		}
		if (sakura.llegoACasaDelPedido(casaDelPedido)) {
			numeroCasaDelPedido = numeroRandom.nextInt(casas.length);
			casaDelPedido = casas[numeroCasaDelPedido];
			puntos += 5;
			cantidadDeRasengans = 3;
			ninjaQueReaparecera = 0;
			for (int i = 0; i < ninjas.length; i++) {
				if (ninjas[i] == null && !(ninjasReapariciones[ninjaQueReaparecera][0]<0)) {
					ninjas[i] = new Ninja(ninjasReapariciones[ninjaQueReaparecera][0], ninjasReapariciones[ninjaQueReaparecera][1], ninjasReapariciones[ninjaQueReaparecera][2]);
					ninjasReapariciones[ninjaQueReaparecera][0] = -10;
					ninjaQueReaparecera += 1;
				}
			}
			if (ninjaQueReaparecera > 0) {
				Herramientas.cargarSonido("goNinja.wav").start();
			}
			ninjaQueReaparecera = 0;
			
		}
		if (entorno.estaPresionada(entorno.TECLA_SHIFT)) {
			sakura.correr(entorno);
		} else {
			sakura.caminar(entorno);
		}

		entorno.dibujarImagen(arboles, entorno.ancho() / 2, entorno.alto() / 2, 0);
		casaDelPedido.marcar(entorno);
		entorno.cambiarFont("sans", entorno.alto() / 33, Color.RED);
		entorno.escribirTexto("Rasengans: " + cantidadDeRasengans, entorno.ancho() / 78, entorno.alto() / 15);
		entorno.escribirTexto("Pts: " + puntos, entorno.ancho() - entorno.ancho() / 11, entorno.alto() / 35);
		entorno.escribirTexto("Kills: " + ninjasEliminados, entorno.ancho() / 80, entorno.alto() / 35);

		if (finDelJuego) {
			entorno.dibujarImagen(imagenGameOver, entorno.ancho() / 2, entorno.alto() / 2, 0);
			entorno.cambiarFont("sans", entorno.alto() / 33, Color.CYAN);
			entorno.escribirTexto("Puntuacion Total: " + puntos, entorno.ancho() / 2.5, entorno.alto() - 90);
		}

		// Procesamiento de un instante de tiempo
		// ...
	}

	@SuppressWarnings("unused")
	public static void main(String[] args) {
		Juego juego = new Juego();
	}
}
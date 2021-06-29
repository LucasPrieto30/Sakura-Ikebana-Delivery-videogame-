package juego;

import java.awt.Image;

import entorno.Entorno;
import entorno.Herramientas;

public class Ninja {

	private double x;
	private double y;
	private double velocidad;
	private double direccion;
	private double tamaño;
	private Image ninjaMirandoHaciaDerecha;
	private Image ninjaMirandoHaciaIzquierda;

	public Ninja(double x, double y, double direccion) {
		this.x = x;
		this.y = y;
		this.velocidad = 1.5;
		this.direccion = direccion;
		this.ninjaMirandoHaciaDerecha = Herramientas.cargarImagen("ninjaMirandoHaciaDerecha.png");
		this.ninjaMirandoHaciaIzquierda = Herramientas.cargarImagen("ninjaMirandoHaciaIzquierda.png");
		this.tamaño = ninjaMirandoHaciaDerecha.getHeight(null);
	}

	public void dibujar(Entorno e) {
		if (direccion == Math.PI) {
			e.dibujarImagen(ninjaMirandoHaciaIzquierda, x, y, 0);
		} else {
			e.dibujarImagen(ninjaMirandoHaciaDerecha, x, y, 0);
		}
	}

	public void mover(Entorno e) {
		x += velocidad * Math.cos(direccion);
		y += velocidad * Math.sin(direccion);
		if (x < tamaño / 2) {
			x += e.ancho();
		}
		if (y < tamaño / 2) {
			y += e.alto();
		}
		if (x > e.ancho() - tamaño / 2) {
			x -= e.ancho();
		}
		if (y > e.alto() - tamaño / 2) {
			y -= e.alto();
		}
	}

	public double getDireccion() {
		return direccion;
	}

	public double getX() {
		return x;
	}

	public double getY() {
		return y;
	}
}

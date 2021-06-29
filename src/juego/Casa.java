package juego;

import java.awt.*;

import entorno.Entorno;
import entorno.Herramientas;

public class Casa {

	private int numero;
	private double x;
	private double y;
	private double tamaño;
	private double direccion;
	private Image casaRoja;
	private Image casaAzul;
	private Image flecha;

	public Casa(int numero, double x, double y, double direccion) {
		this.numero = numero;
		this.direccion = direccion;
		this.x = x;
		this.y = y;
		this.flecha = Herramientas.cargarImagen("flecha.gif");
		this.casaRoja = Herramientas.cargarImagen("casaRoja.png");
		this.casaAzul = Herramientas.cargarImagen("casaAzul.png");
		this.tamaño = casaRoja.getWidth(null);
	}

	public void dibujar(Entorno e) {
		if (this.numero % 2 == 0) {
			e.dibujarImagen(casaRoja, x, y, direccion, 0.8);
		} else {
			e.dibujarImagen(casaAzul, x, y, direccion, 0.8);
		}
	}

	public void marcar(Entorno e) {
		if (this.numero == 9 || this.numero == 13 || this.numero == 17 || this.numero == 21 || this.numero == 25
				|| this.numero == 29 || this.numero == 33 || this.numero == 37) {
			e.dibujarImagen(flecha, x, y + tamaño, -Math.PI / 2, 1.5);
		} else {
			e.dibujarImagen(flecha, x, y - tamaño, Math.PI / 2, 1.5);
		}
	}

	public double getX() {
		return x;
	}

	public double getY() {
		return y;
	}

}

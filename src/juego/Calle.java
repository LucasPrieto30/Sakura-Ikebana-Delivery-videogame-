package juego;

import java.awt.Color;

import entorno.Entorno;

public class Calle {

	private double x;
	private double y;
	private int ancho;
	private int alto;
	private Color color;

	public Calle(double x, double y, int ancho, int alto) {
		this.x = x;
		this.y = y;
		this.ancho = ancho;
		this.alto = alto;
		this.color = Color.GRAY;
	}

	public void dibujar(Entorno e) {
		e.dibujarRectangulo(x, y, ancho, alto, 0, color);
	}
}
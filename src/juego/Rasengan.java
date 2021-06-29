package juego;

import java.awt.Image;

import entorno.Entorno;
import entorno.Herramientas;

public class Rasengan {

	private double x;
	private double y;
	private double tamaño;
	private double velocidad;
	private Image rasengan;
	private double direccion;
	private double ancho;

	public Rasengan(Entorno e, double x, double y, double direccion) {
		this.x = x;
		this.y = y;
		this.velocidad = 5;
		this.direccion = direccion;
		this.rasengan = Herramientas.cargarImagen("Rasengan.png");
		this.tamaño = rasengan.getHeight(null);
		this.ancho = rasengan.getWidth(null);
	}

	public void dibujar(Entorno e) {
		e.dibujarImagen(rasengan, x , y , 0);
	}

	public void mover() {
		x += velocidad * Math.cos(direccion);
		y += velocidad * Math.sin(direccion);
	}

	public boolean colisionoConElEntorno(Entorno e) {
		return x < tamaño / 2 || x > e.ancho() - tamaño / 2 || y < tamaño / 2 || y > e.alto() - tamaño / 2;
	}

	public boolean colisionoConLaCiudad(Entorno e) {
		boolean colisiono = false;
		// verificaciones en calles horizontales
		if (((y + tamaño < e.alto() / 2 + 5 && y + tamaño > e.alto() / 2 - 110) // calle del medio (parte de arriba)
				|| (y + tamaño >= e.alto() / 2 + 5 && y + tamaño < e.alto() / 2 + 20) // calle de medio (parte de abajo)
				|| (y + tamaño < e.alto() / 5 + 5 && y + tamaño > e.alto() / 5 - 110) // calle de arriba (parte de arriba)
				|| (y + tamaño >= e.alto() / 5 + 5 && y + tamaño < e.alto() / 5 + 20) // calle de arriba (parte de abajo)
				|| (y + tamaño < e.alto() - e.alto() / 5 + 5 && y + tamaño > e.alto() - e.alto() / 5 - 110) // calle de abajo (parte de arriba)
				|| (y + tamaño >= e.alto() - e.alto() / 5 + 50))){ // calle de abajo (parte de abajo)

			if (((x + ancho / 2 < e.ancho() / 8)
					|| (x + ancho / 2 > e.ancho() / 8 + 60 && x + ancho / 2 < e.ancho() / 2.66)
					|| (x + ancho / 2 > e.ancho() / 2.66 + 60 && x + ancho / 2 < e.ancho() - e.ancho() / 2.66)
					|| (x + ancho / 2 > e.ancho() - e.ancho() / 2.66 + 60 && x + ancho / 2 < e.ancho() - e.ancho() / 8)
					|| (x + ancho / 2 > e.ancho() - e.ancho() / 8 + 60))) {
				colisiono = true;
			}
		// verificaciones en calles verticales
		} else if ((x - ancho / 3 < e.ancho() / 8 - 20 && x - ancho / 3 > e.ancho() / 8 - 40) // Primera calle desde izquierda (parte izquierda)
				|| (x + ancho / 3 > e.ancho() / 8 + 20 && x + ancho / 3 < e.ancho() / 8 + 40) // Primera calle desde izquierda (parte derecha)
				|| (x - ancho / 3 < e.ancho() / 2.66 - 20 && x - ancho / 3 > e.ancho() / 2.66 - 40) // segunda calle (parte izquierda)
				|| (x + ancho / 3 > e.ancho() / 2.66 + 20 && x + ancho / 3 < e.ancho() / 2.66 + 40) // segunda calle (parte derecha)
				|| (x - ancho / 3 < e.ancho() - e.ancho() / 2.66 - 20
						&& x - ancho / 3 > e.ancho() - e.ancho() / 2.66 - 40)	// tercera calle (parte izquierda)
				|| (x + ancho / 3 > e.ancho() - e.ancho() / 2.66 + 20
						&& x + ancho / 3 < e.ancho() - e.ancho() / 2.66 + 40)  // tercera calle (parte derecha)
				|| (x - ancho / 3 < e.ancho() - e.ancho() / 8 - 20 && x - ancho / 3 > e.ancho() - e.ancho() / 8 - 40) // cuarta calle (parte izquierda)
				|| (x + ancho / 3 > e.ancho() - e.ancho() / 8 + 20 && x + ancho / 3 < e.ancho() - e.ancho() / 8 + 40)) { // cuarta calle (parte derecha)
		} else {
			colisiono = false;
		}
		return colisiono;
	}

	public boolean colisionoConNinja(Ninja n) {
		return Math.abs(x - n.getX()) < 20 && Math.abs(y - n.getY()) < 20;
	}
}

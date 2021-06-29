package juego;

import java.awt.Image;

import entorno.Entorno;
import entorno.Herramientas;

public class Sakura {

	private double x;
	private double y;
	private double velocidad;
	private double tamaño;
	private double ancho;
	private double direccion;
	private Image sakuraMirandoHaciaFrente;
	private Image sakuraMirandoHaciaDerecha;
	private Image sakuraMirandoHaciaIzquierda;

	public Sakura(double x, double y, double direccion) {
		this.x = x;
		this.y = y;
		this.sakuraMirandoHaciaFrente = Herramientas.cargarImagen("sakuraMirandoHaciaFrente.png");
		this.sakuraMirandoHaciaDerecha = Herramientas.cargarImagen("sakuraMirandoHaciaDerecha.png");
		this.sakuraMirandoHaciaIzquierda = Herramientas.cargarImagen("sakuraMirandoHaciaIzquierda.png");
		this.tamaño = sakuraMirandoHaciaFrente.getHeight(null);
		this.ancho = sakuraMirandoHaciaFrente.getWidth(null);
	}

	public void mirarIzquierda() {
		direccion = Math.PI;
	}
	
	public void mirarDerecha() {
		direccion = 0;
	}
	
	public void mirarArriba() {
		direccion = - Math.PI / 2;
	}

	public void mirarAbajo() {
		direccion = Math.PI / 2;
	}
	
	public void dibujar(Entorno e) {
		if (direccion == Math.PI) {
			e.dibujarImagen(sakuraMirandoHaciaIzquierda, x, y, 0);
		} else {
			if (direccion == 0) {
				e.dibujarImagen(sakuraMirandoHaciaDerecha, x, y, 0);
			} else {
				e.dibujarImagen(sakuraMirandoHaciaFrente, x, y, 0);
			}
		}
	}
	
	public void mover(Entorno e) {
		x += velocidad * Math.cos(direccion);
		y += velocidad * Math.sin(direccion);
	}

	public boolean colisionoConElEntorno(Entorno e) {
		return x < tamaño / 2 || x > e.ancho() - tamaño / 2 || y < tamaño / 2 || y > e.alto() - tamaño / 2;
	}

	public boolean colisionoConNinja(Entorno e, Ninja n) {
		if (n == null) {
			return false;
		}
		return x > n.getX() - tamaño / 2 && x < n.getX() + tamaño / 2 && y > n.getY() - tamaño / 2
				&& y < n.getY() + tamaño / 2;
	}

	public boolean llegoACasaDelPedido(Casa c) {
		return (Math.abs(x - c.getX()) < 55 && Math.abs(y - c.getY()) < 50);
	}

	public boolean salioDeCalle(Entorno e) {
		boolean noAvanza = false;
		// verificaciones en calles horizontales
		if (((y + tamaño < e.alto() / 2 + 45 && y + tamaño > e.alto() / 2 - 1) // calle del medio (parte de arriba)
				|| (y + tamaño >= e.alto() / 2 + 45 && y + tamaño < e.alto() / 2 + 50) // calle del medio (parte de abajo)
				|| (y + tamaño < e.alto() / 5 + 45 && y + tamaño > e.alto() / 5 - 1) // calle de arriba (parte de arriba)
				|| (y + tamaño >= e.alto() / 5 + 45 && y + tamaño < e.alto() / 5 + 50) // calle de arriba (parte de abajo)
				|| (y + tamaño < e.alto() - e.alto() / 5 + 45 && y + tamaño > e.alto() - e.alto() / 5 - 1) // calle de abajo (parte de arriba)
				|| (y + tamaño >= e.alto() - e.alto() / 5 + 45 && y + tamaño < e.alto() - e.alto() / 5 + 50))) { // calle de abajo (parte de abajo)

			if (((x + ancho / 2 < e.ancho() / 8)
					|| (x + ancho / 2 > e.ancho() / 8 + 40 && x + ancho / 2 < e.ancho() / 2.66)
					|| (x + ancho / 2 > e.ancho() / 2.66 + 40 && x + ancho / 2 < e.ancho() - e.ancho() / 2.66)
					|| (x + ancho / 2 > e.ancho() - e.ancho() / 2.66 + 40 && x + ancho / 2 < e.ancho() - e.ancho() / 8)
					|| (x + ancho / 2 > e.ancho() - e.ancho() / 8 + 40))) {
				noAvanza = true;
			}
		// verificaciones en calles verticales	
		} else if ((x - ancho / 3 < e.ancho() / 8 - 20 && x - ancho / 3 > e.ancho() / 8 - 40) // Primera calle desde la izquierda (parte izquierda)
				|| (x + ancho / 3 > e.ancho() / 8 + 20 && x + ancho / 3 < e.ancho() / 8 + 40) // Primera calle desde la izquierda (parte derecha)
				|| (x - ancho / 3 < e.ancho() / 2.66 - 20 && x - ancho / 3 > e.ancho() / 2.66 - 40) // segunda calle (parte izquierda)
				|| (x + ancho / 3 > e.ancho() / 2.66 + 20 && x + ancho / 3 < e.ancho() / 2.66 + 40) // segunda calle (parte derecha)
				|| (x - ancho / 3 < e.ancho() - e.ancho() / 2.66 - 20
							&& x - ancho / 3 > e.ancho() - e.ancho() / 2.66 - 40)	// tercera calle (parte izquierda)
				|| (x + ancho / 3 > e.ancho() - e.ancho() / 2.66 + 20				
						&& x + ancho / 3 < e.ancho() - e.ancho() / 2.66 + 40)		// tercera calle (parte derecha)
				|| (x - ancho / 3 < e.ancho() - e.ancho() / 8 - 20 && x - ancho / 3 > e.ancho() - e.ancho() / 8 - 40) // cuarta calle (parte izquierda)
				|| (x + ancho / 3 > e.ancho() - e.ancho() / 8 + 20 && x + ancho / 3 < e.ancho() - e.ancho() / 8 + 40)) { // cuarta calle (parte derecha)

			if ((y + tamaño / 2 < e.alto() / 5 - 20)
					|| (y + tamaño / 2 > e.alto() / 5 + 20 && y + tamaño / 2 < e.alto() / 2 - 20)
					|| (y + tamaño / 2 > e.alto() / 2 + 20 && y + tamaño / 2 < e.alto() - e.alto() / 5 - 20)
					|| (y + tamaño / 2 > e.alto() - e.alto() / 5 + 20)) {
				noAvanza = true;
			}
		} else {
			noAvanza = false;
		}
		return noAvanza;
	}

	public void noAvanzar(Entorno e) {
		if (x < tamaño / 2) {
			x += velocidad;
		}
		if (y < tamaño / 2) {
			y += velocidad;
		}
		if (x > e.ancho() - tamaño / 2) {
			x -= velocidad;
		}
		if (y > e.alto() - tamaño / 2) {
			y -= velocidad;
		}

		// condiciones para que no salga de las calles
		if (((y + tamaño < e.alto() / 2 + 45 && y + tamaño > e.alto() / 2 - 1)
				|| (y + tamaño < e.alto() / 5 + 45 && y + tamaño > e.alto() / 5 - 1)
				|| (y + tamaño < e.alto() - e.alto() / 5 + 45 && y + tamaño > e.alto() - e.alto() / 5 - 1))) {
			y += velocidad;
		}

		if ((y + tamaño >= e.alto() / 2 + 45 && y + tamaño < e.alto() / 2 + 50
				|| (y + tamaño >= e.alto() / 5 + 45 && y + tamaño < e.alto() / 5 + 50)
				|| (y + tamaño >= e.alto() - e.alto() / 5 + 45 && y + tamaño < e.alto() - e.alto() / 5 + 50))) {
			y -= velocidad;

		} else if ((x - ancho / 3 < e.ancho() / 8 - 20 && x - ancho / 3 > e.ancho() / 8 - 40)
				|| (x - ancho / 3 < e.ancho() / 2.66 - 20 && x - ancho / 3 > e.ancho() / 2.66 - 40)
				|| (x - ancho / 3 < e.ancho() - e.ancho() / 2.66 - 20
						&& x - ancho / 3 > e.ancho() - e.ancho() / 2.66 - 40)
				|| (x - ancho / 3 < e.ancho() - e.ancho() / 8 - 20 && x - ancho / 3 > e.ancho() - e.ancho() / 8 - 40)) {
			x += velocidad;
		}

		else if ((x + ancho / 3 > e.ancho() / 8 + 20 && x + ancho / 3 < e.ancho() / 8 + 40)
				|| (x + ancho / 3 > e.ancho() / 2.66 + 20 && x + ancho / 3 < e.ancho() / 2.66 + 40)
				|| (x + ancho / 3 > e.ancho() - e.ancho() / 2.66 + 20
						&& x + ancho / 3 < e.ancho() - e.ancho() / 2.66 + 40)
				|| (x + ancho / 3 > e.ancho() - e.ancho() / 8 + 20 && x + ancho / 3 < e.ancho() - e.ancho() / 8 + 40)) {
			x -= velocidad;
		}
	}

	public void correr(Entorno e) {
		velocidad = 4;
	}

	public void caminar(Entorno e) {
		velocidad = 2;
	}

	public Rasengan lanzarRasengan(Entorno e) {
		Rasengan rasengan = new Rasengan(e, x, y, direccion);
		return rasengan;
	}
}

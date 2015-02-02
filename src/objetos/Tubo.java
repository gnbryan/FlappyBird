package objetos;

import java.util.LinkedList;
import java.util.List;
import java.util.Random;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.*;
import estados.PartidaDia;
import estados.PartidaNoche;

public class Tubo {
	protected float x, y, velocidadJuego;
	private Shape colision;
	private boolean atravesado;					// ¿Se ha atravesado un tubo? Para generar la puntuación de una partida
	private int altura;							
	protected Image ImagenArriba, ImagenAbajo;
	public List<Shape> colisiones = new LinkedList<>();

	public Tubo() throws SlickException {
		Random aleatorio = new Random();
		x = 560; y = aleatorio.nextInt(350);	 	// Coordenadas x e y desde donde empiezan a salir los tubos en el mapa
		velocidadJuego = 2.5f;
	
		altura = aleatorio.nextInt(40) + 80;
		ImagenArriba = new Image("Images/tuboArriba.png");
		ImagenAbajo = new Image("Images/tuboAbajo.png"); 
		
		// Se evita que se dibujen los tubos despegados del suelo superior y por debajo del suelo inferior
		if(getY() > 390 - altura)
			setY(390 - altura);
		
		// Se generan los rectángulos que ejercerán de colisionadores si el pájaro choca contra el tubo
		colisiones.add(new Rectangle(getX(), 0, 52, getY()));
		colisiones.add(new Rectangle(getX(), getY() + altura, 52, 400 - getY() - altura));
		atravesado = false;
	}

	public void generarTubos() {
		
		// Si no se ha acabado la partida, se posiciona cada tubo y sus rectángulos colisionadores se añade en la lista de tubos del mapa
		if(!PartidaDia.finPartida || !PartidaNoche.finPartida) {
			setX(getX() - getVelocidadJuego());
			colisiones.get(0).setX(this.getX());
			colisiones.get(1).setX(this.getX());
			if(this.getX() < -60) {
				List<Tubo> lista = new LinkedList<>();
				for(Tubo t : PartidaDia.tubos) {
					if(t != this)
						lista.add(t);
				}
				PartidaDia.tubos = lista;
			}
			if(this.getX() < 40 && !atravesado) {
				atravesado = true;
				PartidaDia.puntuacion += 10;
				PartidaNoche.puntuacion += 10;
			}
		}
	}

	public int getAltura() {
		return altura;
	}
	
	public Image getImagenArriba() {
		return ImagenArriba;
	}
	
	public Image getImagenAbajo() {
		return ImagenAbajo;
	}
	
	public void setImagen(Image ar, Image ab) {
		ImagenArriba = ar;
		ImagenAbajo = ab;
	}
	
	public float getX() {
		return x;
	}

	public float getY() {
		return y;
	}
	
	public Shape getColision() {
		return colision;
	}

	public float getVelocidadJuego() {
		return velocidadJuego;
	}
	
	public void setX(float otraX) {
		x = otraX;
	}
	
	public void setY(float otraY) {
		y = otraY;
	}

	public void setVelocidadJuego(float v) {
		velocidadJuego = v;
	}

	public void setColision(Shape c) {
		colision = c;
	}
}
package objetos;

import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.geom.Shape;

import estados.*;

public class Suelo {
	private float x, y;
	private Image imagen;
	private Shape colision;
	
	public Suelo() throws SlickException {
		x = 0; y = 400;
		setColision(new Rectangle(x, y, y, y));
		imagen = new Image("Images/suelo.png");
	}
	
	public void reemplazarSuelo() {
		if(!PartidaDia.finPartida || !PartidaNoche.finPartida) {
			setX(getX());
			if(getX() < -400) {		// Si se sale el suelo de la pantalla, eliminar de la lista	
				PartidaDia.plataforma.remove(this);
				PartidaNoche.plataforma.remove(this);
			}
		}
	}
	
	public Image getImagen() {
		return imagen;
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
	
	public void setColision(Shape c) {
		colision = c;
	}
	
	public void setX(float otraX) {
		x = otraX;
	}
	
	public void setY(float otraY) {
		y = otraY;
	}
}
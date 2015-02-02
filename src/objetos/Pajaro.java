package objetos;

import org.newdawn.slick.Animation;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Circle;
import org.newdawn.slick.geom.Shape;

import patrones.I_Salto;
import patrones.I_Pajaro;
import estados.PartidaDia;
import estados.PartidaNoche;

public abstract class Pajaro implements I_Pajaro {
	protected float x, y, velocidad, rotacion, velRotacion, gravedad, radio;
	protected Shape colision;
	protected Image[] imagenesPajaro;
	protected Animation animacionPajaro;
	protected I_Salto salto;

	public Pajaro() throws SlickException {
		x = 100; y = 200;
		rotacion = 0;
		gravedad = 0.3f;
		radio = 13;
		setColision(new Circle(getX(), getY(), radio));
	}

	public void volar(Input tecla) {
		
		/* 
		 * El pájaro vuela con una rotación y velocidad determinada siempre que no se haya llegado al fin de la partida,
		 * mientras esté dentro del juego verticalmente (evita que se salga fuera del juego por la parte de arriba)
		 * y mientras se haya pulsado la tecla para volar: la 'V'
		*/ 
		if(!PartidaDia.finPartida && getY() > 10 && tecla.isKeyDown(Input.KEY_V) || !PartidaNoche.finPartida && getY() > 10 && tecla.isKeyDown(Input.KEY_V)) {
			setVelocidad(-4.0f);
			rotacion = -45;
		}
		
		// Generación del efecto gravitatorio del pájaro y patrón estrategia
		velRotacion = salto.asignarSalto();
		setY(getY() + getVelocidad());
		setVelocidad(getVelocidad() + gravedad);
		
		// Ubicación del círculo que ejercerá como colisionador del pájaro contra el suelo y los tubos
		getColision().setLocation(getX(), getY());
		getColision().setCenterX(getX());
		getColision().setCenterY(getY());
		
		// Efecto de rotación del pájaro
		rotacion += velRotacion;
	}

	public float getRotacion() {
		return rotacion;
	}
	
	public void setRotacion(float r) {
		rotacion = r;
	}

	public Animation getPajaroAnimacion() {
		return animacionPajaro;
	}

	public void setPajaroAnimacion(Animation p) {
		animacionPajaro = p;
	}
	
	public void setSalto(I_Salto s) {
		salto = s;
	}
	
	public float getX() {
		return x;
	}

	public float getY() {
		return y;
	}
	
	public Shape getColision(){
		return colision;
	}

	public float getVelocidad() {
		return velocidad;
	}
	
	public void setX(float otraX) {
		x = otraX;
	}
	
	public void setY(float otraY) {
		y = otraY;
	}

	public void setVelocidad(float v) {
		velocidad = v;
	}

	public void setColision(Shape c) {
		colision = c;
	}
}
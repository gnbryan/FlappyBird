package objetos;

import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

public class TuboNoche extends Tubo {
	
	public TuboNoche() throws SlickException {
		setVelocidadJuego(4.0f);
		setImagen(new Image("Images/tuboArriba2.png"), new Image("Images/tuboAbajo2.png"));
	}
}

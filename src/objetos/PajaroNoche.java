package objetos;

import org.newdawn.slick.Animation;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

public class PajaroNoche extends Pajaro {
	
	public PajaroNoche() throws SlickException {
		imagenesPajaro = new Image[4];
		imagenesPajaro[0] = new Image("Images/pajaroNoche1.png");
		imagenesPajaro[1] = new Image("Images/pajaroNoche.png");
		imagenesPajaro[2] = new Image("Images/pajaroNoche1.png");
		imagenesPajaro[3] = new Image("Images/pajaroNoche2.png");
		animacionPajaro = new Animation(imagenesPajaro, 50);
	}
	
	@Override
	public Pajaro crearPajaro() throws SlickException {
		return new PajaroNoche();
	}
}

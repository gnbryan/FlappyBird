package objetos;

import org.newdawn.slick.Animation;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

public class PajaroDia extends Pajaro {

	public PajaroDia() throws SlickException {
		imagenesPajaro = new Image[4];
		imagenesPajaro[0] = new Image("Images/pajaro1.png");
		imagenesPajaro[1] = new Image("Images/pajaro.png");
		imagenesPajaro[2] = new Image("Images/pajaro1.png");
		imagenesPajaro[3] = new Image("Images/pajaro2.png");
		animacionPajaro = new Animation(imagenesPajaro, 50);
	}

	@Override
	public Pajaro crearPajaro() throws SlickException {
		return new PajaroDia();
	}

}

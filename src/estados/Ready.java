package estados;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;
import org.newdawn.slick.state.transition.FadeInTransition;
import org.newdawn.slick.state.transition.FadeOutTransition;

public class Ready extends BasicGameState {
	private Image fondo, getReady, plataforma, pajaro;
	private float pajaroX = 270, pajaroY = 160;
	
	public Ready(int state) {}

	@Override
	public void init(GameContainer gc, StateBasedGame sbg) throws SlickException {	
		fondo = new Image("Images/fondo.png");
		pajaro = new Image("Images/pajaro.png");
		getReady = new Image("Images/get-ready.png");
		plataforma = new Image("Images/suelo.png");
	}

	@Override
	public void render(GameContainer gc, StateBasedGame sbg, Graphics g) throws SlickException {
		Input tecla = gc.getInput();
		fondo.draw(0, 0, gc.getWidth(), gc.getWidth());
		getReady.draw(200, 80);
		pajaro.draw(pajaroX, pajaroY);
		plataforma.draw(0, 442);

		String textoDay = "* PULSE '1' PARA COMENZAR UN FLAPPY DAY";
	    int xFlappyDay = 130;
	    int yFlappyDay = 230;

	    g.setColor(Color.black);
	    g.drawString(textoDay, xFlappyDay, yFlappyDay);
	    
	    g.setColor(Color.white);
	    g.drawString(textoDay, xFlappyDay+1, yFlappyDay+1);
	    
		String textoNight = "* PULSE '2' PARA COMENZAR UN FLAPPY NIGHT";
	    int xFlappyNight = 130;
	    int yFlappyNight = 250;

	    g.setColor(Color.black);
	    g.drawString(textoNight, xFlappyNight, yFlappyNight);
	    
	    g.setColor(Color.white);
	    g.drawString(textoNight, xFlappyNight+1, yFlappyNight+1);
	    
		String textoTecla = "PARA VOLAR PULSE LA TECLA 'V'";
	    int xTecla = 130;
	    int yTecla = 290;

	    g.setColor(Color.black);
	    g.drawString(textoTecla, xTecla, yTecla);
	    
	    g.setColor(Color.white);
	    g.drawString(textoTecla, xTecla+1, yTecla+1);

	    // Entramos al siguiente estado del juego (Flappy Day) usando transiciones
		if(tecla.isKeyPressed(Input.KEY_1) || tecla.isKeyPressed(Input.KEY_NUMPAD1)){
			sbg.enterState(2, new FadeOutTransition(), new FadeInTransition());
	    }
		
		// Entramos al siguiente estado del juego (Flappy Night) usando transiciones
		if(tecla.isKeyPressed(Input.KEY_2) || tecla.isKeyPressed(Input.KEY_NUMPAD2))
		    sbg.enterState(3, new FadeOutTransition(), new FadeInTransition());
	}

	@Override
	public void update(GameContainer gc, StateBasedGame sbg, int delta) throws SlickException {}

	@Override
	public int getID() {
		return 1;
	}
}
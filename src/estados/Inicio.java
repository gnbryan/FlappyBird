package estados;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

public class Inicio extends BasicGameState {
	private Image fondo, titulo, plataforma, pajaro;
	private float pajaroX = 270, pajaroY = 230;
	
	public Inicio(int state) {}

	@Override
	public void init(GameContainer container, StateBasedGame sbg) throws SlickException {
		fondo = new Image("Images/fondo.png");
		pajaro = new Image("Images/pajaro.png");
		titulo = new Image("Images/logo.png");
		plataforma = new Image("Images/suelo.png");
	}

	@Override
	public void render(GameContainer gc, StateBasedGame sbg, Graphics g) throws SlickException {
		Input tecla = gc.getInput();
		fondo.draw(0, 0, gc.getWidth(), gc.getWidth());
		titulo.draw(200, 80);
		pajaro.draw(pajaroX, pajaroY);
		plataforma.draw(0, 442);
		
		String texto = "PULSE 'ENTER' PARA COMENZAR UN JUEGO";
	    int xEnter = 130;
	    int yEnter = 300;

	    g.setColor(Color.black);
	    g.drawString(texto, xEnter, yEnter);
	    
	    g.setColor(Color.white);
	    g.drawString(texto, xEnter+1, yEnter+1);

	    // Entramos al siguiente estado del juego (Ready)
		if(tecla.isKeyPressed(Input.KEY_ENTER)){
			sbg.enterState(1);
	    }
	}

	@Override
	public void update(GameContainer gc, StateBasedGame sbg, int delta) throws SlickException {}

	@Override
	public int getID() {
		return 0;
	}
}
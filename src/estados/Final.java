package estados;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

public class Final extends BasicGameState {
	private Image fondo, plataforma, gameover, pajaro;
	private float pajaroX = 250, pajaroY = 230;
	
	public Final(int state) {}
	
	@Override
	public void init(GameContainer gc, StateBasedGame sbg) throws SlickException {
		fondo = new Image("Images/fondo.png");
		pajaro = new Image("Images/pajaro.png");
		plataforma = new Image("Images/suelo.png");
		gameover = new Image("Images/gameover.png");
	}

	@Override
	public void render(GameContainer gc, StateBasedGame sbg, Graphics g) throws SlickException {
		Input tecla = gc.getInput();
		fondo.draw(0, 0, gc.getWidth(), gc.getWidth());
		gameover.draw(180, 100);
		pajaro.draw(pajaroX, pajaroY);
		plataforma.draw(0, 442);

		String texto = "PULSE 'ESC' PARA UNA NUEVA PARTIDA";
		String puntos = "¡Ha conseguido " + PartidaDia.puntuacion + " puntos!";
	    int x = 140;
	    int y = 320;

	    g.setColor(Color.black);
	    g.drawString(texto, x, y);
	    
	    g.setColor(Color.white);
	    g.drawString(texto, x+1, y+1);
	    
	    int xPuntos = 140;
	    int yPuntos = 300;

	    g.setColor(Color.black);
	    g.drawString(puntos, xPuntos+1, yPuntos+1);

	    if(tecla.isKeyPressed(Input.KEY_ESCAPE)) {
			gc.reinit();
			tecla.clearKeyPressedRecord();
			PartidaDia.puntuacion = 0;
		    PartidaNoche.puntuacion = 0;
			sbg.enterState(0);
		}
	}

	@Override
	public void update(GameContainer gc, StateBasedGame sbg, int delta) throws SlickException {}

	@Override
	public int getID() {
		return 4;
	}
}

package estados;

import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;

public class Principal extends StateBasedGame {
	public Principal(String name) {
		super(name);
	}

	public static void main(String[] args) throws SlickException {
		AppGameContainer contenedor = new AppGameContainer(new Principal("Bryan García Navarro - Flappy Bird"));
		contenedor.setDisplayMode(570, 512, false);
		contenedor.setShowFPS(false);
		contenedor.setTargetFrameRate(70);
		contenedor.start();
	}

	@Override			// Creación de la lista de estados del juego
	public void initStatesList(GameContainer gc) throws SlickException {
		addState(new Inicio(0));
		addState(new Ready(1));
		addState(new PartidaDia(2));
		addState(new PartidaNoche(3));
		addState(new Final(4));
	}
}
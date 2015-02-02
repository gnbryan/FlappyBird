package estados;

import java.util.LinkedList;
import java.util.List;

import objetos.*;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

import patrones.SaltoNormal;
import patrones.SaltoMortal;
import patrones.I_Pajaro;

public class PartidaNoche extends BasicGameState {
	private I_Pajaro creadorPajaros;
	private Pajaro pajaroRojo;
	private Image fondo;
	private Suelo piso;
	private long timer;
	public static List<TuboNoche> tubos;
	public static List<Suelo> plataforma;
	public static int puntuacion;
	public static boolean finPartida;

	public PartidaNoche(int state) {}

	@Override
	public void init(GameContainer gc, StateBasedGame sbg) throws SlickException {
		creadorPajaros = new PajaroNoche();
		pajaroRojo = creadorPajaros.crearPajaro();
		pajaroRojo.setSalto(new SaltoNormal());
		tubos = new LinkedList<>();
		fondo = new Image("Images/fondo2.png");
		piso = new Suelo();
		plataforma = new LinkedList<Suelo>();
		timer = System.nanoTime() / 1000000;		// Hora del sistema en milisegundos
		plataforma.add(piso);
		finPartida = false;
	}

	@Override
	public void render(GameContainer gc, StateBasedGame sbg, Graphics g) throws SlickException {
		g.drawImage(fondo, 0, 0);

		/* Se dibujan los tubos superiores e inferiores y se redimensionan
		 * Parámetros: 
		 * 		Imagen del tubo
		 *		Coordenadas X e Y donde se dibujan en el escenario. El inferior se dibuja con una distancia de 5 pixeles por defecto
		 *		Coordenadas X e Y de la esquina inferior-derecha de la imagen de los tubos
		 *		Coordenadas X e Y donde se dibujan los rectángulos de colisión de los tubos en el escenario
		 *		Coordenadas X e Y de la esquina inferior-derecha de los rectángulos de colisión de los tubos
		*/
		for(TuboNoche t : tubos) {
			g.drawImage(t.getImagenArriba(), t.getX(), 0, t.getX() + 52, t.getY(), 0, 322 - t.colisiones.get(0).getMaxY(), 57, 322);
			g.drawImage(t.getImagenAbajo(), t.getX() + 5, t.getY() + t.getAltura(), t.getX() + 52, 720, 0, 0, 52, 736 - t.getAltura() - t.getY());
		}
		
		// Rotación y dibujo del pájaro
		g.rotate(pajaroRojo.getX(), pajaroRojo.getY(), pajaroRojo.getRotacion());
		g.drawAnimation(pajaroRojo.getPajaroAnimacion(), pajaroRojo.getX() - 18, pajaroRojo.getY() - 12);
		g.rotate(pajaroRojo.getX(), pajaroRojo.getY(), -pajaroRojo.getRotacion());

		// Dibujo del suelo, que se va pintando poco a poco
		for(Suelo s : plataforma)
			g.drawImage(piso.getImagen(), s.getX(), s.getY());
		
		// Entramos al último estado del juego si el pájaro se choca contra un tubo o el suelo (Final)
		if(finPartida) 
			sbg.enterState(4);
	}

	@Override
	public void update(GameContainer gc, StateBasedGame sbg, int delta) throws SlickException {
		Input input = gc.getInput();
		pajaroRojo.volar(input);
		
		for(TuboNoche t : tubos) 
			t.generarTubos();

		// Generación de tubos cada 1400 milisegundos
		if((System.nanoTime() / 1000000) - timer > 1400) {
			tubos.add(new TuboNoche());
			timer = System.nanoTime() / 1000000;
		}
		
		// Generación del suelo si se sale de la pantalla
		if(plataforma.size() < 2 && plataforma.get(0).getX() < 0)
			plataforma.add(new Suelo());
		
		// Comprobación de si el pájaro se ha chocado contra el suelo
		for(Suelo s : plataforma) 
			if (s.getColision().intersects(pajaroRojo.getColision())) 
				finPartida = true;
			else
				s.reemplazarSuelo();

		// Comprobación de si el pájaro se ha chocado contra alguno de los dos tubos
		for(TuboNoche t : tubos) 
			if (t.colisiones.get(0).intersects(pajaroRojo.getColision()) || t.colisiones.get(1).intersects(pajaroRojo.getColision()))
				finPartida = true;
		
		// Patrón estrategia. Se alternan los comportamientos cada vez que el pájaro atraviesa un tubo
		// Cada 20 puntos (2 tubos superados) se repite comportamiento
		if(PartidaNoche.puntuacion % 20 == 0) 
			pajaroRojo.setSalto(new SaltoNormal());
		else 
			pajaroRojo.setSalto(new SaltoMortal());
	}

	@Override
	public int getID() {
		return 3;
	}
}

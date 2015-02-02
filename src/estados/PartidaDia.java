package estados;

import java.util.LinkedList;
import java.util.List;
import objetos.*;
import org.newdawn.slick.*;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

import patrones.SaltoNormal;
import patrones.I_Pajaro;

public class PartidaDia extends BasicGameState {
	private I_Pajaro creadorPajaros;
	private Pajaro pajaroAmarillo;
	private Image fondo;
	private Suelo piso;
	private long timer;
	public static List<Tubo> tubos;
	public static List<Suelo> plataforma;
	public static int puntuacion;
	public static boolean finPartida;

	public PartidaDia(int state) {}

	@Override
	public void init(GameContainer gc, StateBasedGame sbg) throws SlickException {
		creadorPajaros = new PajaroDia();
		pajaroAmarillo = creadorPajaros.crearPajaro();
		pajaroAmarillo.setSalto(new SaltoNormal());
		tubos = new LinkedList<>();
		piso = new Suelo();
		plataforma = new LinkedList<Suelo>();
		timer = System.nanoTime() / 1000000;		// Hora del sistema en milisegundos
		fondo = new Image("Images/fondo.png");
		plataforma.add(piso);
		finPartida = false;
		puntuacion = 0;
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
		for(Tubo t : tubos) {		
			g.drawImage(t.getImagenArriba(), t.getX(), 0, t.getX() + 52, t.getY(), 0, 322 - t.colisiones.get(0).getMaxY(), 57, 322);
			g.drawImage(t.getImagenAbajo(), t.getX() + 5, t.getY() + t.getAltura(), t.getX() + 52, 720, 0, 0, 52, 736 - t.getAltura() - t.getY());
		}

		// Rotación y dibujo del pájaro
		g.rotate(pajaroAmarillo.getX(), pajaroAmarillo.getY(), pajaroAmarillo.getRotacion());
		g.drawAnimation(pajaroAmarillo.getPajaroAnimacion(), pajaroAmarillo.getX() - 18, pajaroAmarillo.getY() - 12);
		g.rotate(pajaroAmarillo.getX(), pajaroAmarillo.getY(), -pajaroAmarillo.getRotacion());

		// Dibujo del suelo, que se va pintando poco a poco
		for(Suelo s : plataforma) 
			g.drawImage(piso.getImagen(), s.getX(), s.getY());
		
		// Entramos al último estado del juego si el pájaro se choca contra un tubo o el suelo (Final)
		if(finPartida) 
			sbg.enterState(4);
	}

	@Override
	public void update(GameContainer gc, StateBasedGame sbg, int tiempo) throws SlickException {
		Input input = gc.getInput();
		pajaroAmarillo.volar(input);
		
		for(Tubo t : tubos)
			t.generarTubos();

		// Generación de tubos cada 1400 milisegundos
		if((System.nanoTime() / 1000000) - timer > 1400) {
			tubos.add(new Tubo());
			timer = System.nanoTime() / 1000000;
		}
		
		// Generación del suelo si se sale de la pantalla
		if(plataforma.size() < 2 && plataforma.get(0).getX() < 0)
			plataforma.add(new Suelo());
		
		// Comprobación de si el pájaro se ha chocado contra el suelo
		for(Suelo s : plataforma) 
			if (s.getColision().intersects(pajaroAmarillo.getColision())) 
				finPartida = true;
			else
				s.reemplazarSuelo();

		// Comprobación de si el pájaro se ha chocado contra alguno de los dos tubos
		for(Tubo t : tubos) 
			if (t.colisiones.get(0).intersects(pajaroAmarillo.getColision()) || t.colisiones.get(1).intersects(pajaroAmarillo.getColision()))
				finPartida = true;
	}

	@Override
	public int getID() {
		return 2;
	}
}
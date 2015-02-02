package patrones;

public class SaltoMortal implements I_Salto {
	private float salto;

	@Override
	public float asignarSalto() {
		setSalto(15.5f);
		return getSalto();
	}

	public float getSalto() {
		return salto;
	}

	public void setSalto(float s) {
		salto = s;
	}
}

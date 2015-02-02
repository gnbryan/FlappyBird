package patrones;

public class SaltoNormal implements I_Salto {
	private float salto;
	
	@Override
	public float asignarSalto() {
		setSalto(1.5f);
		return getSalto();
	}

	public float getSalto() {
		return salto;
	}

	public void setSalto(float s) {
		salto = s;
	}

}

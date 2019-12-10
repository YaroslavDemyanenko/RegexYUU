package validador;

public class RegexPropio {

	private final char[] letras = "abcdefghijklmnopqrstuvwxyz".toCharArray();
	private final char[] numeros = "0123456789".toCharArray();
	private final char[] espacios = " 	\n".toCharArray();
	private boolean[] objetivos = { false, false, false };
	private final char[] reglasChar = { 'l', 'n', 's' };

	// ORDEN: L N S

	private void sacarObjetivos(String reglas) {
		for (int x = 0; x < objetivos.length; x++) {
			objetivos[x] = false;
		}
		if (reglas.contains("&l")) {
			objetivos[0] = true;
		}
		if (reglas.contains("&n")) {
			objetivos[1] = true;
		}
		if (reglas.contains("&s")) {
			objetivos[2] = true;
		}
	}

	public boolean match(String cadena, String regex) {
		sacarObjetivos(regex);
		boolean[] validaciones = new boolean[objetivos.length];
		for (int x = 0; x < validaciones.length; x++) {
			validaciones[x] = false;
		}
		for (int x = 0; x < objetivos.length; x++) {
			switch (reglasChar[x]) {
			case 'l':
				validaciones[x] = letrasL(cadena);
				break;
			case 'n':
				validaciones[x] = numerosN(cadena);
				break;
			case 's':
				validaciones[x] = espaciosS(cadena);
				break;
			default:
				validaciones[x] = false;
				break;
			}
		}
		for (int i = 0; i < objetivos.length; i++) {
			if (!(objetivos[i] == validaciones[i])) {
				return false;
			}
		}
		return true;
	}

	private boolean espaciosS(String cadena) {
		for (char espacio : espacios) {
			if (cadena.contains("" + espacio)) {
				return true;
			}
		}
		return false;
	}

	private boolean letrasL(String cadena) {
		for (char letr : letras) {
			if (cadena.contains("" + letr)) {
				return true;
			}
		}
		return false;
	}

	public boolean numerosN(String cadena) {
		for (char num : numeros) {
			if (cadena.contains("" + num)) {
				return true;
			}
		}
		return false;
	}
	
	
	//[1] 	 split("[")    &n[123][13]  &n,
	
	//123],13]  split("]")  123,13,""

	
	//"1"    "asdasd" "&l[d]
	
	public boolean charEspecificos(String cadena,char[] charespecificos) {
		
	
		return false;
	}

}

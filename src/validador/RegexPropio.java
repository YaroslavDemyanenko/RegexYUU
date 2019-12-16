package validador;

import java.util.ArrayList;

public class RegexPropio {

	private final char[] letras = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ".toCharArray();
	private final char[] numeros = "0123456789".toCharArray();
	private final char[] espacios = " 	\n".toCharArray();
	private ArrayList<String[]> reglasMetidas = null;

	public boolean match(String cadena, String regex) {
		reglasMetidas = sacarReglas(regex);

		boolean[] validaciones = new boolean[reglasMetidas.size()];
		for (int x = 0; x < validaciones.length; x++) {
			validaciones[x] = false;
		}
		try {
			int posicionEnString = 0;
			int longitudRegla = Integer.valueOf(reglasMetidas.get(0)[1].substring(2, 3));
			for (int i = 0; i < reglasMetidas.size(); i++) {
				switch (reglasMetidas.get(i)[0]) {
				case "&l":
					validaciones[i] = validarCharacteres(cadena.substring(posicionEnString, longitudRegla), reglasMetidas.get(i)[1], letras);
					break;
				case "&n":
					validaciones[i] = validarCharacteres(cadena.substring(posicionEnString, longitudRegla), reglasMetidas.get(i)[1], numeros);
					break;
				case "&s":
					validaciones[i] = validarCharacteres(cadena.substring(posicionEnString, longitudRegla), reglasMetidas.get(i)[1], espacios);
					break;
				default:
					validaciones[i] = validarCharacteres(cadena.substring(posicionEnString, longitudRegla), reglasMetidas.get(i)[1], reglasMetidas.get(i)[0].toCharArray());
					break;
				}
				if (i+1 != reglasMetidas.size()) {
					posicionEnString = longitudRegla;
					int aux = Integer.valueOf(reglasMetidas.get(i + 1)[1].substring(2, 3));
					if (aux == 0) {
						aux++;
					}
					longitudRegla = posicionEnString + aux;
				}
			}
		} catch (ArrayIndexOutOfBoundsException e) {
			return false;
		}

		for (int i = 0; i < validaciones.length; i++) {
			if (validaciones[i] == false) {
				return false;
			}
		}
		return true;
	}

	private boolean validarCharacteres(String cadena, String taman, char[] tipo) {
		boolean validacion = false;
		int min = Integer.valueOf(taman.substring(0, 1));
		int max = Integer.valueOf(taman.substring(2, 3));
		if (max == 0) {
			max = Integer.MAX_VALUE;
		}

		if (cadena.length() >= min && cadena.length() <= max) {
			for (char caracter : cadena.toCharArray()) {
				for (char esp : tipo) {
					if (caracter == esp) {
						validacion = true;
						break;
					}
				}
				if (!validacion) {
					return false;
				} else {
					validacion = false;
				}
			}
			return true;

		} else {
			return false;
		}
	}

	public ArrayList<String[]> sacarReglas(String cadena) {
		String[] primerSplit = { cadena };
		ArrayList<String[]> reglas = new ArrayList<String[]>();
		boolean primerSplitHecho = false;
		if (cadena.contains("[")) {
			primerSplit = cadena.split("\\[");
			primerSplitHecho = true;
		}
		if (cadena.contains("]") && primerSplitHecho) {
			for (String cadenaSpliteada : primerSplit) {
				if (cadenaSpliteada.contains("]")) {
					String[] segundoSplit = cadenaSpliteada.split("\\]");
					if (segundoSplit.length == 1) {
						reglas.add(new String[] { segundoSplit[0], "1-1" });
					} else if (!segundoSplit[1].equals("")) {
						if (segundoSplit[1].contains("{")) {
							String[] cadenaSpliteada3 = segundoSplit[1].split("\\{");
							if (cadenaSpliteada3[1].contains(",")) {
								String[] cadenaSpliteada4 = cadenaSpliteada3[1].split(",");
								if (cadenaSpliteada4[0].equals("")) {
									reglas.add(new String[] { segundoSplit[0], "0-" + cadenaSpliteada4[1].substring(0, cadenaSpliteada4[1].length() - 1) });
								} else {
									if (cadenaSpliteada4[1].equals("}")) {
										reglas.add(new String[] { segundoSplit[0], cadenaSpliteada4[0] + "-0" });
									} else {
										reglas.add(new String[] { segundoSplit[0], cadenaSpliteada4[0] + "-" + cadenaSpliteada4[1].substring(0, cadenaSpliteada4[1].length() - 1) });
									}
								}
							} else {
								reglas.add(new String[] { segundoSplit[0], cadenaSpliteada3[1].substring(0, cadenaSpliteada3[1].length() - 1) + "-" + cadenaSpliteada3[1].substring(0, cadenaSpliteada3[1].length() - 1) });
							}
						} else {
							reglas.add(new String[] { segundoSplit[0], "1-1" });
						}
					}
				} else if (!cadenaSpliteada.equals("")) {
					if (!cadenaSpliteada.equals("")) {
						reglas.add(new String[] { cadenaSpliteada, "1-1" });
					}
				}
			}
		}
		return reglas;
	}

}

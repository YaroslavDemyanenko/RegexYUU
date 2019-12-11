package validador;

import java.util.ArrayList;

public class RegexPropio {

	private final char[] letras = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ".toCharArray();
	private final char[] numeros = "0123456789".toCharArray();
	private final char[] espacios = " 	\n".toCharArray();
	private ArrayList<String> reglasMetidas = null;

	// ORDEN: L N S	

	public boolean match(String cadena, String regex) {
		reglasMetidas = sacarReglas(regex);

		boolean[] validaciones = new boolean[reglasMetidas.size()];
		for (int x = 0; x < validaciones.length; x++) {
			validaciones[x] = false;
		}

		if (cadena.length() == reglasMetidas.size()) {
			for (int i = 0; i < reglasMetidas.size(); i++) {
				switch (reglasMetidas.get(i)) {
				case "&l":
					validaciones[i] = letrasL(cadena);
					break;
				case "&n":
					validaciones[i] = numerosN(cadena);
					break;
				case "&s":
					validaciones[i] = espaciosS(cadena);
					break;
				default:
					validaciones[i]=caracteresEspecificos(cadena, i);
					break;
				}
			}
		} else {
			return false;
		}

		for (int i = 0; i < validaciones.length; i++) {
			if (validaciones[i] == false) {
				return false;
			}
		}
		return true;
	}

	private boolean caracteresEspecificos(String cadena, int i) {
		for (int j = 0; j < reglasMetidas.get(i).length(); j++) {
			if (reglasMetidas.get(i).charAt(j) == cadena.charAt(i)) {
				return true;
			}
		}
		return false;
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

	//a-z A-Z

	
	
	public ArrayList<String[]> sacarReglas2(String cadena) {
		//						"[&l][123]{1,2}[13]"
		//SPLIT("[") 			"" , "&l]" , "123]{1,2}" , "13]"
		//SPLIT("]")			"" ERROR
		//						"&l"
		//						"123","{1,2}"
		//						"13",""
		
		
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
					
					
					for (String cadenaSpliteada2 : segundoSplit) {
						if (!cadenaSpliteada2.equals("")) {
							if (cadenaSpliteada2.contains("{")) {
								
								//						"123","{1,2}"
								String[] cadenaSpliteada3=cadenaSpliteada2.split("{");
								//{1,2} {1} {1,} {,3}
								//  "1,2}" "1}" "1,}" ",2}"
								if(cadenaSpliteada3[1].contains(",")) {
									String[] cadenaSpliteada4=cadenaSpliteada3[1].split(",");
									//"1","2}"  "1","}"  "","2}"
									
									
									
								}else {
									reglas.add(new String[] {cadenaSpliteada2,cadenaSpliteada3[1].substring(0, 1)});
								}
								
								
								//reglas.add(new String[]cadenaSpliteada2.split(regex))
							}else {
								reglas.add(new String[]{cadenaSpliteada2,"{1}"});	
							}													
						}
					}
				} else {
					if (!cadenaSpliteada.equals("")) {
						reglas.add(new String[] {cadenaSpliteada,"{1}"});
					}
				}
			}
		}
		return reglas;
	}
	
	
	
	public ArrayList<String> sacarReglas(String cadena) {
		//"[&l][123]{1,2}[13]"
		//SPLIT("[") 			"" , "&l]" , "123]{1,2}" , "13]"
		//SPLIT("]")			"" ERROR
		//						"&l"
		//						"123","{1,2}"
		//						"13",""
		
		
		String[] primerSplit = { cadena };
		ArrayList<String> reglas = new ArrayList<String>();
		boolean primerSplitHecho = false;
		if (cadena.contains("[")) {
			primerSplit = cadena.split("\\[");
			primerSplitHecho = true;
		}
		if (cadena.contains("]") && primerSplitHecho) {
			for (String cadenaSpliteada : primerSplit) {
				if (cadenaSpliteada.contains("]")) {
					String[] segundoSplit = cadenaSpliteada.split("\\]");
					for (String cadenaSpliteada2 : segundoSplit) {
						if (!cadenaSpliteada2.equals("")) {
							reglas.add(cadenaSpliteada2);
						}
					}
				} else {
					if (!cadenaSpliteada.equals("")) {
						reglas.add(cadenaSpliteada);
					}
				}
			}
		}
		return reglas;
	}

}

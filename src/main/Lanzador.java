package main;

import validador.RegexPropio;

public class Lanzador {

	public static void main(String[] args) {
		RegexPropio reg=new RegexPropio();
		
		
		System.out.println(reg.match("yyyyy23u", "[y]{5}[&n]{1,2}[&l]{1,}"));
		
		
		
		System.out.println(reg.match("y27u", "[&l][123][13][&l]"));
		System.out.println(reg.match("127u", "[&l][123][13][&l]"));
		System.out.println(reg.match("y278", "[&l][123][13][&l]"));
		System.out.println(reg.match("Y3u7", "[&l][&n][&l][&n]"));
		
	}

}

package main;

import validador.RegexPropio;

public class Lanzador {

	public static void main(String[] args) {
		RegexPropio reg=new RegexPropio();
		
		
		System.out.println(reg.match("1234bsc", "&l&n&s"));
		System.out.println(reg.match("1234bsc ", "&l&n&s"));
		System.out.println(reg.match("1234bsc	", "&l&n&s"));
		System.out.println(reg.match("1234bsc\n", "&l&n&s"));
		
		System.out.println();
		
		
		//[1] 	 split("[")    &n[123][13]  &n,
		
		//123],13]  split("]")  123,13,""

		System.out.println(reg.match("cxzc112323", "[1]"));
		
		System.out.println(reg.match("1234bsc", "&l&n")); //true
		System.out.println(reg.match("1234", "&l&n"));	
		System.out.println(reg.match("bsc", "&l&n"));
		System.out.println("");
		System.out.println(reg.match("bsc1234", "&n"));
		System.out.println(reg.match("1234", "&n")); //true
		System.out.println(reg.match("bsc", "&n"));
		System.out.println("");
		System.out.println(reg.match("1234bsc", "&l"));
		System.out.println(reg.match("1234", "&l"));
		System.out.println(reg.match("bsc", "&l")); //true
	}

}

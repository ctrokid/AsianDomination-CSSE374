package unit;

import utils.SignatureParser;

public class ArgumentParserClass {

	public static void main(String[] args) {
		System.out.println(SignatureParser.getParams("([Ljava/lang/String;ILjava/lang/String;Ljava/lang/Object;)[I").toString());
	}

}

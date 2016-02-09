package utils;

import java.util.ArrayList;
import java.util.HashMap;
import org.objectweb.asm.Type;

/**
 * SignatureParsserr The parser current supports all the non collection object
 * types, int, double, float, and those type of arrays
 * 
 * @author yangr
 *
 */

public class SignatureParser {

	private static ArrayList<String> result;
	private static HashMap<Character, String> primitiveTypes = new HashMap<Character, String>();
	private static Type[] params;

	static {
		primitiveTypes.put('I', "int");
		primitiveTypes.put('D', "double");
		primitiveTypes.put('F', "float");
		primitiveTypes.put('J', "long");
		primitiveTypes.put('B', "boolean");
		primitiveTypes.put('C', "collection");
		primitiveTypes.put('Z', "boolean");
		primitiveTypes.put('S', "short");
	}

	/**
	 * This parse the parameters of signatures
	 * 
	 * @param signatures
	 * @return ArrayList<String> parameters
	 */
	public static ArrayList<String> getParams(String signatures, boolean parseSlashes) {
		result = new ArrayList<String>();
		if (signatures.contains("(T"))
			return result;
		
		params = Type.getArgumentTypes(signatures);
//		System.err.println(params);
		if (params == null)
			return result;

		for (Type param : params) {
			String p = parseParam(param.toString());

			if (parseSlashes)
				p = AsmClassUtils.GetStringStrippedByCharacter(p, '/');
			result.add(p);
		}
		return result;
	}

	private static String parseParam(String param) {
		if (primitiveTypes.containsKey(param.charAt(0))) {
			return primitiveTypes.get(param.charAt(0));
		} else if (param.charAt(0) == '[') {
			return parseParam(param.substring(1)) + "[]";
		} else if (param.charAt(0) == 'L') {
			return param.substring(1, param.length() - 1);
		} else {
			System.err.println("SignatureParser cannot understand type: " + param);
			return param;
		}
	}

}

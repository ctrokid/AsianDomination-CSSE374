package utils;
import java.util.ArrayList;
import java.util.HashMap;

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
	private static boolean lastIsArrayObject;

	static {
		primitiveTypes.put('I', "int");
		primitiveTypes.put('D', "double");
		primitiveTypes.put('F', "float");
		primitiveTypes.put('B',"boolean");
		lastIsArrayObject = false;
	}

	public static ArrayList<String> getParams(String signature) {
		result = new ArrayList<String>();
		ArrayList<String> temp = getParamsHelper(signature);
		return temp;
	}

	private static ArrayList<String> getParamsHelper(String signatures) {
		String temp = signatures.substring(signatures.indexOf('(') + 1,
				signatures.indexOf(')'));
		parseHelperHelper(temp);
		return result;
	}

	private static boolean parseHelperHelper(String temp) {
		while (!temp.isEmpty()) {
			char control = temp.charAt(0);
			// System.out.println(control);
			if (parsePrimitiveTypes(control, temp)) {
				temp = temp.substring(1);
			} else if (parseObject(control, temp)) {
				temp = temp.substring(temp.indexOf(';') + 1);
			} else if (parseArray(control, temp)) {
				if (lastIsArrayObject) {
					temp = temp.substring(temp.indexOf(';') + 1);
					lastIsArrayObject = false;
				} else {
					temp = temp.substring(2);
				}
			} else {
				System.out.println(temp + " there is an error");
				return false;
			}
		}
		return true;
	}

	private static boolean parsePrimitiveTypes(char type, String sig) {
		if (primitiveTypes.containsKey(type)) {
			result.add(primitiveTypes.get(type));
			return true;
		}
		return false;
	}

	private static boolean parseObject(char type, String sig) {
		if (type == 'L') {
			result.add(sig.substring(1, sig.indexOf(';')));
			return true;
		}
		return false;
	}

	private static boolean parseArray(char type, String sig) {
		if (type == '[') {
			if (primitiveTypes.containsKey(sig.charAt(1))) {
				result.add(primitiveTypes.get(sig.charAt(1)) + " []");
				return true;
			}
			if (parseHelperHelper(sig.substring(1))) {
				String current = result.get(result.size() - 1);
				result.set(result.size() - 1, current + " []");
				lastIsArrayObject = true;
				return true;
			}
		}
		return false;
	}

}
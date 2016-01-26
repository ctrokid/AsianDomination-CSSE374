package utils;

import org.objectweb.asm.Opcodes;
import org.objectweb.asm.Type;

public class AsmClassUtils {
	public static String GetAccessLevel(int access) {
		String level = "";
		if ((access & Opcodes.ACC_PUBLIC) != 0) {
			level = "+";
		} else if ((access & Opcodes.ACC_PROTECTED) != 0) {
			level = "#";
		} else if ((access & Opcodes.ACC_PRIVATE) != 0) {
			level = "-";
		} else {
			level = "";
		}

		return level;
	}
	
	public static String GetReturnType(String desc) {
		String returnType = Type.getReturnType(desc).getClassName();
		
		return returnType.replace(".", "/");
	}

	public static String GetArguments(String desc, boolean parseSlashes) {
		StringBuilder sb = new StringBuilder();
		Type[] args = Type.getArgumentTypes(desc);
		
		for (int i = 0; i < args.length; i++) {
			String arg = args[i].getClassName();
			arg = arg.replace('.', '/');
			
			if (parseSlashes)
				arg = arg.substring(arg.lastIndexOf("/") + 1);
			
			sb.append(arg);
			if (i != args.length - 1) {
				sb.append(", ");
			}
		}

		return sb.toString();
	}
	
	public static String GetStringStrippedByCharacter(String str, char character) {
		return str.substring(str.lastIndexOf(character) + 1);
	}

	public static String parseSignature(String signature, boolean parseSlashes) {
		StringBuilder sb = new StringBuilder();
		String[] tokens = signature.split("<");

		// FIXME: Collection inside of a collection is not working right now.
		
		for (int i = 1; i < tokens.length; i++) {
			String str = "(" + tokens[i].substring(0, tokens[i].length() - 2) + ")";
			// FIXME : these weird symbols are sometimes passed in from ClassFieldVisitor
			if (str.contains("$") || str.contains("*") || str.contains("TK;TV"))
				continue;
			String params = SignatureParser.getParams(str, parseSlashes).toString();
			String toAppend = params.substring(1, params.length() - 1);
			
			sb.append(toAppend);
			
			if (i != tokens.length - 1)
				sb.append(',');
		}

		return sb.toString().replaceAll("\\$", ".");
	}
}

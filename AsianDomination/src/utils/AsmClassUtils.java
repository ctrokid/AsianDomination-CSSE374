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
		if (desc.equals(""))
			return desc;
		
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

		for (int i = 1; i < tokens.length; i++) {
			// check if it's a hash map
			String[] split = tokens[i].split(";");
			if (split.length > 2 || (split.length == 2 && !split[0].contains(">"))) {
				// hash map logic
				for (int j = 0; j < split.length; j++) {
					if (split[j].charAt(0) == 'L') {
						split[j] = split[j].replaceFirst("L", "");
					}
					
					if (!split[j].contains(">")) {
						String toAppend = split[j].replace("$", "/");
						
						if (parseSlashes)
							toAppend = GetStringStrippedByCharacter(toAppend, '/');
						
						sb.append(toAppend);
						
						if (j != split.length - 1 && !split[j + 1].contains(">"))
							sb.append(',');
						else if (i != tokens.length -1)
							sb.append(',');
					}
				}
			} else {
				String str = tokens[i];
				
				if (tokens[i].charAt(0) == 'L') {
					str = str.replaceFirst("L", "");
				}
				
				String toAppend = str.replace("$", "/");
				
				if (parseSlashes)
					toAppend = GetStringStrippedByCharacter(toAppend, '/');
				
				sb.append(toAppend);
				
				if (i != tokens.length - 1)
					sb.append(',');
			}			
		}
		
		return sb.toString();
	}
}

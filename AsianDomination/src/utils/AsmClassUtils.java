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

	public static String GetArguments(String desc) {
		StringBuilder sb = new StringBuilder();
		Type[] args = Type.getArgumentTypes(desc);
		
		for (int i = 0; i < args.length; i++) {
			String arg = args[i].getClassName();
			arg = arg.substring(arg.lastIndexOf(".") + 1);
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

	public static String parseSignature(String signature) {
		StringBuilder sb = new StringBuilder();
		String[] tokens = signature.split(";");

		sb.append("\\<");
		
		// FIXME: Collection inside of a collection is not working right now.
		int size = 1;
		if (tokens.length >= 3)
			size = 2;
		
		for (int i = 0; i < size; i++) {
			sb.append(GetStringStrippedByCharacter(tokens[i], '/'));
			
			if (i != size - 1)
				sb.append(',');
		}
		sb.append("\\>");
		
		return sb.toString().replaceAll("\\$", ".");
	}
}

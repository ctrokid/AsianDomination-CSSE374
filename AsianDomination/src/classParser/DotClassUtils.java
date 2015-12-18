package classParser;

import org.objectweb.asm.Opcodes;
import org.objectweb.asm.Type;

public class DotClassUtils {
	public static String GetAccessLevel(int access) {
		String level = "";
		if ((access & Opcodes.ACC_PUBLIC) != 0) {
			level = "public";
		} else if ((access & Opcodes.ACC_PROTECTED) != 0) {
			level = "protected";
		} else if ((access & Opcodes.ACC_PRIVATE) != 0) {
			level = "private";
		} else {
			level = "default";
		}

		return level;
	}
	
	public static String GetReturnType(String desc) {
		String returnType = Type.getReturnType(desc).getClassName();

		return returnType;
	}

	public static String GetArguments(String desc) {
		StringBuilder sb = new StringBuilder();
		Type[] args = Type.getArgumentTypes(desc);
		
		for (int i = 0; i < args.length; i++) {
			String arg = args[i].getClassName();
			sb.append(arg);
			
			if (i != args.length - 1) {
				sb.append(", ");
			}
		}
		
		return sb.toString();
	}
}

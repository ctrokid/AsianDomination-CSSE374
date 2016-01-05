package classParser;
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
		returnType = returnType.substring(returnType.lastIndexOf('.') + 1);
		
		return returnType.toLowerCase();
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
}

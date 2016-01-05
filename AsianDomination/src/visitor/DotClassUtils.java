package visitor;

public class DotClassUtils {
	static enum RelationshipType {
		INHERITANCE,
		IMPLEMENTATION
	}
	
	public static String CreateRelationshipEdge(RelationshipType type) {
		StringBuilder sb = new StringBuilder();
		
		sb.append("edge [\n\t");
		
		switch (type) {
			case INHERITANCE:
				sb.append("arrowhead = \"empty\"\n");
				break;
			case IMPLEMENTATION:
				sb.append("arrowhead = \"empty\"\n\t");
				sb.append("style = \"dashed\"\n");
			default:
				break;
		}
		
		sb.append("]\n\n");
		
		return sb.toString();
	}
	
	public static String CreateFontNode(String fontName, String fontSize) {
		StringBuilder sb = new StringBuilder();
		
		sb.append("node [\n\t");
		sb.append("fontname = \"" + fontName + "\"\n\t");
		sb.append("fontsize = " + fontSize + "\n\t");
		sb.append("shape = \"record\"\n");
		sb.append("]\n\n");
		
		return sb.toString();
	}
}

package utils;

public class DotClassUtils {
	public static enum RelationshipType {
		INHERITANCE,
		IMPLEMENTATION,
		ASSOCIATION,
		USES
	}
	
	public static String CreateRelationshipEdge(RelationshipType type) {
		String edge = "";
		
		switch (type) {
			case INHERITANCE:
				edge = "[arrowhead = \"empty\",style = \"solid\"];\n";
				break;
			case IMPLEMENTATION:
				edge = "[arrowhead = \"empty\",style = \"dashed\"];\n";
				break;
			case ASSOCIATION:
				edge = "[arrowhead = \"vee\",style = \"solid\"];\n";
				break;
			case USES:
				edge = "[arrowhead = \"vee\",style = \"dashed\"];\n";
				break;
			default:
				break;
		}
		
		return edge;
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

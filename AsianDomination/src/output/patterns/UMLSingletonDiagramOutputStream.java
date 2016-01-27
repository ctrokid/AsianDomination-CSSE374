package output.patterns;

import java.util.HashMap;
import java.util.HashSet;

import api.ITargetClass;
import pattern.decoration.SingletonDecorator;
import utils.AsmClassUtils;
import utils.DotClassUtils;
import utils.DotClassUtils.RelationshipType;
import visitor.ITraverser;
import visitor.IVisitor;
import visitor.VisitType;

public class UMLSingletonDiagramOutputStream extends AbstractPatternOutputStream {

	public UMLSingletonDiagramOutputStream(String asmOutputPath, IVisitor visitor) {
		super(asmOutputPath, visitor);
	}

	@Override
	protected void setupPreVisitTargetClass() {
		super.addVisit(VisitType.PreVisit, SingletonDecorator.class, (ITraverser t) -> {
			ITargetClass c = (ITargetClass) t;
			StringBuilder sb = new StringBuilder();
			String className = AsmClassUtils.GetStringStrippedByCharacter(c.getClassName(), '/');
			String singletonDecor = "\\n\\<\\<Singleton\\>\\>";
			String colorChange = "color = blue,";

			sb.append(className + "[\n\t");
			sb.append(colorChange + "label = \"{" + className + singletonDecor + "|");
			write(sb.toString());

			
			
			//TODO: refactor this part of code
			HashMap<RelationshipType, HashSet<String>> relationshipEdges = c.getRelationEdges();
			for (RelationshipType type : RelationshipType.values()) {

				for (String edge : relationshipEdges.get(type)) {

					if (_projectModel.getTargetClassByName(edge) == null)
						continue;

					String edgeToWrite = AsmClassUtils.GetStringStrippedByCharacter(c.getClassName(), '/') + " -> "
							+ AsmClassUtils.GetStringStrippedByCharacter(edge, '/')
							+ DotClassUtils.CreateRelationshipEdge(type);

					if (type.equals(RelationshipType.USES)) {
						if (!c.containsRelationship(RelationshipType.ASSOCIATION, edge)) {
							_relationships.add(edgeToWrite);
						}
					} else {
						_relationships.add(edgeToWrite);
					}
				}
			}
		});
	}
}

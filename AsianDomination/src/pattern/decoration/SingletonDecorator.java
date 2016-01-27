package pattern.decoration;

import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;

import api.IClassDeclaration;
import api.IClassField;
import api.IClassMethod;
import api.ITargetClass;
import impl.ClassDeclaration;
import pattern.detection.PATTERN_TYPE;
import utils.DotClassUtils.RelationshipType;
import visitor.IVisitor;

public class SingletonDecorator extends AbstractTargetClassDecorator {

	public SingletonDecorator(PATTERN_TYPE pattern, String _associatedClassName, ITargetClass _decoratedClass) {
		super(pattern, _associatedClassName, _decoratedClass);
		// TODO Auto-generated constructor stub
	}

	

}

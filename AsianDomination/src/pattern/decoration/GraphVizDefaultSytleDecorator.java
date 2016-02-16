package pattern.decoration;

import api.ITargetClass;
import pattern.detection.PATTERN_TYPE;

public class GraphVizDefaultSytleDecorator extends GraphVizStyleTargetClass {

	public GraphVizDefaultSytleDecorator(ITargetClass _decoratedClass) {
		super(PATTERN_TYPE.GRAPHVIZ_DEFAULT, "", _decoratedClass);
	}

}

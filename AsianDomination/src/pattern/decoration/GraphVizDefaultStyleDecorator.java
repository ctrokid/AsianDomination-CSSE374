package pattern.decoration;

import api.ITargetClass;
import pattern.detection.PATTERN_TYPE;

public class GraphVizDefaultStyleDecorator extends GraphVizStyleTargetClass {

	public GraphVizDefaultStyleDecorator(ITargetClass _decoratedClass) {
		super(PATTERN_TYPE.GRAPHVIZ_DEFAULT, "", _decoratedClass);
	}

}

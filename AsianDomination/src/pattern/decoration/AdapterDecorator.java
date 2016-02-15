package pattern.decoration;

import api.ITargetClass;
import pattern.detection.PATTERN_TYPE;

public class AdapterDecorator extends GraphVizStyleTargetClass {

	public AdapterDecorator(PATTERN_TYPE pattern, String _associatedClassName, ITargetClass _decoratedClass) {
		super(pattern, _associatedClassName, _decoratedClass);
		switch (pattern) {
		case ADAPTER_ADAPTER:
//			setPatternString("\\n\\<\\<adapter\\>\\>");
			graphVizStyle.addConfig("type", "adapter");
			break;
		case ADAPTER_ADAPTEE:
//			setPatternString("\\n\\<\\<adaptee\\>\\>");
			graphVizStyle.addConfig("type", "adaptee");
			break;
		case ADAPTER_TARGET:
//			setPatternString("\\n\\<\\<target\\>\\>");
			graphVizStyle.addConfig("type", "target");
			break;
		default:
			break;
		}
	}

}

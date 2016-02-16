package pattern.decoration;

import api.ITargetClass;
import pattern.detection.PATTERN_TYPE;

public class AdapterDecorator extends GraphVizStyleTargetClass {

	public AdapterDecorator(PATTERN_TYPE pattern, String _associatedClassName, ITargetClass _decoratedClass) {
		super(pattern, _associatedClassName, _decoratedClass);
		this.addConfig("style", "solid");
		this.addConfig("color", "red");
		switch (pattern) {
		case ADAPTER_ADAPTER:
			graphVizStyle.addConfig("type", "adapter");
			break;
		case ADAPTER_ADAPTEE:
			graphVizStyle.addConfig("type", "adaptee");
			break;
		case ADAPTER_TARGET:
			graphVizStyle.addConfig("type", "target");
			break;
		default:
			break;
		}
	}

}

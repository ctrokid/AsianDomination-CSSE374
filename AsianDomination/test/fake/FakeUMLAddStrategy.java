package fake;

import java.util.Properties;

import construction.UMLAddStrategy;

public class FakeUMLAddStrategy extends UMLAddStrategy {

	public FakeUMLAddStrategy(Properties props, String[] classes) {
		super(props);
		_params = classes;
	}

}

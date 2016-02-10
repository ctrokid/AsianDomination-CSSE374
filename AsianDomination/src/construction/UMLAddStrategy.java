package construction;

import java.util.Properties;

import api.IProjectModel;

public class UMLAddStrategy extends AbstractAddStrategy {
	
	public UMLAddStrategy(Properties props) {
		super(props);
	}
	
	@Override
	protected void loadConfig(Properties props) {
		String classes = props.getProperty("input-classes");
		if (classes != null) {
			_params = classes.substring(1, classes.length()-1).split(",");
		}
	}
	
	@Override
	public void buildModel(IProjectModel model) {
		for (String c : _params) {
			model.addClass(c.trim());
		}
	}

	

}

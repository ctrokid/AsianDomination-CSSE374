package construction;

public class UMLAddStrategy extends AbstractAddStrategy {

	@Override
	public void buildModel(String[] classes) {
		if (_projectModel == null) {
			return;
		}
		
		for (String clazz : classes) {
			_projectModel.addClass(clazz);
		}
	}

}

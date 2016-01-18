package construction;

public class SDAddStrategy extends AbstractAddStrategy {

	@Override
	public void buildModel(String[] params) {
		addClassesRecursively(params[0], params[1], params[2], 1, Integer.parseInt(params[3]));
	}
	
	private void addClassesRecursively(String className, String methodName, String params, int level, int sequenceLevel){
		
	}

}

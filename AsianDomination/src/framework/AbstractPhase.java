package framework;

import java.util.Properties;

import api.IProjectModel;

public abstract class AbstractPhase implements IPhase {

	public AbstractPhase(Properties props) {
		loadConfig(props);
	}
	
	protected abstract void loadConfig(Properties props);
	
	@Override
	public abstract void execute(IProjectModel model) ;

}

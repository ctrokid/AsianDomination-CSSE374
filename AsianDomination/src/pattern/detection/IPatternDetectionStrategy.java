package pattern.detection;

import api.IProjectModel;
import framework.IPhase;

public interface IPatternDetectionStrategy extends IPhase {
	public void detectPatterns(IProjectModel model);
}

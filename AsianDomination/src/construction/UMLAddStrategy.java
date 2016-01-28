package construction;

import java.util.List;

import pattern.detection.IPatternDetectionStrategy;

public class UMLAddStrategy extends AbstractAddStrategy {
	private List<IPatternDetectionStrategy> _detectors;
	
	public UMLAddStrategy(List<IPatternDetectionStrategy> detectors) {
		_detectors = detectors;
	}
	
	@Override
	public void buildModel(String[] classes) {
		if (_projectModel == null) {
			return;
		}
		
		for (String clazz : classes) {
			_projectModel.addClass(clazz);
		}
		
		if (_detectors == null)
			return;
		
		for (IPatternDetectionStrategy detector : _detectors) {
			detector.detectPatterns(_projectModel);
		}
	}

}

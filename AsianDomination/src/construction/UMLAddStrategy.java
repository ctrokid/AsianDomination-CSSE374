package construction;

import java.util.List;

import api.IProjectModel;
import api.ITargetClass;
import pattern.detection.IDetectionVisitor;
import pattern.detection.IPatternDetectionStrategy;

public class UMLAddStrategy extends AbstractAddStrategy {
	private List<IPatternDetectionStrategy> _detectors;
	private List<IDetectionVisitor> _detectVisitor;

	public UMLAddStrategy(List<IPatternDetectionStrategy> detectors, List<IDetectionVisitor> detectionVisitor,
			IProjectModel model) {
		super(model);
		_detectors = detectors;
		_detectVisitor = detectionVisitor;
		
		if (_detectVisitor == null)
			return;
		
		for(IDetectionVisitor d: _detectVisitor){
			d.setProjectModel(model);
		}
	}

	@Override
	public void buildModel(String[] classes) {
		if (_projectModel == null) {
			return;
		}

		for (String c : classes) {
			_projectModel.addClass(c);
			
			if (_detectVisitor == null)
				continue;
			
			ITargetClass clazz = _projectModel.getTargetClassByName(c);
			for(IDetectionVisitor d: _detectVisitor){
				clazz.accept(d.getVisitor());
			}
		}

		if (_detectors == null)
			return;

		for (IPatternDetectionStrategy detector : _detectors) {
			detector.detectPatterns(_projectModel);
		}
	}

}

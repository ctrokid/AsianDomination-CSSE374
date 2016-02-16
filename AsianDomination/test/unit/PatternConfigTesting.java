package unit;


import org.junit.Test;

import api.ITargetClass;
import impl.TargetClass;
import pattern.decoration.GraphVizDefaultSytleDecorator;
import pattern.decoration.GraphVizStyleTargetClass;
import pattern.decoration.PATTERN_CONFIG;
import pattern.detection.PATTERN_TYPE;

public class PatternConfigTesting {
	@Test
	public void test1() {
		ITargetClass cla = new TargetClass("Testing");
		GraphVizStyleTargetClass clazz = new GraphVizDefaultSytleDecorator(cla);
		PATTERN_CONFIG patt = PATTERN_CONFIG.getInstance();
		patt.setConfig(PATTERN_TYPE.COMPOSITE_COMPOSITE, clazz);
		System.out.println(clazz.getClassTypeWithCarrots());
	}
}

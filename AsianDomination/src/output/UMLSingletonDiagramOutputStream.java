package output;

import java.util.Collection;

import api.IClassField;
import api.IClassMethod;
import api.ITargetClass;
import utils.AsmClassUtils;
import visitor.ITraverser;
import visitor.VisitType;

public class UMLSingletonDiagramOutputStream extends UMLDiagramOutputStream {

	public UMLSingletonDiagramOutputStream(String asmOutputPath) {
		super(asmOutputPath);
	}
	
	@Override
	protected void setupPreVisitTargetClass() {
		super.addVisit(VisitType.PreVisit, ITargetClass.class, (ITraverser t) -> {
			ITargetClass c = (ITargetClass) t;
			StringBuilder sb = new StringBuilder();
			String className = AsmClassUtils.GetStringStrippedByCharacter(c.getClassName(), '/');
			String singletonDecor = "";
			String colorChange = "";
			
			if(isSingleton(c)){
				colorChange = "color = blue,";
				singletonDecor = "\\n\\<\\<Singleton\\>\\>";
			}
			
			sb.append(className + "[\n\t");
			sb.append(colorChange+"label = \"{" + className +singletonDecor+ "|");
			write(sb.toString());
		});
	}	

	private boolean isSingleton(ITargetClass t){
		String classType = t.getClassName();
		if(fieldContainsClassInstance(t, classType)){
			if(isConstructorPrivate(t)){
				if(returnsSelfInstance(t, classType)){
					return true;
				}
			}
		}
		return false;
	}
	
	private boolean fieldContainsClassInstance(ITargetClass t, String classType){
		Collection<IClassField> fields = t.getFields();
		for(IClassField current: fields) {
			if(current.getType().equals(classType)){
				return true;
			}
		}
		return false;
	}
	
	private boolean isConstructorPrivate(ITargetClass t){
		Collection<IClassMethod> methods = t.getMethods();
		for(IClassMethod current: methods){
			if(current.getMethodName().contains("<init>")&&(!String.valueOf(current.getAccessLevel()).equals("-"))){
				return false;
			}
		}
		return true;
	}
	
	private boolean returnsSelfInstance(ITargetClass t, String classType){
		Collection<IClassMethod> methods = t.getMethods();
		for(IClassMethod current: methods){
			if(current.getReturnType().equals(classType)){
				return true;
			}
		}
		return false;
	}


}

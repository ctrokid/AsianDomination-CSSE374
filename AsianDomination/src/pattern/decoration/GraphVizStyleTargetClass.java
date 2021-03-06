package pattern.decoration;

import java.util.Collection;

import api.IClassDeclaration;
import api.IClassField;
import api.IClassMethod;
import api.ITargetClass;
import impl.ClassField;
import pattern.detection.PATTERN_TYPE;
import utils.ClassStyle;
import visitor.IVisitor;

public abstract class GraphVizStyleTargetClass implements ITargetClass {
	protected PATTERN_TYPE pattern;
	protected String _associatedClassName;
	protected ITargetClass _decoratedClass;
	protected ClassStyle graphVizStyle;
	

	public GraphVizStyleTargetClass(PATTERN_TYPE pattern, String _associatedClassName,
			ITargetClass _decoratedClass) {
		this.pattern = pattern;
		this._associatedClassName = _associatedClassName;
		this._decoratedClass = _decoratedClass;
		this.graphVizStyle = new ClassStyle();
	}

	public String getAssociatedClassName() {
		return _associatedClassName;
	}
	
	public String getColor() {
		return graphVizStyle.getColor();
	}

	public PATTERN_TYPE getPatternType() {
		return pattern;
	}
	
	public String getClassTypeWithCarrots(){
		return graphVizStyle.getClassTypeWithCarrots();
	}
	
	public String getClassType(){
		return graphVizStyle.getClassType();
	}
	
	public String getStyle(){
		return graphVizStyle.toString();
	}

	public void addConfig(String config, String setting) {
		graphVizStyle.addConfig(config, setting);
	}
	
	public void setPattern(PATTERN_TYPE pattern){
		this.pattern = pattern;
	}

	@Override
	public String getClassName() {
		return _decoratedClass.getClassName();
	}

	@Override
	public Collection<IClassMethod> getMethods() {
		return _decoratedClass.getMethods();
	}

	@Override
	public Collection<IClassField> getFields() {
		return _decoratedClass.getFields();
	}

	@Override
	public void addClassMethod(IClassMethod classMethod) {
		_decoratedClass.addClassMethod(classMethod);

	}

	@Override
	public void addClassField(IClassField classField) {
		_decoratedClass.addClassField(classField);
	}

	@Override
	public IClassMethod getMethodByName(String methodName, String params) {
		return _decoratedClass.getMethodByName(methodName, params);
	}

	@Override
	public void setClassDeclaration(IClassDeclaration classDeclaration) {
		_decoratedClass.setClassDeclaration(classDeclaration);
	}

	@Override
	public IClassDeclaration getDeclaration() {
		return _decoratedClass.getDeclaration();
	}

	@Override
	public void accept(IVisitor v) {
		v.visit(this);
		for (IClassField field : getFields()) {
			field.accept(v);
		}

		v.postVisit(new ClassField(null, 0, null, null));

		for (IClassMethod method : getMethods()) {
			method.accept(v);
		}

		v.postVisit(this);
	}

}

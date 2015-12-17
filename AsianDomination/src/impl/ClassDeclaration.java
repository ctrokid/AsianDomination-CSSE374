package impl;

import java.util.Collection;

import api.IClassDeclaration;
import visitor.IVisitor;

public class ClassDeclaration implements IClassDeclaration {
	private String _name;
	private String _type;
	private String _superType;
	private Collection<String> _interfaces;
	
	public ClassDeclaration(String name, String type, String superType, Collection<String> interfaces) {
		_name = name;
		_type = type;
		_superType = superType;
		_interfaces = interfaces;
	}

	@Override
	public String getName() {
		return _name;
	}

	@Override
	public String getType() {
		return _type;
	}

	@Override
	public String getSuperType() {
		return _superType;
	}

	@Override
	public Collection<String> getInterfaces() {
		return _interfaces;
	}

	@Override
	public void accept(IVisitor v) {
		v.visit(this);
	}
}

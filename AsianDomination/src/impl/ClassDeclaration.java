package impl;

import api.IClassDeclaration;
import visitor.IVisitor;

public class ClassDeclaration implements IClassDeclaration {
	private String _name;
	private String _type;
	private String _superType;
	private String[] _interfaces;
	
	public ClassDeclaration(String name, String type, String superType, String[] interfaces) {
		_name = name;
		_type = type;
		_superType = superType;

		_interfaces = new String[interfaces.length];
		for (int i = 0; i < interfaces.length; i++) {
			_interfaces[i] = interfaces[i].substring(interfaces[i].lastIndexOf("/") + 1);
		}
	}

	@Override
	public String getName() {
		return _name.substring(_name.lastIndexOf("/") + 1);
	}

	@Override
	public String getType() {
		return _type;
	}

	@Override
	public String getSuperType() {
		return _superType.substring(_superType.lastIndexOf("/") + 1);
	}

	@Override
	public String[] getInterfaces() {
		return _interfaces;
	}

	@Override
	public void accept(IVisitor v) {
		v.visit(this);
	}
}

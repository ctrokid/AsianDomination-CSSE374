package impl;

import java.util.List;

import api.IClassDeclaration;
import visitor.IVisitor;

public class ClassDeclaration implements IClassDeclaration {
	private String _superType;
	private String _signature;
	private List<String> _interfaces;
	
	public ClassDeclaration(String superType, String signature, List<String> interfaces) {
		_superType = superType;
		_signature = signature;
		_interfaces = interfaces;
	}
	
	@Override
	public String getSuperType() {
		return _superType;
	}

	@Override
	public String getSignature() {
		return _signature;
	}

	@Override
	public List<String> getInterfaces() {
		return _interfaces;
	}
	
	@Override
	public void accept(IVisitor v) {
		v.visit(this);
	}
}

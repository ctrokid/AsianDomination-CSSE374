package impl;

import api.IClassMethod;
import visitor.IVisitor;

public class ClassMethod implements IClassMethod {
	private String _signature;
	private String _name;
	private String _accessLevel;
	private String _arguments;
	
	// TODO: FIXME: String signature is currently being passed in the returnType
	// Do we need signature or keep return type????
	public ClassMethod(String name, String signature, String accessLevel, String arguments) {
		_name = name;
		_signature = signature;
		_accessLevel = accessLevel;
		_arguments = arguments;
	}
	
	@Override
	public String getSignature() {
		return _signature;
	}

	@Override
	public String getName() {
		return _name;
	}

	@Override
	public String getAccessLevel() {
		return _accessLevel;
	}
	
	@Override
	public String getArguments() {
		return _arguments;
	}

	@Override
	public void accept(IVisitor v) {
		v.visit(this);
	}

}

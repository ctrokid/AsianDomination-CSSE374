package impl;

import java.util.Collection;
import java.util.HashSet;

import api.IClassMethod;
import visitor.IVisitor;

public class ClassMethod implements IClassMethod {
	private String _signature;
	private String _name;
	private String _accessLevel;
	private String _returnType;
	private Collection<MethodStatement> _statements;
	
	public ClassMethod(String name, String signature, String accessLevel, String returnType) {
		_name = name;
		_signature = signature;
		_accessLevel = accessLevel;
		_returnType = returnType;
		_statements = new HashSet<MethodStatement>();
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
	public String getReturnType() {
		return _returnType;
	}
	
	public void addStatement(MethodStatement stmt) {
		_statements.add(stmt);
	}
	
	public Collection<MethodStatement> getStatements() {
		return _statements;
	}

	@Override
	public void accept(IVisitor v) {
		v.visit(this);
	}

}

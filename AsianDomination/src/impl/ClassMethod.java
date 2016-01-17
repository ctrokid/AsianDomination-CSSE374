package impl;

import java.util.Collection;
import java.util.HashSet;

import api.IClassMethod;
import api.IMethodStatement;
import visitor.IVisitor;

public class ClassMethod implements IClassMethod {
	private String _methodName;
	private String _signature;
	private String _accessLevel;
	private String _returnType;
	private Collection<IMethodStatement> _methodStatements;

	public ClassMethod(String name, String signature, String accessLevel, String returnType) {
		_methodName = name;
		_signature = signature;
		_accessLevel = accessLevel;
		_returnType = returnType;
		_methodStatements = new HashSet<IMethodStatement>();
	}

	@Override
	public String getSignature() {
		return _signature;
	}

	@Override
	public String getAccessLevel() {
		return _accessLevel;
	}

	@Override
	public String getReturnType() {
		return _returnType;
	}

	@Override
	public void accept(IVisitor v) {
		v.visit(this);
	}

	@Override
	public String getMethodName() {
		return _methodName;
	}

	@Override
	public void addMethodStatement(IMethodStatement stmt) {
		_methodStatements.add(stmt);

	}

	@Override
	public Collection<IMethodStatement> getMethodStatements() {
		return _methodStatements;
	}

}

package impl;

import java.util.ArrayList;
import java.util.Collection;

import api.IClassMethod;
import api.IMethodStatement;
import visitor.IVisitor;

public class ClassMethod implements IClassMethod {
	private String _methodName;
	private String _signature;
	private int _accessLevel;
	private String _returnType;
	private Collection<IMethodStatement> _methodStatements;

	public ClassMethod(String name, String signature, int accessLevel, String returnType) {
		_methodName = name;
		_signature = signature;
		_accessLevel = accessLevel;
		_returnType = returnType;
		_methodStatements = new ArrayList<IMethodStatement>();
	}

	@Override
	public String getSignature() {
		return _signature;
	}

	@Override
	public int getAccessLevel() {
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

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + _accessLevel;
		result = prime * result + ((_methodName == null) ? 0 : _methodName.hashCode());
		result = prime * result + ((_methodStatements == null) ? 0 : _methodStatements.hashCode());
		result = prime * result + ((_returnType == null) ? 0 : _returnType.hashCode());
		result = prime * result + ((_signature == null) ? 0 : _signature.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ClassMethod other = (ClassMethod) obj;
		if (_accessLevel != other._accessLevel)
			return false;
		if (_methodName == null) {
			if (other._methodName != null)
				return false;
		} else if (!_methodName.equals(other._methodName))
			return false;
		if (_methodStatements == null) {
			if (other._methodStatements != null)
				return false;
		} else if (!_methodStatements.equals(other._methodStatements))
			return false;
		if (_returnType == null) {
			if (other._returnType != null)
				return false;
		} else if (!_returnType.equals(other._returnType))
			return false;
		if (_signature == null) {
			if (other._signature != null)
				return false;
		} else if (!_signature.equals(other._signature))
			return false;
		return true;
	}

}

package impl;

import java.util.HashMap;
import java.util.Map;

import visitor.ITraverser;
import visitor.IVisitMethod;
import visitor.IVisitor;
import visitor.LookupKey;
import visitor.VisitType;

public class Visitor implements IVisitor{
	private Map<LookupKey, IVisitMethod> _keyToVisitMethodMap;

	public Visitor() {
		_keyToVisitMethodMap = new HashMap<LookupKey, IVisitMethod>();
	}

	public void preVisit(ITraverser i) {
		this.doVisit(VisitType.PreVisit, i);
	}

	public void visit(ITraverser i) {
		this.doVisit(VisitType.Visit, i);
	}

	public void postVisit(ITraverser i) {
		this.doVisit(VisitType.PostVisit, i);
	}

	protected void doVisit(VisitType vType, ITraverser t) {
		LookupKey key = new LookupKey(vType, t.getClass());
		IVisitMethod m = _keyToVisitMethodMap.get(key);

		if (m != null)
			m.execute(t);
	}

	public void addVisit(VisitType visitType, Class<?> clazz, IVisitMethod m) {
		LookupKey key = new LookupKey(visitType, clazz);
		_keyToVisitMethodMap.put(key, m);
	}

	public void removeVisit(VisitType visitType, Class<?> clazz) {
		LookupKey key = new LookupKey(visitType, clazz);
		_keyToVisitMethodMap.remove(key);
	}
}

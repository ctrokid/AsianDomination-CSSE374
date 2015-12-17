package visitor;

import java.io.IOException;
import java.io.OutputStream;

import api.ITargetClass;

public class TargetClassOutputStream extends VisitorAdapter {
	private OutputStream out;

	public TargetClassOutputStream(OutputStream out){
		this.out = out;
	}
	
	private void write(String s) {
		s = s + "\n";
		try {
			this.out.write(s.getBytes());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public void visit(ITargetClass c) {
		
	}

}

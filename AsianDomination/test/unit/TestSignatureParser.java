package unit;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Test;

import utils.SignatureParser;

public class TestSignatureParser {

	@Test
	public void integerParsingTest(){
		ArrayList<String> param = SignatureParser.getParams("(I)V");
		assertEquals(1,param.size());
		assertEquals("int", param.get(0));
	}
	
	@Test
	public void integerArrayParsingTest(){
		ArrayList<String> param = SignatureParser.getParams("([I)V");
		assertEquals(1,param.size());
		assertEquals("int[]", param.get(0));
	}
	
	@Test
	public void hashMapParsingTest(){
		ArrayList<String> param = SignatureParser.getParams("(Ljava/util/HashMap;)Ljava/util/Collection");
		assertEquals(1,param.size());
		assertEquals("HashMap", param.get(0));
	}
	

}

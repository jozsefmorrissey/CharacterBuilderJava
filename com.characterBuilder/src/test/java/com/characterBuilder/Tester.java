package com.characterBuilder;

import org.junit.*;

public class Tester {
	@Test
	public void test(){
		assert(4 == 4);
	}
	
	@Test
	public void fail(){
		assert(3 == 4);
	}
}

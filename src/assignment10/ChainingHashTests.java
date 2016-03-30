package assignment10;

import static org.junit.Assert.*;
import static assignment10.*;
import org.junit.Before;
import org.junit.Test;

public class ChainingHashTests {

	HashFunctor badFunctor = new BadHashFunctor();
	HashFunctor mediocreFunctor = new MediocreHashFunctor();
	HashFunctor goodFunctor = new GoodHashFunctor();
	
	@Test
	public void testChainHashTableAdd() {
		ChainingHashTable chainTable = new ChainingHashTable(5, mediocreFunctor);
		chainTable.add("word");
		assertEquals(1, chainTable.size());
	}

}

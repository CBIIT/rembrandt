/*
 * ParserTest.java
 * Created on Sep 28, 2004
 *Owner
 * 
 */
package gov.nih.nci.nautilus.test;

import junit.framework.TestCase;
import java.util.*;
import gov.nih.nci.nautilus.parser.*;
import gov.nih.nci.nautilus.query.*;

/**
 * Add one sentence class summary here
 * Add class description here
 * @author Owner
 * @version 1.0, Sep 28, 2004
 *
**/
public class ParserTest extends TestCase {

	private Vector v1;
	/**
	 * Constructor for ParserTest.
	 * @param name
	 */
	public ParserTest(String name) {
		super(name);
	}

	/*
	 * @see TestCase#setUp()
	 */
	GeneExpressionQuery q1 = (GeneExpressionQuery) QueryManager.createQuery(QueryType.GENE_EXPR_QUERY_TYPE);
	GeneExpressionQuery q2 = (GeneExpressionQuery) QueryManager.createQuery(QueryType.GENE_EXPR_QUERY_TYPE);
	GeneExpressionQuery q3 = (GeneExpressionQuery) QueryManager.createQuery(QueryType.GENE_EXPR_QUERY_TYPE);
	protected void setUp() throws Exception {
		

		q1.setQueryName("Query 1");
		q2.setQueryName("Query 2");
		q3.setQueryName("Query 3");
		
		super.setUp();
		v1 = new Vector();
		v1.addElement(q1);
		v1.addElement("PRB");
		v1.addElement("(");
		v1.addElement(q2);
		v1.addElement("OR");
		v1.addElement(q3);
		v1.addElement(")");
		
		
		
		
	}

	/*
	 * @see TestCase#tearDown()
	 */
	protected void tearDown() throws Exception {
		super.tearDown();
	}

	public void testParser() {
		try {
			Parser p = new Parser(v1);
			p.expression();
			Queriable q = p.getCompoundQuery();
			System.out.println("Compound Query is "+q.toString());
			CompoundQuery x = new CompoundQuery(OperatorType.AND, q1,q2);
			System.out.println("Query is "+x.toString());
	
		}catch (Exception e){
			System.out.println("Error is " + e.getMessage());
		}
			
		
	}


}

/*L
 * Copyright (c) 2006 SAIC, SAIC-F.
 *
 * Distributed under the OSI-approved BSD 3-Clause License.
 * See http://ncip.github.com/rembrandt/LICENSE.txt for details.
 */

/*
 * Created on Nov 12, 2004
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package gov.nih.nci.rembrandt.queryservice.test;

import gov.nih.nci.caintegrator.dto.de.ChromosomeNumberDE;
import gov.nih.nci.caintegrator.dto.de.CytobandDE;
import gov.nih.nci.rembrandt.dto.lookup.AllGeneAliasLookup;
import gov.nih.nci.rembrandt.dto.lookup.CytobandLookup;
import gov.nih.nci.rembrandt.dto.lookup.LookupManager;
import gov.nih.nci.rembrandt.dto.lookup.PatientDataLookup;
import gov.nih.nci.rembrandt.queryservice.validation.DataValidator;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.TreeSet;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

/**
 * @author Himanso
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */


/**
* caIntegrator License
* 
* Copyright 2001-2005 Science Applications International Corporation ("SAIC"). 
* The software subject to this notice and license includes both human readable source code form and machine readable, 
* binary, object code form ("the caIntegrator Software"). The caIntegrator Software was developed in conjunction with 
* the National Cancer Institute ("NCI") by NCI employees and employees of SAIC. 
* To the extent government employees are authors, any rights in such works shall be subject to Title 17 of the United States
* Code, section 105. 
* This caIntegrator Software License (the "License") is between NCI and You. "You (or "Your") shall mean a person or an 
* entity, and all other entities that control, are controlled by, or are under common control with the entity. "Control" 
* for purposes of this definition means (i) the direct or indirect power to cause the direction or management of such entity,
*  whether by contract or otherwise, or (ii) ownership of fifty percent (50%) or more of the outstanding shares, or (iii) 
* beneficial ownership of such entity. 
* This License is granted provided that You agree to the conditions described below. NCI grants You a non-exclusive, 
* worldwide, perpetual, fully-paid-up, no-charge, irrevocable, transferable and royalty-free right and license in its rights 
* in the caIntegrator Software to (i) use, install, access, operate, execute, copy, modify, translate, market, publicly 
* display, publicly perform, and prepare derivative works of the caIntegrator Software; (ii) distribute and have distributed 
* to and by third parties the caIntegrator Software and any modifications and derivative works thereof; 
* and (iii) sublicense the foregoing rights set out in (i) and (ii) to third parties, including the right to license such 
* rights to further third parties. For sake of clarity, and not by way of limitation, NCI shall have no right of accounting
* or right of payment from You or Your sublicensees for the rights granted under this License. This License is granted at no
* charge to You. 
* 1. Your redistributions of the source code for the Software must retain the above copyright notice, this list of conditions
*    and the disclaimer and limitation of liability of Article 6, below. Your redistributions in object code form must reproduce 
*    the above copyright notice, this list of conditions and the disclaimer of Article 6 in the documentation and/or other materials
*    provided with the distribution, if any. 
* 2. Your end-user documentation included with the redistribution, if any, must include the following acknowledgment: "This 
*    product includes software developed by SAIC and the National Cancer Institute." If You do not include such end-user 
*    documentation, You shall include this acknowledgment in the Software itself, wherever such third-party acknowledgments 
*    normally appear.
* 3. You may not use the names "The National Cancer Institute", "NCI" "Science Applications International Corporation" and 
*    "SAIC" to endorse or promote products derived from this Software. This License does not authorize You to use any 
*    trademarks, service marks, trade names, logos or product names of either NCI or SAIC, except as required to comply with
*    the terms of this License. 
* 4. For sake of clarity, and not by way of limitation, You may incorporate this Software into Your proprietary programs and 
*    into any third party proprietary programs. However, if You incorporate the Software into third party proprietary 
*    programs, You agree that You are solely responsible for obtaining any permission from such third parties required to 
*    incorporate the Software into such third party proprietary programs and for informing Your sublicensees, including 
*    without limitation Your end-users, of their obligation to secure any required permissions from such third parties 
*    before incorporating the Software into such third party proprietary software programs. In the event that You fail 
*    to obtain such permissions, You agree to indemnify NCI for any claims against NCI by such third parties, except to 
*    the extent prohibited by law, resulting from Your failure to obtain such permissions. 
* 5. For sake of clarity, and not by way of limitation, You may add Your own copyright statement to Your modifications and 
*    to the derivative works, and You may provide additional or different license terms and conditions in Your sublicenses 
*    of modifications of the Software, or any derivative works of the Software as a whole, provided Your use, reproduction, 
*    and distribution of the Work otherwise complies with the conditions stated in this License.
* 6. THIS SOFTWARE IS PROVIDED "AS IS," AND ANY EXPRESSED OR IMPLIED WARRANTIES, (INCLUDING, BUT NOT LIMITED TO, 
*    THE IMPLIED WARRANTIES OF MERCHANTABILITY, NON-INFRINGEMENT AND FITNESS FOR A PARTICULAR PURPOSE) ARE DISCLAIMED. 
*    IN NO EVENT SHALL THE NATIONAL CANCER INSTITUTE, SAIC, OR THEIR AFFILIATES BE LIABLE FOR ANY DIRECT, INDIRECT, 
*    INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE 
*    GOODS OR SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF 
*    LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT 
*    OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
* 
*/

public class LookupManagerTest extends TestCase {
	/**
	 * @param string
	 */
	public LookupManagerTest(String string) {
		super(string);
	}

	public static Test suite() {
		TestSuite suite =  new TestSuite();
        //suite.addTest(new LookupManagerTest("testGetCytobandPositions"));
//        suite.addTest(new LookupManagerTest("testgetCytobandDEs"));
        //suite.addTest(new LookupManagerTest("testGetExpPlatforms"));
        //suite.addTest(new LookupManagerTest("testGetPathways"));
        //suite.addTest(new LookupManagerTest("testGetPatientData"));
        //suite.addTest(new LookupManagerTest("testPatientData"));
//		suite.addTest(new LookupManagerTest("testgetChrosomeCDEs"));
		suite.addTest(new LookupManagerTest("testgetCytobandDEstoo"));


        return suite;
	}

	/*
	 * @see TestCase#setUp()
	 */
	protected void setUp() throws Exception {
		super.setUp();
	}

	/*
	 * @see TestCase#tearDown()
	 */
	protected void tearDown() throws Exception {
		super.tearDown();
	}

	public void testGetCytobandPositions() {
		try {
			CytobandLookup[] cytobands = LookupManager.getCytobandPositions();
			assertNotNull(cytobands);
			System.out.println("cbEndPos"+
					"\tcbStart"+
					"\tchromosome"+
					"\tcytoband"+
					"\tcytobandPositionId"+
					"\torganism");
	        for (int i =0;i<cytobands.length;i++) {
	        	CytobandLookup cytoband = cytobands[i];
	            System.out.println(cytoband.getCbEndPos()+
	            					"\t"+cytoband.getCbStart()+
									"\t"+cytoband.getChromosome()+
									"\t"+cytoband.getCytoband()+
									"\t"+cytoband.getCytobandPositionId()+
									"\t"+cytoband.getOrganism());
	        }
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}
	public void testgetCytobandDEs(){
		ChromosomeNumberDE[] chromosomes;
		try {
			chromosomes = LookupManager.getChromosomeDEs();
			if(chromosomes != null){
				for(int i =0; i < chromosomes.length; i++){
					System.out.println("Chr:"+ chromosomes[i].getValueObject());
					CytobandDE[] cytobands = LookupManager.getCytobandDEs(chromosomes[i]);
					if(cytobands != null){
						for(int k = 0; k < cytobands.length; k++){
							System.out.println("Cytos:"+ cytobands[k].getValueObject());
						}
					}
					
				}
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	public void testGetPathways() {
		//TODO Implement getPathways().
	}

	public void testGetPatientData() {
		//TODO Implement getPatientData().
	}
    public void testGetExpPlatforms(){
	    /*try{
	    	ExpPlatformLookup[] expPlatforms = LookupManager.getExpPlatforms();
			assertNotNull(expPlatforms);
	        for (int i =0;i<expPlatforms.length;i++) {
	        	ExpPlatformLookup platform = expPlatforms[i];
	            System.out.println("expPlatformName"+ platform.getExpPlatformName()+
	            					"\t expPlatformDesc"+platform.getExpPlatformDesc()+
									"\t expPlatformId"+platform.getExpPlatformId());
	        }
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		*/
    }
    public void testPatientData(){
	    try{
	    	PatientDataLookup[] patientData = LookupManager.getPatientData();
			assertNotNull(patientData);
			System.out.println("patientID"+
					"\t survival"+
					"\t censor");
	        for (int i =0;i<patientData.length;i++) {
	        	PatientDataLookup patient = patientData[i];
	        	System.out.println(patient.getSampleId()+
    					"\t"+patient.getSurvivalLength()+
						"\t"+patient.getCensoringStatus());

	        }
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
    }
public void testgetChrosomeCDEs(){
	ChromosomeNumberDE[] chromosomes;
	try {
		chromosomes = LookupManager.getChromosomeDEs();
		TreeSet chrNum = new TreeSet();
		TreeSet chrStr = new TreeSet();
		Collection returnColl = new ArrayList();

		if(chromosomes != null){
			for(int i =0; i < chromosomes.length; i++){

				String x =  chromosomes[i].getValueObject();

				try {
					chrNum.add(new Integer(x));
				}catch(NumberFormatException ex){
					chrStr.add(x);
				}

			}
		}
		for (Iterator iter = chrNum.iterator(); iter.hasNext();) {
			returnColl.add(((Integer)iter.next()).toString());
		}
		for (Iterator iter = chrStr.iterator(); iter.hasNext();) {
			returnColl.add(iter.next());
		}
		for (Iterator iter = returnColl.iterator(); iter.hasNext();) {
			String m = (String) iter.next();
			System.out.println(m);
			
		}
	} catch (Exception e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}

}

public void testgetCytobandDEstoo(){
	String[] chrNumber = {"1","2"};
	System.out.println(chrNumber.toString());
	for (int i = 0; i < chrNumber.length; i++) {
	try {
		CytobandDE[] cytobands = LookupManager.getCytobandDEs(new ChromosomeNumberDE(chrNumber[i]));
		if(cytobands != null){
			for(int k = 0; k < cytobands.length; k++){
				System.out.println("CHR "+chrNumber[i]+" Cytos:"+ cytobands[k].getValueObject());
			}
		}
					
	} catch (Exception e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	}

}
public void testGeneSymbolAlias(){
    try{
    	List symbols = new ArrayList();
    	symbols.add("P53");
    	symbols.add("p53");
    	symbols.add("tp53");
    	symbols.add("TP53");
    	symbols.add("NAT2");
    	symbols.add("nat2");
    	symbols.add("EGFR");
		System.out.println("Entered Symbol"+"\tAccepted Symbol"+"\tGene Name");
    	for (Iterator iter = symbols.iterator(); iter.hasNext();) {
			String symbol = (String) iter.next();
			System.out.print("user Input: "+symbol+"\n");
			if(!DataValidator.isGeneSymbolFound(symbol)){
				AllGeneAliasLookup[] allGeneAlias = DataValidator.searchGeneKeyWord(symbol);
				if(allGeneAlias != null){
					for(int i =0; i < allGeneAlias.length ; i++){
						AllGeneAliasLookup alias = allGeneAlias[i];
						System.out.println(alias.getAlias()+"\t"+alias.getApprovedSymbol()+"\t"+alias.getApprovedName()+"\n");	
					}
				}
			}
			else{
			System.out.println(symbol+" found! \n");
			}
    	}

    } catch (Exception e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	}
public void testGeneSymbolAliasList(){
    try{
    	List<String> symbols = new ArrayList();
    	symbols.add("P53");
    	symbols.add("p53");
    	symbols.add("tp53");
    	symbols.add("TP53");
    	symbols.add("NAT2");
    	symbols.add("nat2");
    	symbols.add("EGFR");
    	symbols.add("XYZ");
    	symbols.add("TP5*");
		System.out.println("Entered Symbol"+"\tAccepted Symbol"+"\tGene Name");
    	Map<String,List<AllGeneAliasLookup>> validMap = DataValidator.searchGeneKeyWordList(symbols);
		if(validMap != null){
			for(String symbol:symbols){
				List<AllGeneAliasLookup> allGeneAliasLookupList = validMap.get(symbol);
				if(allGeneAliasLookupList != null){
					for(AllGeneAliasLookup allGeneAliasLookup: allGeneAliasLookupList){
						System.out.println("Symbol:"+symbol+"\t"+allGeneAliasLookup.getApprovedSymbol()+"\t"+allGeneAliasLookup.getApprovedName()+"\n");	
					}				
				}else{ //no symbol found
					System.out.println("Symbol:"+symbol+"\t"+ "Invalid symbol or not in the database."+"\n");	
				}
			}
		}
    } catch (Exception e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	}

}

<%@ page language="java" %>
<%@ page import="
gov.nih.nci.nautilus.criteria.*,
gov.nih.nci.nautilus.de.*,
gov.nih.nci.nautilus.query.GeneExpressionQuery,
gov.nih.nci.nautilus.query.*,
gov.nih.nci.nautilus.query.QueryManager,
gov.nih.nci.nautilus.query.QueryType,
gov.nih.nci.nautilus.view.*,
gov.nih.nci.nautilus.queryprocessing.ge.GeneExpr,
gov.nih.nci.nautilus.resultset.*,
gov.nih.nci.nautilus.resultset.gene.*,
gov.nih.nci.nautilus.resultset.sample.*,
gov.nih.nci.nautilus.constants.Constants,
java.text.DecimalFormat,
java.util.*" %>


<%!

	String theColors[] = {"0073E6","FFFF61"};

	 DecimalFormat resultFormat = new DecimalFormat("0.00");
     FoldChangeCriteria foldCrit;
     GeneIDCriteria  geneIDCrit;
     GeneOntologyCriteria ontologyCrit;
     PathwayCriteria pathwayCrit;
     RegionCriteria regionCrit;
     CloneOrProbeIDCriteria cloneCrit;
     CloneOrProbeIDCriteria probeCrit;
     ArrayPlatformCriteria allPlatformCrit;
     ArrayPlatformCriteria affyOligoPlatformCrit;
     ArrayPlatformCriteria cdnaPlatformCrit;
 
     ResultSet[] geneExprObjects;
     Resultant resultant;
     
	//put the functions needed here

%>

<html>
<head>
<style>
	body { font-family:arial; }
	Td {
		font-size: 10px;
		background: #F2F2F2;
		padding: 5px;
		}
</style>
<body>
<%
System.out.println("Here WE Go");

/* old way using resultSet[]
		// get the ResultSet[] from the session
        geneExprObjects = ( ResultSet[] ) (session.getAttribute(Constants.RESULTSET_KEY));
		//System.out.println("geneExprObjects"+geneExprObjects.length);
*/

//get query collection from session
		QueryCollection queryCollection = (QueryCollection) (session.getAttribute(Constants.QUERY_KEY));
		CompoundQuery myCompoundQuery = queryCollection.getCompoundQuery();

		//execute the query
		try{
			resultant = ResultsetManager.executeQuery(myCompoundQuery);
		}
		catch(Exception e) {
			System.out.println("error executing query");
			out.println("Error with Query");
			//redirect somewhere? back?
			e.printStackTrace();
		}
		
		
//see if theres at least on RS in the array and the RS exists
//if(geneExprObjects != null && geneExprObjects.length > 0) {    
if(resultant != null) {      
		%>
		<a href="jsp/geneViewReportCSV.jsp">[Download this report for Excel]</a><Br>
		<%
		

 //ResultsContainer resultsContainer = ResultsetProcessor.handleGeneExprView(geneExprObjects, GroupType.DISEASE_TYPE_GROUP);
 ResultsContainer  resultsContainer = resultant.getResultsContainer(); 
 Viewable view = resultant.getAssociatedView(); 
		if (view instanceof GeneExprSampleView){
		System.out.println("view:"+view);
		    DimensionalViewContainer dimensionalViewContainer = (DimensionalViewContainer) resultsContainer;
	        GeneExprSingleViewResultsContainer geneViewContainer = dimensionalViewContainer.getGeneExprSingleViewContainer();
	    	if(geneViewContainer != null && geneViewContainer.getGeneResultsets().size() > 0){
		    	Collection genes = geneViewContainer.getGeneResultsets();
		    	Collection labels = geneViewContainer.getGroupsLabels();
		    	Collection sampleIds = null;
		    	StringBuffer header = new StringBuffer();
		    	header.append("<table>\n<tr>\n"); //start the table
		    	StringBuffer sampleNames = new StringBuffer();
		        StringBuffer stringBuffer = new StringBuffer();
		    	//get group size (as Disease or Agegroup )from label.size
		        System.out.println("GroupSize= "+labels.size());
		    	
		        //set up the header for the table
		    	header.append("<Td>Gene</td>\n<td>Reporter</td>\n");
		    	sampleNames.append("<tr><Td> </td><Td> </tD>"); //start the second pseudo row
			   
				ArrayList cssLabels = new ArrayList(); //try to create the CSS dynamically
			   
		    	for (Iterator labelIterator = labels.iterator(); labelIterator.hasNext();) {
		        	String label = (String) labelIterator.next();
		        	sampleIds = geneViewContainer.getBiospecimenLabels(label);        // how many samples per group (label)?	
			    	header.append("<td colspan="+sampleIds.size()+" class='"+label+"'>"+label+"</td>"); //remove this for table
			    	cssLabels.add(label);
			    	
			           	for (Iterator sampleIdIterator = sampleIds.iterator(); sampleIdIterator.hasNext();) {
			            	sampleNames.append("<td class='"+label+"'>"+sampleIdIterator.next()+"</td>"); // print the samples row
			            	header.append("\t");
			           	}
		           	header.deleteCharAt(header.lastIndexOf("\t"));
		    	}
		    	sampleNames.append("</tr>");
		    	header.append("</tr>"); 
		    	
				//generate the CSS once we have all the labels
				StringBuffer css = new StringBuffer();
				css.append("<style>\n");
				String color = "";
				String font = "";
				for (int i = 0; i < cssLabels.size(); i++) {
					if(i%2 == 0)
					{
						color = theColors[1];
						font = theColors[0];
					}
					else
					{
						color = theColors[0];
						font = theColors[1];
					}
				    css.append("td."+(String)(cssLabels.get(i))+ " { background-color: #"+color+"; color: #"+font+" }\n");
				}
				css.append("</style>\n");
				out.println(css.toString());
		
		
		    	//System.out.println("Gene Count: "+genes.size());
		    	out.println(header.toString());
				out.println(sampleNames.toString());
		
		    	for (Iterator geneIterator = genes.iterator(); geneIterator.hasNext();) {
		    		GeneResultset geneResultset = (GeneResultset)geneIterator.next();
		    		Collection reporters = geneResultset.getReporterResultsets();
		        	//System.out.println("Reporter Count: "+reporters.size());
		    		for (Iterator reporterIterator = reporters.iterator(); reporterIterator.hasNext();) {
		        		ReporterResultset reporterResultset = (ReporterResultset)reporterIterator.next();
		        		Collection groupTypes = reporterResultset.getGroupByResultsets();
		        		stringBuffer = new StringBuffer();
		            	//System.out.println("Group Count: "+groupTypes.size());
		        		String reporterName = reporterResultset.getReporter().getValue().toString();
		        		
		        		//get the gene name, and reporter Name
		        		stringBuffer.append("<tr><td>"+geneResultset.getGeneSymbol().getValueObject().toString()+"</td><td>"+
		    					reporterName+"</td>");
		    			// System.out.println(groupTypes.size());
		
						// iterate through the groups (lables) and get the results
		        		for (Iterator labelIterator = labels.iterator(); labelIterator.hasNext();) {
		        			// ViewByGroupResultset groupResultset = (ViewByGroupResultset)groupIterator.next();
							String label = (String) labelIterator.next();
		        			ViewByGroupResultset groupResultset = (ViewByGroupResultset) reporterResultset.getGroupByResultset(label);
		        			
			        			// String label = groupResultset.getType().getValue().toString();
			        			sampleIds = geneViewContainer.getBiospecimenLabels(label);
			        			if(groupResultset != null)
		        				{
			                     	for (Iterator sampleIdIterator = sampleIds.iterator(); sampleIdIterator.hasNext();) {
			                       		String sampleId = (String) sampleIdIterator.next();
			                       		SampleFoldChangeValuesResultset biospecimenResultset = (SampleFoldChangeValuesResultset) groupResultset.getBioSpecimenResultset(sampleId);
			                       		if(biospecimenResultset != null){
			                       			Double ratio = (Double)biospecimenResultset.getFoldChangeRatioValue().getValue();
			                       			if(ratio != null)
				                       			stringBuffer.append("<Td class='"+label+"'>"+resultFormat.format(ratio)+" </td>");                                 
				                       		else
				                      			stringBuffer.append("<td class='"+label+"'>-</td>");
			                       		}
			                       		else 
			                       		{
			                       			stringBuffer.append("<td class='"+label+"'>-</td>");
			                       		}
			                       	}
		                       }
		                       else	{
		                       for(int s=0;s<sampleIds.size();s++) 
		                        	stringBuffer.append("<td>+</td>");                      
		                       }
		
		         		}
		         		
		        		// System.out.println(stringBuffer.toString());
		        		out.println(stringBuffer.toString() + "</tr>");
		    		}
		    	}
					out.println("</table>");
			}
		out.println("<h4>No Results Available</h4>");
	}
}
// else if Sample View or DiseaseView
	

// clean up the session, but not now
//session.removeAttribute("geneViewResultSet");
%>


</body>
</html>

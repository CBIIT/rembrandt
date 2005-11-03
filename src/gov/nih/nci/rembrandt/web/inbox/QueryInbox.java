package gov.nih.nci.rembrandt.web.inbox;

import gov.nih.nci.rembrandt.cache.BusinessTierCache;
import gov.nih.nci.rembrandt.cache.ConvenientCache;
import gov.nih.nci.rembrandt.cache.PresentationTierCache;
import gov.nih.nci.rembrandt.dto.query.CompoundQuery;
import gov.nih.nci.rembrandt.web.factory.ApplicationFactory;

import java.util.Random;

import javax.servlet.http.HttpSession;

import uk.ltd.getahead.dwr.ExecutionContext;

public class QueryInbox {
	
	private HttpSession session;
	private BusinessTierCache btc;
	private PresentationTierCache ptc;
	
	public QueryInbox()	{
		//get some common stuff
		session = ExecutionContext.get().getSession(false);
		btc = ApplicationFactory.getBusinessTierCache();
		ptc = ApplicationFactory.getPresentationTierCache();
	}
	
	public String checkStatus()	{
		//simulate that the query is still running, assuming we have only 1 query for testing
		
		//HttpSession session = ExecutionContext.get().getSession(false);

		Random r = new Random();
		int randInt = Math.abs(r.nextInt()) % 11;
		if(randInt % 2 == 0)
			return "false";
		else
			return "true";
	}

	public String getQueryName()	{
		String st = "nothing";
		
		try	{
			st = String.valueOf(ptc.getSessionQueryBag(session.getId()).getQueries().size());
		}
		catch(Exception e){
			st = "no worky";
		}
		
		return st;
		
		
	}
}

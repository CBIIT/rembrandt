package gov.nih.nci.nautilus.de;

import gov.nih.nci.nautilus.util.ApplicationContext;

/**
 * Created by IntelliJ IDEA.
 * User: BhattarR
 * Date: Jul 12, 2003
 * Time: 3:06:10 PM
 * To change this template use Options | File Templates.
 */
abstract public class DomainElement {
   protected Object value;
   abstract public Object getValue();
   abstract public void setValue(Object obj) throws Exception;
   // public static String LABEL = (String)ApplicationContext.getLabelProperties().get("ChromosomeNumber");

    public DomainElement(Object value) {
        this.value = value;
    }
}



package gov.nih.nci.nautilus.de;

import gov.nih.nci.nautilus.util.ApplicationContext;

/**
 * Created by IntelliJ IDEA.
 * User: BhattarR
 * Date: Jul 12, 2004
 * Time: 6:06:49 PM
 * To change this template use Options | File Templates.
 */
abstract public class BasePairPositionDE extends DomainElement {
   private String positionType;
   public final static String START_POSITION = "StartPosition";
   public final static String END_POSITION = "StartPosition";

   public final static class StartPosition extends BasePairPositionDE {
        public StartPosition(Integer startPosition) {
            super(START_POSITION, startPosition);
        }
    }
    public final static class EndPosition extends BasePairPositionDE {
        public EndPosition(Integer endPosition) {
            super(END_POSITION, endPosition);
        }
    }

    private BasePairPositionDE(String positionType, Integer value) {
        super(value);
        this.positionType = positionType;
    }

    public String getRegulationTypeType() {
        return positionType;
    }

    public Object getValue() {
        return value;
    }

    public void setValue(Object obj) throws Exception {
         if (! (obj instanceof Integer) )
            throw new Exception ( "Could not set the value.  Parameter is of invalid data type: " + obj);
         setValueObject((Integer)obj);
    }

    public Integer getValueObject() {
        return (Integer) getValue();
    }

    public void setValueObject(Integer value) {
        this.value = value;
    }
}

package gov.nih.nci.nautilus.queryprocessing.cgh;

import gov.nih.nci.nautilus.criteria.CopyNumberCriteria;
import gov.nih.nci.nautilus.de.CopyNumberDE;
import gov.nih.nci.nautilus.query.ComparativeGenomicQuery;
import gov.nih.nci.nautilus.queryprocessing.QueryHandler;

import java.util.Collection;

import org.apache.log4j.Logger;
import org.apache.ojb.broker.PersistenceBroker;
import org.apache.ojb.broker.query.Criteria;

/**
 * Created by IntelliJ IDEA.
 * User: BhattarR
 * Date: Oct 22, 2004
 * Time: 10:55:58 AM
 * To change this template use Options | File Templates.
 */
public class CopyNumberCriteriaHandler {
    private static Logger logger = Logger.getLogger(CopyNumberCriteriaHandler.class);
    static void addCopyNumberCriteria(ComparativeGenomicQuery cghQuery, Class beanClass, PersistenceBroker pb, Criteria criteria) throws Exception {
        CopyNumberCriteria copyNumberCrit = cghQuery.getCopyNumberCriteria();
        if (copyNumberCrit != null) {
               String columnName = QueryHandler.getColumnName(pb, CopyNumberDE.class.getName(), beanClass.getName());
               Collection objs = copyNumberCrit.getCopyNummbers();
               Object[] copyObjs = objs.toArray();

                // Either only UpRegulation or DownRegulation
                if (copyObjs .length == 1) {
                    CopyNumberDE copyObj = (CopyNumberDE) copyObjs [0];
                    Double foldChange = new Double(copyObj.getValueObject().floatValue());
                    addSingleUpORDownCriteria(foldChange, copyObj.getCGHType(), columnName, criteria, pb);
                }

                // else it could be EITHER both (UpRegulation or DownRegulation) OR UnChangedRegulation
                else if (copyObjs.length == 2) {
                   String type = ((CopyNumberDE)copyObjs[0]).getCGHType();
                   if (type.equals(CopyNumberDE.UNCHANGED_COPYNUMBER_UPPER_LIMIT) || type.equals(CopyNumberDE.UNCHANGED_COPYNUMBER_DOWN_LIMIT) ) {
                       addUnChangedCriteria(copyObjs, columnName, criteria, pb);
                   }
                   else if (type.equals(CopyNumberDE.AMPLIFICATION) || type.equals(CopyNumberDE.DELETION) ) {
                       addUpAndDownCriteria(copyObjs, columnName, criteria, pb);
                   }
                }
                else {
                   throw new Exception("Invalid number of Copy Numnber Criteria objects: " + copyObjs.length);
                }
         }
    }

    private static void addUpAndDownCriteria(Object[] copyObjs, String columnName, Criteria criteria, PersistenceBroker pb) throws Exception {
        String type1 = ((CopyNumberDE)copyObjs[0]).getCGHType();
        Double copyChange1 = new Double(((CopyNumberDE)copyObjs[0]).getValueObject().floatValue());
        Criteria newCrit = new Criteria();
        addSingleUpORDownCriteria(copyChange1, type1, columnName, newCrit, pb);

        String type2 = ((CopyNumberDE)copyObjs[1]).getCGHType();
        Double copyChange2 = new Double(((CopyNumberDE)copyObjs[1]).getValueObject().floatValue());
        Criteria copy2Crit = new Criteria();
        addSingleUpORDownCriteria(copyChange2, type2, columnName, copy2Crit, pb);

        newCrit.addOrCriteria(copy2Crit);

        criteria.addAndCriteria(newCrit);
    }

    private static void addUnChangedCriteria(Object[] copyObjs, String columnName, Criteria criteria, PersistenceBroker pb) throws Exception {

        String type1 = ((CopyNumberDE)copyObjs[0]).getCGHType();
        Double upperLimit;
        Double lowerLimit;

        if (type1.equals(CopyNumberDE.UNCHANGED_COPYNUMBER_UPPER_LIMIT)) {
            upperLimit = new Double(((CopyNumberDE) copyObjs[1]).getValueObject().floatValue());
            lowerLimit = new Double(((CopyNumberDE)copyObjs[0]).getValueObject().floatValue());
        }
        else {
            upperLimit = new Double(((CopyNumberDE)copyObjs[0]).getValueObject().floatValue());
            lowerLimit = new Double(((CopyNumberDE)copyObjs[1]).getValueObject().floatValue());
        }
        criteria.addBetween(columnName, lowerLimit, upperLimit);
    }

    private static void addSingleUpORDownCriteria(Double copyChange, String type, String colunName, Criteria subCrit, PersistenceBroker pb) throws Exception {
        if (type.equals(CopyNumberDE.AMPLIFICATION))
            subCrit.addGreaterOrEqualThan(colunName,copyChange);
        else if (type.equals(CopyNumberDE.DELETION))
            subCrit.addLessOrEqualThan(colunName, copyChange);
        else {
            throw new Exception("Invalid Copy Nuumber: " + type + " Value:" + copyChange);
        }
   }
}

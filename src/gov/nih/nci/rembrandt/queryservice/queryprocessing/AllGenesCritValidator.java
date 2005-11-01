package gov.nih.nci.rembrandt.queryservice.queryprocessing;

import gov.nih.nci.caintegrator.dto.critieria.FoldChangeCriteria;
import gov.nih.nci.caintegrator.dto.critieria.SampleCriteria;
import gov.nih.nci.caintegrator.dto.de.ExprFoldChangeDE;
import gov.nih.nci.rembrandt.dto.query.GeneExpressionQuery;
import gov.nih.nci.rembrandt.dto.query.Query;
import gov.nih.nci.rembrandt.queryservice.queryprocessing.ge.FoldChangeCriteriaHandler;
import gov.nih.nci.rembrandt.util.RembrandtConstants;

/**
 * Created by IntelliJ IDEA.
 * User: Ram
 * Date: Apr 5, 2005
 * Time: 11:42:49 AM
 * To change this template use File | Settings | File Templates.
 */
public class AllGenesCritValidator {

    public static void validateSampleIDCrit(Query query) throws Exception{
        SampleCriteria sampleIDCrit = query.getSampleIDCrit();
        if (sampleIDCrit.getSampleIDs().size() > RembrandtConstants.ALL_GENES_MAX_SAMPLE_COUNT )
            throw new Exception("Total Sample Number of Samples can not exceed " + RembrandtConstants.ALL_GENES_MAX_SAMPLE_COUNT);

    }

    public static void validateFoldChangeForAllGenes(GeneExpressionQuery geQuery) throws Exception {
       FoldChangeCriteria foldChgCrit = geQuery.getFoldChgCrit();
       ExprFoldChangeDE c = (ExprFoldChangeDE)foldChgCrit.getFoldChangeObjects().toArray()[0];
       String type = c.getRegulationType();

        // TODO:  all  of this need to be Re-Factored and get rid of If-else: -- RAM


        if (type.equals(ExprFoldChangeDE.UP_REGULATION)) {
           if (c.getValueObject().compareTo(FoldChangeCriteriaHandler.ALL_GENES_REGULATION_LIMIT) < 0) {
               throw new Exception("Fold Change must be at greater than or equal to " + FoldChangeCriteriaHandler.ALL_GENES_REGULATION_LIMIT);
           }
       }

       else if(type.equals(ExprFoldChangeDE.DOWN_REGULATION)) {
            if (c.getValueObject().compareTo(FoldChangeCriteriaHandler.ALL_GENES_REGULATION_LIMIT) > 0) {
               throw new Exception("Fold Change must be at less than or equal to " + FoldChangeCriteriaHandler.ALL_GENES_REGULATION_LIMIT);
           }
       }

       /* else if (type.equals(ExprFoldChangeDE.UNCHANGED_REGULATION_UPPER_LIMIT)) {
            if (c.getValueObject().compareTo(ALL_GENES_UNCHANGED_UPPER_LIMIT) > 0) {
               throw new Exception("Unchanged Fold Change must be at between " + ALL_GENES_UNCHANGED_UPPER_LIMIT + "-" + ALL_GENES_UNCHANGED_DOWN_LIMIT);
            }
        }

        else if (type.equals(ExprFoldChangeDE.UNCHANGED_REGULATION_DOWN_LIMIT)) {
            if (c.getValueObject().compareTo(ALL_GENES_UNCHANGED_DOWN_LIMIT) < 0 ) {
               throw new Exception("Unchanged Fold Change must be at between " + ALL_GENES_UNCHANGED_UPPER_LIMIT + "-" + ALL_GENES_UNCHANGED_DOWN_LIMIT);
            }
        }
        */

   }
}

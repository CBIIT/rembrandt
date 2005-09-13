/*
 * Created on Mar 25, 2005
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package gov.nih.nci.nautilus.ui.helper;

import gov.nih.nci.nautilus.constants.NautilusConstants;
import gov.nih.nci.nautilus.ui.struts.form.KMDataSetForm;

import java.util.ArrayList;
import java.util.List;

/**
 * 
 * @author Himanso
 */
public class KMDataSetHelper {

	public static KMDataSetForm populateReporters(List _reporters,
			String _plotType, KMDataSetForm _kmForm) {
		List reporters = new ArrayList();
		
		if (_reporters != null && _plotType != null && _kmForm != null) {
			reporters = _reporters;
			if (_plotType.equals(NautilusConstants.GENE_EXP_KMPLOT)) {
				reporters.add(0, NautilusConstants.GRAPH_DEFAULT);
			}
			if (_plotType.equals(NautilusConstants.COPY_NUMBER_KMPLOT)
					&& reporters.size() > 1) {
				reporters.add(0, " ");

			}
			_kmForm.setReporters(reporters);
		}
		return _kmForm;
	}
}

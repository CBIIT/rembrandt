package gov.nih.nci.nautilus.ui.bean;

/**
 * @author BauerD
 * Jan 27, 2005
 * 
 */
public class SelectedQueryBean {
	private String leftParen;
    private String rightParen;
    private String queryName;
    private String operand;
    private boolean allGeneQuery = false;

	/**
	 * @return Returns the leftParen.
	 */
	public String getLeftParen() {
		return leftParen;
	}
	/**
	 * @return Returns the operand.
	 */
	public String getOperand() {
		return operand;
	}
	/**
	 * @return Returns the queryName.
	 */
	public String getQueryName() {
		return queryName;
	}
	/**
	 * @return Returns the rightParen.
	 */
	public String getRightParen() {
		return rightParen;
	}
	/**
	 * @param leftParen The leftParen to set.
	 */
	public void setLeftParen(String leftParen) {
		this.leftParen = leftParen;
	}
	/**
	 * @param operand The operand to set.
	 */
	public void setOperand(String operand) {
		this.operand = operand;
	}
	/**
	 * @param queryName The queryName to set.
	 */
	public void setQueryName(String queryName) {
		this.queryName = queryName;
	}
	/**
	 * @param rightParen The rightParen to set.
	 */
	public void setRightParen(String rightParen) {
		this.rightParen = rightParen;
	}
	/**
	 * @return Returns the allGeneQuery.
	 */
	public boolean isAllGeneQuery() {
		return allGeneQuery;
	}
	/**
	 * @param allGeneQuery The allGeneQuery to set.
	 */
	public void setAllGeneQuery(boolean allGeneQuery) {
		this.allGeneQuery = allGeneQuery;
	}
}

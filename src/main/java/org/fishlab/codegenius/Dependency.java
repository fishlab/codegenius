package org.fishlab.codegenius;
/**依赖描述<br>
 * TODO:添加更多属性
 * */
public class Dependency extends BasicCoordinate{
	private String type;
	private String classifier;
	private String scope;
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getClassifier() {
		return classifier;
	}
	public void setClassifier(String classifier) {
		this.classifier = classifier;
	}
	public String getScope() {
		return scope;
	}
	public void setScope(String scope) {
		this.scope = scope;
	}
	
}

package Inter;

import java.util.ArrayList;
import Symbol.Environment;

public class node {
	
	private String code="";
	private String interString="";
	private Environment environment;
	private ArrayList<node> chlirds=new ArrayList<node>();
	private int index=0;
	private node trueNode;
	private node falseNode;
	
	public node(){
		
	}
	
	public node(String interString,Environment environment){
		this.interString=interString;
		this.environment=environment;
	}
	
	public node(String code,String interString,Environment environment){
		this.code=code;
		this.interString=interString;
		this.environment=environment;
	}
	
	public ArrayList<node > getChilds(){
		return chlirds;
	}
	
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	
	public node Next(){
		return chlirds.get(index++);
	}
	
	public void Add(node child){
		chlirds.add(child);
	}
	
	public boolean hasNext()
	{
		return index<chlirds.size()?true:false;
	}
	public Environment getEnvironment() {
		return environment;
	}
	public void setEnvironment(Environment environment) {
		this.environment = environment;
	}

	public String getInterString() {
		return interString;
	}

	public void setInterString(String interString) {
		this.interString = interString;
	}

	public node getTrueNode() {
		return trueNode;
	}

	public void setTrueNode(node trueNode) {
		this.trueNode = trueNode;
		interCode.put(trueNode);
	}

	public node getFalseNode() {
		return falseNode;
	}

	public void setFalseNode(node falseNode) {
		this.falseNode = falseNode;
		interCode.put(falseNode);
	}
	
}

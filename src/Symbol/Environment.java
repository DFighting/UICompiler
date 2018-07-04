package Symbol;

import java.util.Hashtable;

import Utility.utility;

public class Environment {
	
	private Environment pre=null;
	private Hashtable<AttributeKey, SymbolRecord> symbols=new Hashtable<AttributeKey,SymbolRecord>();
	private Environment next=null;
	private String name="";
	private int return_tag=0;
	private String args="";
	private String return_value="";
	
	public Environment(Environment pr)
	{
		this.pre=pr;
	}
	
	public void addRecord(AttributeKey key,SymbolRecord record)
	{
		for(SymbolRecord te : this.symbols.values())
			if(te.getName().equals(record.getName()))
					throw new Error("Repeated Variable in line "+record.getLine()+" and line "+te.getLine());
	
		this.symbols.put(key, record);
	}
	
	public boolean searchRecord(String variable)
	{
		boolean result=false;
		for(SymbolRecord te : this.symbols.values())
		      if(te.getName().equals(variable)){
				result=true;
				 break;
				}
		if((!result)&&(this.pre!=null))
			result=this.pre.searchRecord(variable);
			
	   return result;	
	}
	
	public SymbolRecord getRecord(String variable)
	{
		SymbolRecord record=new SymbolRecord();
		boolean tag=false;
		for(SymbolRecord te : this.symbols.values())
		      if(te.getName().equals(variable)){
				utility.Copy(record, te);
				tag=true;
				 break;
				}
		if((!tag)&&(this.pre!=null))
			record=this.pre.getRecord(variable);
		return record;
	}
	
	public SymbolRecord getRecord(String variable,int func)
	{
		SymbolRecord record=new SymbolRecord();
		for(SymbolRecord te : this.symbols.values())
		      if(te.getName().equals(variable)){
				utility.Copy(record, te);
				 break;
				}
		return record;
	}
	
	public SymbolRecord getRecord(AttributeKey key)
	{
		return this.symbols.get(key);
	}
	
	public void removeRecord(AttributeKey key)
	{
		this.symbols.remove(key);
	}
	
	public Hashtable<AttributeKey, SymbolRecord> CurrentSymbols()
	{
		return this.symbols;
	}
	
	public Environment PreEnvironment()
	{
		if(this.pre==null)
		    return null;
		else
			return this.pre;
	}
	
	public void printEnvironment()
	{
		for(SymbolRecord te:this.symbols.values())
			te.print();
			
	}
	
	public void printValue(){
		for(SymbolRecord te:this.symbols.values())
			System.out.println("  name:"+te.getName()+"  type:"+Type.printType(te.getTag())+"  value:"+te.getValue());
	}
	
	public String retValue(){
		String temp="";
		for(SymbolRecord te:this.symbols.values())
			temp+="        name: "+te.getName()+"    type: "+Type.printType(te.getTag())+"    value: "+te.getValue()+"\n";
		return temp;
	}

	public Environment getNext() {
		if(this.next==null)
		    return null;
		else
			return this.next;
	}

	public void setNext(Environment next) {
		this.next = next;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getReturn_tag() {
		return return_tag;
	}

	public void setReturn_tag(int return_tag) {
		this.return_tag = return_tag;
	}

	public String getArgs() {
		return args;
	}

	public void setArgs(String args) {
		this.args = args;
	}

	public String getReturn_value() {
		return return_value;
	}

	public void setReturn_value(String return_value) {
		this.return_value = return_value;
	}

}

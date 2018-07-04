package Symbol;

public class SymbolRecord {
	
	private String value=null;
	private String name=null;
	private int line;
	private int tag;
	private Type type=new Type(null, 0, 0);

	public SymbolRecord(int l,int t,String name,String value)
	{  
		this.setName(name);
		this.setValue(value);
		this.setLine(l);
		this.setTag(t);	
		this.type.setTag(t);
	}
	
	public SymbolRecord()
	{
		
	}
	
	public SymbolRecord CurrentRecord()
	{
		SymbolRecord result=new SymbolRecord(line, tag, name, value);
		return result;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getLine() {
		return line;
	}

	public void setLine(int line) {
		this.line = line;
	}

	public int getTag() {
		return tag;
	}

	public void setTag(int tag) {
		this.tag = tag;
	}
	
	public void print()
	{
		System.out.print("Name:"+this.name+"  Value:"+this.value+"  Tag:"+this.tag+"  Line:"+this.line+"\n");
	}

	public Type getType() {
		return type;
	}
	
	public String getTypeName()
	{
		return type.getToken_name();
	}

	public void setType(String name,int width) {
		this.type.setToken_name(name);//type name
		this.type.setWidth(width);
	}
	
	public void setType(Type type)
	{
		this.type.setToken_name(type.getToken_name());//type name
		this.type.setWidth(type.getWidth());
	}

}

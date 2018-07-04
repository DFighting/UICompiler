package Symbol;

public class AttributeKey {
	
	private int line;
	private Token token=new Token();
	public void setToken(Token t)
	{
		this.token=t;
	}
	public void setLine(int l)
	{
		this.line=l;
	}
	public AttributeKey getKey()
	{
		AttributeKey result=new AttributeKey();
		result.setLine(line);
		result.setToken(token);
		return result;
	}

}

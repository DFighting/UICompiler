 package Symbol;

public class Token {
	
	private int tag;
	private String token_name=null;
	
	public Token(String token_name,int tag)
	{
		this.token_name=token_name;
		this.tag=tag;
	}
	public Token()
	{
		
	}
	public void setTag(int i)
	{
		this.tag=i;
	}
	public void setToken_name(String s)
	{
		this.token_name=s;
	}
	public int getTag()
	{
		return this.tag;
	}
	public String getToken_name()
	{
		return this.token_name;
	}
	
	public void print()
	{
		System.out.print("Token name:"+token_name+" Tag:"+tag+"\n");
	}

}

package Utility;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public class SourceCode {
	
	private String path=null;
	private String sourceCode="";
	
	public void setPath(String s)
	{
		this.path=s;
	}
	
	public void Input() throws IOException
	{
		Scanner in=new Scanner(new File(path));
		StringBuilder result=new StringBuilder();
		while(in.hasNextLine()){
			result.append(in.nextLine()+"\r\n");
		}
		in.close();
		this.sourceCode=result.toString();
	}

	public String getSourceCode() {
		return sourceCode;
	}
	
	public void setSourceCode(String s)
	{
		this.sourceCode=s;	
	}

}

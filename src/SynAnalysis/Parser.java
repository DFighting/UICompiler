package SynAnalysis;

import java.util.ArrayList;
import java.util.Stack;

import Inter.node;
import LexAnalysis.Lexer;
import Symbol.AttributeKey;
import Symbol.Environment;
import Symbol.SymbolRecord;
import Symbol.Tag;
import Symbol.Token;
import Utility.SourceCode;
import Utility.utility;

public class Parser {
	
	private Token look=new Token();
	private ArrayList<Environment> environments=new ArrayList<Environment>();
	private AttributeKey currentKey=new AttributeKey();
	private int line=0;
	private Lexer lexer=new Lexer();
	private ArrayList<node> roots=new ArrayList<node>();//roots[last] is main root
	private final String NullString=new String();
	
	public Parser(Lexer lexer)
	{
		this.lexer=lexer;
	}
	
	public Token getLook() {
		return look;
	}
	public void setLook(Token look) {
		this.look = look;
	}
	
	private void move(SourceCode code) throws Exception
	{
		this.look=lexer.lexer(code);
		this.line=lexer.getSymbolRecord().getLine();
	}
	
	private void rollback(SourceCode code)
	{
		code.setSourceCode(this.look.getToken_name()+code.getSourceCode());
	}
	
	private Boolean match(int Tag)
	{
		if(look.getTag()==Tag)
			return true;
		else
			return false;
	}
	public void program(SourceCode code) throws Exception
	{
	  boolean tag=true;
	  while(tag){
		  move(code);  Environment local=new Environment(null); 
	    switch (look.getTag()) {
		case Tag.Type:
		case Tag.VOID:
			if(look.getToken_name().equals("int"))
			   local.setReturn_tag(Tag.NUM);
			else if(look.getToken_name().equals("float"))
				   local.setReturn_tag(Tag.REAL);
			  else if(look.getToken_name().equals("char"))
				     local.setReturn_tag(Tag.CHAR);
			     else if(look.getToken_name().equals("bool"))
				        local.setReturn_tag(Tag.BASIC);
			     else			
			    	 local.setReturn_tag(look.getTag());
			move(code);
			if(look.getToken_name().equals("main"))
		  		tag=false;
		     function(code, local);
			break;
		default:
			throw new Error("Syntax Error! there need a return declare before function in line "+line);
		}
		  
	}
	}
	
	private void function(SourceCode code,Environment environment) throws Exception{
		if(match(Tag.STRING)){
  		  environment.setName(look.getToken_name()); 
  		  move(code); 
  		  match('(');
  		  String args=utility.Extract("("+code.getSourceCode(), '(',line);//function parameter
  		  args=args.replace(',', ';'); 
  		  args+=";"; 
  		  environment.setArgs(utility.ExtractVarible(args));
  		  code.setSourceCode(code.getSourceCode().substring(args.length()-1));
  		  move(code); 
  		  match(')');
  		  move(code);
  	    if(match('{')){
  		    node temp=new node(); 
  		    roots.add(temp);
  		    environments.add(environment);
  		    code.setSourceCode(args+code.getSourceCode());
   	        block(code,environment,utility.Extract("{"+code.getSourceCode(), '{',line).length(),temp);
   	        temp.setEnvironment(environment); 
   	        temp.setInterString(environment.getName());
  	        while(look.getToken_name()==null)
  			    move(code);
  	        if(!match('}'))
      	        throw new Error("Syntax Error, There need a } in line "+line);
  	   }else
  		     throw new Error("Syntax Error, There need a { in line "+line);
  	}else
  	   throw new Error("Syntax Error, There need a block name in line "+line);
	}
	
	private void call(SourceCode code,Environment environment,node function) throws Exception{
		String[] paramas=function.getEnvironment().getArgs().split(" ");
		move(code); 
		if(match('('))
			for(int i=0;i<paramas.length;i++){
				move(code); 
				int currenttag=0;
				if(look.getTag()==Tag.STRING)//variable
					currenttag=environment.getRecord(look.getToken_name()).getTag();
				else
					currenttag=look.getTag();
			if(function.getEnvironment().getRecord(paramas[i],1).getTag()!=currenttag)
				throw new Error("Syntax Error, parama not match of "+function.getEnvironment().getName()+" in line "+line);
			else{
				if(i<paramas.length-1) { 
					move(code);
					if(!match(','))
						throw new Error("Syntax Error, maybe need a , in line "+line);
				 }
				}
			}
		 else
			throw new Error("Syntax Error!,there need a ( in line "+line);
		 move(code);
		if(!match(')'))
			throw new Error("Syntax Error!,there need a ) in line "+line);
		
	}
	
	private void block(SourceCode code,Environment environment,int number,node root)
	{
		try {
			if(code.getSourceCode().charAt(code.getSourceCode().length()-1)!='@')
				code.setSourceCode(code.getSourceCode()+"@");
			int startLength=code.getSourceCode().length();
			move(code);
			int movedLength=startLength-code.getSourceCode().length();
			Environment local=new Environment(environment); 
			environment.setNext(local);
			while(movedLength<number){
				if(match(Tag.Type))
				   decls(code,environment);
				else if(look.getToken_name()!=null)
				   stmts(code,local,root);
				move(code);
				movedLength=startLength-code.getSourceCode().length();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private void decls(SourceCode code,Environment environment)
	{
		//No decls
		int tag=look.getTag();
		if(tag!=Tag.Type)
		{
			rollback(code);
			return;
		}
		//except array
		String type=null;
		int currLine=line;
		SymbolRecord prev=lexer.getSymbolRecord();
		environment.removeRecord(currentKey);
	    
		try {		
			type=prev.getName();
			move(code);
			SymbolRecord temp_record=lexer.getSymbolRecord();
			AttributeKey temp_key=lexer.getAttributeKey();
			switch (type) {
			case "int":
                temp_record.setValue("0");
                temp_record.setTag(Tag.NUM); 
                temp_record.setType("int", 4);
                environment.addRecord(temp_key, temp_record);
				break;
				
			case "float":
                temp_record.setValue("0.0");
                temp_record.setTag(Tag.REAL);
                temp_record.setType("float", 4);
                environment.addRecord(temp_key, temp_record);
                break;
				
			case "char":
                temp_record.setValue(null);
                temp_record.setTag(Tag.CHAR);
                temp_record.setType("char", 1);
                environment.addRecord(temp_key, temp_record);
                break;
				
			case "bool":
                temp_record.setValue("true");
                temp_record.setTag(Tag.BASIC);
                temp_record.setType("bool", 1);
                environment.addRecord(temp_key, temp_record);
                break;
			default:
				throw new Error("There is Syntax error in line "+line);
			}
			move(code);
			if(!match(';'))			
				throw new Error("there need a ; in line "+currLine);	
			else
				return;
		} catch (Exception e) {
			e.printStackTrace();
			return;
		}
				
	}
	
	private void stmts(SourceCode code,Environment environment,node root)
	{
		try {
			node loca=new node(); loca.setEnvironment(environment);
			if(look.getTag()==Tag.STRING){
				if(environment.searchRecord(lexer.getSymbolRecord().getName()))
				    look.setTag(Tag.BASIC);
				else if(SearchFunction())
					look.setTag(Tag.FUNC);
				else
					throw new Error("Undefined Variable or Function in line "+line);
			}
			switch(look.getTag()){
				case(Tag.IF):
					loca.setInterString("condition");
				    root.Add(loca);
					Condition_Statement(code,environment,loca); 
				    break;
				case(Tag.DO):	
					loca.setInterString("do_while");
				    root.Add(loca);
					Loop_DO_WHILE_Statement(code, environment,loca);  
				    break;
				case(Tag.WHILE):
					loca.setInterString("while"); 
				    root.Add(loca);
					Loop_WHILE_Statement(code,environment,loca); 
				    break;
				case(Tag.BASIC):
					loca.setInterString("assign"); 
				    root.Add(loca);
					Assign_Statement(code,environment,loca); 
				    break;
				case(Tag.RETURN):
					loca.setInterString("return"); 
				    root.Add(loca);
					Environment temp=environment;
				    while(temp.getReturn_tag()==0)
				    	temp=temp.PreEnvironment();
				    move(code);
					if((temp.getReturn_tag()==Tag.VOID)&&(match(';')))
						if(match(';')){
							loca.setCode("return");
							break;
						}
						else
							throw new Error("Syntax Error! there need a ; in line "+line);
					
					if((match(temp.getReturn_tag()))||(temp.getReturn_tag()==environment.getRecord(look.getToken_name()).getTag())){
						String re=look.getToken_name();
						move(code); 
						if(match(';')){
							loca.setCode("return "+re);
							break;
						}
						else
							throw new Error("Syntax Error! there need a ; in line "+line);
					}else 
						throw new Error("Syntax Error! Don't match return type or undefined varible in line "+line);
				
				case(Tag.FUNC):
				  String codeString=look.getToken_name();
				  loca.setInterString("call_function"); 
				  node func=null; 
				  root.Add(loca);
				  for(int i=0;i<roots.size();i++){
					 func=roots.get(i);
					 if(func.getEnvironment().getName().equals(look.getToken_name()))
						 break;	
				}
				loca.Add(func);
				codeString+="("+utility.Extract(code.getSourceCode(), '(', line)+")";
				call(code, environment, func);loca.setCode(codeString);
				break;
				
				case(Tag.BREAK):
					loca.setCode("break");  
				    loca.setInterString("break"); 
				    root.Add(loca); 
				    move(code);
				    if(match(';'))
				    	break;
				case('{'):
					 loca.setInterString("block"); 
				     root.Add(loca);
					 block(code,environment,utility.Extract("{"+code.getSourceCode(), '{',line).length(),loca);
			         break;
				case(';'):
				    break;
				case(Tag.EOF):
					break;
				default:
					    throw new Error("Syntax Error in line "+line);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private boolean SearchFunction()
	{
		boolean re=false;
		for(int i=0;i<roots.size();i++){
			node func=roots.get(i);
			if(func.getEnvironment().getName().equals(look.getToken_name())){
				re=true;
				break;	
			}
		}	
		return re;
	}
	
	private void Assign_Statement(SourceCode code,Environment environment,node root)
	{
		try {
			String name=look.getToken_name();
			if(!environment.searchRecord(name))
				throw new Error("Undefined Variable in line "+line);
			root.Add(new node(name,name, environment));
			move(code);
		    if(match(Tag.ASS)){
				root.Add(new node("=","=", null));
				StringBuilder rightS=new StringBuilder(utility.Instranction(code));
				String temp=rightS.toString();
				node bool=new node("","bool",environment);
				if(!bool(code,rightS.length(),environment,bool,rightS))
					throw new Error("There is syntax error in line "+line);
				else 
				{
					bool.setCode(rightS.toString().equals(NullString)?temp:rightS.toString());
					root.Add(bool);
					move(code);
					if(match(';'))
						return;
					else
						throw new Error("There need a ; in line "+line);
				}
			}else 
				throw new Error("Syntax Error in line "+line);
		} catch (Exception e) {
			e.printStackTrace();
			return;
		}
	}
	
	private void Condition_Statement(SourceCode code,Environment environment,node root)
	{
		try {
			node loca=new node(); 
			root.Add(new node("if","if", null));
			move(code);
			if(match('(')){
				StringBuilder boolstring=new StringBuilder(utility.Extract("("+code.getSourceCode(), '(',line));
				String temp=boolstring.toString();
				node bool=new node("","bool",environment);
				if(bool(code,boolstring.length(), environment,bool,boolstring))
				{
					bool.setCode(boolstring.toString().equals(NullString)?temp:boolstring.toString());
					root.Add(bool);move(code);
					if(!match(')'))
						throw new Error("Syntax Error in line "+line+", maybe need a )");
					else {
						move(code);
						loca.setInterString("stmt"); 
						loca.setEnvironment(environment);
						stmts(code, environment,loca);
						root.Add(loca); 
						move(code);
						if(match(Tag.ELSE))
						{
							root.Add(new node("else","else", null)); 
							move(code);
							node locb=new node();  
							locb.setInterString("stmt"); 
							locb.setEnvironment(environment);
							stmts(code, environment,locb);
							root.Add(locb);
						}else{
							if(look.getToken_name()!=null)
							   rollback(code);
						}	
					}
				}		
			}else
				throw new Error("Syntax error in line "+line+", maybe need a (");
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private void Loop_DO_WHILE_Statement(SourceCode code,Environment environment,node root)
	{
	  try {
		    node loca=new node(); 
		    root.Add(new node("do","do", null)); 
		    move(code);
		    loca.setInterString("stmt"); 
		    loca.setEnvironment(environment);
		    stmts(code, environment,loca); 
		    root.Add(loca);
   	        move(code);
   	        if(match(Tag.WHILE))
   	        {
   		      root.Add(new node("while","while", null)); 
   		      move(code);
   		      if (match('(')) {
   			    StringBuilder boolString=new StringBuilder(utility.Extract("("+code.getSourceCode(), '(',line));
   			    String temp=boolString.toString();
   			    node bool=new node("","bool",environment);
				  if(!bool(code,boolString.length(), environment,bool,boolString))
					 throw new Error("There is a syntax error in line "+line);
				  else {
					bool.setCode(boolString.toString().equals(NullString)?temp:boolString.toString());
					root.Add(bool); move(code);
					if(match(')')){
						int currentLine=line;
						move(code);
						if (match(';')) 
							return ;
						else 
							throw new Error("there need a ; in line "+currentLine);
						
					}else 
						throw new Error("Syntax Error in line "+line+" , maybe a )");
				}	
			}else 
				throw new Error("Syntax Error in line "+line+" , maybe a (");
   	 }else 
   		 throw new Error("Syntax Error in line "+line+" ,maybe a while");
		}catch (Exception e) {
			e.printStackTrace();
		}	
	}
	
	private void Loop_WHILE_Statement(SourceCode code,Environment environment,node root)
	{	
       try {
    	   node loca=new node();
			root.Add(new node("while","while", null)); 
			move(code);
			if(match('(')){
				StringBuilder boolString=new StringBuilder(utility.Extract("("+code.getSourceCode(), '(',line));
				String temp=boolString.toString(); 
				node bool=new node("","bool",environment);
				if(!bool(code,boolString.length(), environment,bool,boolString))
					throw new Error("There is a syntax error in line "+line);
				else{
					bool.setCode(boolString.toString().equals(NullString)?temp:boolString.toString());
					root.Add(bool);  move(code);
					if(match(')')){
					  move(code);
					  loca.setInterString("stmt"); 
					  loca.setEnvironment(environment);
					  stmts(code, environment,loca);  
					  root.Add(loca); 
					  }
					else 
						throw new Error("Syntax Error in line "+line+" , maybe a )");
					}			
			}else
				throw new Error("Syntax Error in line "+line+" , maybe a (");
	      
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	private boolean bool(SourceCode code, int length,Environment environment,node bool,StringBuilder inter)
	{
		boolean result=false;
		inter.setLength(0);
		Stack<SymbolRecord> TempSymbols=new Stack<SymbolRecord>();
		Stack<node> ast=new Stack<node>();
		SymbolRecord temprecord=new SymbolRecord();
		try {
			move(code);
			temprecord=lexer.getSymbolRecord();
			String input="";
			if(look.getTag()==Tag.STRING){
				if(environment.searchRecord(look.getToken_name())){
					input="basic";	
					TempSymbols.push(environment.getRecord(look.getToken_name()));
				}
			    else if(SearchFunction()){
			    	node func=null;
					  for(int i=0;i<roots.size();i++){
						 func=roots.get(i);
						 if(func.getEnvironment().getName().equals(look.getToken_name()))
							 break;	
					}
					call(code, environment, func);	
					bool.Add(func);
					bool.setInterString("call_function");
					return true;
			    }else
					throw new Error("Undefined Variable or Function  in line "+line);
			}else if((look.getTag()==Tag.TRUE)||(look.getTag()==Tag.FALSE)||(look.getTag()==Tag.NUM)||(look.getTag()==Tag.REAL))
			{
				input="basic";
				temprecord.setType(utility.type(look.getTag()));
				TempSymbols.push(temprecord);
			}
			else
				input=look.getToken_name();
			
			Action.Initial_ToState();
			Stack<Integer> state =new Stack<Integer>();
			state.push(0);
			ArrayList<String> temp=new ArrayList<String>();
			temp.add(0, "0"); //store temp node maked till now
			int stat;
			int count=look.getToken_name().length();
			SymbolRecord record=new SymbolRecord();
			while(true){
				stat=state.peek();
				 if(Action.action[stat][utility.Convert(input,line)]>=1&&Action.action[stat][utility.Convert(input,line)]<=26){
					temp.add(input); ast.push(new node("",input,environment));
					state.push(Action.action[stat][utility.Convert(input,line)]);
					
	                if(count==length)
	                	input="@";
	                else{
	                	move(code);
	                	temprecord=lexer.getSymbolRecord();
	                	count+=look.getToken_name().length();
	          
					   if(((look.getTag()==Tag.STRING)&&(environment.searchRecord(look.getToken_name())))
							   ||(look.getTag()==Tag.TRUE)||(look.getTag()==Tag.FALSE)||(look.getTag()==Tag.NUM)
							   ||(look.getTag()==Tag.REAL)){
						   input="basic";
						   if((record=environment.getRecord(look.getToken_name())).getType().getToken_name()!=null)
				      		   TempSymbols.push(record);
						   else {
							   temprecord.setType(utility.type(look.getTag()));
							   TempSymbols.push(temprecord);
						   }
					   }
					   else 
						   input=look.getToken_name();
				       }
				}else if(Action.action[stat][utility.Convert(input,line)]==-1)
					Action.Shift(state,TempSymbols,ast,temp, Action.ToState.get(stat),line,inter);
				else if(Action.action[stat][utility.Convert(input,line)]==27){//accept
					//System.out.println("Intermediate code is:");
					//System.out.print(inter.toString()+"\n");
					for(node child:ast.pop().getChilds())
						bool.Add(child);
					result=true;
					break;
				}else 
					throw new Error("There is a syntax error in line:"+line);	
			}
			return result;		
		} catch (Exception e) {
			e.printStackTrace();
			return result;
		}
	}

	public ArrayList<node> getRoots() {
		return roots;
	}
}

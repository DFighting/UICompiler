package Inter;

import java.util.ArrayList;

import Utility.utility;

import java.util.HashMap;
import java.util.Set;

import Symbol.Environment;
import Symbol.Type;

public class interCode {
	private static HashMap<node, String> JumpSet=new HashMap<>();
    //private static StringBuffer interCodeGen=new StringBuffer();
    private static HashMap<String, String> jumpSetGen=new HashMap<>();
	private static ArrayList<node> breakNodes=new ArrayList<node>();
	private static ArrayList<node> continueNodes=new ArrayList<node>();
	//private static node returnNode;
    private static node enNode=new node();
    private static int index=0;
    private final static String NullString=new String();
    public static String YFcode="";
    public static String Incode="";
    public static String Smbcode="";
    
	public static void CodeGen(ArrayList<node> roots){
		for(int i=0;i<roots.size();i++){
			node temproot=roots.get(i);
			for(int j=0;j<temproot.getChilds().size();j++)
				interCode.Stmt(temproot.getChilds().get(j),(j+1)<temproot.getChilds().size()?
						temproot.getChilds().get(j+1):((i+1)<roots.size()?roots.get(i+1):enNode));	
		}
		for(node root:roots)
			root.setCode(Code(root));
		Set<node> keys=JumpSet.keySet();
		for(node key:keys){
			if(key.getCode().equals(NullString))
				key.setCode(Code(key));
			jumpSetGen.put(JumpSet.get(key), key.getCode());
		}
		
		int num=jumpSetGen.size();
		for(int i=0;i<num;i++){
			String key="L"+i;
			YFcode +="  "+key+":\n";
			String[] inter=jumpSetGen.get(key).split("\n");
			int index=1;
			for(String temp:inter)
				YFcode += "    "+(index++)+" :  "+temp+"\n";
			YFcode +="\n";
		}
			
		for(node root:roots){
			printSymbols(root.getEnvironment());
		  Incode +="\n InterCode of  "+root.getEnvironment().getName()+"  is: \n";
		  String[] interCodes=root.getCode().split("\n");
		  for(int i=0;i<interCodes.length-1;i++)
			  Incode +="    "+(i+1)+" : "+interCodes[i]+"\n";
		  System.out.println();
		}
	}
	
	private static String Code(node root){
		ArrayList<node> childs=root.getChilds();
		StringBuilder code=new StringBuilder(root.getCode());
		for(node child:childs)
			code.append(child.getCode().equals(NullString)?Code(child):child.getCode());
		root.setCode(code.toString());
		return code.toString();
	}
	
	public static void put(node temp){
		if(!JumpSet.containsKey(temp)){
			JumpSet.put(temp, "L"+index);
			index++;
		}
		
	}
	
	private static String FormateCondition(String code){
		StringBuilder re=new StringBuilder();
		String[] temps=code.split("\n");
		for(String temp:temps)
			re.append("\t  "+temp+"\n");
		return re.toString();
	}
	
	private static void printSymbols(Environment environment){
		Smbcode +="\nSymbol Tables of "+environment.getName()+
				" Block with return "+Type.printType(environment.getReturn_tag())+" :\n";  
	  int clas=1;
	  Environment temp=new Environment(null);
	  temp=environment;
	  while(temp!=null){
		  if(temp.CurrentSymbols().size()>0)
			  Smbcode+="  Level "+(clas++)+" Symbols:\n"+temp.retValue();		  
		  temp=temp.getNext();
	  }
	}
	
	public static void Stmt(node root,node next){
		ArrayList<node> childs=root.getChilds();
		node bool=new node();
		ArrayList<node> tempchilds=new ArrayList<node>();
		switch(root.getInterString())
		{
			case("block"):
				for(int i=0;i<childs.size();i++)
					Stmt(childs.get(i),(i+1)<childs.size()?childs.get(i+1):next);
				break;
			case("condition"):
				bool=childs.get(1);
			    bool.setTrueNode(childs.get(2)); 
			    tempchilds=childs.get(2).getChilds();
			    for(int i=0;i<tempchilds.size();i++)
				   interCode.Stmt(tempchilds.get(i),(i+1)<tempchilds.size()?tempchilds.get(i+1):next);
			   if(childs.size()==3)
			    bool.setFalseNode(next);
			   else{
				  bool.setFalseNode(childs.get(4));
				  tempchilds=childs.get(4).getChilds();
				 for(int i=0;i<tempchilds.size();i++)
					 interCode.Stmt(tempchilds.get(i),(i+1)<tempchilds.size()?tempchilds.get(i+1):next);
			     }	//interCode
			   if(bool.getCode().charAt(bool.getCode().length()-1)!='\n')
					bool.setCode(bool.getCode()+"\n");
			   root.setCode("condition:\n"+FormateCondition(bool.getCode())+"  if true goto "+
					           JumpSet.get(bool.getTrueNode())+"\n"+"  if false goto "+
					           (JumpSet.containsKey(bool.getFalseNode())?"next\n":JumpSet.get(bool.getFalseNode())));
				break;
			case("do_while"):
			    bool= childs.get(3); 
			    tempchilds=childs.get(1).getChilds();
			    breakNodes.add(next); continueNodes.add(childs.get(1));
			    for(int i=0;i<tempchilds.size();i++)
				  interCode.Stmt(tempchilds.get(i),(i+1)<tempchilds.size()?tempchilds.get(i+1):next);
			    bool.setTrueNode(childs.get(1));
			    bool.setFalseNode(next);
			    utility.Remove(breakNodes, next);
			    utility.Remove(continueNodes, childs.get(1));
			    if(bool.getCode().charAt(bool.getCode().length()-1)!='\n')
					bool.setCode(bool.getCode()+"\n");
			    root.setCode("condition:\n"+FormateCondition(bool.getCode())+"  if true goto "+
				           JumpSet.get(bool.getTrueNode())+"\n"+"  if false goto "+
				           (JumpSet.containsKey(bool.getFalseNode())?"next\n":JumpSet.get(bool.getFalseNode())));
				break;
			case("while"):
				bool=childs.get(1); 
			    tempchilds=childs.get(2).getChilds();
				breakNodes.add(next); continueNodes.add(childs.get(2));
			    bool.setTrueNode(childs.get(2));
			    for(int i=0;i<tempchilds.size();i++)
				   interCode.Stmt(tempchilds.get(i),(i+1)<tempchilds.size()?tempchilds.get(i+1):next);
			    bool.setFalseNode(next);
			    utility.Remove(breakNodes, next);
			    utility.Remove(continueNodes, childs.get(2));
			    if(bool.getCode().charAt(bool.getCode().length()-1)!='\n')
					bool.setCode(bool.getCode()+"\n");
			    root.setCode("condition:\n"+FormateCondition(bool.getCode())+"  if true goto "+
				           JumpSet.get(bool.getTrueNode())+"\n"+"  if false goto "+
				           (JumpSet.containsKey(bool.getFalseNode())?"next\n":JumpSet.get(bool.getFalseNode())));
				break;
			case("assign"):
				int index=childs.get(2).getCode().lastIndexOf('=');
			    if(index==-1)
				   root.setCode(childs.get(0).getCode()+"="+childs.get(2).getCode()+";\n");
			    else{
				   StringBuilder code=new StringBuilder(childs.get(2).getCode());
				   code.replace(index-2, index, childs.get(0).getCode());
				   root.setCode(code.toString());
				   if(childs.get(2).getInterString()=="call_function")
					  Stmt(childs.get(2), next);	  
			    }
				break;
			case("call_function"):
				//ADD Function Code
				root.setCode(root.getCode()+";\n");
			    root.setTrueNode(next);
			    root.setCode(root.getCode()+"goto "+JumpSet.get(next)+";\n");
			    break;
			case("break"):
				root.setTrueNode(breakNodes.get(breakNodes.size()-1));
			    root.setCode("goto "+JumpSet.get(root.getTrueNode()));
			    break;
			case("continue"):
				root.setTrueNode(continueNodes.get(continueNodes.size()-1));
			    root.setCode("goto "+JumpSet.get(root.getTrueNode()));
			    break;
			/*case("return"):
				 if(returnNode.equals(null))
					throw new Error("Null Return Node");
				 root.setTrueNode(returnNode);*/
			default:
				break;
		}
	}
}

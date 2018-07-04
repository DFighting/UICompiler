package UI;

import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.layout.FormAttachment;
import org.eclipse.swt.layout.FormData;
import org.eclipse.swt.layout.FormLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import java.awt.Font;
import java.io.IOException;

import com.cloudgarden.resource.SWTResourceManager;

import Inter.interCode;
import Inter.node;
import LexAnalysis.Lexer;
import Symbol.Token;
import SynAnalysis.Parser;
import Utility.SourceCode;
import Utility.utility;

import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.graphics.Rectangle;

import java.awt.BorderLayout;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTree;
import javax.swing.WindowConstants;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeCellRenderer;

import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.ScrolledComposite;
import org.eclipse.swt.custom.StyledText;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.events.MouseWheelListener;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;

/**
* This code was edited or generated using CloudGarden's Jigloo
* SWT/Swing GUI Builder, which is free for non-commercial
* use. If Jigloo is being used commercially (ie, by a corporation,
* company or business for any purpose whatever) then you
* should purchase a license for each developer using Jigloo.
* Please visit www.cloudgarden.com for details.
* Use of Jigloo implies acceptance of these licensing terms.
* A COMMERCIAL LICENSE HAS NOT BEEN PURCHASED FOR
* THIS MACHINE, SO JIGLOO OR THIS CODE CANNOT BE USED
* LEGALLY FOR ANY CORPORATE OR COMMERCIAL PURPOSE.
*/
public class UImain extends org.eclipse.swt.widgets.Composite {

	{
		//Register as a resource user - SWTResourceManager will
		//handle the obtaining and disposing of resources
		SWTResourceManager.registerResourceUser(this);
	}
	
	private Button FHButton;
	private Button YFButton;
	private Button CFButton;
	private Label Title;
	private ScrolledComposite scrolledComposite1;
	private Button ASTButton;
	private Button ZJButton;
	private StyledText Code;
	private String Result="";
	
	/**
	* Auto-generated main method to display this 
	* org.eclipse.swt.widgets.Composite inside a new Shell.
	 * @throws IOException 
	*/
	public static void main(String[] args) throws Exception {
		SourceCode code=new SourceCode();
		code.setPath("code.txt");
		code.Input();
		code.setSourceCode(code.getSourceCode()+"@");
		Lexer lexer=new Lexer();
	    Parser parser=new Parser(lexer);
	    parser.program(code);
	    interCode.CodeGen(parser.getRoots());
		showGUI();
	}
	
	/**
	* Overriding checkSubclass allows this class to extend org.eclipse.swt.widgets.Composite
	*/	
	protected void checkSubclass() {
	}
	
	/**
	* Auto-generated method to display this 
	* org.eclipse.swt.widgets.Composite inside a new Shell.
	*/
	public static void showGUI() {
		Display display = Display.getDefault();
		Shell shell = new Shell(display);
		UImain inst = new UImain(shell, SWT.NULL);
		Point size = inst.getSize();
		shell.setLayout(new FillLayout());
		shell.layout();
		if(size.x == 0 && size.y == 0) {
			inst.pack();
			shell.pack();
		} else {
			Rectangle shellBounds = shell.computeTrim(0, 0, size.x, size.y);
			shell.setSize(shellBounds.width, shellBounds.height);
		}
		shell.open();
		while (!shell.isDisposed()) {
			if (!display.readAndDispatch())
				display.sleep();
		}
	}

	public UImain(org.eclipse.swt.widgets.Composite parent, int style) {
		super(parent, style);
		initGUI();
	}

	private void initGUI() {
		try {
			FormLayout thisLayout = new FormLayout();
			this.setBackground(SWTResourceManager.getColor(0, 64, 64));
			this.setForeground(SWTResourceManager.getColor(0, 64, 64));
			this.setLayout(thisLayout);
			this.setSize(804, 440);
			{
				ASTButton = new Button(this, SWT.PUSH | SWT.CENTER);
				FormData ASTButtonLData = new FormData();
				ASTButtonLData.left =  new FormAttachment(0, 1000, 656);
				ASTButtonLData.top =  new FormAttachment(0, 1000, 283);
				ASTButtonLData.width = 117;
				ASTButtonLData.height = 37;
				ASTButton.setLayoutData(ASTButtonLData);
				ASTButton.setText("\u62bd\u8c61\u8bed\u6cd5\u6811");
				ASTButton.setBackground(SWTResourceManager.getColor(255, 255, 198));
				ASTButton.setFont(SWTResourceManager.getFont("Arial", 11, 1, false, false));
				ASTButton.addSelectionListener(new SelectionAdapter() {
					public void widgetSelected(SelectionEvent evt) {
						try {
							ASTButtonWidgetSelected(evt);
						} catch (Exception e) {
							e.printStackTrace();
						}
					}
				});
			}
			{
				ZJButton = new Button(this, SWT.PUSH | SWT.CENTER);
				FormData ZJButtonLData = new FormData();
				ZJButtonLData.left =  new FormAttachment(0, 1000, 656);
				ZJButtonLData.top =  new FormAttachment(0, 1000, 358);
				ZJButtonLData.width = 117;
				ZJButtonLData.height = 37;
				ZJButton.setLayoutData(ZJButtonLData);
				ZJButton.setText("\u4e2d\u95f4\u4ee3\u7801");
				ZJButton.setBackground(SWTResourceManager.getColor(255, 255, 198));
				ZJButton.setFont(SWTResourceManager.getFont("Arial", 11, 1, false, false));
				ZJButton.addSelectionListener(new SelectionAdapter() {
					public void widgetSelected(SelectionEvent evt) {
						try {
							ZJButtonWidgetSelected(evt);
						} catch (Exception e) {
							e.printStackTrace();
						}
					}
				});
			}
			{
				FormData scrolledComposite1LData = new FormData();
				scrolledComposite1LData.width = 604;
				scrolledComposite1LData.height = 373;
				scrolledComposite1LData.left =  new FormAttachment(0, 1000, 23);
				scrolledComposite1LData.top =  new FormAttachment(0, 1000, 42);
				scrolledComposite1 = new ScrolledComposite(this, SWT.NONE);
				FormLayout scrolledComposite1Layout = new FormLayout();
				scrolledComposite1.setLayout(scrolledComposite1Layout);
				scrolledComposite1.setLayoutData(scrolledComposite1LData);
				scrolledComposite1.setEnabled(true);
				scrolledComposite1.setDragDetect(true);
				scrolledComposite1.setExpandHorizontal(true);
				scrolledComposite1.setExpandVertical(true);
				scrolledComposite1.setTouchEnabled(true);
				scrolledComposite1.setVisible(true);
				scrolledComposite1.setLayoutDeferred(false);
				scrolledComposite1.setShowFocusedControl(true);
				scrolledComposite1.setAlwaysShowScrollBars(true);
				scrolledComposite1.addMouseWheelListener(new MouseWheelListener() {
					public void mouseScrolled(MouseEvent evt) {
						System.out.println("scrolledComposite1.mouseScrolled, event="+evt);
						//TODO add your code for scrolledComposite1.mouseScrolled
					}
				});
				{
					Code = new StyledText(scrolledComposite1, SWT.MULTI | SWT.WRAP | SWT.H_SCROLL | SWT.V_SCROLL);
					FormLayout CodeLayout = new FormLayout();
					Code.setLayout(CodeLayout);
					scrolledComposite1.setContent(Code);
					FormData styledText1LData = new FormData();
					Code.setLayoutData(styledText1LData);
					Code.setText("Please input your code...");
					Code.setBackground(SWTResourceManager.getColor(234, 249, 234));
					Code.setTouchEnabled(true);
					Code.setFont(SWTResourceManager.getFont("Arial", 10, 0, false, false));
					Code.setLeftMargin(10);
					Code.setJustify(true);
					Code.setOrientation(SWT.HORIZONTAL);
					Code.setLineSpacing(2);

				}
			}

			{
				FHButton = new Button(this, SWT.PUSH | SWT.CENTER);
				FormData FHButtonLData = new FormData();
				FHButtonLData.left =  new FormAttachment(0, 1000, 656);
				FHButtonLData.top =  new FormAttachment(0, 1000, 208);
				FHButtonLData.width = 117;
				FHButtonLData.height = 37;
				FHButton.setLayoutData(FHButtonLData);
				FHButton.setText("\u7b26\u53f7\u8868");
				FHButton.setBackground(SWTResourceManager.getColor(255, 255, 198));
				FHButton.setFont(SWTResourceManager.getFont("Arial", 11, 1, false, false));
				FHButton.addSelectionListener(new SelectionAdapter() {
					public void widgetSelected(SelectionEvent evt) {
						try {
							FHButtonWidgetSelected(evt);
						} catch (Exception e) {
							e.printStackTrace();
						}
					}
				});
			}
			{
				YFButton = new Button(this, SWT.PUSH | SWT.CENTER);
				FormData YFButtonLData = new FormData();
				YFButtonLData.left =  new FormAttachment(0, 1000, 656);
				YFButtonLData.top =  new FormAttachment(0, 1000, 133);
				YFButtonLData.width = 117;
				YFButtonLData.height = 37;
				YFButton.setLayoutData(YFButtonLData);
				YFButton.setText("\u8bed\u6cd5\u5206\u6790");
				YFButton.setBackground(SWTResourceManager.getColor(255, 255, 198));
				YFButton.setFont(SWTResourceManager.getFont("Arial", 11, 1, false, false));
				YFButton.addSelectionListener(new SelectionAdapter() {
					public void widgetSelected(SelectionEvent evt) {
						try {
							YFButtonWidgetSelected(evt);
						} catch (Exception e) {
							e.printStackTrace();
						}
					}
				});
			}
			{
				CFButton = new Button(this, SWT.PUSH | SWT.CENTER);
				FormData CFButtonLData = new FormData();
				CFButtonLData.left =  new FormAttachment(0, 1000, 656);
				CFButtonLData.top =  new FormAttachment(0, 1000, 58);
				CFButtonLData.width = 117;
				CFButtonLData.height = 37;
				CFButton.setLayoutData(CFButtonLData);
				CFButton.setText("\u8bcd\u6cd5\u5206\u6790");
				CFButton.setBackground(SWTResourceManager.getColor(255, 255, 198));
				CFButton.setFont(SWTResourceManager.getFont("Arial", 11, 1, false, false));
				CFButton.addSelectionListener(new SelectionAdapter() {
					public void widgetSelected(SelectionEvent evt) {
						try {
							CFButtonWidgetSelected(evt);
						}  catch (Exception e) {
							e.printStackTrace();
						}
					}
				});
			}
			{
				Title = new Label(this, SWT.NONE);
				FormData TitleLData = new FormData();
				TitleLData.left =  new FormAttachment(0, 1000, 198);
				TitleLData.top =  new FormAttachment(0, 1000, 3);
				TitleLData.width = 248;
				TitleLData.height = 44;
				Title.setLayoutData(TitleLData);
				Title.setText("\u5c0f\u578bJAVA\u7f16\u8bd1\u5668");
				Title.setBackground(SWTResourceManager.getColor(0, 64, 64));
				Title.setFont(SWTResourceManager.getFont("Arial", 15, 1, false, false));
				Title.setEnabled(false);
			}
			this.layout();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	

	
	
	private void CFButtonWidgetSelected(SelectionEvent evt) throws Exception {
		SourceCode test=new SourceCode();
		test.setPath("code.txt");
		test.Input();
		test.setSourceCode(test.getSourceCode()+"@");
		Lexer lex=new Lexer();
		Token re=lex.lexer(test);
		
		Result += "Scanner result is:"+"\r\n";
		int index=1;
		while(re.getToken_name()!="@")
		{
			Result +=String.format("%s %s%16s   %-10s%10s %s\n","No:", (index++),"Name :",re.getToken_name(),"Tag:",re.getTag());
			re=lex.lexer(test);
			
		}	
		Code.setText(Result);	
		
	}
	
	private void YFButtonWidgetSelected(SelectionEvent evt) throws Exception {
		//TODO add your code for YFButton.widgetSelected
		Code.setText(interCode.YFcode);
		//add more code if needed
		
	}
	
	private void ZJButtonWidgetSelected(SelectionEvent evt) throws Exception {
		//TODO add your code for ZJButton.widgetSelected
		Code.setText(interCode.Incode);
		//add more code if needed
	}
	
	private void FHButtonWidgetSelected(SelectionEvent evt) throws Exception {
		//TODO add your code for FHButton.widgetSelected
		Code.setText(interCode.Smbcode);
		//add more code if needed
	}
	
	private void ASTButtonWidgetSelected(SelectionEvent evt) throws Exception {
		//TODO add your code for ASTButton.widgetSelected
		SourceCode test=new SourceCode();
		test.setPath("code.txt");
		test.Input(); 
		Lexer lex=new Lexer();
		Parser par=new Parser(lex);
		par.program(test);
		ArrayList<node> roots = par.getRoots();
		  ArrayList<DefaultMutableTreeNode> treeNodes=new ArrayList<DefaultMutableTreeNode>();
		  for(int i=0;i<roots.size();i++){
			  node root=roots.get(i);
			  DefaultMutableTreeNode node=new DefaultMutableTreeNode(root.getInterString());
			  treeNodes.add(node);
			  utility.ChildADD(root, node);
		  }
		  DefaultMutableTreeNode rootNode=new DefaultMutableTreeNode("AST");
		  for(int i=0;i<treeNodes.size();i++)
			  rootNode.add(treeNodes.get(i));
		  
 
		  JFrame jf = new JFrame("AST");
	      jf.setSize(1200, 800);
	      jf.setLocationRelativeTo(null);
	      jf.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
	      JPanel panel = new JPanel(new BorderLayout());
	      JTree tree = new JTree(rootNode);
	      tree.setRowHeight(40);  
	      tree.setFont(new Font("Arial", 0, 30)); 
	      
	      DefaultTreeCellRenderer treeCellRenderer = (DefaultTreeCellRenderer) tree.getCellRenderer();  
	      treeCellRenderer.setLeafIcon(null);  
	      treeCellRenderer.setClosedIcon(null);  
	      treeCellRenderer.setOpenIcon(null); 

	      tree.setShowsRootHandles(true);
	      JScrollPane scrollPane = new JScrollPane(tree);
	      panel.add(scrollPane, BorderLayout.CENTER);
	      jf.setContentPane(panel);
	      jf.setVisible(true);
	      jf.setFont(new java.awt.Font("Arial",0,14));
	      jf.setEnabled(true);
	}
	
}

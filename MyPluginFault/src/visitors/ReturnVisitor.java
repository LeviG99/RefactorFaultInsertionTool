package visitors;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.jdt.core.dom.ASTVisitor;
import org.eclipse.jdt.core.dom.IBinding;
import org.eclipse.jdt.core.dom.ReturnStatement;
import org.eclipse.jdt.core.dom.SimpleName;
import org.eclipse.jdt.core.dom.Type;

public class ReturnVisitor extends ASTVisitor {
    public List<ReturnStatement> names = new ArrayList<>();
    private String variableName;
    private VisitorUtil util = new VisitorUtil();
    private boolean check;
	private String code;
	private Type type;
    public ReturnVisitor(String name,boolean check, String code, Type type) {
    	super();
    	variableName = name;
    	this.check = check;
    	this.code = code;
    	this.type = type;
    }
    @Override
    public boolean visit(ReturnStatement node) {
		if (node instanceof ReturnStatement) {
			if(check && node.toString().equals(code)) {
    		node.setExpression(util.changeValue(node,type));
			}
			System.out.println(node);
			System.out.println(node.getExpression());

			if(util.counter(node)){
    		names.add(node);
			}
    	}
    	return super.visit(node);
  
    }

    public List<ReturnStatement> getNames() {
        return names;
    }
}
package visitors;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.jdt.core.dom.ASTNode;
import org.eclipse.jdt.core.dom.ASTVisitor;
import org.eclipse.jdt.core.dom.IBinding;
import org.eclipse.jdt.core.dom.ReturnStatement;
import org.eclipse.jdt.core.dom.SimpleName;
import org.eclipse.jdt.core.dom.Statement;
import visitors.VisitorUtil;

public class ReturnVisitor extends ASTVisitor {
    public List<ReturnStatement> names = new ArrayList<>();
    private String variableName;
    private VisitorUtil util = new VisitorUtil();

    public ReturnVisitor(String name) {
    	super();
    	variableName = name;
    }
    @Override
    public boolean visit(ReturnStatement node) {
    	if (node instanceof ReturnStatement && util.counter(node)) {
    		names.add(node);
    	}
    	return super.visit(node);
  
    }

    public List<ReturnStatement> getNames() {
        return names;
    }
}
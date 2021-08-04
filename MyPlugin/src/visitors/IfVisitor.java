package visitors;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.jdt.core.dom.ASTVisitor;
import org.eclipse.jdt.core.dom.IBinding;
import org.eclipse.jdt.core.dom.IfStatement;
import org.eclipse.jdt.core.dom.SimpleName;
import visitors.VisitorUtil;
public class IfVisitor extends ASTVisitor {
    public List<IfStatement> names = new ArrayList<>();
    private String variableName;
    private VisitorUtil util = new VisitorUtil();
    
    public IfVisitor(String name) {
    	super();
    	variableName = name;
    }
    @Override
    public boolean visit(IfStatement node) {
    	if (node instanceof IfStatement && util.counter(node)) {
    		names.add(node);
    	}
    	return super.visit(node);
  
    }

    public List<IfStatement> getNames() {
        return names;
    }
}
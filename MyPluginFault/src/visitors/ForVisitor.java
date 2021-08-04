package visitors;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.jdt.core.dom.ASTVisitor;
import org.eclipse.jdt.core.dom.IBinding;
import org.eclipse.jdt.core.dom.ForStatement;
import org.eclipse.jdt.core.dom.SimpleName;
import visitors.VisitorUtil;
public class ForVisitor extends ASTVisitor {
    public List<ForStatement> names = new ArrayList<>();
    private String variableName;
    private VisitorUtil util = new VisitorUtil();
    
    public ForVisitor(String name) {
    	super();
    	variableName = name;
    }
    @Override
    public boolean visit(ForStatement node) {
    	if (node instanceof ForStatement && util.counter(node)) {
    		names.add(node);
    	}
    	return super.visit(node);
  
    }

    public List<ForStatement> getNames() {
        return names;
    }
}
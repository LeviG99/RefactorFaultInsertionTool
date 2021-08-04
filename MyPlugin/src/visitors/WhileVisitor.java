package visitors;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.jdt.core.dom.ASTVisitor;
import org.eclipse.jdt.core.dom.IBinding;
import org.eclipse.jdt.core.dom.WhileStatement;
import org.eclipse.jdt.core.dom.SimpleName;
import visitors.VisitorUtil;

public class WhileVisitor extends ASTVisitor {
    public List<WhileStatement> names = new ArrayList<>();
    private String variableName;
    private VisitorUtil util = new VisitorUtil();

    public WhileVisitor(String name) {
    	super();
    	variableName = name;
    }
    @Override
    public boolean visit(WhileStatement node) {
    	if (node instanceof WhileStatement && util.counter(node)) {
    		names.add(node);
    	}
    	return super.visit(node);
  
    }

    public List<WhileStatement> getNames() {
        return names;
    }
}
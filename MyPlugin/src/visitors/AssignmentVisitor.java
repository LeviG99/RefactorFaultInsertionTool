package visitors;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.eclipse.jdt.core.dom.ASTVisitor;
import org.eclipse.jdt.core.dom.Assignment;
import org.eclipse.jdt.core.dom.ExpressionStatement;
import org.eclipse.jdt.core.dom.SimpleName;
import org.eclipse.jdt.core.dom.TryStatement;
import visitors.VisitorUtil;

public class AssignmentVisitor extends ASTVisitor {
    private List<ExpressionStatement> assignments = new ArrayList<>();
    private String variableName;
    private VisitorUtil util = new VisitorUtil();

    public AssignmentVisitor(String name) {
    	super();
    	variableName = name;
    }

    public boolean visit(Assignment node) {
    	SimpleNameVisitor simpleNameVisitor = new SimpleNameVisitor();
    	node.getLeftHandSide().accept(simpleNameVisitor);
    	
    	Iterator<SimpleName> namesIt = simpleNameVisitor.getNames().iterator();
    	
    	while(namesIt.hasNext()) {
    		if(namesIt.next().toString().equals(variableName) && !(node.getParent().getParent() instanceof TryStatement)&& util.counter(node.getParent())) {
    			assignments.add((ExpressionStatement)node.getParent());
    			break;
    		}
    	}
    	
    	return true;
    }

    public List<ExpressionStatement> getAssignments() {
        return assignments;
    }
}
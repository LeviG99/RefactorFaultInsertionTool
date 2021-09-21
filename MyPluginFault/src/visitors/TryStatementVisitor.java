package visitors;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.jdt.core.dom.ASTVisitor;
import org.eclipse.jdt.core.dom.MethodInvocation;
import org.eclipse.jdt.core.dom.TryStatement;

public class TryStatementVisitor extends ASTVisitor {
    private List<TryStatement> tryStatements = new ArrayList<>();
    private VisitorUtil util = new VisitorUtil();

    @Override
    public boolean visit(TryStatement node) {
    	if(util.counter(node))
        tryStatements.add(node);
    	return super.visit(node);
    }

    public List<TryStatement> getTryStatements() {
        return tryStatements;
    }
}
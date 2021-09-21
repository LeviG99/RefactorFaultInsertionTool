package visitors;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.jdt.core.dom.ASTVisitor;
import org.eclipse.jdt.core.dom.MethodInvocation;
import org.eclipse.jdt.core.dom.SwitchStatement;

public class SwitchVisitor extends ASTVisitor {
    private List<SwitchStatement> switchStatements = new ArrayList<>();
    private VisitorUtil util = new VisitorUtil();

    @Override
    public boolean visit(SwitchStatement node) {
    	if(util.counter(node))
        switchStatements.add(node);
    	return super.visit(node);
    }

    public List<SwitchStatement> getSwitchStatements() {
        return switchStatements;
    }
}
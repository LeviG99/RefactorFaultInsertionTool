package visitors;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.jdt.core.dom.ASTVisitor;
import org.eclipse.jdt.core.dom.MethodDeclaration;

public class MethodDeclarationVisitor extends ASTVisitor {
    private List<MethodDeclaration> methods = new ArrayList<>();
    private VisitorUtil util = new VisitorUtil();

    @Override
    public boolean visit(MethodDeclaration node) {
    	if (util.counter(node)) methods.add(node);
        return super.visit(node);
    }

    public List<MethodDeclaration> getMethods() {
        return methods;
    }
}
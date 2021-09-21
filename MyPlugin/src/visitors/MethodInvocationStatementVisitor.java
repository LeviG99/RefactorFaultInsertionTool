package visitors;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.eclipse.jdt.core.dom.ASTVisitor;
import org.eclipse.jdt.core.dom.ExpressionStatement;
import org.eclipse.jdt.core.dom.MethodInvocation;
import org.eclipse.jdt.core.dom.SimpleName;
import org.eclipse.jdt.core.dom.TryStatement;
import org.eclipse.jdt.core.dom.VariableDeclarationFragment;
import org.eclipse.jdt.core.dom.VariableDeclarationStatement;

public class MethodInvocationStatementVisitor extends ASTVisitor {
	private List<ExpressionStatement> methodInvocations = new ArrayList<>();
	private String variableName;
    private VisitorUtil util = new VisitorUtil();

	public MethodInvocationStatementVisitor(String name) {
		super();
		variableName = name;
	}

	@Override
	public boolean visit(MethodInvocation node) {
		if (node.getParent() instanceof ExpressionStatement) {
			SimpleNameVisitor simpleNameVisitor = new SimpleNameVisitor();

			node.accept(simpleNameVisitor);

			Iterator<SimpleName> namesIt = simpleNameVisitor.getNames().iterator();

			while (namesIt.hasNext() && util.counter(node.getParent())) {
				if (namesIt.next().toString().equals(variableName) && !(node.getParent().getParent() instanceof TryStatement)) {
					methodInvocations.add((ExpressionStatement) node.getParent());
					break;
				}
			}
		}

		return super.visit(node);
	}

	public List<ExpressionStatement> getMethodInvocations() {
		return methodInvocations;
	}
}
package visitors;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.eclipse.jdt.core.dom.ASTVisitor;
import org.eclipse.jdt.core.dom.TryStatement;
import org.eclipse.jdt.core.dom.VariableDeclarationFragment;
import org.eclipse.jdt.core.dom.VariableDeclarationStatement;

public class VariableDeclarationStatementVisitor extends ASTVisitor {
    private List<VariableDeclarationStatement> variableDeclarationStatements = new ArrayList<>();
    private String variableName;
    
    public VariableDeclarationStatementVisitor(String name) {
    	super();
    	variableName = name;
    }

    @Override
    public boolean visit(VariableDeclarationStatement node) {
    	VariableDeclarationFragmentVisitor fragmentVisitor = new VariableDeclarationFragmentVisitor();
    	node.accept(fragmentVisitor);
    	
    	Iterator<VariableDeclarationFragment> fragmentsIt = fragmentVisitor.getDeclarationFragments().iterator();
    	
    	while(fragmentsIt.hasNext()) {
    		if(fragmentsIt.next().getName().toString().equals(variableName) && !(node.getParent().getParent() instanceof TryStatement)) {
    			variableDeclarationStatements.add(node);
    			break;
    		}
    	}
    	
    	return super.visit(node);
    }

    public List<VariableDeclarationStatement> getDeclarations() {
        return variableDeclarationStatements;
    }
}
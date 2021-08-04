package visitors;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.jdt.core.dom.ASTVisitor;
import org.eclipse.jdt.core.dom.VariableDeclarationFragment;
import org.eclipse.jdt.core.dom.VariableDeclarationStatement;

public class VariableDeclarationFragmentVisitor extends ASTVisitor {
	 private List<VariableDeclarationFragment> variableDeclarationsFragments = new ArrayList<>();

	    @Override
	    public boolean visit(VariableDeclarationFragment node) {
	    	variableDeclarationsFragments.add(node);
	    	return super.visit(node);
	    }

	    public List<VariableDeclarationFragment> getDeclarationFragments() {
	        return variableDeclarationsFragments;
	    }
}
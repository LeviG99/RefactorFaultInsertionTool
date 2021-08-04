package visitors;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.jdt.core.dom.ASTVisitor;
import org.eclipse.jdt.core.dom.IBinding;
import org.eclipse.jdt.core.dom.SimpleName;

public class SimpleNameVisitor extends ASTVisitor {
    private List<SimpleName> names = new ArrayList<>();

    @Override
    public boolean visit(SimpleName node) {
    	if(node.resolveBinding()!= null && node.resolveBinding().getKind() == IBinding.VARIABLE )
    		names.add(node);
    	
    	return super.visit(node);
    }

    public List<SimpleName> getNames() {
        return names;
    }
}
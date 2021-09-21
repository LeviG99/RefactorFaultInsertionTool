package visitors;

import org.eclipse.jdt.core.dom.ASTNode;
import org.eclipse.jdt.core.dom.MethodDeclaration;


public class VisitorUtil {
	//Dois niveis acima, checa se é methoddeclaration, checar se pai do pai é null
	public boolean counter(ASTNode node) {
		if(node.getParent() == null || node.getParent().getParent() == null) {
			return false;
		}
		int i = 0;
    	while(node.getRoot()!= node) {
    		i++;
    		node = node.getParent();
    		if(node instanceof MethodDeclaration) {
    			break;
    		}
    	}
    	return i<=2;
    }
}

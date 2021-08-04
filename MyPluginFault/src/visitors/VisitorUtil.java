package visitors;

import org.eclipse.jdt.core.dom.ASTNode;
import org.eclipse.jdt.core.dom.ASTVisitor;
import org.eclipse.jdt.core.dom.Expression;
import org.eclipse.jdt.core.dom.IBinding;
import org.eclipse.jdt.core.dom.MethodDeclaration;
import org.eclipse.jdt.core.dom.NumberLiteral;
import org.eclipse.jdt.core.dom.ReturnStatement;
import org.eclipse.jdt.core.dom.SimpleName;
import org.eclipse.jdt.core.dom.StringLiteral;
import org.eclipse.jdt.core.dom.Type;

public class VisitorUtil { 
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
	//Lembrar que String, Integer são object, colocar Object no final para pegar objects diferentes dos tipos base
	public Expression changeValue(ReturnStatement node, Type type) {
		if(type == null) {
			return null;
		}
		String typeString = type.toString();
		if(typeString.equals("String")) {
			if(node.getExpression().toString().isEmpty()) return node.getAST().newStringLiteral();
			else {
				StringLiteral x = node.getAST().newStringLiteral();
				x.setLiteralValue("_");
				return x;
			}
		}
		else if(typeString.equals("Integer")||typeString.equals("int") ) {
			if(node.getExpression().toString().equals("1")) {
			NumberLiteral xI = node.getAST().newNumberLiteral();
			xI.setToken("2");
			return xI;
			}else {
			NumberLiteral yI = node.getAST().newNumberLiteral();
			yI.setToken("1");
			return yI;
			}
		}
		else {
			if(!node.getExpression().toString().equals("null")) return node.getAST().newReturnStatement().getExpression();
			else return node.getAST().newNullLiteral();
		}
	}
}

package visitors;

import javax.transaction.xa.Xid;

import org.eclipse.jdt.core.dom.ASTNode;
import org.eclipse.jdt.core.dom.ASTVisitor;
import org.eclipse.jdt.core.dom.BooleanLiteral;
import org.eclipse.jdt.core.dom.CharacterLiteral;
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
			StringLiteral x = node.getAST().newStringLiteral();
			x.setLiteralValue("_");
			if(node.getExpression().toString().equals(x.toString())) {
				x.setLiteralValue("1");
			}
			return x;
			
		}
		else if(typeString.equals("char")) {
			CharacterLiteral x = node.getAST().newCharacterLiteral();
			x.setCharValue('_');
			if(node.getExpression().toString().equals(x.toString())) {
				x.setCharValue('1');
			}
			return x;
			
		}else if(typeString.equals("boolean")) {
			BooleanLiteral x = node.getAST().newBooleanLiteral(false);
			if(node.getExpression().toString().equals(x.toString())) {
				x.setBooleanValue(true);
			}
			return x;
		}
		else if(typeString.equals("Integer")||typeString.equals("int")||typeString.equals("float")
				||typeString.equals("short")||typeString.equals("byte")||typeString.equals("double") || typeString.equals("long")) {
			if(node.getExpression().toString().equals("1")|| node.getExpression().toString().equals("1.0")) {
			NumberLiteral xI = node.getAST().newNumberLiteral();
			//trocar valores 2 por -1
			xI.setToken("2");
			return xI;
			}else {
			NumberLiteral yI = node.getAST().newNumberLiteral();
			yI.setToken("1");
			return yI;
			}
		}
		else {
			if(!node.getExpression().toString().equals("null")) return node.getAST().newNullLiteral();
			else return node.getAST().newNullLiteral();
		}
	}
}

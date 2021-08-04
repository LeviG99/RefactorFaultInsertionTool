package visitors;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.jdt.core.dom.ASTVisitor;
import org.eclipse.jdt.core.dom.MethodInvocation;

public class AssertVisitor extends ASTVisitor {
	public static final String ASSERT_REGEX = ".*assert.*(True|False|Equals|NotEquals|Same|NotSame|That|Null|NotNull|ArrayEquals|Throws)(.*\\n)*";
	private List<MethodInvocation> methods = new ArrayList<>();

    @Override
    public boolean visit(MethodInvocation node) {
        if(node.getName().toString().matches(ASSERT_REGEX))
    	   methods.add(node);
        
    	return super.visit(node);
    }

    public List<MethodInvocation> getMethods() {
        return methods;
    }
}
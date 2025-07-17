import java.util.ArrayList;
import java.util.List;

import org.testng.IMethodInstance;
import org.testng.IMethodInterceptor;
import org.testng.ITestContext;
import org.testng.annotations.Test;

public class MethodInterceptor implements IMethodInterceptor {

	public List<IMethodInstance> intercept(List<IMethodInstance> methods, ITestContext context) {
		// TODO Auto-generated method stub
		List<IMethodInstance> result = new ArrayList<IMethodInstance>();
		for (IMethodInstance method : methods) {
		Test testMethod = method.getMethod().getConstructorOrMethod().getMethod().getAnnotation(Test.class);
		if (testMethod.enabled()) {
		//System.out.println(context.getOutputDirectory());
		result.add(method);
		}
		}
		
		return result;
		}
}

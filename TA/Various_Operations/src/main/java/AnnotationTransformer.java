import java.lang.reflect.Constructor;
import java.lang.reflect.Method;

import org.testng.IAnnotationTransformer;
import org.testng.annotations.ITestAnnotation;

public class AnnotationTransformer implements IAnnotationTransformer {

	public void transform(ITestAnnotation testAnnotation, Class testClass, Constructor testConstructor,
			Method testMethod) {
		// TODO Auto-generated method stub
		if (testMethod.getName().equals("m4")) {
			System.out.println("Changing invocation for the following method: " + testMethod.getName());
			testAnnotation.setInvocationCount(3);

		}		
		
		if (testMethod.getName().equals("m7")) {
			System.out.println("Changing enabled for the following method: " + testMethod.getName());
			testAnnotation.setEnabled(true);

		}

	}

}

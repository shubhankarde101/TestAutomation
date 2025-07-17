package frameWorkAnnotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface FrameWorkAnnotation {
    public String[] author() default {};
    //CategoryType[] category();
    public String TESTID() default "";
}

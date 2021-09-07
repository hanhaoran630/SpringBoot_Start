package top.hanhaoran.admin.util.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface Auth {
    boolean NeedLogin() default true;
    boolean NeedAuth() default true;
    String NeedPower() default  "";
}

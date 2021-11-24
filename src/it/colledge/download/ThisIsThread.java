package it.colledge.download;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**Этой анотацией помечаются объекты потоков**/

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.LOCAL_VARIABLE)

public @interface ThisIsThread {

}

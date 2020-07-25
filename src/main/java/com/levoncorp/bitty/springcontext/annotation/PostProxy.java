package com.levoncorp.bitty.springcontext.annotation;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/*
    Annotate a method with @PostProxy
    to invoke it after spring context is refreshed.
 */

@Retention(RetentionPolicy.RUNTIME)
public @interface PostProxy {
}

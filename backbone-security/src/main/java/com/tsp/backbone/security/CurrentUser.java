package com.tsp.backbone.security;

import org.springframework.security.core.annotation.AuthenticationPrincipal;

import java.lang.annotation.*;


/**
 * Current user auth info
 * <p>
 * Example:
 * public Response someMethod(@CurrentUser UserPrincipal  curUser) {...}
 */
@Target({ElementType.PARAMETER, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@AuthenticationPrincipal
public @interface CurrentUser {
}

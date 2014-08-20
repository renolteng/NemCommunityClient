package org.nem.ncc.controller.annotations;

import java.lang.annotation.*;

/**
 * API methods marked with this annotation require a trusted NIS server.
 * For now, that means that they will require a local NIS server.
 */
@Target({ ElementType.METHOD })
@Retention(RetentionPolicy.RUNTIME)
public @interface RequiresTrustedNis {
}

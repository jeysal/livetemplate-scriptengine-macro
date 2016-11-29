package com.github.jeysal.intellij.plugin.livetemplate.scriptenginemacro.execution.data

import com.intellij.openapi.editor.Editor
import groovy.transform.EqualsAndHashCode
import groovy.transform.Immutable
import groovy.transform.ToString
import groovy.transform.TupleConstructor

/**
 * Holds the dynamic information for the current script execution context,
 * i.e. information likely to change every time the macro is evaluated.
 *
 * @{@link Immutable} for some reason generates broken constructors on this class as of groovy 2.4.7,
 * so other annotations are used.
 *
 * @author seckinger
 * @since 10/24/16
 */
@TupleConstructor
@EqualsAndHashCode
@ToString
class Context {
    final List args
    final Goal goal
    final Editor editor
}

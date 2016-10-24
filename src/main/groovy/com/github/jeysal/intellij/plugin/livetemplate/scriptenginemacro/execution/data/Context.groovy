package com.github.jeysal.intellij.plugin.livetemplate.scriptenginemacro.execution.data

import com.intellij.openapi.editor.Editor
import groovy.transform.Immutable

/**
 * Holds the dynamic information for the current script execution context,
 * i.e. information likely to change every time the macro is evaluated.
 *
 * @author seckinger
 * @since 10/24/16
 */
@Immutable(knownImmutables = ['editor'])
class Context {
    List args
    Goal goal
    Editor editor
}

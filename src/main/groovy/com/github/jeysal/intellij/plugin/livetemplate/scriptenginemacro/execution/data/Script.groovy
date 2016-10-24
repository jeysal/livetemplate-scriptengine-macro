package com.github.jeysal.intellij.plugin.livetemplate.scriptenginemacro.execution.data

import groovy.transform.Immutable

/**
 * Holds the static information drawn from the first macro parameter that can be used to execute a script,
 * i.e. information that usually only changes when the template variable (or script file) is modified.
 *
 * @author seckinger
 * @since 10/24/16
 */
@Immutable
class Script {
    String language
    String source
}

package com.github.jeysal.intellij.plugin.livetemplate.scriptenginemacro.conversion.script.processing

import com.github.jeysal.intellij.plugin.livetemplate.scriptenginemacro.script.processing.ScriptProcessor
import spock.lang.Specification

/**
 * @author seckinger
 * @since 10/24/16
 */
class ScriptProcessorTest extends Specification {
    def proc = new ScriptProcessor()

    def 'throws when passed any Object'() {
        when:
        proc.call(new Object(), null)

        then:
        thrown(RuntimeException)
    }
}

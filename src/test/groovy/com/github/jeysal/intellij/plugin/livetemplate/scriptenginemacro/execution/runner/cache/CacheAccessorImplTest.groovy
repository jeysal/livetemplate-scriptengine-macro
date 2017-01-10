package com.github.jeysal.intellij.plugin.livetemplate.scriptenginemacro.execution.runner.cache

import com.github.jeysal.intellij.plugin.livetemplate.scriptenginemacro.execution.data.Context
import com.github.jeysal.intellij.plugin.livetemplate.scriptenginemacro.execution.data.Execution
import com.github.jeysal.intellij.plugin.livetemplate.scriptenginemacro.execution.data.Goal
import com.github.jeysal.intellij.plugin.livetemplate.scriptenginemacro.execution.data.Script
import com.github.jeysal.intellij.plugin.livetemplate.scriptenginemacro.execution.runner.cache.data.CacheKey
import com.github.jeysal.intellij.plugin.livetemplate.scriptenginemacro.execution.runner.cache.data.CacheValue
import com.google.common.cache.Cache
import com.google.common.cache.CacheBuilder
import spock.lang.Specification

/**
 * @author seckinger
 * @since 1/9/17
 */
class CacheAccessorImplTest extends Specification {
    CacheAccessorImpl accessor

    final Cache<CacheKey, CacheValue> cache = CacheBuilder.newBuilder().build()
    final Execution execution = new Execution(new Script('lang', 'src'), new Context([], Goal.RESULT, null))

    def setup() {
        accessor = new CacheAccessorImpl(cache, execution)
    }

    def 'store returns the passed value'() {
        expect:
        accessor.store('val', 'meta') == 'val'
    }

    def 'load returns a previously cached value'() {
        when:
        accessor.store('val')

        then:
        accessor.load().get() == 'val'
    }

    def 'load returns a previously cached value with metadata'() {
        when:
        accessor.store('val', 'meta')

        then:
        accessor.load('meta').get() == 'val'
    }

    def 'load returns an empty Optional if no value is available'() {
        expect:
        !accessor.load().isPresent()
    }

    def 'load returns an empty Optional if only values with different metadata are available'() {
        when:
        accessor.store('val', 'meta1')

        then:
        !accessor.load('meta2').isPresent()
    }
}

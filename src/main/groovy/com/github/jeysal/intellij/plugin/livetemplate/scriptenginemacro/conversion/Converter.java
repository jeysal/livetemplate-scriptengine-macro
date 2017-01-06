package com.github.jeysal.intellij.plugin.livetemplate.scriptenginemacro.conversion;

import java.util.function.Function;

/**
 * @author seckinger
 * @since 1/6/17
 */
@FunctionalInterface
public interface Converter<T, R> extends Function<T, R> {
    R convert(T t);

    @Override
    default R apply(T t) {
        return convert(t);
    }
}

package com.github.jeysal.intellij.plugin.livetemplate.scriptenginemacro.execution.runner.cache.data;

import com.github.jeysal.intellij.plugin.livetemplate.scriptenginemacro.execution.data.Script;
import lombok.Value;
import lombok.experimental.Wither;

import java.util.List;

/**
 * @author seckinger
 * @since 1/9/17
 */
@Value
@Wither
public class CacheKey {
    Script script;
    List<Object> meta;
}

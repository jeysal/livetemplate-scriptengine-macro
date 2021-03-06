package com.github.jeysal.intellij.plugin.livetemplate.scriptenginemacro.execution.data;

import lombok.Value;
import lombok.experimental.Wither;

/**
 * Holds the information for a specific execution of a specific script,
 * i.e. the combined {@link Script static} and {@link Context dynamic} information.
 *
 * @author seckinger
 * @since 10/24/16
 */
@Value
@Wither
public class Execution {
    Script script;
    Context context;
}

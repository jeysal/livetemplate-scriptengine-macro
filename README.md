# LiveTemplate ScriptEngine Macro

[![build status](https://img.shields.io/travis/jeysal/livetemplate-scriptengine-macro.svg?style=flat-square)](https://travis-ci.org/jeysal/livetemplate-scriptengine-macro)
[![AppVeyor build status](https://img.shields.io/appveyor/ci/jeysal/livetemplate-scriptengine-macro.svg?style=flat-square&label=windows+build)](https://ci.appveyor.com/project/jeysal/livetemplate-scriptengine-macro)
[![license](https://img.shields.io/github/license/jeysal/livetemplate-scriptengine-macro.svg?style=flat-square)](https://github.com/jeysal/livetemplate-scriptengine-macro/blob/master/LICENSE)

An IntelliJ IDEA plugin for enhanced live template scripting support

Enables you to insert text, offer completion options or perform editor actions using your own scripts when inserting a live template.

Unlike IntelliJ's native `groovyScript` macro, this plugins allows for much more flexibility in what you can return from your script,
is able to read your script from a file, and offers ECMAScript / JavaScript as an alternative language instead of binding you to Groovy. 

## Installation

You can install the plugin from the [JetBrains IntelliJ IDEA Plugin Repository](https://plugins.jetbrains.com/idea/plugin/9365-livetemplate-scriptengine-macro).
In your IDE's plugin repository browser, you will find it named `LiveTemplate ScriptEngine Macro`.

## Usage

To create a live template, open the settings dialog an go to Editor > Live Templates.
Create a live template and define at least one variable (such as `$EXAMPLE$`) in it.
Don't forget to define applicable contexts at the bottom of the dialog so you can actually use the template later on.
Click "Edit variables" and look for the row with your variable.

In the expression field, enter for example `script("javascript:21 * 2")`.
This will replace your variable with the result of the javascript expression `21 * 2`.

You can also [use another language](https://github.com/jeysal/livetemplate-scriptengine-macro/wiki/Defining-a-script#languages) that has a ScriptEngine available.
Alternatively, you can [specify a file path](https://github.com/jeysal/livetemplate-scriptengine-macro/wiki/Defining-a-script#script-from-a-file) (relative to your home directory or absolute)
to avoid writing complex scripts inline in the template variable editor.

For more comprehensive and detailed documentation, check out the [wiki](https://github.com/jeysal/livetemplate-scriptengine-macro/wiki)!

## License

livetemplate-scriptengine-macro is [MIT-licensed](https://github.com/jeysal/livetemplate-scriptengine-macro/blob/master/LICENSE).

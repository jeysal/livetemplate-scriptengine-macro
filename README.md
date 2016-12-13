# LiveTemplate ScriptEngine Macro

[![build status](https://img.shields.io/travis/jeysal/livetemplate-scriptengine-macro.svg?style=flat-square)](https://travis-ci.org/jeysal/livetemplate-scriptengine-macro)
[![license](https://img.shields.io/github/license/jeysal/livetemplate-scriptengine-macro.svg?style=flat-square)](https://github.com/jeysal/livetemplate-scriptengine-macro/blob/master/LICENSE)

An IntelliJ IDEA plugin for enhanced live template scripting support

Enables you to insert text, offer completion options or perform editor actions using your own scripts when inserting a live template.

## Installation

Once released, you will be able to install the plugin from the [JetBrains IntelliJ IDEA Plugin Repository](https://plugins.jetbrains.com/).

## Usage

To create a live template, open the settings dialog an go to Editor > Live Templates.
Create a live template and define at least one variable (such as `$EXAMPLE$`) in it.
Don't forget to define applicable contexts at the bottom of the dialog so you can actually use the template later on.
Click "Edit variables" and look for the row with your variable.

In the expression field, enter for example `script("javascript:21 * 2")`.
This will replace your variable with the result of the javascript expression `21 * 2`.

You can also use another language that has a ScriptEngine available.
Alternatively, you can specify a file path (relative to your home directory)
to avoid writing complex scripts inline in the template variable editor.

## License

livetemplate-scriptengine-macro is [MIT-licensed](https://github.com/jeysal/livetemplate-scriptengine-macro/blob/master/LICENSE).

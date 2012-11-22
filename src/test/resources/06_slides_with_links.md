
# Abbreviations

The HTML specification is maintained by the W3C.

*[HTML]: Hyper Text Markup Language
*[W3C]:  World Wide Web Consortium

# Definitions

Multiple terms:

Term 1
Term 2
:   Definition 1

Term 3
Term 4
:   Definition 2

# Wikilinks

Wikilinks are simple URIs like [[Autolinks]],
which will be converted by pegdown.

Another example with spaces: [[Special Chars]].

# Autolinks

Autolinks are simple URIs like http://www.parboiled.org,
which will be automatically "activated" by pegdown.

pegdown tries to be smart and not include trailing
punctuation marks like commas and such in the email
and URI links (joe@somewhere.com is such an example).
ftp://somesite.org:1234: this would be another one!

# Markdown links I

The following links should work just normally:

* [example](http://example)
* [ex@mple](http://example)
* [example://](http://example)

# Markdown links II

This is a [regular](http://regular.com) link and
[this one here][] is a reference link.

  [this one here]: http://blabla.com
  
  
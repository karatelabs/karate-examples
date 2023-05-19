# Karate and Axe for Accessibility Testing

It is possible to load any arbitrary JS library and execute it within a Karate UI test. One of the things that this opens up is the possibility to connect [Axe](https://www.deque.com/axe/) and even run a custom report.

## Example
Here is a simple example: [axe.feature](https://github.com/karatelabs/karate/blob/master/karate-e2e-tests/src/test/java/axe/axe.feature)

How it works is very straightforward:
* get the axe library JS / module from a CDN (this can also be from the file-system)
* wait for the page to load and then execute the library 
* use a [cusom HTML template](https://github.com/karatelabs/karate#doc) to generate a report


 
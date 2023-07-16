# Karate and BrowserStack

Karate supports the [W3C WebDriver protocol](https://www.w3.org/TR/webdriver) and can be used to run tests on [BrowserStack](https://www.browserstack.com).

The [example in this folder](browserstack.feature) demonstrates how to run a test on BrowserStack using Karate. All you need is a BrowserStack account and the URL (which encodes your credentials).

All you need to do in Karate is perform the [`configure driver` step](https://github.com/karatelabs/karate/tree/master/karate-core/#configure-driver) as per the documentation. You can also pass the name of your project, build and test-case which will be used by BrowserStack to organize results in your dashboard view.

Note how `bstack:options` are added to the WebDriver "capabilities":

```cucumber
  * def browserStackOptions = { projectName: 'Karate and Browser Stack', buildName: 'myBuild', sessionName: '#(karate.feature.prefixedPath)' }
  * def session = { capabilities: { alwaysMatch: { browserName: 'chrome', browserVersion: 'latest', platformName: 'Windows 11', 'bstack:options': '#(browserStackOptions)' } } }
  * configure driver = { type: 'chromedriver', start: false, webDriverSession: '#(session)', webDriverUrl: '#(browserStackUrl)' }
```

Of course you can change the `browserVersion` and `platformName` to suit your needs. You will have to ensure that the Karate driver type matches the `browserName`, for example `chromedriver` for `chrome`. Refer to the [Karate documentation](https://github.com/karatelabs/karate/tree/master/karate-core/#driver-types) for all possible types.

Also refer to the documentation of [`webDriverSession`](https://github.com/karatelabs/karate/tree/master/karate-core/#webdriversession) and [`webDriverUrl`](https://github.com/karatelabs/karate/tree/master/karate-core/#webdriverurl) for more details.

Note that for a typical test-suite, you will set variables via the [`karate-config.js`](https://github.com/karatelabs/karate/#karate-configjs) so you can switch browsers or between local and remote execution.

## Further Reading

* [Karate and BrowserStack on Stack Overflow](https://stackoverflow.com/search?q=%5Bkarate%5D+browserstack)
* [Running UI Tests in parallel](https://stackoverflow.com/a/60387907/143475)






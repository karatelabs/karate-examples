# Karate and Sauce Labs

Karate supports the [W3C WebDriver protocol](https://www.w3.org/TR/webdriver) and can be used to run tests on Sauce Labs.

The [example in this folder](sauce-labs.feature) demonstrates how to run a test on Sauce Labs using Karate. All you need is a Sauce Labs account and the values of the Sauce Labs "build" and the URL to connect to.

From your Sauce Labs dashboard, you can get the values you need from the quickstart guide for Selenium / Java. Here is a screenshot showing which sections you can focus on:

<img height="400" src="https://github.com/karatelabs/karate-examples/assets/915480/1a6634b6-797e-4a6c-ae39-3d5788f0f4cd">

All you need to do in Karate is perform the [`configure driver` step](https://github.com/karatelabs/karate/tree/master/karate-core/#configure-driver) as per the documentation. Note how `sauce:options` are added to the WebDriver "capabilities":

```cucumber
  * def sauceOptions = { build: '#(sauceLabsBuild)', name: '#(karate.feature.prefixedPath)' }
  * def session = { capabilities: { alwaysMatch: { browserName: 'chrome', browserVersion: 'latest', platformName: 'Windows 11', 'sauce:options': '#(sauceOptions)' } } }
  * configure driver = { type: 'chromedriver', start: false, webDriverSession: '#(session)', webDriverUrl: '#(sauceLabsUrl)' }
```

Of course you can change the `browserVersion` and `platformName` to suit your needs. You will have to ensure that the Karate driver type matches the `browserName`, for example `chromedriver` for `chrome`. Refer to the [Karate documentation](https://github.com/karatelabs/karate/tree/master/karate-core/#driver-types) for all possible types.

Also refer to the documentation of [`webDriverSession`](https://github.com/karatelabs/karate/tree/master/karate-core/#webdriversession) and [`webDriverUrl`](https://github.com/karatelabs/karate/tree/master/karate-core/#webdriverurl) for more details.

Note that for a typical test-suite, you will set variables via the [`karate-config.js`](https://github.com/karatelabs/karate/#karate-configjs) so you can switch browsers or between local and remote execution.

## Further Reading

* [Karate and Sauce Labs on Stack Overflow](https://stackoverflow.com/search?q=%5Bkarate%5D+sauce+labs)
* [Running UI Tests in parallel](https://stackoverflow.com/a/60387907/143475)






# Karate and Lambdatest

Karate supports the [W3C WebDriver protocol](https://www.w3.org/TR/webdriver) and can be used to run tests on [Lambdatest](https://www.lambdatest.com).

The [example in this folder](lambdatest.feature) demonstrates how to run a test on Lambdatest using Karate. All you need is a Lambdatest account and values such as the URL, user, key etc.

From your Lambdatest profile, you can get the values you need. The main ones you need are the `username` and `accessKey`.

All you need to do in Karate is perform the [`configure driver` step](https://github.com/karatelabs/karate/tree/master/karate-core/#configure-driver) as per the documentation. Note how `LT:Options` are added to the WebDriver "capabilities":

```cucumber
  * def session = { capabilities: { alwaysMatch: { browserName: 'chrome', browserVersion: 'latest', platformName: 'Windows 11', 'LT:Options': '#(lambdaTestOptions)' } } }
  * configure driver = { type: 'chromedriver', start: false, webDriverSession: '#(session)', webDriverUrl: '#(lambdaTestUrl)' }
```

Of course you can change the `browserVersion` and `platformName` to suit your needs. You will have to ensure that the Karate driver type matches the `browserName`, for example `chromedriver` for `chrome`. Refer to the [Karate documentation](https://github.com/karatelabs/karate/tree/master/karate-core/#driver-types) for all possible types.

Also refer to the documentation of [`webDriverSession`](https://github.com/karatelabs/karate/tree/master/karate-core/#webdriversession) and [`webDriverUrl`](https://github.com/karatelabs/karate/tree/master/karate-core/#webdriverurl) for more details.

Note that for a typical test-suite, you will set variables via the [`karate-config.js`](https://github.com/karatelabs/karate/#karate-configjs) so you can switch browsers or between local and remote execution.

## Further Reading

* [Karate and Lambdatest on Stack Overflow](https://stackoverflow.com/search?q=%5Bkarate%5D+lambdatest)
* [Running UI Tests in parallel](https://stackoverflow.com/a/60387907/143475)
* [LambdaTest support for Karate](https://github.com/LambdaTest/hyperexecute-karate-sample)






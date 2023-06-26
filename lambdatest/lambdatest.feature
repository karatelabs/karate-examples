Feature: lambdatest automation demo

Background:
  # enter the values for the next few lines from your lambatest account
  * def lambdaTestUser = ''
  * def lambdaTestKey = ''
  * def lambdaTestUrl = 'https://hub.lambdatest.com/wd/hub'
  * def lambdaTestOptions = { project: '#(karate.feature.prefixedPath)', username: '#(lambdaTestUser)', accessKey: '#(lambdaTestKey)' }
  * def session = { capabilities: { alwaysMatch: { browserName: 'chrome', browserVersion: 'latest', platformName: 'Windows 11', 'LT:Options': '#(lambdaTestOptions)' } } }
  * configure driver = { type: 'chromedriver', start: false, webDriverSession: '#(session)', webDriverUrl: '#(lambdaTestUrl)' }

Scenario: try to login to github and check for expected error message
  * driver 'https://github.com/login'
  * input('#login_field', 'XXXX')
  * input('#password', 'world')
  * submit().click("input[name=commit]")
  * match html('.flash-error') contains 'Incorrect username or password.'    
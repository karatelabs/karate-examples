Feature: sauce labs automation demo

Background:
  # enter the values for the next few lines from your sauce labs account
  * def sauceLabsBuild = ''
  * def sauceLabsUser = ''
  * def sauceLabsKey = ''
  * def sauceLabsUrl = 'https://ondemand.eu-central-1.saucelabs.com:443/wd/hub'
  * def sauceOptions = { build: '#(sauceLabsBuild)', name: '#(karate.feature.prefixedPath)', username: '#(sauceLabsUser)', accessKey: '#(sauceLabsKey)' }
  * def session = { capabilities: { alwaysMatch: { browserName: 'chrome', browserVersion: 'latest', platformName: 'Windows 11', 'sauce:options': '#(sauceOptions)' } } }
  * configure driver = { type: 'chromedriver', start: false, webDriverSession: '#(session)', webDriverUrl: '#(sauceLabsUrl)' }

Scenario: try to login to github and check for expected error message
  * driver 'https://github.com/login'
  * input('#login_field', 'XXXX')
  * input('#password', 'world')
  * submit().click("input[name=commit]")
  * match html('.flash-error') contains 'Incorrect username or password.'    
Feature: browserstack automation demo

Background:
  # enter the value for the next line from your browserstack account
  * def browserStackUrl = ''
  * def browserStackOptions = { projectName: 'Karate and Browser Stack', buildName: 'myBuild', sessionName: '#(karate.feature.prefixedPath)' }
  * def session = { capabilities: { alwaysMatch: { browserName: 'chrome', browserVersion: 'latest', platformName: 'Windows 11', 'bstack:options': '#(browserStackOptions)' } } }
  * configure driver = { type: 'chromedriver', start: false, webDriverSession: '#(session)', webDriverUrl: '#(browserStackUrl)' }

Scenario: try to login to github and check for expected error message
  * driver 'https://github.com/login'
  * input('#login_field', 'XXXX')
  * input('#password', 'world')
  * submit().click("input[name=commit]")
  * match html('.flash-error') contains 'Incorrect username or password.'    
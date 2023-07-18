Feature:

Scenario:
* def config = { user: 'ec2-user', host: '', privateKey: '' }
* def SshSession = Java.type('karate.SshSession')
* def ssh = new SshSession(config)
* ssh.input('pwd')
* ssh.input('whoami')
* ssh.input('exit')

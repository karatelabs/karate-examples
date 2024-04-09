@ignore
Feature:

Background:
* def KafkaUtils = Java.type('karate.KafkaUtils')

Scenario:
* KafkaUtils.send()
* def response = { success: true }

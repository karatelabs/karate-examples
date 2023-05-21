function fn() {
  var KafkaUtils = Java.type('karate.KafkaUtils');
  return { Kafka: new KafkaUtils('test-topic') };
}

function fn() {
  var KafkaUtils = Java.type('karate.KafkaUtils');
  return { kafka: new KafkaUtils('test-topic') };
}

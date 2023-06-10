function fn() {
  var Rmq = Java.type('karate.RmqUtils');
  return { rmq: new Rmq('my-queue') };
}

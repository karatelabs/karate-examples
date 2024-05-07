#! /usr/bin/env node
const karate = require('@karatelabs/karate');
karate.jvm.args = `Hello.java`
karate.exec();

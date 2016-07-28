[![Build Status](https://secure.travis-ci.org/ilves/test.svg)](https://travis-ci.org/ilves/test)
[![Coverage Status](https://coveralls.io/repos/github/ilves/test/badge.svg?branch=master)](https://coveralls.io/github/ilves/test?branch=master)

Dragon Trainer
===============

## Requirements

* Java 1.8 and later.
* Maven

## Installation and running

```
git clone https://github.com/ilves/test.git dragon-trainer
cd dragon-trainer
mvn compile && mvn exec:exec -Dgames=501 -Dworkers=50 -Dconnections=50
```

### Parameters
* **games** = number of games to run
* **workers** = number of game solver threads
* **connections** = number of http clients in connection pool
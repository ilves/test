[![Build Status](https://secure.travis-ci.org/ilves/test.svg)](https://travis-ci.org/ilves/test)
[![Coverage Status](https://coveralls.io/repos/github/ilves/test/badge.svg?branch=master)](https://coveralls.io/github/ilves/test?branch=master)

Dragon Trainer
==============

Solves the game using worker threads, task que and connection pooling.
Solving 5000 games using 50 workers and 50 connections takes about 26s.
The victory rate is about ~96% as it is not stated explicitly what should be done
when weather is "STORM" as Dragon always dies. If the dragons are not sent to
the battle when weather is "STORM" then the victory rate should be 100%.

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
##### [Home](Home) > Errors Encountered

## log4j

```sh
    anthony@lovethislife:/usr/share/elasticsearch/bin$ ./elasticsearch -f 
    log4j:WARN No appenders could be found for logger (node).
    log4j:WARN Please initialize the log4j system properly.
    log4j:WARN See http://logging.apache.org/log4j/1.2/faq.html#noconfig for more info.
```

First check log:

```sh
    vim /var/log/elasticsearch/elasticsearch.log 
```

Output:

```sh
    anthony   5812  0.0  0.0  13632   952 pts/3    S+   00:29   0:00 grep --color=auto elasticsearch
```
S - Interruptible sleep (waiting for an event to complete)

#### First Attempt pkill
Does not work. It just spawn a new process (look at the pid number it's a different one after kill)

#### Solution
Restart your machine. Herp Derp.

Next time use /etc/init.d/elasticsearch

```sh
   * Usage: /etc/init.d/elasticsearch {start|stop|restart|force-reload|status}
```

#### Resources


* https://groups.google.com/forum/#!topic/archivematica/18hUoN3SBkQ
* http://forums.fedoraforum.org/showthread.php?t=145492

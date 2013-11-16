##### [Home](Home) > [Scale](scale) > [Cluster](Clustering) > Cluster States

The cluster health status is: green, yellow or red. 

## Red
On the shard level, a red status indicates that the specific shard is not allocated in the cluster.

## Yellow
Yellow means that the primary shard is allocated but replicas are not

## Green 
Green means that all shards are allocated. The index level status is controlled by the worst shard status. The cluster status is controlled by the worst index status.

## Commandline

```sh
curl -XGET 'http://192.168.1.11:9200/_cluster/health?pretty'
```

### Reference
1. http://www.elasticsearch.org/guide/en/elasticsearch/reference/current/cluster-health.html
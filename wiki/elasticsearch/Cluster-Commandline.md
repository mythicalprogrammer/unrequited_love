##### [Home](Home) > [Scale](scale) > [Cluster](Clustering) > Cluster Commandline

## Health (cluster)

```sh
curl -XGET 'http://192.168.1.11:9200/_cluster/health?pretty'
```

Output

```json
{
  "cluster_name" : "elasticsearch",
  "status" : "green",
  "timed_out" : false,
  "number_of_nodes" : 1,
  "number_of_data_nodes" : 1,
  "active_primary_shards" : 0,
  "active_shards" : 0,
  "relocating_shards" : 0,
  "initializing_shards" : 0,
  "unassigned_shards" : 0
}
```

## Node Status (Node)

### ES1

```sh
curl -XGET 'http://192.168.1.10:9200'
```

```json
{
  "ok" : true,
  "status" : 200,
  "name" : "Kohl Harder Boulder Man",
  "version" : {
    "number" : "0.90.6",
    "build_hash" : "e2a24efdde0cb7cc1b2071ffbbd1fd874a6d8d6b",
    "build_timestamp" : "2013-11-04T13:44:16Z",
    "build_snapshot" : false,
    "lucene_version" : "4.5.1"
  },
  "tagline" : "You Know, for Search"
}
```

### ES2

```sh
curl -XGET 'http://192.168.1.11:9200'
```

```json
{
  "ok" : true,
  "status" : 200,
  "name" : "Bloodlust",
  "version" : {
    "number" : "0.90.6",
    "build_hash" : "e2a24efdde0cb7cc1b2071ffbbd1fd874a6d8d6b",
    "build_timestamp" : "2013-11-04T13:44:16Z",
    "build_snapshot" : false,
    "lucene_version" : "4.5.1"
  },
  "tagline" : "You Know, for Search"
}
```

## List All Nodes

### Summary

```sh
curl -XGET 'http://192.168.1.11:9200/_nodes?pretty'
```

Output

```json
{
  "ok" : true,
  "cluster_name" : "elasticsearch",
  "nodes" : {
    "Bz50avTCRhyyCxO7epcQEQ" : {
      "name" : "Eternal Brain",
      "transport_address" : "inet[/192.168.1.10:9300]",
      "hostname" : "precise64",
      "version" : "0.90.6",
      "http_address" : "inet[/192.168.1.10:9200]"
    },
    "BJ_3KE-jSZOqnCuwNh_RGg" : {
      "name" : "Silke, Samuel",
      "transport_address" : "inet[/192.168.1.11:9300]",
      "hostname" : "precise64",
      "version" : "0.90.6",
      "http_address" : "inet[/192.168.1.11:9200]"
    }
  }
}
```

### In Details

```sh
curl 192.168.1.11:9200/_nodes/stats?pretty
```

```json
{
  "cluster_name" : "elasticsearch",
  "nodes" : {
    "Bz50avTCRhyyCxO7epcQEQ" : {
      "timestamp" : 1383616335977,
      "name" : "Eternal Brain",
      "transport_address" : "inet[/192.168.1.10:9300]",
      "hostname" : "precise64",
      "indices" : {
        "docs" : {
          "count" : 0,
          "deleted" : 0
        },
        "store" : {
          "size" : "0b",
          "size_in_bytes" : 0,
          "throttle_time" : "0s",
          "throttle_time_in_millis" : 0
        },
        "indexing" : {
          "index_total" : 0,
          "index_time" : "0s",
          "index_time_in_millis" : 0,
          "index_current" : 0,
          "delete_total" : 0,
          "delete_time" : "0s",
          "delete_time_in_millis" : 0,
          "delete_current" : 0
        },
        "get" : {
          "total" : 0,
          "get_time" : "0s",
          "time_in_millis" : 0,
          "exists_total" : 0,
          "exists_time" : "0s",
          "exists_time_in_millis" : 0,
          "missing_total" : 0,
          "missing_time" : "0s",
          "missing_time_in_millis" : 0,
          "current" : 0
        },
        "search" : {
          "open_contexts" : 0,
          "query_total" : 0,
          "query_time" : "0s",
          "query_time_in_millis" : 0,
          "query_current" : 0,
          "fetch_total" : 0,
          "fetch_time" : "0s",
          "fetch_time_in_millis" : 0,
          "fetch_current" : 0
        }
      }
    },
    "BJ_3KE-jSZOqnCuwNh_RGg" : {
      "timestamp" : 1383616335966,
      "name" : "Silke, Samuel",
      "transport_address" : "inet[/192.168.1.11:9300]",
      "hostname" : "precise64",
      "indices" : {
        "docs" : {
          "count" : 0,
          "deleted" : 0
        },
        "store" : {
          "size" : "0b",
          "size_in_bytes" : 0,
          "throttle_time" : "0s",
          "throttle_time_in_millis" : 0
        },
        "indexing" : {
          "index_total" : 0,
          "index_time" : "0s",
          "index_time_in_millis" : 0,
          "index_current" : 0,
          "delete_total" : 0,
          "delete_time" : "0s",
          "delete_time_in_millis" : 0,
          "delete_current" : 0
        },
        "get" : {
          "total" : 0,
          "get_time" : "0s",
          "time_in_millis" : 0,
          "exists_total" : 0,
          "exists_time" : "0s",
          "exists_time_in_millis" : 0,
          "missing_total" : 0,
          "missing_time" : "0s",
          "missing_time_in_millis" : 0,
          "current" : 0
        },
        "search" : {
          "open_contexts" : 0,
          "query_total" : 0,
          "query_time" : "0s",
          "query_time_in_millis" : 0,
          "query_current" : 0,
          "fetch_total" : 0,
          "fetch_time" : "0s",
          "fetch_time_in_millis" : 0,
          "fetch_current" : 0
        }
      }
    }
  }
}
```

## Master Node

```sh
curl "192.168.1.10:9200/_cluster/state?filter_metadata=1&filter_routing_table=1&filter_indices=1&pretty"
```

```json
{
  "cluster_name" : "elasticsearch",
  "master_node" : "BJ_3KE-jSZOqnCuwNh_RGg",
  "blocks" : { },
  "nodes" : {
    "Bz50avTCRhyyCxO7epcQEQ" : {
      "name" : "Eternal Brain",
      "transport_address" : "inet[/192.168.1.10:9300]",
      "attributes" : { }
    },
    "BJ_3KE-jSZOqnCuwNh_RGg" : {
      "name" : "Silke, Samuel",
      "transport_address" : "inet[/192.168.1.11:9300]",
      "attributes" : { }
    }
  }
}
```

## Decommission a Node

Remove a node from the cluster and gracefully move all data to the other nodes.

### Reasons
1. Traffic isn't as high as it's used to be
2. Hardware upgrade for a node

### Command

```sh
curl -XPUT localhost:9200/_cluster/settings -d '{
  "transient" : {
    "cluster.routing.allocation.exclude._ip" : "192.168.1.10"
  }
}'
```

## List All Shards (from all nodes)

### Command

```sh
curl "192.168.1.10:9200/_cluster/state?filter_nodes&filter_metadata&filter_blocks&pretty"
```

### Output

```json
{
  "cluster_name" : "elasticsearch",
  "routing_table" : {
    "indices" : {
      "test" : {
        "shards" : {
          "0" : [ {
            "state" : "STARTED",
            "primary" : true,
            "node" : "Bz50avTCRhyyCxO7epcQEQ",
            "relocating_node" : null,
            "shard" : 0,
            "index" : "test"
          }, {
            "state" : "STARTED",
            "primary" : false,
            "node" : "BJ_3KE-jSZOqnCuwNh_RGg",
            "relocating_node" : null,
            "shard" : 0,
            "index" : "test"
          } ],
          "1" : [ {
            "state" : "STARTED",
            "primary" : false,
            "node" : "Bz50avTCRhyyCxO7epcQEQ",
            "relocating_node" : null,
            "shard" : 1,
            "index" : "test"
          }, {
            "state" : "STARTED",
            "primary" : true,
            "node" : "BJ_3KE-jSZOqnCuwNh_RGg",
            "relocating_node" : null,
            "shard" : 1,
            "index" : "test"
          } ],
          "2" : [ {
            "state" : "STARTED",
            "primary" : true,
            "node" : "Bz50avTCRhyyCxO7epcQEQ",
            "relocating_node" : null,
            "shard" : 2,
            "index" : "test"
          }, {
            "state" : "STARTED",
            "primary" : false,
            "node" : "BJ_3KE-jSZOqnCuwNh_RGg",
            "relocating_node" : null,
            "shard" : 2,
            "index" : "test"
          } ],
          "3" : [ {
            "state" : "STARTED",
            "primary" : false,
            "node" : "Bz50avTCRhyyCxO7epcQEQ",
            "relocating_node" : null,
            "shard" : 3,
            "index" : "test"
          }, {
            "state" : "STARTED",
            "primary" : true,
            "node" : "BJ_3KE-jSZOqnCuwNh_RGg",
            "relocating_node" : null,
            "shard" : 3,
            "index" : "test"
          } ],
          "4" : [ {
            "state" : "STARTED",
            "primary" : true,
            "node" : "Bz50avTCRhyyCxO7epcQEQ",
            "relocating_node" : null,
            "shard" : 4,
            "index" : "test"
          }, {
            "state" : "STARTED",
            "primary" : false,
            "node" : "BJ_3KE-jSZOqnCuwNh_RGg",
            "relocating_node" : null,
            "shard" : 4,
            "index" : "test"
          } ]
        }
      }
    }
  },
  "routing_nodes" : {
    "unassigned" : [ ],
    "nodes" : {
      "Bz50avTCRhyyCxO7epcQEQ" : [ {
        "state" : "STARTED",
        "primary" : true,
        "node" : "Bz50avTCRhyyCxO7epcQEQ",
        "relocating_node" : null,
        "shard" : 0,
        "index" : "test"
      }, {
        "state" : "STARTED",
        "primary" : false,
        "node" : "Bz50avTCRhyyCxO7epcQEQ",
        "relocating_node" : null,
        "shard" : 1,
        "index" : "test"
      }, {
        "state" : "STARTED",
        "primary" : true,
        "node" : "Bz50avTCRhyyCxO7epcQEQ",
        "relocating_node" : null,
        "shard" : 2,
        "index" : "test"
      }, {
        "state" : "STARTED",
        "primary" : false,
        "node" : "Bz50avTCRhyyCxO7epcQEQ",
        "relocating_node" : null,
        "shard" : 3,
        "index" : "test"
      }, {
        "state" : "STARTED",
        "primary" : true,
        "node" : "Bz50avTCRhyyCxO7epcQEQ",
        "relocating_node" : null,
        "shard" : 4,
        "index" : "test"
      } ],
      "BJ_3KE-jSZOqnCuwNh_RGg" : [ {
        "state" : "STARTED",
        "primary" : false,
        "node" : "BJ_3KE-jSZOqnCuwNh_RGg",
        "relocating_node" : null,
        "shard" : 0,
        "index" : "test"
      }, {
        "state" : "STARTED",
        "primary" : true,
        "node" : "BJ_3KE-jSZOqnCuwNh_RGg",
        "relocating_node" : null,
        "shard" : 1,
        "index" : "test"
      }, {
        "state" : "STARTED",
        "primary" : false,
        "node" : "BJ_3KE-jSZOqnCuwNh_RGg",
        "relocating_node" : null,
        "shard" : 2,
        "index" : "test"
      }, {
        "state" : "STARTED",
        "primary" : true,
        "node" : "BJ_3KE-jSZOqnCuwNh_RGg",
        "relocating_node" : null,
        "shard" : 3,
        "index" : "test"
      }, {
        "state" : "STARTED",
        "primary" : false,
        "node" : "BJ_3KE-jSZOqnCuwNh_RGg",
        "relocating_node" : null,
        "shard" : 4,
        "index" : "test"
      } ]
    }
  },
  "allocations" : [ ]
}
```
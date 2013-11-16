##### [Home](Home) > [Scale](scale) > [Cluster](Clustering) > Setup

## Setting up (Multiple Nodes with Vagrant)

### Setup

#### Vagrant

Install it.

#### [Berkshelf](http://berkshelf.com/)

Install it.

```sh
gem install berkshelf
```

```sh
vagrant plugin list
```

```sh
vagrant plugin install vagrant-berkshelf
```

#### Berksfile

```ruby
cookbook 'java'
```

### Vagrant (java 7 & es 0.90.6)

```ruby
# -*- mode: ruby -*-
# vi: set ft=ruby :

# Vagrantfile API/syntax version. Don't touch unless you know what you're doing!
VAGRANTFILE_API_VERSION = "2"

Vagrant.configure(VAGRANTFILE_API_VERSION) do |config|
  config.vm.box = "precise64"
  config.vm.box_url = "http://files.vagrantup.com/precise64.box"
  config.berkshelf.enabled = true

  config.vm.define :es1 do |es1|
    es1.vm.network :private_network, ip: "192.168.1.10"
    es1.vm.provision "shell", inline: "apt-get update"
    es1.vm.provision :shell, :inline => "gem install chef --version 11.4.2 --no-rdoc --no-ri --conservative"

    es1.vm.provision :chef_solo do |chef|
      chef.add_recipe "java"
      chef.json = {
        :java => {
          "install_flavor" => "oracle",
          "jdk_version" => "7",
          :oracle => {
            "accept_oracle_download_terms" => true
          }
        }
      }
    end
    es1.vm.provision :shell, :inline => "wget https://download.elasticsearch.org/elasticsearch/elasticsearch/elasticsearch-0.90.6.deb"
    es1.vm.provision :shell, :inline => "dpkg -i elasticsearch-0.90.6.deb"
  end

  config.vm.define :es2 do |es2|
    es2.vm.network :private_network, ip: "192.168.1.11"
    es2.vm.provision "shell", inline: "apt-get update"
    es2.vm.provision :shell, :inline => "gem install chef --version 11.4.2 --no-rdoc --no-ri --conservative"

    es2.vm.provision :chef_solo do |chef|
      chef.add_recipe "java"
      chef.json = {
        :java => {
          "install_flavor" => "oracle",
          "jdk_version" => "7",
          :oracle => {
            "accept_oracle_download_terms" => true
          }
        }
      }
    end
    es2.vm.provision :shell, :inline => "wget https://download.elasticsearch.org/elasticsearch/elasticsearch/elasticsearch-0.90.6.deb"
    es2.vm.provision :shell, :inline => "dpkg -i elasticsearch-0.90.6.deb"
  end

end
```

### Run

```sh
berks install
```

```sh
vagrant up
```

### DISCOVERY (vagrant quirk)

Turns out that Vagrant always sets the NAT adapter on eth0 and it was the IP of that that Elasticsearch was binding to by default.

You need to ssh into each node and do this:

`/etc/elasticsearch/elasticsearch.yml`

Note: set it to the correct IP.

```sh
network.host: 192.168.1.11
```


#### References

1. http://misheska.com/blog/2013/06/16/getting-started-writing-chef-cookbooks-the-berkshelf-way/
2. http://www.opscode.com/blog/chefconf-talks/the-berkshelf-way-jamie-winsor/
3. http://red-badger.com/blog/2013/06/24/berkshelf-application-cookbooks/
4. http://thelampposts.blogspot.com/2013/09/elasticsearch-chef-and-vagrant.html

### Resources

* [Vagrant file & Instructions](https://github.com/mythicalprogrammer/two-node-elasticsearch-vagrant)

### References
* http://tech.toptable.co.uk/blog/2013/08/05/using-vagrant-to-work-with-elasticsearch-on-your-local-machine/


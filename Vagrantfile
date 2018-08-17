# -*- mode: ruby -*-
# vi: set ft=ruby :

Vagrant.configure("2") do |config|
  config.vm.box = 'acceptance-test'
  config.vm.box_url = 'https://cloud-images.ubuntu.com/vagrant/trusty/current/trusty-server-cloudimg-amd64-vagrant-disk1.box'
  
  #config.vm.hostname = "icinga2-test"
  config.vm.network "forwarded_port", guest: 8090, host: 8090, auto_correct: true
  config.vm.network "forwarded_port", guest: 8091, host: 8091, auto_correct: true

  config.vm.synced_folder "gradle/vagrant/images", "/tmp/images"

  config.vm.provision :puppet do |puppet|
    puppet.module_path = "gradle/vagrant/.vagrant-puppet/modules"
    puppet.manifests_path = "gradle/vagrant/.vagrant-puppet/manifests"
    puppet.options = "--verbose --debug"
  end

  #config.vm.provision :shell, :path => "./finalize.sh"
end


class { 'docker':
  manage_kernel    => false,
  extra_parameters => '--insecure-registry 192.168.0.81:5000',
}

docker::image { '192.168.0.81:5000/oose/de.oose.environmentservice.web:RELEASE':
  require => [ Class['docker'] ],
}

docker::run { 'environmentservice.web':
  image    => '192.168.0.81:5000/oose/de.oose.environmentservice.web:RELEASE',
  ports    => ['8090:8090','8091:8091'],
  use_name => false,
  require => [ Docker::Image['192.168.0.81:5000/oose/de.oose.environmentservice.web:RELEASE'] ],
}



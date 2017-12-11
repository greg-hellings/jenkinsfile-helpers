#!/usr/bin/groovy
import java.nio.file.Files
import java.nio.file.Paths

def call(String name, List packages = []) {
	def path = "${WORKSPACE}/${name}"
	if ( path.contains(" ") ) {
		throw new Exception("Path cannot include a space")
	}
	// Ensure we hvae a fresh path
	if( Files.exists(Paths.get(path)) ) {
		sh "rm -rf '${path}'"
	}

	sh """virtualenv --no-setuptools --system-site-packages '${name}'
	      . '${path}/bin/activate'
	      curl https://bootstrap.pypa.io/get-pip.py | python
	      '${path}/bin/python' '${path}/bin/pip' install ${packages.join(' ')}"""
}

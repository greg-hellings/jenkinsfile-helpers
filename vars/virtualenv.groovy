#!/usr/bin/groovy
import java.nio.file.Files
import java.nio.file.Paths

def call(String name, List packages = []) {
	def path = "${WORKSPACE}/${name}"
	// Ensure we hvae a fresh path
	if( Files.exists(Paths.get(path)) ) {
		sh "rm -rf '${path}'"
	}

	sh """virtualenv --no-setuptools --system-site-packages '${name}'
	      . '${name}/bin/activate'
	      curl https://bootstrap.pypa.io/get-pip.py | python
	      '${name}/bin/pip' install ${packages.join(' ')}"""
}

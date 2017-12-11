#!/usr/bin/groovy

def call(String name, List packages = []) {
	def path = "${WORKSPACE}/${name}"
	// Ensure we hvae a fresh path
	if( dirExists(path) ) {
		sh "rm -rf '${path}'"
	}

	sh """virtualenv --no-setuptools --system-site-packages '${name}'
	      . '${name}/bin/activate'
	      curl https://bootstrap.pypa.io/get-pip.py | python
	      pip install ${packages.join(' ')}"""
}

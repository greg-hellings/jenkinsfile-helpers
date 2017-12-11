#!/usr/bin/groovy

def call(String venvName, String command = "up") {
	def cmd = 'WORKSPACE=$(pwd) linchpin -v --creds-path credentials ' + command
	def localhost = "localhost ansible_python_interpreter=\"${WORKSPACE}/${venvName}/bin/python\""
	writeFile file: "localhost", text: localhost
	venvSh(venvName, [cmd])
}

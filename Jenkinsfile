def gradle(tasks) {
	 sh "./gradlew --info $tasks"
}

timestamps {
    node {
	    stage('Checkout') {
            checkout scm
        }

        stage('Build and Run Tests') {
            dir('test'){
                try {
                    gradle 'clean build'
                } finally {
                    junit '**/build/test-results/*.xml'
                }
            }
        }

        stage('Deploy Scenarioo Test-Results') {
            dir('test/build') {
                zip archive: true, dir: 'scenariooDocumentation', zipFile: 'documentation.zip'
                withCredentials([usernamePassword(credentialsId: 'SCENARIOO_DEMO', passwordVariable: 'password', usernameVariable: 'user')]) {
                    def cmd = /curl --user user:password -F "file=@documentation.zip" http://demo.scenarioo.org/scenarioo-master/rest/builds/
                    sh cmd
                }
            }

        }
     }
}

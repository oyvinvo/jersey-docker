#!groovy
stage('build') {
    node {
        checkout scm
        sh "mvn clean install"
        echo 'Testing. Hi, this is pipeline asking for his pipes back. '
    }
}
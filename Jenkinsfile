pipeline {
    agent any

    stages {
        stage('pull code') {
            steps {
                checkout([$class: 'GitSCM', branches: [[name: '*/master']], doGenerateSubmoduleConfigurations: false, extensions: [], submoduleCfg: [], userRemoteConfigs: [[credentialsId: 'd096518f-04d7-4464-ab90-c05d1d3db1dc', url: 'http://58.22.61.222:27008/53110/bosssoft-ecds.git']]])
            }
        }
        stage('build project') {
            steps {
                sh label: '', script: 'mvn clean install package -Dmaven.test.skip=true'
            }
        }
        stage('code checking') {
            steps {
                script {
                    scannerHome = tool 'MyScanner'
                }
                withSonarQubeEnv('MySonarQube') {
                    sh "${scannerHome}/bin/sonar-scanner"
                }
            }
        }
    }
}
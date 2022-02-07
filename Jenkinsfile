pipeline{
    agent any
    
    stages{
        stage('Build'){
            steps {
                git url: 'https://github.com/JavaHajar/jenkinspractice.git', branch: 'main'
                //sh './mvn clean compile'
                // sh 'mvn clean build'
                //sh 'mvn build'
               sh 'mvn install'
                
            }
        }
        
        stage('Test'){
            steps {
                //sh './mvn test'
                sh 'mvn test'
            }

            post{
                always{
                    junit '**/target/surefire-reports/TEST-*.xml'
                }
            }
        }
    }
}
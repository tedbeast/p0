pipeline {
    agent any
    stages {
       stage ('Compile Stage'){
            steps {
                withMaven{
                    bat 'mvn clean compile'
                }
            }

       }
          stage ('Testing Stage'){
            steps {
                withMaven{
                    bat 'mvn test'
                }
            }
          //deploy
       }
    }
}
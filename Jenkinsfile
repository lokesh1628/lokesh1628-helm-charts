pipeline{
	agent any
	environment {
		DOCKERHUB_PASS = '8008053604@Aa'
	}
	stages{
		stage("Building the Student Survey Image"){
			steps{
				script{
					checkout scm
					sh 'rm -rf *.jar'
					sh 'mvn clean package'
					sh 'echo ${BUILD_TIMESTAMP}'
					sh 'docker login -u akhil453 -p ${DOCKERHUB_PASS}'
					sh 'docker build -t akhil453/repo9 .'
				}
			}
		}
		stage("Pushing image to docker"){
			steps{
				script{
					sh 'docker push akhil453/repo9'
				}
			}
		}
		stage("Deploying to rancher"){
			steps{
				script{
					sh 'kubectl rollout restart deploy clusterdeploy'
				}
			}
		}
	}
}
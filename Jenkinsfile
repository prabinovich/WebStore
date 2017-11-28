node ('Build-Deploy-Box') {
    stage ('Build Application'){
           git credentialsId: 'Github-prabinovich', url: 'https://github.com/prabinovich/WebStore.git'
           // Compile project
           sh 'javac ./src/store/*.java -classpath "./libs/*" -d build/classes'
          }
}

node ('master'){
    stage ('CAST - Check Results'){
		git credentialsId: 'Github-prabinovich', url: 'https://github.com/prabinovich/CAST-Jenkins-Pipeline.git'               
    }
}


node ('CAST-Analysis-Server') {
    stage ('CAST - Code Packaging') {
        dir ('CAST-CLI') {
           git credentialsId: 'Github-prabinovich', url: 'https://github.com/prabinovich/CAST-Jenkins-Pipeline.git'
        }
        dir('Webstore') {
           git credentialsId: 'Github-prabinovich', url: 'https://github.com/prabinovich/WebStore.git'
        }
        echo '-- Packaging and Delivery of Source Code --'
        bat '%WORKSPACE%\\CAST-CLI\\CLI-Scripts\\CMS_AutomateDelivery.bat "profile=sandbox826" "app=Webstore" "fromVersion=Package_v2" "version=version %BUILD_NUMBER%"'
    }
    stage ('CAST - Analysis') {
        echo '-- Analyze Application --'
        bat '%WORKSPACE%\\CAST-CLI\\CLI-Scripts\\CMS_Analyze.bat "profile=sandbox826" "app=Webstore"'
    }
    stage ('CAST - Snapshot') {
        echo '-- Generate Snapshot --'
        bat '%WORKSPACE%\\CAST-CLI\\CLI-Scripts\\CMS_GenerateSnapshot.bat "profile=sandbox826" "app=Webstore" "version=version %BUILD_NUMBER%"'
    }
    stage ('CAST - Update AAD') {
    	echo '-- Publish Snapshot --'
    	echo 'to-do'
    }
}

node ('Build-Deploy-Box') {
    stage ('Package Application'){
			   // Organize web project
			   sh 'rm -rf ./Deploy'
			   sh 'mkdir ./Deploy'
			   sh 'mkdir ./Deploy/Package'
			   sh 'cp -r ./WebContent/* ./Deploy/Package'
			   sh 'cp -r ./build/* ./Deploy/Package/WEB-INF/'
			   sh 'mkdir ./Deploy/Package/WEB-INF/lib'
			   sh 'cp -r ./libs/* ./Deploy/Package/WEB-INF/lib'
	   
			   // Create WAR deployment package
			   sh 'cd ./Deploy/Package; jar -cvf ../WebStore.war *'
          }
	stage ('Deploy Application'){
			   sh 'sudo rm -f /var/lib/tomcat7/webapps/WebStore.war'
			   sh 'sudo cp -f ./Deploy/WebStore.war /var/lib/tomcat7/webapps'
	      }
	stage ('Deploy Database'){
	          sh 'mysqladmin --defaults-file=/home/ubuntu/.my.cnf -u root flush-logs drop -f webstore || true' 
	          //sh 'sudo service mysql restart'
	          sh 'mysqladmin --defaults-file=/home/ubuntu/.my.cnf -u root flush-logs create webstore'
	          sh 'mysql --defaults-file=/home/ubuntu/.my.cnf -u root webstore -e "use webstore; SET autocommit=0 ; source ./db/webstore_ddls.sql ; COMMIT;"'
	          sh 'mysql --defaults-file=/home/ubuntu/.my.cnf -u root webstore -e "use webstore; SET autocommit=0 ; source ./db/webstore_data.sql ; COMMIT;"'
	          // Create application user
	          sh 'mysql --defaults-file=/home/ubuntu/.my.cnf -u root webstore -e "drop user if exists \'appuser\'@\'localhost\'"'
	          sh 'mysql --defaults-file=/home/ubuntu/.my.cnf -u root webstore -e "create user \'appuser\'@\'localhost\' identified by \'Password1234%\'"'
	          sh 'mysql --defaults-file=/home/ubuntu/.my.cnf -u root webstore -e "grant all privileges on webstore.* to appuser@localhost"'
	      }
}



node ('CAST-Web-Server') {
    stage ('Refresh CAST AAD'){
    	sh 'sudo systemctl restart tomcat'
    }
}

/*
node ('Docker-Build-Box') {
   
   stage ('Get Code') {
        git credentialsId: '1b132c46-025f-4c76-986d-91b3237c7c1f', url: 'https://gitlab.com/johnny2136/SmallFibonacci.git'
   }
   
   stage ('Build App') {
        sh 'javac -d bin -cp "src/lib/*" src/fibo/Fibonacci.java src/fibo/FibonacciTest.java'
   }
   
   stage ('Unit Tests') {
       dir ("bin") {
            sh 'java -cp .:../src/lib/* org.junit.runner.JUnitCore fibo.FibonacciTest'
       }
   }
   
   stage ('Build Docker Image') {
       writeFile file: 'Dockerfile', 
        text: """
        #
        # Demo Dockerfile
        #
        FROM ubuntu:latest
        
        RUN apt-get update
        RUN apt-get install -y openjdk-8-jdk
        RUN apt-get install -y openssh-server
        
        RUN mkdir /var/run/sshd
        RUN echo 'root:CastAIP1234' | chpasswd
        RUN sed -i 's/PermitRootLogin prohibit-password/PermitRootLogin yes/' /etc/ssh/sshd_config
        
        # SSH login fix. Otherwise user is kicked off after login
        
        ENV NOTVISIBLE "in users profile"
        RUN echo "export VISIBLE=now" >> /etc/profile
        
        COPY bin/fibo/* /home/fibo/
        COPY bin/lib/* /home/lib/
        WORKDIR /home
        
        EXPOSE 22
        CMD ["/usr/sbin/sshd", "-D"]
        """
        
        sh "docker build -t prabinovich/smallfibonacci . "
   }

   stage ('Publish Docker Image') {
       sh "docker push prabinovich/smallfibonacci"
   }
}

node ('Docker-Deploy-Box') {
    stage ('Docker Cleanup') {
        sh "docker stop smallfibonacci || true"
        sh "docker rm smallfibonacci || true" 
        sh "docker rmi prabinovich/smallfibonacci || true"
    }
    
    stage ('Run Docker Container') {
        sh "docker run --detach=true -p 2222:22 --name smallfibonacci prabinovich/smallfibonacci" 
    } 
}

*/

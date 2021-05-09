##### Find all local running containers
 - <code>while clear; do date; /bin/sh -c "docker ps -a";sleep 4; done</code>


##### Bring up the DB
 - <code>git clone https://github.com/skshukla/infra.git && cd infra && chmod +x run_postgres/run_postgres.sh && ./run_postgres/run_postgres.sh</code>

##### Check whether its working fine
 - <code>docker exec -it sachin_pg_c psql -h localhost --user sachin --db mydb</code>

 - <code>\d or \l can be executed thereafter</code>
  

##### To Run the application
 - <code>mvn clean spring-boot:run</code>
 
 
##### To Run the Test Case
 - <code>mvn clean test -Dtest=com.example.demo.service.MyServiceTest#empSave</code>


##### Don't forget to check the [Youtube Video](https://www.youtube.com/watch?v=SwUNDUOvg_k)

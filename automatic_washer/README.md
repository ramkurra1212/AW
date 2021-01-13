### Washing Machine Application

The task is to design and implement a backend service to control an appliance such as a wash machine. The API should be REST
based and the state of the appliance should be persisted to any form of persistent storage. 

The project should be implemented using Java. Feel free to use any 3rd party library that you are comfortable with. Unit tests are
expected and the assignment will be assessed based on good programming practices and design.


###Installation and Run

`$ mvn spring-boot:run`


###REST API description and test

`http://localhost:8080/swagger-ui.html#/`

`GET /api/washer - create the new washer machine in database

`GET /api/washer/(id)` - display the selected id details.

`GET /api/AllWahsers` - View all appliance washers: shows all washer descriptions

`GET /api/start` - Start appliance: execute chosen program

`GET /api/state` - View appliance state: show current state with timer to program end

`GET /api/stop` - Stop appliance: interrupt program execution

###Swagger_UI 

http://localhost:8080/swagger-ui.html#

###NB

App creates database file at user home folder

`~/automatic-washer-db`


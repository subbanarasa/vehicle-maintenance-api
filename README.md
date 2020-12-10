# vehicle-maintenance-api

#Swagger URL

http://localhost:8080/swagger-ui.html

#H2 console URL
http://localhost:8080/h2/

#Actuator URL
http://localhost:8080/actuator

#User Management

curl -X POST "http://localhost:8080/user/save" -H "accept: */*" -H "Content-Type: application/json" -d "{ \"cellPhone\": \"919900505288\", \"email\": \"subba@mail.com\", \"firstName\": \"Subba\", \"lastName\": \"Reddy\"}"

curl -X GET "http://localhost:8080/user/get/1" -H "accept: */*"

curl -X GET "http://localhost:8080/user/search/919900505288" -H "accept: */*"

#Vehicle Management

curl -X POST "http://localhost:8080/vehicle/save" -H "accept: */*" -H "Content-Type: application/json" -d "{ \"carModel\": \"i10\", \"manufactureName\": \"Hundai\", \"registrationNumber\": \"KA50N1542\", \"yearOfPurchase\": \"2011\"}"

curl -X GET "http://localhost:8080/vehicle/search/KA50N1542" -H "accept: */*"

#Vehicle ownership

curl -X POST "http://localhost:8080/vehicle/ownership/add_vehicle" -H "accept: */*" -H "Content-Type: application/json" -d "{ \"ownerId\": 1, \"vehicleId\": 2}"

curl -X GET "http://localhost:8080/vehicle/ownership/get_vehicles/1" -H "accept: */*"

#Vehicle Maintenance Record

curl -X POST "http://localhost:8080/vehicle/maintenance/save" -H "accept: */*" -H "Content-Type: application/json" -d "{ \"comments\": \"Done service\", \"maintenanceCost\": 1000.00, \"maintenanceDate\": \"2020-12-10\", \"serviceManager\": \"Hari\", \"serviceStationName\": \"Trident\", \"vehicleId\": 2}"

curl -X GET "http://localhost:8080/vehicle/maintenance/get/7" -H "accept: */*"

curl -X GET "http://localhost:8080/vehicle/maintenance/search/4" -H "accept: */*"

curl -X DELETE "http://localhost:8080/vehicle/maintenance/delete/9" -H "accept: */*"


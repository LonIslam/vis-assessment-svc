# Read Me First
The following command used to run the project:

* mvn spring-boot:run

prerequisites: Java,Maven,Mysql

by default the schema got created with a default data location in data.sql file

there are two controllers (ManagementController, DeviceController)
* ManagementController: contain api to update the device configuration status 'http://localhost:8080/iot-devices-assessment/devices'

with json body '{
"deviceId": 5,
"status": "READY",
"temperature": 12,
"simId": 5
}'


deviceId and status should not be null

if status was READY then the simId must not be null so the sim will be attached to the device and the device will be ready if the temperature between -25 and 85
and the sim status will be active

if the status was NOT_READY then the device status will be updated to  NOT_READY


* DeviceController: contains GET endpoint 'http://localhost:8080/iot-devices-assessment/iot-device/waiting-activation'

used to get all the devices Paginated with NOT_READY status regardless of the simStatus 
assuming that it might be not attached to a sim as it's not configured yet

using request params : page starts from 0, size


* DeviceController: contains GET endpoint 'http://localhost:8080/iot-devices-assessment/iot-device/available-sale'

used to get all devices paginated and ordered by device id Ascending only if the device status was READY, it's temperature between -25 and 85 and attached to a sim card


using request params : page starts from 0, size


### You can use "mvn clean package" to Build the project from the command-line

### You can use "mvn clean test" to run unit test in Maven

### You can use "mvn clean verify" to run integration test in Maven

### You can use "mvn spring-boot:run" to run the application

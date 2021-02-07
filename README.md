# MQ Injector
A simple MQ Message(s) injection example via REST interface using Java SpringBoot. 

## To Start

```bash
./gradlew run
```

## Configuration

```
ibm.mq.queueManager=QM1
ibm.mq.channel=DEV.ADMIN.SVRCONN
ibm.mq.connName=localhost(1414)
ibm.mq.user=admin
ibm.mq.password=passw0rd
```
Note: It is not recommended to store the password locally however to test this we can proceed with this. You can use environment variables to retrieve and use the password.



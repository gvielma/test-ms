# test-ms
Prueba de Arquitectura de Microservicios

### Instrucciones de Instalación. Puede ejecutar la solución tanto en entorno local como entorno Docker

1. Vaya al directorio donde se encuentra el archivo kafka-compose.yml y ejecute el comando:
   - ` docker-compose -f kafka-compose.yml up -d `
  - Esto extraerá un contenedor kafka y creará una red a nivel de contenedor para toda la solución.
2. Abra cada proyecto y ejecute el comando ` mvn install ` en el siguiente orden, si desea ejecuar en entorno local solo ejecute los proyectos desde su IDE.
   - eureka-server
   - customer-service
   - account-service
   - transaction-service
   - gateway
  - Cada proyecto tiene una directiva maven donde ejecutando este comando además de empaquetar el proyecto, se desencadenará la creación de la imagen docker. Este proceso puede tardar entre 4 y 5 min por cada proyecto, puedo ejecutarlo en paralelo en diferentes consola de comandos.
3. Para ejecutar la solución en ambiente docker, siga los pasos que se indican a continuación. si desea ejecutar en solo en ambiente local puede ignorar la configuracion de este paso 3. 
   - Desde una consola de comando ejecute: `docker exec -it service-broker sh` o vaya al escritorio de docker y haga clic en el botón "CLI" del contenedor service-broker. Esto abrirá una consola de comandos dentro del contenedor.
   - Ahora ejecute: ` apk add nano ` para agregar el editor nano
   - ejecute: ` cd opt/kafka_2.13-2.6.0/config/ ` para acceder a la carpeta de configuracion de kafka
   - ejecute: ` nano server.properties ` Abrirá el archivo de configuración kafkfa, vaya a la línea donde encontrará la siguiente declaración: `advertised.listeners=PLAINTEXT://127.0.0.1:9092 `
   - cambie el host al nombre del contenedor broker, en este caso service-broker, debería quedar así:
` advertised.listeners=PLAINTEXT://service-broker:9092 `
   - Esto habilitará la comunicación a nivel contenenedor con el broker. para salir y guardar:  `ctrl + x luego opción Yes y finalmente enter`
   - Para salir de la consola del contenedor ejecute `exit`
4. Ejecute los comandos a continuación en el mismo orden para ejecutar los contenedores y orquestar la solución.
   - `docker run --name=eureka-server --network=bank-net -d -p 8762:8762 neoris/ms-docker-eureka-server:0.0.1-SNAPSHOT`
   - `docker run --name=customer-service --network=bank-net -d -p 9001:80 neoris/ms-docker-customer-service:0.0.1-SNAPSHOT`
   - `docker run --name=account-service --network=bank-net -d -p 9002:80 neoris/ms-docker-account-service:0.0.1-SNAPSHOT`
   - `docker run --name=transaction-service --network=bank-net -d -p 9003:80 neoris/ms-docker-transaction-service:0.0.1-SNAPSHOT`
   - `docker run --name=gateway-service --network=bank-net -d -p 9000:8091 neoris/ms-docker-gateway:0.0.1-SNAPSHOT`
5. Si todos los contenedores se han levantado correctamente, puede ir a la siguiente url: http://localhost:8762/ Para ver el servidor eureka con los microservicios conectados.





 ##### Nota: Si desea probar la aplicacion en entorno local puede ejecutar la aplicación desde su IDE y use la collecion postman TEST_MS.postman_collection con la URL: http://localhost:8090/api/ para entorno Docker use el puerto 9000.
 
 #Oportunidades de mejoras.
 - Agregar un config-server.
 - Agregar seguridad a los endpoints. (SpringSecurity, OAUTH, SpringSession JWT, Cors etc..).
 - Agregar Actuactor.
 - Agregar manejo de logs y configuracion de perfiles
 - Actualizar configuracion del EventConsumerConfig
 - Mejorar comentarios y javaDoc
 - Configurar el despliegue desde un script y no desde Maven
 - implementar el patron Circuit-Braker (Resilience4j) para tolerancia a fallos.

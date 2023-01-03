# employee-manage-end
## Project Description

The project is an enterprise employee management system 1.0, using springboot + vue to build,Mybatis,RabbitMQ,Nginx,Keepalived,Mail Service,Swagger and other technologies.

(1)Front and back-end use json data format interaction, to achieve front and back-end separation.

## Back-end technology stack
- Spring Boot
- Swagger
- MyBatis
- MySQL
- Email Service
- JWT+Interceptor
- Nginx
- Docker
- Keepalived
- RabbitMQ
- Template
- ...

## Project Demo Address

The project is not uploaded to the server at the moment

## Project Address

[GitHub - Tang9696/employee-management-end


## Project Screenshot

### Swagger Integration
The difficulty lies in the selection of the version and the configuration of the interceptor.
In the actual development, you can also use Postman.
![image](https://user-images.githubusercontent.com/95304937/209448134-8b10a4e1-6817-4ac2-b9ba-400bb3d413ba.png)
![image](https://user-images.githubusercontent.com/95304937/209448183-4f637421-a5a4-4b07-a44d-9bda7561d982.png)

### RabbitMQ Screenshot
MQ integration is used for specific functions. We use MQ in two modules, attendance and leave, to reduce the pressure of writing to the database.
Ex:When we have an employee who needs to take a leave of absence and submits a leave of absence in the employee management system, but due to the company's rules for ordinary employees to take a leave of absence, we need to send an SMS to his supervisor, for this business scenario we need to call the leave of absence service and send an SMS at the same time, at this time we need two consumers (leave of absence service and SMS service) to consume the same message, in fact, the essence is to write a message to RabbitMQ that can be received by multiple The essence is to write a message to RabbitMQ that can be received by multiple consumers, so we can use Fanout Exchange, one producer, multiple consumers.
![image](https://user-images.githubusercontent.com/95304937/209448208-022b1683-068e-4ce7-99a5-879506a5b0b2.png)

Since a single service cannot meet high availability, a cluster is built, using keepalived+nginx to achieve high availability, deploying in docker and using related services.
![image](https://user-images.githubusercontent.com/95304937/209448350-dfc2e6eb-4576-4c70-ba0e-6c9fdb45a1d6.png)
![image](https://user-images.githubusercontent.com/95304937/209448305-2cd0cc8d-e66e-454c-860e-ce7ee9ee7b0b.png)

### Email Service
Use SpringBoot mail service spring-boot-starter-mail,Mailbox using Google Mail.
![image](https://user-images.githubusercontent.com/95304937/209448441-445689e5-bfe6-444d-b8bf-bd4ed16aba23.png)

For how to configure Google Mail, please refer to the official website
https://support.google.com/mail/announcements/10010805?hl=en

Template emails were selected for the email delivery format, using Template templates to render templates.
![image](https://user-images.githubusercontent.com/95304937/209448583-e02ab060-1ed2-46e6-b3ad-ec7a451b4741.png)

## Importing the project
The project is created with idea, you can directly import it into idea. If someone is still using Eclipse, you can also directly import.





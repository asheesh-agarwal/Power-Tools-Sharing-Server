[![Build Status](https://travis-ci.org/asheesh-agarwal/Power-Tools-Sharing-Server.svg?branch=master)](https://travis-ci.org/asheesh-agarwal/Power-Tools-Sharing-Server)
# Power-Tools-Sharing-Server

### Background

Every building, every neighborhood, has people passionate about owning every power tool imaginable. 
These are expensive and almost never used. They should be shared.

### Challenge

Part of the reason why nobody is winning in this space is because the for-profit nature of these, 
bound to needing to lend these ites for money. Instead, build a non-for-profit sharing economy where 
individuals are just sharing without asking anything in return, around power tools, an app, site, etc.

### Current Status

Currently server exposes RESTful APIs for following use-cases:
* User Registration
* User Login
* View Public Tools
* View My Tools
* Upload a new Tool
* Mark Tool as Available/Unavailable

### Whats Next

Next development interation will include below items:
* More functional test-cases and coverage

### Technologies Used

* Java 8 - Language used for developing server application
* [Spring Framework](http://projects.spring.io/spring-framework/) 4.1.7 - Development framework for building web based application
* [Spring Data JPA](http://spring.io/guides/gs/accessing-data-jpa/) - Development framework for managing data in datbase through web application
* PostgreSQL - Database provider for storing data
* [Spring Tool Suite](https://spring.io/tools) - IDE for project development
* [Travis-CI](https://travis-ci.org) - Tool used for continous integration and deployment of server code on Amazon EC2 server

### Continuous Integration & Deployment
* [Travis CI](https://travis-ci.org/asheesh-agarwal/Power-Tools-Sharing-Server) - This link takes you to the continous integration page of the power tool sharing server. 
As soon as some code is committed and pushed to this repository, Travis-CI service is invoked and a new build is created. The new build is then pushed to AWS EC2 instance via CodeDeploy service.

### Project Management
* [Project Dashboard](https://waffle.io/asheesh-agarwal/Power-Tools-Sharing) - This dashboard is current out-of-sync with the current state of this repository due to some technical issues in Waffle. I will look into this and will update this section later.

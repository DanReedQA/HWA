
## Hobby Web App - QA Consultancy Individual Project

This is an Hobby Web App based on a fantacy card store themed around Kaiba Corp. from the popular card game Yu-Gi-Oh. The aim of this application is to replicate the process of purchasing cards online. Using CRUD functions and API calls currently users can be created updated and deleted.

## Getting Started

These instructions will get you a copy of the project up and running on your local machine for development and testing purposes. See deployment for notes on how to deploy the project on a live system.
1. Clone this repository
2. Import this as a Maven project into your chosen IDE
3. Link the project to your MySQL instance by replacing IP-adress in the following .java file:
  
src.main.resources > application.properties
14> spring.datasource.username=<your user>
15> spring.datasource.password=<your password>
16> spring.datasource.url=<your MySQL instance url>
  
4. Run the App file found in:
src.main.java > App

5. Open the index.html file found in:
src.main.reasources.static.html> index.html

Use the website on the localhost:8181

### Prerequisites

- Java runtime environment
- IDE
- Maven version
- Git
- Jenkins

## Testing

Using JUnit and Mockito testing I tested the full functionality of each domain in the database and each of thier CRUD functions

## Built With

* [Maven](https://maven.apache.org/) - Dependency Management

## Versioning

We use [SemVer](http://semver.org/) for versioning.

## Authors

* **Dan Reed** - Developer - [DanReedQA](https://github.com/DanReedQA)

## License

This project is licensed under the MIT license - see the [LICENSE.md](LICENSE.md) file for details 

*For help in [Choosing a license](https://choosealicense.com/)*

## Acknowledgements

* **Nicholas** - Software Trainer - [nickstewarttds](https://github.com/nickrstewarttds)
* **Savannah** - Software Trainer - [savannahvaith](https://github.com/savannahvaith)
* **Tadas** - Software Trainer - [tvaidotas](https://github.com/tvaidotas)
* **Jordan** - Software Trainer - [JHarry444](https://github.com/JHarry444)



# Jersey Rest CRUD Project

Simple Rest project with JAX-RS(Jersey) for practising purposes.

# Table Of Contents

- [Technologies Used](#technologies-used)
- [Endpoints](#endpoints)
- [Launch](#launch)

# Technologies Used

- Jersey
- Spring Web
- Spring Data
- Hibernate
- Postgresql
- JUnit
- Mockito
- Log4j
- Apache Tomcat

## Endpoints

**Department Endpoints**

| **Method** | **Endpoint**                     | **Description**                      |
|------------|----------------------------------|--------------------------------------|
| GET        | /rest/departments                | Returns departments list             |
| GET        | /rest/departments/{departmentId} | Returns department by department id. |
| POST       | /rest/departments                | Saves department.                    |
| PUT       | /rest/departments/{departmentId}  | Updates department.                  |
| DELETE     | /rest/departments/{departmentId} | Deletes department by department id. |

**Staff Endpoints**

| **Method** | **Endpoint**                                      | **Description**                                         |
|------------|---------------------------------------------------|---------------------------------------------------------|
| GET        | /rest/departments/{departmentId}/staffs           | Returns staff list for department with department id.   |
| GET        | /rest/departments/{departmentId}/staffs/{staffId} | Returns staff by department id and staff id.            |
| POST       | /rest/departments/{departmentId}/staffs           | Saves staff to department specified with department id. |
| PUT        | /rest/departments/{departmentId}/staffs/{staffId} | Updates staff.                                          |
| DELETE     | /rest/departments/{departmentId}/staffs/{staffId} | Deletes staff.                                          |

# Launch

- To build and run project:

	```bash
	$ git clone https://github.com/erhantuncel/RestCrudJerseyHibernate.git
	$ cd RestCrudJerseyHibernate
	$ mvn clean package -DskipTests
	$ docker-compose up
	```

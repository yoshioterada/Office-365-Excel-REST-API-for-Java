# For Tomcat User

If you would like to run the application on Tomcat,</br> 
please modify pom.xml file and create 2 file for CDI configuration ?

## 1. Please add following dependencies in pom.xml.
```XML
    <dependencies>
        <dependency>
            <groupId>javax</groupId>
            <artifactId>javaee-api</artifactId>
            <version>7.0</version>
            <scope>provided</scope>
        </dependency>
        <dependency>
            <groupId>javax.enterprise</groupId>
            <artifactId>cdi-api</artifactId>
            <version>1.2</version>
            <scope>provided</scope>
        </dependency>
        <dependency>
            <groupId>org.jboss.weld.servlet</groupId>
            <artifactId>weld-servlet</artifactId>
            <version>2.3.5.Final</version>
            <scope>runtime</scope>
        </dependency>
        <dependency>
            <groupId>org.primefaces</groupId>
            <artifactId>primefaces</artifactId>
            <version>5.0</version>
        </dependency>
        <dependency>
            <groupId>javax.faces</groupId>
            <artifactId>javax.faces-api</artifactId>
            <version>2.2</version>
            <scope>provided</scope>
        </dependency>
        <dependency>
            <groupId>com.fasterxml.jackson.jaxrs</groupId>
            <artifactId>jackson-jaxrs-json-provider</artifactId>
            <version>2.3.2</version>
        </dependency>
        <dependency>
            <groupId>com.fasterxml.jackson.core</groupId>
            <artifactId>jackson-databind</artifactId>
            <version>2.3.2</version>
        </dependency>
        <dependency>
            <groupId>org.glassfish.jersey.media</groupId>
            <artifactId>jersey-media-json-jackson</artifactId>
            <version>2.23.1</version>
        </dependency>
        <dependency>
            <groupId>org.glassfish</groupId>
            <artifactId>javax.faces</artifactId>
            <version>2.2.13</version>
            <scope>runtime</scope>
        </dependency>
        <dependency>
            <groupId>org.glassfish.jersey.security</groupId>
            <artifactId>oauth2-client</artifactId>
            <version>2.23.1</version>
        </dependency>
        <dependency>
            <groupId>javax.ejb</groupId>
            <artifactId>javax.ejb-api</artifactId>
            <version>3.2</version>
        </dependency>
        <dependency>
            <groupId>javax.transaction</groupId>
            <artifactId>javax.transaction-api</artifactId>
            <version>1.2</version>
        </dependency>
    </dependencies>
```
## 2. In order use the CDI, please create two file as follows. 

2.1 create “beans.xml" on WEB-INF (same directory as web.xml)
```XML
<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://xmlns.jcp.org/xml/ns/javaee"
       xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
       xsi:schemaLocation="http://xmlns.jcp.org/xml/ns/javaee http://xmlns.jcp.org/xml/ns/javaee/beans_1_1.xsd"
              bean-discovery-mode="annotated">
</beans>
```

2.2 create “context.xml” on WEB-INF (same directory as web.xml)
```XML
<?xml version="1.0" encoding="UTF-8"?>
<Context antiJARLocking="true" path="/tomcat-test">
  <Resource
    name="BeanManager"
    auth="Container"
    type="javax.enterprise.inject.spi.BeanManager"
    factory="org.jboss.weld.resources.ManagerObjectFactory"
    />
</Context>
```

After the modification, finally,please re-build and re-depoy ?

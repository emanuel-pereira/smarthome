# README #

This is the repository used for SWitCH's, 2018-2019 Edition projects.

## What is this repository for? ##

It contains the project implementation.

## Who do I talk to? ##
In case you have any problem, please talk to:

* Ângelo Martins (amm@isep.ipp.pt)

## How was the .gitignore file generated? ##
.gitignore file was generated based on https://www.gitignore.io/ with the following keywords:
  - Java
  - Maven
  - Eclipse
  - NetBeans
  - Intellij

## How do I use Maven? ##

### How to run unit tests? ###
Execute the "test" goals.
`$ mvn test`

### How to generate the javadoc for source code? ###
Execute the "javadoc:javadoc" goal.

`$ mvn javadoc:javadoc`

This generates the source code javadoc in folder "target/site/apidocs/index.html".

### How to generate the javadoc for test cases code? ###
Execute the "javadoc:test-javadoc" goal.

`$ mvn javadoc:test-javadoc`

This generates the test cases javadoc in folder "target/site/testapidocs/index.html".

### How to generate Jacoco's Code Coverage Report? ###
Execute the "jacoco:report" goal.

`$ mvn test jacoco:report`

This generates a jacoco code coverage report in folder "target/site/jacoco/index.html".

### How to generate PIT Mutation Code Coverage? ###
Execute the "org.pitest:pitest-maven:mutationCoverage" goal.

`$ mvn test org.pitest:pitest-maven:mutationCoverage`

This generates a PIT Mutation coverage report in folder "target/pit-reports/YYYYMMDDHHMI".

### How to combine different maven goals in one step? ###
You can combine different maven goals in the same command. For example,
to locally run your project just like on jenkins, use:

`$ mvn clean test jacoco:report org.pitest:pitest-maven:mutationCoverage`

The previous maven command execution result can be seen as previously in the project folder
by accessing "local-path/project_g6/target/pit-reports/"
Here you should be able to see a folder with the test date timestamp and inside you will be
able to see PIT coverage test for each class and a index.html file which you can open in
order to easily check the result in your browser.

You can also check this file in the left side panel in the Project folder tab, under
"target/pit-reports/YYYYMMDDHHMI".
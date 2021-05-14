# Automation GUI Project Demo
This automation framework was built with Java 8 and maven, which you need installed in order to execute it.
Additional dependencies are installed automatically with maven.

In ```src/test/resources/config.properties``` you could change the base url for the system under test and the used browser (Firefox or Chrome).
The included driver for Chrome is for 90.0 version.

To execute it, in the project path run the command: ```mvn clean verify```

This is a Intellij project so if you already have it installed you could execute it using the checkoutTest class inside the test package,
intellij is not required to execute this from the commandline.
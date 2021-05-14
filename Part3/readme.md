# Part 3: Reflection on the automation exercise

*	It is a System test as the test are focus on the functionality of the whole system 
rather than specific parts of it or the interactions between those parts. 
As in this case the test is designed using a black box approach 
this test case verifies the behavior of the system in an end-to-end flow 
without knowing which parts of the system interact during this flow.

* Test in other scopes:
    * Unit testing: inferring the internal structure of the system we could have a good coverage of unit testing in the different components of the system (layers, microservices, orchestrators, GUI clients) making test for the specific modules, classes and functions that are necessary for the functionalities related with searches, filters, getting products details, calculating expenses, etc. 
        * Pros: Quicker feedback, test easier to implement and maintain, defects are easier and cost lest to fix.
        * Cons: interaction between those components is not tested in this scope, overall system behavior or how the software behaves in a productive similar environment is not tested neither, defects might still exist in the system and this test wouldn’t be able to find them.
    * Integration testing: In this scope we test the interaction between components/modules of the system, as the scope of this type is to test the interaction itself, we use mocks, drivers and stubs to design test focused on those interactions.
        * Pros: we cover the communications, interactions, and integrations between multiple components, ensuring that those components work as expected interacting with each other.
        * Cons: A good integration test verifies the integration of the involved components and not their individual functionalities, so with this test we are not covering what was covered in the unit testing being the internal functionality of the system, again we are leaving important parts of the system without being tested. Also, integration tests are more complex than unit test, requiring more time to develop and to maintain.
    * System testing: In these tests as we describe before we test the overall functionalities of the system, in general we usually design this test using a black box approach.
        * Pros: system test are usually in line of what actual final users could do in the system finding defects that are more important from a business perspective, a single test could cover multiple system components and interactions between them.
        * Cons: test require more effort to be designed as we should think more in multiple real world possible scenarios, test are also more complex than unit and integration test, harder to maintain and implement, defects found with this test are more expensive to fix and requires more effort and time as it requires to debug multiple parts of the system to find the root cause/causes of the defect. Test also requires more execution time. We are just covering the specific components and integrations that are part of the tested flow, leaving internal parts of the system without coverage.
    * Acceptance Testing:  Acceptance test aims to check if the system is ready to work in an operational environment with real users that should be in the capacity of fulfil their requirements with the system.
        * Pros: In this testing types we usually cover other non-functional aspects of the system, like security, performance, usability, etc. As we also test functionalities of the system, so we are covering different aspects and parts of the system with the same test.
        * Cons: this test are more focused to ensure the correctness of the system rather than finding defects as those defects should had been found in previous layers, test are harder to implement and execute as it could involve different tools, licenses or require to be executed by different stakeholders like system administrators, final users, or legal auditors.
## Conclusion: 
Test in one single scope are not enough no matter which scope is chosen, 
a good testing coverage includes tests in those different scopes, 
trying to reduce the number of test on the higher layers 
but ensuring that those reductions are because those parts have been 
already addressed in previous layers.

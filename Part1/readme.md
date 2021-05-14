# Part 1: Exploratory testing - Booking a stay in a hotel
## Goal: booking functionality
## Notes:
### Risks:
* Booking form might be susceptible to SQL injection - security.
* Console is logging information of responses, giving additional unnecessary system details – security.
* Booking form does not validate the fields in the frontend, some of them just in the backend. – Data consistence, usability.
* Adding and deleting extras must update the total information. -Functionality

### Should be tested:
* Normal successful booking priority High, Severity High
* Booking with wrong data priority High, Severity High
* Booking with extras priority Medium, Severity High
* Booking with coupons priority Medium, Severity Medium
* Booking with a logged user priority High, Severity High
## Test: 
We choose the test with higher priorities and severities:
* Booking successful booking:
With correct data we can continue to the payment the process, but the email until this point seems to be ignored as no confirmation mail is received, this is also a desired functionality as user for sure need to track the booking process using its own email as this are non-registered users.
* Booking with wrong data:
In names, address and number you are allowed to include special characters, allowing users to introduce wrong data, the only field that is checked is the email that just checks that it has an email structure, this verification is made by the backend, the frontend doesn’t seem to validate any form field. If you introduce a wrong email the system does not allow you to continue but it does not show any error, this is also a defect as users might not be aware that they wrote a wrong email and they could just assume that the webpage is not working. From the API perspective the request with a wrong email response a 200 successful status code, but it returns an error in the response, this error is ignored by the frontend as we mention that the front end does not display it, being a wrong request, the API should return that in the status code (400) and include the error description as a message.
* Booking with a logged user: test user credentials for the system doesn’t work, we should update them to continue with this scenario, I could register a new user but as I am not sure if I have permission to perform test with a different user I will not continue with this scenario until new credentials are provided.

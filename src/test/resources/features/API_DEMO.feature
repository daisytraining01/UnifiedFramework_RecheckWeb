Feature: API demo Feature

  @apipositive
  Scenario: Get and Update User Details
    Given I get User Details
    Then I create user

  @apinegative
  Scenario: Create and Delete User
    Then I update User Details
    And I Delete User
    
   Scenario: Test Login
   Given Access the Application URL
   When Enter the Username
   And Enter the Password
   Then I verify the Login successful
   
   Given Access the URL in the "desktop-web"
    And Enter the User Credentials and Signin as Maveric Systems
    Then Check the balance of Source Checking Account
	When Perform Transfer between two Savings Account

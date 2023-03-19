@p0
  Feature: Log in

    Scenario: Log in negative test
      When Click on submit button
      Then Verify error message is - "Please enter your name"

    Scenario: Log in positive test
      Given Enter "Shashank" in the name field
      And Select "Australia" as country
      And Select "male" as gender
      Then Click on submit button

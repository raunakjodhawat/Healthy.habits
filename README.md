# Healthy Habits

Goal is to have an easy to use, nudging API. That takes no more than 10 goals that you set for an year. For example:
- I will read 1 book per month, please remind me every Saturday and ask for my update.
- I will spend 1 hour every day learning a musical instrument. Remind be every morning.

And then get the daily/weekly/monthly report of all the status. With an assigned score.
All this information, will be served in a way that, any one from age 8 to 80 can use.

healthy Habits is aimed to easily create an ecosystem/nudges for users on the basis of their prefrences

## Sign in
- You can Sign in with your phone number. For First sign in we will ask your name, and an OTP (generated on the phone number)

## User's API endpoint
- /POST createUser (phoneNumber, countryCode, name)
    - Sends an OTP (10 minutes validity) via Text
- /PUT authenticateUserOTP (phoneNumber, countryCode, OTP)
  - Successfull verification [User is added into DB, and success message is sent]
    - on OTP mis-match, ask user to input for 3 tries. 
    - If all three tries fail, block user's phone number for 30 minutes
  
## Habit's API
- Create new habit
- View Today's status
- Mark as Completed
- Delete a habit
- Update a habit

## Try out
Please use insomnia.json file to try the project

## System requirements
- Scala 2.12.15 (Play Does not work with Scala 3 yet)
- JDK 11 (Play does not work with JDK 17)
- Postgres (CRUD operations will be done in postgres)

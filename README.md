# Healthy Habits

healthy Habits is aimed to easily create a ecosystem/nudges for users on the basis of their prefrences

## Sign in
- You can Sign in with your phone number. For First sign in we will ask your name, and an OTP (generated on the phone number)

## User's API endpoint
/POST createUser (phoneNumber, countryCode, name)
 => Sends an OTP (10 minutes validity) via Text
/PUT authenticateUserOTP (phoneNumber, countryCode, OTP)
 => Successfull verification [User is added into DB, and success message is sent]
 => on OTP mis-match, ask user to input for 3 tries. 
  => If all three tries fail, block user's phone number for 30 minutes
  
## Habit's API
- Create new habit
- View Today's status
- Mark as Completed
- Delete a habit
- Update a habit


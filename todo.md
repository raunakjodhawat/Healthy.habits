## TODO's

This space is to keep track of missing features/functionalities that will be implemented soon. All the features listed here are critical for scaling.  


Users 
- Initiate create user
  - Have a batch process that runs every 15 minutes, and deletes tuples from `pendingOTPVerification` when `currentTime - creationTime` is `more than 15 minutes`
    - It is not needed now, as we don't anticipate huge growth in usage of this table in initial few days/months
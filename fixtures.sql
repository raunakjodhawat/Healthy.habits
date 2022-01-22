create table IF NOT EXISTS pendingOTPVerification(id SERIAL PRIMARY KEY, phoneNumber varchar(10), countryCode varchar(5), otpCode varchar(6), creationTime timestamp not null default current_timestamp);
INSERT INTO pendingOTPVerification(phoneNumber, countryCode) VALUES ('999999999', '+1', '123456');
SELECT * FROM pendingOTPVerification;
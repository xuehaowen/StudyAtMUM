movies = LOAD '/user/cloudera/lab5/q4output/part-r-00000' AS (movieid:int,genre:chararray,rating:int,title:chararray);

usersDS = LOAD '/user/cloudera/lab5/users.txt' USING PigStorage ('|') AS (userid:int,age:int,gender:chararray,occupation:chararray,zipcode:chararray);
users = FILTER usersDS BY gender=='M' AND occupation=='programmer';

ratingDS = LOAD '/user/cloudera/lab5/rating.txt' AS 
(userid:int,movieid:int,rating:int,timestamp:long);

userAndMovie = JOIN movies BY $0, ratingDS BY $1;
maleProgrammerAndMovie = JOIN userAndMovie BY $4, users BY $0;
gp = GROUP maleProgrammerAndMovie ALL;
result = FOREACH gp generate group, COUNT($1);
STORE result into '/user/cloudera/lab5/q5output';

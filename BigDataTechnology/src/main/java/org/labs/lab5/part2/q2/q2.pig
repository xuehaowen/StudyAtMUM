usersDS = LOAD '/user/cloudera/lab5/users.txt' USING PigStorage ('|') AS (userid:int,age:int,gender:chararray,occupation:chararray,zipcode:chararray);
maleusers = FILTER usersDS BY gender == 'M';
malelawyers = FILTER maleusers BY occupation == 'lawyer';
x = GROUP malelawyers ALL;
y = FOREACH x GENERATE group,MAX($1.age),$1.userid;
STORE y into '/user/cloudera/lab5/q2output';

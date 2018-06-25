usersDS = LOAD '/user/cloudera/lab5/users.txt' USING PigStorage ('|') AS (userid:int,age:int,gender:chararray,occupation:chararray,zipcode:chararray);
maleusers = FILTER usersDS BY gender == 'M';
malelawyers = FILTER maleusers BY occupation == 'lawyer';
gp = GROUP malelawyers ALL;
count = FOREACH gp GENERATE group,COUNT($1);
STORE count into '/user/cloudera/lab5/q1output';

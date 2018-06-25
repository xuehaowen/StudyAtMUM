users = LOAD '/user/cloudera/users.csv' USING PigStorage(',') AS(name:chararray,age:int);
sites = LOAD '/user/cloudera/pages.csv' USING PigStorage(',') AS(name:chararray,url:chararray);
newusers = FILTER users BY age<25 AND age>18;
joining = JOIN newusers BY name, sites BY name;
gp = GROUP joining BY $3;
records = FOREACH gp GENERATE group,COUNT($1);
ordered = ORDER records BY $1 DESC;
result =LIMIT ordered 5;

STORE result INTO 'output';

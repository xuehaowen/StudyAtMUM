movieDS = LOAD '/user/cloudera/lab5/movies.csv' USING PigStorage (',') AS (movieid:int,title:chararray,genres:chararray);
filtered = FILTER movieDS BY (title matches '[aA].*');
movies = FOREACH filtered GENERATE movieid,TOKENIZE (genres,'|');
flatmovie = FOREACH movies GENERATE $0, FLATTEN ($1);
gp = GROUP flatmovie BY $1;
count = FOREACH gp GENERATE group, COUNT($1);
STORE count into '/user/cloudera/lab5/q3output';

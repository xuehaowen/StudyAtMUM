file = LOAD '/user/cloudera/wordcount.txt' AS (line:chararray);
tokenBag = FOREACH file GENERATE TOKENIZE (line); 
flatBag = FOREACH tokenBag GENERATE FLATTEN ($0);
gp = GROUP flatBag BY $0;
counts = FOREACH gp GENERATE group,COUNT($1);
dump counts;

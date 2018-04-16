# Setup
VM or VMBox or Docker

# fetch the docker image
docker pull cloudera/quickstart:latest

# run a container with the image
docker run --hostname=quickstart.cloudera 
  --privileged=true \
  -t -i \
  -p 8888:8888 \
  -p 80:80 \
  -p 8088:8088 \
  cloudera/quickstart \
  /usr/bin/docker-quickstart

# create new file and directory
sudo su hdfs  \
hadoop fs -mkdir /user/cloudera \
hadoop fs -chown cloudera /user/cloudera  \
exit  \

sudo su cloudera  \
hadoop fs -mkdir /user/cloudera/wordcount /user/cloudera/wordcount/input  \
exit  

# add some input data 
echo "Hadoop is an elephant" > file0  \
echo "Hadoop is as yellow as can be" > file1  \
echo "Oh what a yellow fellow is Hadoop" > file2  \
hadoop fs -put file* /user/cloudera/wordcount/input 

# put jar on hdfs and run jar
docker cp .\WordCount.jar [containerId]:/wc.jar \
hadoop jar wc.jar org.myTest.WordCount /user/cloudera/wordcount/input /user/cloudera/wordcount/output 

# check the output
hadoop fs -cat /user/cloudera/wordcount/output/*  

# run the sample again
hadoop fs -rm -r /user/cloudera/wordcount/output  \
hadoop jar wc.jar org.myTest /user/cloudera/wordcount/input /user/cloudera/wordcount/output 

logfile=$1

echo " [+] building ncc"
mvn clean package -Dmaven.test.skip=true >> $logfile


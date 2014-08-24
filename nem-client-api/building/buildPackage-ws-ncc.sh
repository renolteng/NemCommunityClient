cd ../../../nis
rm site/serverjars/nem-infra*
rm webstart/html/lib/nem-infra*
mvn -fpom-core.xml clean compile install -Duser.name=$3 -DskipTests=true

cd ../ncc/nem-client-download
mvn clean compile install -Duser.name=$3 -DskipTests=true  

cd ../nem-monitor
mvn clean compile install -DskipTests=true -Dnem-certificate-store=$1 -Dnem-certificate-password=$2

cd ../nem-client-api
rm target/nem-*
rm site/jars/nem-*
mvn clean compile install -Duser.name=$3 -DproGuard=$4 -DskipTests=true -Dnem-certificate-store=$1 -Dnem-certificate-password=$2

cd ../nem-client-api/building
rm nem-*
bash ./copy-ws.sh
bash ./buildPackage-ws-sign-ncc.sh $1 $2 $3
cd ../site
rm NCC.zip
7z a -r -x!NCC.zip NCC.zip *
cp NCC.zip ../../..

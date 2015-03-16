cd ../../../ncc/nem-client-api
rm target/nem-*
rm site/jars/nem-*
mvn clean compile install -Duser.name=$3 -DproGuard=$4 -DskipTests=true -Dnem-certificate-store=$1 -Dnem-certificate-password=$2

cd ../nem-monitor
mvn clean compile install -DskipTests=true -Dnem-certificate-store=$1 -Dnem-certificate-password=$2

cd ../nem-client-api/building
rm nem-*
bash ./copy-ws.sh
bash ./buildPackage-ws-sign-ncc.sh $1 $2 $3
cd ../site
rm NCC.zip
7z a -r -x!NCC.zip NCC.zip *
cp NCC.zip ../../..

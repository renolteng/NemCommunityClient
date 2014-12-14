rem batch files have funny escaping
rem

pushd nem-monitor
  awk "{ if (match($0, /(.*\.)([0-9]+)(-BETA.*)/, arr)) { printf \"%%s%%d%%s\n\", arr[1], arr[2]+1, arr[3] } else { print } }" < pom.xml > pom.out
  tr --delete "\r" < pom.out > pom.xml
  rm pom.out
  git add pom.xml
popd

pushd nem-client-download
  awk "{ if (match($0, /(.*\.)([0-9]+)(-BETA.*)/, arr)) { printf \"%%s%%d%%s\n\", arr[1], arr[2]+1, arr[3] } else { print } }" < pom.xml > pom.out
  tr --delete "\r" < pom.out > pom.xml
  rm pom.out
  git add pom.xml
popd

pushd nem-client-api
  awk "{ if (match($0, /(.*\.)([0-9]+)(-BETA.*)/, arr)) { printf \"%%s%%d%%s\n\", arr[1], arr[2]+1, arr[3] } else { print } }" < pom.xml > pom.out
  tr --delete "\r" < pom.out > pom.xml
  rm pom.out

  git add pom.xml
popd

awk "{ a=gensub(/(.*\.)([0-9]+)(-BETA.*)/,\"\\2\", \"g\",$0); if(a~/[0-9]+/) { gsub(/[0-9]+-BETA/, a+1\"-BETA\", $0);} print $0; }" < .travis.yml > .travis.out
tr --delete "\r" < .travis.out > .travis.yml
rm .travis.out
git add .travis.yml

awk "{ a=gensub(/(.*\.)([0-9]+)(-BETA.*)/,\"\\2\", \"g\",$0); if(a~/[0-9]+/) { gsub(/[0-9]+-BETA/, a+1\"-BETA\", $0);} print $0; }" < install-core.bat > install-core.bat.out
ren install-core.bat.out install-core.bat
git add install-core.bat

sed -n "/BETA/{ s/.*\([0-9]\+.[0-9]\+.[0-9]\+-BETA\).*/bump version to \1/; p }" .travis.yml | xargs -iXX git commit -m XX


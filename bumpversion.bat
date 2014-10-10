rem batch files have funny escaping
rem

pushd nem-monitor
  awk "{ if (match($0, /(.*\.)([0-9]+)(-ALPHA.*)/, arr)) { printf \"%%s%%d%%s\n\", arr[1], arr[2]+1, arr[3] } else { print } }" < pom.xml > pom.out
  tr --delete "\r" < pom.out > pom.xml
  rm pom.out
  git add pom.xml
popd

pushd nem-client-download
  awk "{ if (match($0, /(.*\.)([0-9]+)(-ALPHA.*)/, arr)) { printf \"%%s%%d%%s\n\", arr[1], arr[2]+1, arr[3] } else { print } }" < pom.xml > pom.out
  tr --delete "\r" < pom.out > pom.xml
  rm pom.out
  git add pom.xml
popd

pushd nem-client-api
  awk "{ if (match($0, /(.*\.)([0-9]+)(-ALPHA.*)/, arr)) { printf \"%%s%%d%%s\n\", arr[1], arr[2]+1, arr[3] } else { print } }" < pom.xml > pom.out
  tr --delete "\r" < pom.out > pom.xml
  rm pom.out

  git add pom.xml
popd

awk "{ a=gensub(/(.*\.)([0-9]+)(-ALPHA.*)/,\"\\2\", \"g\",$0); if(a~/[0-9]+/) { gsub(/[0-9]+-ALPHA/, a+1\"-ALPHA\", $0);} print $0; }" < .travis.yml > .travis.out
tr --delete "\r" < .travis.out > .travis.yml
rm .travis.out

git add .travis.yml
sed -n "/ALPHA/{ s/.*\([0-9]\+.[0-9]\+.[0-9]\+-ALPHA\).*/bump version to \1/; p }" .travis.yml | xargs -iXX git commit -m XX


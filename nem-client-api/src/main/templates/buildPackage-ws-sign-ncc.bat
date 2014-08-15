REM Remove Signature 
7z d nem-client-api__V${project.version}.jar "META-INF/*"
7z d nem-core__V${project.version}.jar "META-INF/*"
REM Add Meta file again
7z u nem-client-api__V${project.version}.jar "META-INF/*"
7z u nem-core__V${project.version}.jar "META-INF/*"

"${javaHome}\..\bin\jarsigner" -keystore %1 -storepass %2 -keypass %2 nem-client-api__V${project.version}.jar nem-cert
"${javaHome}\..\bin\jarsigner" -keystore %1 -storepass %2 -keypass %2 nem-core__V${project.version}.jar nem-cert
copy /Y /V nem-core*.jar ..\site\jars
copy /Y /V nem-client-api*.jar ..\site\jars

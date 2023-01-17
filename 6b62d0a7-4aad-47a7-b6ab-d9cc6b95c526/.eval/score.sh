#!/bin/bash
evalDir=.eval

mvn clean test spring-boot:start exec:exec spring-boot:stop -Dscoring.dir="${evalDir}" -Dmaven.test.failure.ignore=true -Dserver.port=8001 -Dspring-boot.run.arguments="--server.port=\${server.port}"

mvn -q -f "${evalDir}/pom.xml" compile exec:java -Deval.dir="${evalDir}" -Dexec.mainClass="com.hackerrank.eval.evaluation.PerformEvaluation" | tee evaluation.out

exit 0

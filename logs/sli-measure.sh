echo "$(grep Response staticLogs.log)"  > http.sh
httpCodes=$(grep Response staticLogs.log | cut -f 2 -d [ | cut -d ] -f 1 | cut -d " " -f 1)
httpRequestTotal=0
httpFailures=0

echo "$(grep ms http.sh | cut -f 2 -d , | cut -f 4 -d " ")" > latency.sh
# latency=$(grep ms http.sh | cut -f 2 -d , | cut -f 4 -d " ")


for response in $httpCodes
do
	((httpRequestTotal++))
	if [ $response -eq 500 ]
	then
		((httpFailures++))
	fi
done

result=$(awk "BEGIN {print $httpRequestTotal / $httpSuccess * 100; exit}")
totalMs=$(awk '{Total=Total+$1} END{print Total}' latency.sh)
avgMiliseconds=$(awk "BEGIN {print $totalMs / $httpRequestTotal; exit}")

httpSuccess=$(($httpRequestTotal - $httpFailures))
echo "~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~"
echo "Total http requests: " $httpRequestTotal 
echo "Successful http requests: " $httpSuccess 
echo "Bad http requests: " $httpFailures
echo "Success http requests percentage : "$result%
echo "Total latency time: "$totalMs "ms"
echo "Average latency time: "$avgMiliseconds "ms"

# variableName=$(awk "BEGIN {print $var1 (+,-,/,*) $var2; exit}")

echo "~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~"



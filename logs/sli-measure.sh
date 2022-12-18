echo "$(grep Response staticLogs.log)"  > http.sh
echo "Filtered INFO from 'staticLogs' was pushed into a 'http.sh' file"
httpCodes=$(grep Response staticLogs.log | cut -f 2 -d [ | cut -d ] -f 1 | cut -d " " -f 1)
httpRequestTotal=0
httpFailures=0

# echo "$(grep ms http.sh | cut -f 2 -d , | cut -f 4 -d " "))" > latency.sh

for response in $httpCodes
do
	((httpRequestTotal++))
	if [ $response -eq 500 ]
	then
		((httpFailures++))
	fi
done

httpSuccess=$(($httpRequestTotal - $httpFailures))
echo "~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~"
echo "Total http requests: " $httpRequestTotal 
echo "SUCCESSFUL http requests: " $httpSuccess 
echo "BAD http requests: " $httpFailures
echo "~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~"

result=$(awk "BEGIN {print $httpSuccess / $httpRequestTotal * 100; exit}")

if [[ $result% > 98% ]]

then
	echo "HTTP success rate is $result%, SLO is being maintained"
else
	echo "HTTP success rate is $result%, SLO is NOT being maintained"
fi



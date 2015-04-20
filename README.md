# javacachingsystem
Concurrent hashmap is used because it is thread safe and also it's lookup and storage complexity depends upon no of segment used but on average it's nearly O(1) .
Key value is taken as an integer and value is store in another data structure (double linked list) and it's value is taken as string
Double linked list is used to store head and end node comprising recently used pair and last used pair.

getvalue function is comprised of two condition :-
1) if key is not there than return null
2) if key is present than first delete the earlier node in cache than set the head node towards that node

setvalue function is also comprised of two condition :-
1) If key is already there than follow the same instruction as followed in getvalue function condition 2
2) If key is not there than first check whether the cache frame size is filled or not 
	a) If filled than delete the last key value pair in cache than set the head node towards the new pair and add it in the map.
	b) If not filled than add the key value pair in map and set the head node towards that node.
	
printdata function prints all the key,value pair stored in cache.

# webflux

심심해서 공부하는 웹플럭스(짭플럭스).  


**map과 flatMap..?**    
map은 많이 본 친구고,,, 근데 flatMap?? 둘 다 스트림의 중간에 값을 변환해주는 역할을 한다. (hashMap할 때 그 Map 아님!!). 
map은 1 : 1로 변환하고, flatMap은 1 : N 으로 변환 가능하다!

## map
map()은 단일 스트림 원소를 매핑시킨 후 매핑시킨 값을 다시 스트림으로 변환하는 중간 연산이다! (객체에서 원히는 원소를 추출)
넘 많이 쓰면 연산마다 객체를 생성시켜서 GC가 많이 일해야하는 단점이 있다


## flatMap
flatMap()은 Array나 Object로 감싸져 있는 모든 원소를 단일 원소 스트림으로 반환한다. map()은 입력한 원소를 그대로 스트림으로 반환하지만, **flatMap()은 입력한 원소를 가장 작은 단위의 단일 스트림으로 반환한다!!!**

var DIFFS, TIMES, LIMIT

function solution(diffs, times, limit) {
    DIFFS = diffs
    TIMES = times
    LIMIT = limit
    
    var answer = 0;
    let l=1, r=100000, mid=50000, isEasy=false
    
    while(l+1<r){
        mid = Math.floor((l+r)/2)
        
        isEasy = solve(mid)
        if(isEasy) r=mid
        else l=mid
    }
    
    while(solve(mid) && mid>1) mid--
    while(!solve(mid)) mid++
    
    answer = mid
    
    return answer;
}

function solve(level){
    let time=0
    let d
    for(let i=0; i<DIFFS.length; i++){
        if(DIFFS[i]>level){
            d = DIFFS[i]-level
            time+= TIMES[i]*(d+1) + TIMES[i-1]*d
        } else{
            time+= TIMES[i]
        }
        
        if(time>LIMIT) return false
    }
    return true
}
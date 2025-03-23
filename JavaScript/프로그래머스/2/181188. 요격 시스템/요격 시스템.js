function solution(targets) {
    var answer = 0;
    targets.sort((a, b)=>a[1]-b[1])
    
    answer++
    let cur = 0
    for(let i=0; i<targets.length; i++){
        if(i==cur) continue
        if(targets[i][0]<targets[cur][1]) continue
        
        // 요격 불가
        cur = i
        answer++
    }
    
    return answer;
}
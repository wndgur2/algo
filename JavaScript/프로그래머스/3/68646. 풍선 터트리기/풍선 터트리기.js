const MAX_NUMBER = 1000000000

function solution(a) {
    var answer = 0;
    let minFromStart = Array(a.length)
    let minFromEnd = Array(a.length)
    
    let curMin = MAX_NUMBER
    for(let i=0; i<a.length; i++){
        if(a[i]<curMin) curMin = a[i]
        minFromStart[i] = curMin
    }
    
    curMin = MAX_NUMBER
    for(let i=a.length-1; i>=0; i--){
        if(a[i]<curMin) curMin = a[i]
        minFromEnd[i] = curMin
    }
    
    a.forEach((v, i)=>{
        let left = false
        let right = false
        
        if(minFromStart[i]==v) left=true
        if(minFromEnd[i]==v) right=true
        
        if(left || right) answer++
    })
    
    return answer;
}
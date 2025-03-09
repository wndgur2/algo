function solution(n, s) {
    var answer = [];
    
    for(let i=0; i<n; i++){
        const cur = parseInt(s/(n-i))
        if(s%(n-i)==0){
            answer = [...answer, ...(new Array(n-i).fill(cur))]
            break
        }
        if(cur==0) return [-1]
        answer.push(cur)
        s -= cur
    }
    
    return answer;
}
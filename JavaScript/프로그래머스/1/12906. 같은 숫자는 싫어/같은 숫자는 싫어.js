function solution(arr)
{
    var answer = [];
    answer.push(arr[0])
    arr.reduce((pre, cur)=>{
        if(pre!=cur)answer.push(cur)
        // console.log(pre, cur)
        return cur
    })
    
    return answer;
}
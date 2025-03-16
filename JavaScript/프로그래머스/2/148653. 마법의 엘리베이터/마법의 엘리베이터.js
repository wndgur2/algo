let ANSWER = 100000000
function solution(storey) {
    let storeyStr= storey.toString()
    
    let storeyArr = storeyStr.split('').map(v=>parseInt(v))
    storeyArr.reverse()
    
    console.log(storeyArr)
    storeyArr.push(0)
    
    dfs(storeyArr, 0, storeyArr.length-1, 0)
    
    return ANSWER
}

function dfs(arr, depth, maxDepth, tempAnswer){
    if(depth == maxDepth){
        tempAnswer += arr[depth]
        if(tempAnswer < ANSWER){
            ANSWER = tempAnswer
        }
        return
    }
    
    let newArray = [...arr]
    newArray[depth] = 0
    dfs(newArray, depth+1, maxDepth, tempAnswer+arr[depth])
    
    newArray = [...arr]
    newArray[depth+1]++
    dfs(newArray, depth+1, maxDepth, tempAnswer+(10-arr[depth]))
}

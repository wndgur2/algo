function solution(num_list, n) {
    var answer = [];
    let i = 0
    while(i<num_list.length){
        answer.push(num_list[i])
        i+=n
    }
    return answer;
}
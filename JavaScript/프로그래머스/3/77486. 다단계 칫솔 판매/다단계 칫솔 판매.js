function solution(enroll, referral, seller, amount) {
    var answer = Array(enroll.length).fill(0);
    let name_index_map = new Map()
    enroll.forEach((name, index)=>{
        name_index_map.set(name, index)
    })
    name_index_map.set('-', -1)
    
    seller.forEach((name, i)=>{
        makeMoney(name_index_map.get(name), amount[i]*100)
    })
    
    return answer;
    
    function makeMoney(index, margin){
        if(index==-1) return
        
        let passOver = Number.parseInt(margin/10)
        answer[index] += margin-passOver
        
        if(passOver==0) return
        
        makeMoney(name_index_map.get(referral[index]), passOver)
    }
}
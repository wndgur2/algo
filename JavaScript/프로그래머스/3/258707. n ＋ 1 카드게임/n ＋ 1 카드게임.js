

function solution(coin, cards) {
    var answer = 1;
    
    const n = cards.length
    const myCards = []
    const card_index = new Map()
    const used = new Map()
    const combisByCost = [[],[],[]]
    
    // search combinations
    cards.forEach((v, i)=>{
        card_index.set(v, i) // v는 i번째에 있어요.
    })
    
    cards.forEach((v,i)=>{
        if(v>(n+1)/2) return
        const other = n+1-v
        const other_i = card_index.get(other)
        const newCombi = i<other_i?[i, other_i]:[other_i, i]
        if(newCombi[1]<n/3) combisByCost[0].push(newCombi)
        else if(newCombi[0]<n/3) combisByCost[1].push(newCombi)
        else combisByCost[2].push(newCombi)
    })
    
    // 뒷 카드순 정렬
    combisByCost.forEach(combis=>combis.sort((a,b)=>a[1]-b[1]))
    
    let index = n/3-1 + 2
    while(index<=n){
        // console.log('answer: ', answer, 'coin: ', coin, 'index: ', index)
        
        let flag = false
        let pair
        
        if(combisByCost[0].length>0){
            flag = true
            pair = combisByCost[0].splice(0,1)
        } 
        if(!flag && combisByCost[1].length>0 && coin>=1){
            if(combisByCost[1][0][1]<=index){
                flag = true
                coin--
                pair = combisByCost[1].splice(0, 1)
            }
        }
        if(!flag && combisByCost[2].length>0 && coin>=2){
            if(combisByCost[2][0][1]<=index){
                flag = true
                coin -= 2
                pair = combisByCost[2].splice(0, 1)
            }
        }
        
        if(!flag){
            break
        }
        // console.log('pair: ', pair)
        
        index += 2
        answer += 1
    }
    
    return answer;
}
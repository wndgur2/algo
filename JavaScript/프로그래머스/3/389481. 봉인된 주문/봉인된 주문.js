function solution(n, bans) {
    bans.sort((a, b)=> a.length==b.length?(b<a?1:-1):a.length-b.length)
    for(let i=0; i<bans.length; i++){
        const seq = getSeq(bans[i])
        if(seq > n) break
        n++
    }
    return getString(n);
}

function getSeq(str){
    let seq = 0
    let unit = 1
    for(let i=str.length-1; i>=0; i--){
        seq += getSignleSeq(str[i]) * unit
        unit *= 26
    }
    return seq
}

function getSignleSeq(char){
    return char.charCodeAt()-96
}

function getCharacter(seq){
    if(seq==0) seq=26
    return String.fromCharCode(seq+96)
}

function getString(seq){
    let result = ''
    while(seq>0){
        result = getCharacter(seq%26) + result
        seq = Number.parseInt(seq/26) - ((seq%26==0)?1:0)
    }
    return result
}
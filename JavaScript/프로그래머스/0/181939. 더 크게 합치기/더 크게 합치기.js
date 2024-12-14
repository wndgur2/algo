function solution(a, b) {
    return Number(`${a}${b}`>`${b}${a}`?`${a}${b}`:`${b}${a}`)
}
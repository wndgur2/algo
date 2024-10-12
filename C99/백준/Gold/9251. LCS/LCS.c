#include <stdio.h>
// 두 문자열의 최대 공통 수열의 길이를 구하는 프로그램
int main(){
    char str1[1000], str2[1000];
    int i, j, len1, len2, LCS[1001][1001];
    scanf("%s", str1);
    scanf("%s", str2);
    for(i=0; str1[i]!='\0'; i++);
    len1 = i;
    for(i=0; str2[i]!='\0'; i++);
    len2 = i;
    for(i=0; i<=len1; i++)
        LCS[i][0] = 0;
    for(i=0; i<=len2; i++)
        LCS[0][i] = 0;
    for(i=1; i<=len1; i++){
        for(j=1; j<=len2; j++){
            if(str1[i-1] == str2[j-1])
                LCS[i][j] = LCS[i-1][j-1] + 1;
            else if(LCS[i-1][j] >= LCS[i][j-1])
                LCS[i][j] = LCS[i-1][j];
            else
                LCS[i][j] = LCS[i][j-1];
        }
    }
    printf("%d\n", LCS[len1][len2]);
}
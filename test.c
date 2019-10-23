#include<stdio.h>
#include<stdbool.h>

/********************* Declarations ****************/
int value,result;
void function(int *result){
double value;
value = 0.0;
printf("%s,%d", "Il valore è",value);
}

/********************* Main  ***********************/
int main(int args, char *argv){
  value = 0;
printf("%s,%d", "Il valore è",value);
function(&result);

  return 0;
}
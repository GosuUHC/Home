#include <iostream>
#include <windows.h>
#include "Wrapper.h"
using namespace std;

int main()
{
    //CoInitialize(NULL);
    Server serv;
    system("pause");   
    serv.enter();
    serv.tranPrint();
    system("pause");
    return 0;
}

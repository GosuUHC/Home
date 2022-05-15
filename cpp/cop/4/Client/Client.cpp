#include <iostream>
#include <windows.h>
#include "Wrapper.h"
using namespace std;


int main()
{
    
    Server serv;
    serv.enter();
    serv.tranPrint();
    serv.multByTwo();

    system("pause");
    return 0;
}

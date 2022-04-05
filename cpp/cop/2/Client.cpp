#include <iostream>
#include "Wrapper.h"

int main()
{
    Server serv;
    Server serv2 = serv;
    std::cout<<&serv<<std::endl;
    std::cout<<&serv2<<std::endl;
    serv.enter();
    serv.tranPrint();
    serv2.enter();
    serv2.tranPrint();
    return 0;
}

#include <iostream>
#include <windows.h>
#include "../Server/Component/Serv_comp.h"

//обертка
class Server
{
private:
    IEnterIntMatrix *enterMatr = NULL;
    ITransposeAndPrintAnyMatrix *TandP = NULL;
    IClassFactory* fact = NULL;
public:
    Server();
    ~Server();
    void enter();
    void tranPrint();
};

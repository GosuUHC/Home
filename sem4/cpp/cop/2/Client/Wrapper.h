#include "../Server/Serv_comp.h"
#include <iostream>

//обертка 
class Server
{
private:
    Factory *fact = NULL;
    IEnterIntMatrix *enterMatr = NULL;
    ITransposeAndPrintAnyMatrix *TandP = NULL;

public:
    Server();
    Server(const Server &other);
    ~Server();
    void enter();
    void tranPrint();
    Server& operator= (const Server& other);
};

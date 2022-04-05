#include "serv.h"
#include <iostream>

//обертка над всеми компонентами
class Server
{
private:
    IFactory *fact = NULL;
    IEnterIntMatrix *enterMatr = NULL;
    ITransposeAndPrintAnyMatrix *TandP = NULL;
    int n, m;
    int **matrix;

public:
    Server();
    Server(const Server &other);
    ~Server();
    void enter();
    void tranPrint();
    Server& operator= (const Server& other);
};

#include <iostream>
#include "../manager/manager.h"

typedef H_RESULT (*FunctionType)(CLS_ID, I_ID, void **);
void trace(const char *msg);

//обертка
class Server
{
private:
    IEnterIntMatrix *enterMatr = NULL;
    ITransposeAndPrintAnyMatrix *TandP = NULL;

public:
    Server();
    Server(const Server &other);
    ~Server();
    void enter();
    void tranPrint();
    // Server &operator=(const Server &other);
};

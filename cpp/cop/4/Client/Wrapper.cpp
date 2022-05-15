#include "Wrapper.h"
#include <windows.h>
void trace(const char *msg) { std::cout << msg << std::endl; }
HINSTANCE hh;

Server::Server()
{
    trace("Server construct");
    FunctionType Co_create_instance;
    hh = LoadLibrary("manager/compiled/manager.dll");
    if (!hh)
    {
        std::cout << "no dll" << std::endl;
    }

    Co_create_instance = (FunctionType)GetProcAddress(hh, "Co_CreateInstance");
    if (!Co_create_instance)
    {
        std::cout << "no dll func" << std::endl;
    }
    Co_create_instance(clsidOuter, iid_IMultTM, (void **)&TM);
    TM->QueryInterface(iid_IEnter, (void **)&enterMatr);
    TM->QueryInterface(iid_ITandP, (void **)&TandP);

    system("pause");
}

void Server::enter()
{
    trace("Entering matrix");
    trace("Enter n, m:");
    int n, m;
    std::cin >> n;
    std::cin >> m;
    enterMatr->EnterMatrix(n, m);
}
void Server::tranPrint()
{
    TandP->PrintMatrix();
    TandP->TransposeMatrix();
    trace("Transposed matrix:");
    TandP->PrintMatrix();
}
void Server::multByTwo()
{
    TM->MultTMByTwo();
}
Server::Server(const Server &other)
{
    trace("Copy constructor");
    enterMatr = other.enterMatr;
    enterMatr->AddRef();
    TandP = other.TandP;
    TandP->AddRef();
}

Server::~Server()
{
    trace("Server dest");
    enterMatr->Release();
    TandP->Release();
    TM->Release();
    FreeLibrary(hh);
    system("pause");
}

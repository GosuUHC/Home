#include "Wrapper.h"
#include <windows.h>

Server::Server()
{
    trace("Server construct");
    FunctionType GetClassObject_;
    HINSTANCE h;
    h = LoadLibrary("Server/Compiled/Serv_comp.dll");
    if (!h)
    {
        std::cout << "no dll" << std::endl;
    }

    GetClassObject_ = (FunctionType)GetProcAddress(h, "DLLGetClassObject");
    if (!GetClassObject_)
    {
        std::cout << "no dll func" << std::endl;
    }

    GetClassObject_(clsidServ, iid_IClassFactory, (void **)&fact);

    fact->CreateInstance(iid_IEnter, (void **)&enterMatr);

    enterMatr->QueryInterface(iid_ITandP, (void **)&TandP);

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
Server::Server(const Server &other)
{
    trace("Copy constructor");
    fact = other.fact;
    fact->AddRef();
    enterMatr = other.enterMatr;
    enterMatr->AddRef();
    TandP = other.TandP;
    TandP->AddRef();
}
/*Server &Server::operator=(const Server &other)
{
    trace("======");
    if (this == &other)
    {
        return *this;
    }
    if (this->fact)
    {
        this->fact->Release();
        GetClassObject(clsidServ, iid_IClassFactory, (void **)&fact);
    }
    if (this->enterMatr)
    {
        this->enterMatr->Release();
        fact->CreateInstance(iid_IEnter, (void **)&enterMatr);
    }
    if (this->TandP)
    {
        this->TandP->Release();
        fact->CreateInstance(iid_ITandP, (void **)&TandP);
    }
    return *this;
}
*/
Server::~Server()
{
    trace("Server dest");
    enterMatr->Release();
    TandP->Release();
    fact->Release();
    system("pause");
}

#include "Wrapper.h"

Server::Server()
{
    trace("Server construct");
    GetClassObject(clsidServ, iid_IClassFactory, (void **)&fact);
    fact->AddRef();

    fact->CreateInstance(iid_IServ0, (void **)&enterMatr);
    enterMatr->AddRef();

    fact->CreateInstance(iid_IServ1, (void **)&TandP);
    TandP->AddRef();

    system("pause");
}

void Server::enter()
{
    trace("Entering matrix");
    trace("Enter n, m");
    std::cin >> n;
    std::cin >> m;

    matrix = enterMatr->EnterMatrix(n, m);
}
void Server::tranPrint()
{
    TandP->PrintMatrix(matrix, n, m);
    TandP->TransposeMatrix(matrix, n, m);
    trace("Transposed matrix:");
    TandP->PrintMatrix(matrix, n, m);
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
Server &Server::operator=(const Server &other)
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
        fact->AddRef();
    }
    if (this->enterMatr)
    {
        this->enterMatr->Release();
        fact->CreateInstance(iid_IServ0, (void **)&enterMatr);
        enterMatr->AddRef();
    }
    if (this->TandP)
    {
        this->TandP->Release();
        fact->CreateInstance(iid_IServ1, (void **)&TandP);
        TandP->AddRef();
    }
    return *this;
}

Server::~Server()
{
    trace("Server dest");
    enterMatr->DelMemoryForIntMatrix(matrix, n, m);
    enterMatr->Release();
    TandP->Release();
    fact->Release();
    system("pause");
}

//переопределить оператор = для вызова addref и проследить, когда вызвается конструктор копии; задача в том, чтобы увеличить кол-во ссылок
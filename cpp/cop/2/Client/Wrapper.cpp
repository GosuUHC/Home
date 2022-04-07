#include "Wrapper.h"

Server::Server()
{
    trace("Server construct");
    GetClassObject(clsidServ, iid_IClassFactory, (void **)&fact);

    fact->CreateInstance(iid_IServ0, (void **)&enterMatr);

    enterMatr->QueryInterface(iid_IServ1, (void**)&TandP);

    system("pause");
}

void Server::enter()
{
    trace("Entering matrix");

    enterMatr->EnterMatrix();
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
    }
    if (this->enterMatr)
    {
        this->enterMatr->Release();
        fact->CreateInstance(iid_IServ0, (void **)&enterMatr);
    }
    if (this->TandP)
    {
        this->TandP->Release();
        fact->CreateInstance(iid_IServ1, (void **)&TandP);
    }
    return *this;
}

Server::~Server()
{
    trace("Server dest");
    enterMatr->Release();
    TandP->Release();
    fact->Release();
    system("pause");
}

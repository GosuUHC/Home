#include "Wrapper.h"
#include <windows.h>
void trace(const char *msg) { std::cout << msg << std::endl; }

Server::Server()
{
    trace("Server construct");
    //CoInitialize(NULL);
    HRESULT res = CoCreateInstance(clsidServ, NULL, CLSCTX_INPROC_SERVER, iid_IEnter, (void **)&enterMatr);
    if (!SUCCEEDED(res))
    {
        std::cout << "ERROR CoCreateInstance!" << std::endl;
        return;
    }

    HRESULT res2 = enterMatr->QueryInterface(iid_ITandP, (void **)&TandP);
    if (!SUCCEEDED(res2))
    {
        std::cout << "ERROR QueryInterface!" << std::endl;
        return;
    }
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

Server::~Server()
{
    trace("Server dest");
    enterMatr->Release();
    TandP->Release();
    CoUninitialize();
    system("pause");
}

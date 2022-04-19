#include <iostream>
#include <windows.h>
#include "Wrapper.h"
using namespace std;

// typedef H_RESULT (*FunctionType)(CLS_ID, I_ID, void **);

int main()
{
    // может правильно, а может и нет :)
    Server serv;
    Server serv2 = serv;
    std::cout << &serv << std::endl;
    std::cout << &serv2 << std::endl;
    serv.enter();
    serv.tranPrint();
    serv2.enter();
    serv2.tranPrint();

    /*
    FunctionType f;
    HINSTANCE h;
    Factory *fact = NULL;
    IEnterIntMatrix *enterMatr = NULL;
    ITransposeAndPrintAnyMatrix *TandP = NULL;

    h = LoadLibrary("Server/Compiled/Serv_comp.dll");
    if (!h)
    {
        std::cout << "no dll" << std::endl;
    }

    f = (FunctionType)GetProcAddress(h, "GetClassObject");
    if (!f)
    {
        std::cout << "no dll func" << std::endl;
    }
    f(clsidServ, iid_IClassFactory, (void **)&fact);
    fact->CreateInstance(iid_IEnter, (void**)&enterMatr);
    enterMatr->QueryInterface(iid_ITandP, (void**)&TandP);
    enterMatr->EnterMatrix();
    TandP->PrintMatrix();
    TandP->TransposeMatrix();
    TandP->PrintMatrix();
    enterMatr->Release();
    TandP->Release();
    fact->Release();
*/

    system("pause");
    return 0;
}

#include <iostream>
#include "Serv.h"

int main()
{
    IFactory *fact = NULL;
    EnterIntMatrix *ent_m = NULL;
    TransposeAndPrintAnyMatrix *t_and_p = NULL;

    GetClassObject(clsidServ, iid_IClassFactory, (void **)&fact);
    fact->AddRef();

    fact->CreateInstance(iid_IServ0, (void **)&ent_m);
    ent_m->AddRef();

    fact->CreateInstance(iid_IServ1, (void **)&t_and_p);
    t_and_p->AddRef();

    int n, m;
    trace("Entering matrix");
    trace("Enter n, m");
    std::cin >> n;
    std::cin >> m;

    int **matrix = ent_m->EnterMatrix(n, m);
    t_and_p->PrintMatrix(matrix, n, m);
    t_and_p->TransposeMatrix(matrix, n, m);
    trace("Transposed matrix:");
    t_and_p->PrintMatrix(matrix, n, m);

    system("pause");
    ent_m->DelMemoryForIntMatrix(matrix, n, m);
    ent_m->Release();
    t_and_p->Release();
    fact->Release();
    system("pause");
    return 0;
}

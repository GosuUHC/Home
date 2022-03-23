#include <iostream>
#include "Serv.h"


int main()
{
    IServFactory* fact=NULL; 
    Serv0* s=NULL; 
    
    GetClassObject(clsidServ, iid_IClassFactory, (void**)&fact);
    fact->CreateInstance(iid_IServ0, (void**)&s);
    s->AddRef();
    fact->AddRef();
    s->Fx();
    s->Fy();
    system("pause");
    s->Release();
    fact->Release();
    return 0;
} 



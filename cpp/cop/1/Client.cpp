#include <iostream>
#include "Serv.h"

int main()
{
    Serv0* s;
    Serv1* s1;
    CLS_ID clsid = 1;
    CLS_ID clsid2 = 2;
    I_ID iid = -1;
    I_ID iid2 = 2;
    if (CreateInstance(clsid, iid, (void**)&s)!=0){
        trace("Error");
        return -1;
    }
    if (CreateInstance(clsid2, iid, (void**)&s1)!=0){
        trace("Error");
        return -1;
    }
    s->Fx();
    s->QueryInterface(iid2, (void**)&s);//меняется указатель и теперь будет указывать на IY
    s->Fx();
    s1->Fx();
    s1->QueryInterface(iid2, (void**)&s1);
    s1->Fx();
    system("pause");
    
    return 0;
}


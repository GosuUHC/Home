#include "Serv.h"
#include <iostream>

//Fx and Fy for IX and IY realization

void trace(const char* msg){std::cout<<msg<<std::endl;}

void Serv0::Fx(){
    trace("Fx function server0 interface 1");
}
void Serv0::Fy(){
    trace("Fy function server0 interface 2");
}
void Serv1::Fx(){
    trace("Fx function server1 interface 1");
}
void Serv1::Fy(){
    trace("Fy function server1 interface 2");
}

/*
Походу потому что не используем "unknwn.h" -> реализовали типы
H_RESULT и тд(32 разрядная структура) как обычное интовое значение 
-1 = IUnknown
1 = IX
2 = IY
*/
//запрос интерфейса
H_RESULT Serv0::QueryInterface(I_ID iid, void** ppv){
    if(iid == -1){
        *ppv = (IUnknown_*) (IX*) this;
    }
    else if(iid == 1){
        *ppv = (IX*) this;
    }
    else if(iid == 2){
        *ppv = (IY*) this;
    }
    else{
        *ppv = NULL;
        return -2;
    }
}

H_RESULT Serv1::QueryInterface(I_ID iid, void** ppv){
    if(iid == -1){
        *ppv = (IUnknown_*) (IX*) this;
    }
    else if(iid == 1){
        *ppv = (IX*) this;
    }
    else if(iid == 2){
        *ppv = (IY*) this;
    }
    else{
        *ppv = NULL;
        return -2;
    }
}
H_RESULT CreateInstance(CLS_ID clsid, I_ID iid, void** ppv)
{
    IUnknown_* iknp;
    if (clsid == 1){
        iknp = (IUnknown_*) (IX*) new Serv0();
    } 
    else if (clsid == 2){
        iknp = (IUnknown_*) (IX*) new Serv1();
    }
    else{
        return 1;
    }
    if (iknp->QueryInterface(iid, ppv) == -2){
        return 2;
    }
    return 0;


}
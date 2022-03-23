#include "Serv.h"
#include <iostream>


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

ULONG_ Serv0::AddRef(){
    this->count++;
    std::cout<<"Serv add ref count:"<<this->count<<std::endl;
}
ULONG_ Serv0::Release(){
    count--;
    std::cout<<"Serv release count:"<<this->count<<std::endl;
    if(this->count==0){
        delete this;
    }
}
ULONG_ Serv1::AddRef(){

}
ULONG_ Serv1::Release(){

}

Serv0::~Serv0(){
    trace("~Serv0");
}
IServFactory::~IServFactory(){
    trace("~ServFactory");
}
ULONG_ IServFactory::AddRef(){
    this->count++;
    std::cout<<"Factory add ref count:"<<this->count<<std::endl;
}
ULONG_ IServFactory::Release(){
    this->count--;
    std::cout<<"Factory release count:"<<this->count<<std::endl;
    if (this->count==0){
        delete this;
    }
}

H_RESULT Serv0::QueryInterface(I_ID iid, void** ppv){
    if(iid == iid_IUnknown_){
        *ppv = (IUnknown_*) (IServ0*) this;
    }
    else if(iid == iid_IServ0){
        *ppv = (IServ0*) this;
    }
    else if(iid == iid_IServ1){
        *ppv = (IServ1*) this;
    }
    else{
        *ppv = NULL;
        return E_NOINTERFACE;
    }
    return S_OK;// вот здесь AddRef
}

H_RESULT Serv1::QueryInterface(I_ID iid, void** ppv){
    if(iid == iid_IUnknown_){
        *ppv = (IUnknown_*) (IServ1*) this;
    }
    else if(iid == iid_IServ0){
        *ppv = (IServ0*) this;
    }
    else if(iid == iid_IServ1){
        *ppv = (IServ1*) this;
    }
    else{
        *ppv = NULL;
        return E_NOINTERFACE;
    }
    return S_OK;
}

H_RESULT IServFactory::CreateInstance(I_ID iid, void** ppv){
    if(iid == iid_IServ0){
        Serv0* serv = new Serv0();
        serv->QueryInterface(iid, ppv);
    }
    else if(iid == iid_IServ1){
        Serv1* serv1 = new Serv1();
        serv1->QueryInterface(iid, ppv);
    }
}

H_RESULT IServFactory::QueryInterface(I_ID iid, void** ppv){
    if (iid == iid_IUnknown_){
        *ppv = (IClassFactory_*) this;
    }
    else if (iid == iid_IClassFactory){
        *ppv = (IClassFactory_*) this;
    }
    else{
        ppv = NULL;
        return E_NOINTERFACE;
    }
    return S_OK;
}
H_RESULT GetClassObject(CLS_ID servid, I_ID IClassFactory_id, void** ppv){
    if (servid == clsidServ){
        IServFactory* fact = new IServFactory();
        fact->QueryInterface(IClassFactory_id, ppv);
    }

}
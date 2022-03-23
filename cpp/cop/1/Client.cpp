#include <iostream>
#include "Serv.h"

int main()
{
    IServFactory* fact;
    Serv0* s;//=NULL ?????
    Serv1* s1;//=NULL ?????
    
    GetClassObject(clsidServ, iid_IClassFactory, (void**)&fact);
    fact->CreateInstance(iid_IServ0, (void**)&s);
    s->Fx();
    s->Fy();
    fact->CreateInstance(iid_IServ1, (void**)&s1);
    s->Fx();
    system("pause");
    s->Fy();
    system("pause");
    return 0;
}
/*
IX* pX = NULL;
H_RESULT res = CreateINst(clsid, iid, &pX)

ЗАпрос нового инта

IY* pY = NULL;
res = pX->Query(iid_IY, (void**)&pY)
IX* pX2 = pX   ---->>> сервер про это не знает, чисто клиентская операция(это ссылка?) если такого нет, то addref необязателен
нужно сказать серву, что ссылок теперь на 1 больше --->>>
pX2 -> AddRef() 

перед выходом из области видимости нужно вызвать release():
pX->Release() счетчик ссылок стал 2
pX2->Release() счетчик ссылок стал 1
pY->Release() счетчик ссылок стал 0

Короче когда ссылок 0 то все, удалятся

По сути кол-во AddRefов == кол-во Releaseов
Если для ссылки AddRef, то обязательно Release


AddRef(){
    count++;
}
Release(){
    count--;
    if(count==0){
        delete this; 
    }
}

Фабрика уничтожается после всего(в самом конце)

Нужны отладочные сообщения (в деструкторе(убедиться в том, что компонент уничтожается), при изменении каунта, создании объекта)

Удобно возвращать count и выводить в консоль в AddRef и Release


Сделать addref release и для фабрик
*/


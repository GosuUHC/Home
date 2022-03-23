#include "IServ.h"

void trace(const char* msg);

class Serv0: public IServ0, public IServ1
{
  private:
  int count = 0;
  public:
  void Fx();
  void Fy();
  H_RESULT QueryInterface(I_ID iid, void** ppv);
  ULONG_ AddRef();
  ULONG_ Release();
  ~Serv0();
};

class Serv1: public IServ0, public IServ1
{
  public:
  void Fx();
  void Fy();
  H_RESULT QueryInterface(I_ID iid, void** ppv);
  ULONG_ AddRef();
  ULONG_ Release();
  ~Serv1();
};

class IServFactory: public IClassFactory_{
  private:
  int count = 0;
  public:
  H_RESULT QueryInterface(I_ID iid, void** ppv);
  H_RESULT CreateInstance(I_ID iid, void** ppv);
  ULONG_ AddRef();
  ULONG_ Release();
  ~IServFactory();
};


H_RESULT GetClassObject(CLS_ID serv, I_ID IClassFactory_id, void** ppv);


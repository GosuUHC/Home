#include "IServ.h"

void trace(const char* msg);

class Serv0: public IServ0, public IServ1
{
  public:
  void Fx();
  void Fy();
  H_RESULT QueryInterface(I_ID iid, void** ppv);
  ULONG_ AddRef();
  ULONG_ Release();
};

class Serv1: public IServ0, public IServ1
{
  public:
  void Fx();
  void Fy();
  H_RESULT QueryInterface(I_ID iid, void** ppv);
  ULONG_ AddRef();
  ULONG_ Release();
};

class IServFactory: public IClassFactory_{
  public:
  H_RESULT QueryInterface(I_ID iid, void** ppv);
  H_RESULT CreateInstance(I_ID iid, void** ppv);
  ULONG_ AddRef();
  ULONG_ Release();
};


H_RESULT GetClassObject(CLS_ID serv, I_ID IClassFactory_id, void** ppv);


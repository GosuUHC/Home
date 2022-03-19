#include "IServ.h"

void trace(const char* msg);

class Serv0: public IX, public IY
{
  public:
  void Fx();
  void Fy();
  H_RESULT QueryInterface(I_ID iid, void** ppv);
};

class Serv1: public IX, public IY
{
  public:
  void Fx();
  void Fy();
  H_RESULT QueryInterface(I_ID iid, void** ppv);
};
H_RESULT GetClassObject();
H_RESULT CreateInstance(CLS_ID clsid, I_ID iid, void** ppv);


/*
H_RESULT CreateInstance(CLS_ID clsid, I_ID iid, void** ppv)
{
    iUnknown* Serv;
    switch (clsid)
    {
        case 1:
        {
            Serv = (iUnknown*) (iServ*) new Serv2();
            break;
        }
        case 2:
        {
            Serv = (iUnknown*) (iServ2*) new Serv2();
            break;
        }
        default:
        {
            return 1;
        }
    }
    if(Serv->QueryInterface(iid, ppv) == -1) return 2;
    return 0;
}
*/
#include "IUnknown.h"

class IServ0 : public IUnknown_
{
  public:
  virtual void Fx() = 0;
};

class IServ1 : public IUnknown_
{
  public:
  virtual void Fy() = 0;
};
class IClassFactory_ : public IUnknown_
{
  public:
  virtual H_RESULT CreateInstance(I_ID iid, void** ppv) = 0;
};





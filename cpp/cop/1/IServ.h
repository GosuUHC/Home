#include "IUnknown.h"

class IX : public IUnknown_
{
  public:
  virtual void Fx() = 0;
};

class IY : public IUnknown_
{
  public:
  virtual void Fy() = 0;
};
class IClassFactory : public IUnknown_
{
  //public:
};

//переделать под матем объект


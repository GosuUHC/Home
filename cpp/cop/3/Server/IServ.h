#include "IUnknown.h"

class IEnterIntMatrix : public IUnknown_
{
public:
  virtual void EnterMatrix(int n, int m) = 0;
};

class ITransposeAndPrintAnyMatrix : public IUnknown_
{
public:
  virtual void TransposeMatrix() = 0;
  virtual void PrintMatrix() = 0;
};
class IClassFactory_ : public IUnknown_
{
public:
  virtual H_RESULT CreateInstance(I_ID iid, void **ppv) = 0;
};

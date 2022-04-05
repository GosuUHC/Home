#include "IUnknown.h"

class IEnterIntMatrix : public IUnknown_
{
public:
  virtual void NewMemoryForIntMatrix(int **&, int, int) = 0;
  virtual void DelMemoryForIntMatrix(int **&, int, int) = 0;
  virtual int **EnterMatrix(int, int) = 0;
};

class ITransposeAndPrintAnyMatrix : public IUnknown_
{
public:
  virtual void TransposeMatrix(int **&, int, int) = 0;
  virtual void PrintMatrix(int **, int, int) = 0;
};
class IClassFactory_ : public IUnknown_
{
public:
  virtual H_RESULT CreateInstance(I_ID iid, void **ppv) = 0;
};

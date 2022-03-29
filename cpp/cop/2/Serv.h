#include "IServ.h"

void trace(const char *msg);

class EnterIntMatrix : public IEnterIntMatrix
{
private:
  int count = 0;

public:
  int **EnterMatrix(int, int);
  void NewMemoryForIntMatrix(int**&, int, int);
  void DelMemoryForIntMatrix(int**&, int, int);
  H_RESULT QueryInterface(I_ID iid, void **ppv);
  ULONG_ AddRef();
  ULONG_ Release();
  ~EnterIntMatrix();
};

class TransposeAndPrintAnyMatrix : public ITransposeAndPrintAnyMatrix
{
private:
  int count = 0;

public:
  void TransposeMatrix(int **&, int, int);
  void PrintMatrix(int **, int, int);
  H_RESULT QueryInterface(I_ID iid, void **ppv);
  ULONG_ AddRef();
  ULONG_ Release();
  ~TransposeAndPrintAnyMatrix();
};

class IFactory : public IClassFactory_
{
private:
  int count = 0;

public:
  H_RESULT QueryInterface(I_ID iid, void **ppv);
  H_RESULT CreateInstance(I_ID iid, void **ppv);
  ULONG_ AddRef();
  ULONG_ Release();
  ~IFactory();
};

H_RESULT GetClassObject(CLS_ID serv, I_ID IClassFactory_id, void **ppv);

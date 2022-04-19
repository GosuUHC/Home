#include "IServ.h"

void trace(const char *msg);
class Component : public IEnterIntMatrix, public ITransposeAndPrintAnyMatrix
{
private:
    int count = 0;
    int **matrix;
    int n, m;
    void NewMemoryForIntMatrix();
    void DelMemoryForIntMatrix();

public:
    void EnterMatrix();
    void TransposeMatrix();
    void PrintMatrix();
    ~Component();
    H_RESULT QueryInterface(I_ID iid, void **ppv);
    ULONG_ AddRef();
    ULONG_ Release();
};

class Factory : public IClassFactory_
{
private:
    int count = 0;

public:
    H_RESULT QueryInterface(I_ID iid, void **ppv);
    H_RESULT CreateInstance(I_ID iid, void **ppv);
    ULONG_ AddRef();
    ULONG_ Release();
    ~Factory();
};

extern "C" H_RESULT __declspec(dllexport) GetClassObject(CLS_ID servid, I_ID IClassFactory_id, void **ppv);
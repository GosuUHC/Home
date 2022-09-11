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
    void EnterMatrix(int n, int m);
    void TransposeMatrix();
    void PrintMatrix();
    int** getMatr();
    int getN();
    int getM();
    Component();
    ~Component();
    HRESULT QueryInterface(const IID& iid, void **ppv);
    ULONG __stdcall AddRef();
    ULONG __stdcall Release();
};

class Factory : public IClassFactory
{
private:
    int count = 0;

public:
    HRESULT __stdcall QueryInterface(const IID& iid, void **ppv);
    HRESULT __stdcall CreateInstance(IUnknown* pUnknownOuter, const IID& iid, void **ppv);
    ULONG __stdcall AddRef();
    ULONG __stdcall Release();
    HRESULT __stdcall LockServer(BOOL block);
    ~Factory();
};

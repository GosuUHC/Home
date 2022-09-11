#include "Serv.h"
#include <iostream>
#include <iomanip>
void trace(const char *msg) { std::cout << msg << std::endl; }

void EnterIntMatrix::NewMemoryForIntMatrix(int **&matr, int n, int m)
{
    trace("NewMatrMemory");
    matr = new int *[n];
    for (int i = 0; i < n; i++)
    {
        matr[i] = new int[m];
    }
}
void EnterIntMatrix::DelMemoryForIntMatrix(int **&matr, int n, int m)
{
    trace("DelMatrMemory");
    for (int i = 0; i < n; i++)
    {
        delete[] matr[i];
    }
    delete[] matr;
}
int **EnterIntMatrix::EnterMatrix(int n, int m)
{
    int **matr;
    NewMemoryForIntMatrix(matr, n, m);
    for (int i = 0; i < n; i++)
    {
        for (int j = 0; j < m; j++)
        {
            std::cout << "Enter element with index:" << i << " " << j << std::endl;
            std::cin >> matr[i][j];
        }
    }
    return matr;
}

void TransposeAndPrintAnyMatrix::TransposeMatrix(int **&matr, int n, int m)
{
    int buff;
    for (int i = 0; i < n; i++)
    {
        for (int j = 0; j < i; j++)
        {
            buff = matr[i][j];
            matr[i][j] = matr[j][i];
            matr[j][i] = buff;
        }
    }
}

void TransposeAndPrintAnyMatrix::PrintMatrix(int **matr, int n, int m)
{
    std::cout << std::endl
              << std::endl;
    for (int i = 0; i < n; i++)
    {
        for (int j = 0; j < m; j++)
            std::cout << std::setw(15) << matr[i][j];
        std::cout << std::endl;
    }
}

ULONG_ EnterIntMatrix::AddRef()
{
    this->count++;
    std::cout << "EnterMatrix object added; count:" << this->count << std::endl;
}
ULONG_ EnterIntMatrix::Release()
{
    this->count--;
    std::cout << "EnterMatrix object released; count:" << this->count << std::endl;
    if (this->count == 0)
    {
        delete this;
    }
}
ULONG_ TransposeAndPrintAnyMatrix::AddRef()
{
    this->count++;
    std::cout << "TransposeAndPrintAnyMatrix object added; count:" << this->count << std::endl;
}
ULONG_ TransposeAndPrintAnyMatrix::Release()
{
    this->count--;
    std::cout << "TransposeAndPrintAnyMatrix object released; count:" << this->count << std::endl;
    if (this->count == 0)
    {
        delete this;
    }
}

EnterIntMatrix::~EnterIntMatrix()
{
    trace("~EnterIntMatrix");
}

TransposeAndPrintAnyMatrix::~TransposeAndPrintAnyMatrix()
{
    trace("~TransposeAndPrintAnyMatrix");
}
IFactory::~IFactory()
{
    trace("~Factory");
}

ULONG_ IFactory::AddRef()
{
    this->count++;
    std::cout << "Factory add ref count:" << this->count << std::endl;
}
ULONG_ IFactory::Release()
{
    this->count--;
    std::cout << "Factory release count:" << this->count << std::endl;
    if (this->count == 0)
    {
        delete this;
    }
}

H_RESULT EnterIntMatrix::QueryInterface(I_ID iid, void **ppv)
{
    if (iid == iid_IUnknown_)
    {
        *ppv = (IUnknown_ *)(IEnterIntMatrix *)this;
    }
    else if (iid == iid_IServ0)
    {
        *ppv = (IEnterIntMatrix *)this;
    }
    else if (iid == iid_IServ1)
    {
        *ppv = (ITransposeAndPrintAnyMatrix *)this;
    }
    else
    {
        *ppv = NULL;
        return E_NOINTERFACE;
    }
    return S_OK;
}

H_RESULT TransposeAndPrintAnyMatrix::QueryInterface(I_ID iid, void **ppv)
{
    if (iid == iid_IUnknown_)
    {
        *ppv = (IUnknown_ *)(ITransposeAndPrintAnyMatrix *)this;
    }
    else if (iid == iid_IServ0)
    {
        *ppv = (IEnterIntMatrix *)this;
    }
    else if (iid == iid_IServ1)
    {
        *ppv = (ITransposeAndPrintAnyMatrix *)this;
    }
    else
    {
        *ppv = NULL;
        return E_NOINTERFACE;
    }
    return S_OK;
}

H_RESULT IFactory::CreateInstance(I_ID iid, void **ppv)
{
    if (iid == iid_IServ0)
    {
        EnterIntMatrix *ent = new EnterIntMatrix();
        ent->QueryInterface(iid, ppv);
    }
    else if (iid == iid_IServ1)
    {
        TransposeAndPrintAnyMatrix *t_and_p = new TransposeAndPrintAnyMatrix();
        t_and_p->QueryInterface(iid, ppv);
    }
}

H_RESULT IFactory::QueryInterface(I_ID iid, void **ppv)
{
    if (iid == iid_IUnknown_)
    {
        *ppv = (IClassFactory_ *)this;
    }
    else if (iid == iid_IClassFactory)
    {
        *ppv = (IClassFactory_ *)this;
    }
    else
    {
        ppv = NULL;
        return E_NOINTERFACE;
    }
    return S_OK;
}
H_RESULT GetClassObject(CLS_ID servid, I_ID IClassFactory_id, void **ppv)
{
    if (servid == clsidServ)
    {
        IFactory *fact = new IFactory();
        fact->QueryInterface(IClassFactory_id, ppv);
    }
}
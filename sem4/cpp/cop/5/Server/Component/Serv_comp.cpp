#include "Serv_comp.h"
#include <iostream>
#include <iomanip>
#include <fstream>
#include <sstream>
void trace(const char *msg) { std::cout << msg << std::endl; }
using namespace std;

int components_count = 0;

int **Component::getMatr()
{
    return this->matrix;
}
int Component::getN()
{
    return this->n;
}
int Component::getM()
{
    return this->m;
}
void Component::NewMemoryForIntMatrix()
{
    trace("\nNewMatrMemory");

    matrix = new int *[n];
    for (int i = 0; i < n; i++)
    {
        matrix[i] = new int[m];
    }
}

void Component::DelMemoryForIntMatrix()
{
    trace("\nDelMatrMemory");
    for (int i = 0; i < n; i++)
    {
        delete[] this->matrix[i];
    }
    delete[] this->matrix;
}
void Component::EnterMatrix(int n, int m)
{
    this->n = n;
    this->m = m;
    cout << n << endl;
    cout << m << endl;
    NewMemoryForIntMatrix();
    for (int i = 0; i < this->n; i++)
    {
        for (int j = 0; j < this->m; j++)
        {
            std::cout << "Enter element with index:" << i << " " << j << std::endl;
            std::cin >> matrix[i][j];
        }
    }
}

void Component::TransposeMatrix()
{

    int buff;
    for (int i = 0; i < n; i++)
    {
        for (int j = 0; j < i; j++)
        {
            buff = matrix[i][j];
            matrix[i][j] = matrix[j][i];
            matrix[j][i] = buff;
        }
    }
}

void Component::PrintMatrix()
{
    std::cout << std::endl;
    for (int i = 0; i < n; i++)
    {
        for (int j = 0; j < m; j++)
        {
            std::cout << std::setw(15) << matrix[i][j];
        }
        std::cout << std::endl;
    }
}

ULONG __stdcall Component::AddRef()
{
    this->count++;
    std::cout << "\nComponent object " << this << " added; count:" << this->count << std::endl;
}
ULONG __stdcall Component::Release()
{
    this->count--;
    std::cout << "\nComponent object " << this << " released; count:" << this->count << std::endl;
    if (this->count == 0)
    {
        delete this;
    }
}

Component::~Component()
{
    trace("\n~Component");
    components_count--;
    cout << "components count=" << components_count << endl;
    this->DelMemoryForIntMatrix();
}
Component::Component()
{
    trace("Added component");
    components_count++;
    cout << "components count=" << components_count << endl;
}
Factory::~Factory()
{
    trace("\n~Factory");
}

ULONG __stdcall Factory::AddRef()
{
    this->count++;
    std::cout << "\nFactory object " << this << " added; count:" << this->count << std::endl;
}
ULONG __stdcall Factory::Release()
{
    this->count--;
    std::cout << "\nFactory object " << this << " released; count:" << this->count << std::endl;
    if (this->count == 0)
    {
        delete this;
    }
}

HRESULT __stdcall Component::QueryInterface(const IID &iid, void **ppv)
{
    trace("query");
    if (iid == iid_IUnknown)
    {
        *ppv = (IUnknown *)(IEnterIntMatrix *)this;
    }
    else if (iid == iid_IEnter)
    {
        *ppv = (IEnterIntMatrix *)this;
    }
    else if (iid == iid_ITandP)
    {
        *ppv = (ITransposeAndPrintAnyMatrix *)this;
    }
    else
    {
        ppv = NULL;
        return E_NOINTERFACE;
    }
    AddRef();
    return S_OK;
}

HRESULT __stdcall Factory::CreateInstance(IUnknown *pUnknownOuter, const IID &iid, void **ppv)
{
    if (pUnknownOuter != NULL)
    {
        return E_NOTIMPL;
    }

    trace("create");
    Component *comp = new Component();

    return comp->QueryInterface(iid, ppv);
}

HRESULT __stdcall Factory::QueryInterface(const IID &iid, void **ppv)
{
    if (iid == iid_IUnknown)
    {
        *ppv = (IUnknown *)this;
    }
    else if (iid == iid_IClassFactory)
    {
        *ppv = (IClassFactory *)this;
    }
    else
    {
        *ppv = NULL;
        return E_NOINTERFACE;
    }
    AddRef();
    return S_OK;
}
HRESULT __declspec(dllexport) GetClassObject(const CLSID &clsid, const IID &IClassFactory_id, void **ppv)
{
    if (clsid != clsidServ)
    {
        ppv = NULL;
        return E_NOTIMPL;
    }
    Factory *fact = new Factory();
    HRESULT res = fact->QueryInterface(IClassFactory_id, ppv);
    return res;
}

HRESULT __stdcall Factory::LockServer(BOOL block)
{
    trace("LOCK SERVER");
    return S_OK;
}

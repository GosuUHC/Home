#include "Serv_comp.h"
#include <iostream>
#include <iomanip>
void trace(const char *msg) { std::cout << msg << std::endl; }

void Component::NewMemoryForIntMatrix()
{
    trace("\nNewMatrMemory");
    trace("Enter n, m:");
    std::cin >> this->n;
    std::cin >> this->m;
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
        delete[] matrix[i];
    }
    delete[] matrix;
}
void Component::EnterMatrix()
{
    NewMemoryForIntMatrix();
    for (int i = 0; i < n; i++)
    {
        for (int j = 0; j < m; j++)
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
            std::cout << std::setw(15) << matrix[i][j];
        std::cout << std::endl;
    }
}

ULONG_ Component::AddRef()
{
    this->count++;
    std::cout << "\nComponent object " << this << " added; count:" << this->count << std::endl;
}
ULONG_ Component::Release()
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
    this->DelMemoryForIntMatrix();
}

Factory::~Factory()
{
    trace("\n~Factory");
}

ULONG_ Factory::AddRef()
{
    this->count++;
    std::cout << "\nFactory object " << this << " added; count:" << this->count << std::endl;
}
ULONG_ Factory::Release()
{
    this->count--;
    std::cout << "\nFactory object " << this << " released; count:" << this->count << std::endl;
    if (this->count == 0)
    {
        delete this;
    }
}

H_RESULT Component::QueryInterface(I_ID iid, void **ppv)
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
        ppv = NULL;
        return E_NOINTERFACE;
    }
    AddRef();
    return S_OK;
}

H_RESULT Factory::CreateInstance(I_ID iid, void **ppv)
{
    Component *comp = new Component();

    comp->QueryInterface(iid, ppv);
}

H_RESULT Factory::QueryInterface(I_ID iid, void **ppv)
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
        *ppv = NULL;
        return E_NOINTERFACE;
    }
    AddRef();
    return S_OK;
}
H_RESULT GetClassObject(CLS_ID servid, I_ID IClassFactory_id, void **ppv)
{
    if (servid == clsidServ)
    {
        Factory *fact = new Factory();
        fact->QueryInterface(IClassFactory_id, ppv);
    }
}
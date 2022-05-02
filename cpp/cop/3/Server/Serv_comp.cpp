#include "Serv_comp.h"
#include <iostream>
#include <iomanip>
#include <windows.h>
#include <fstream>
#include <sstream>
void trace(const char *msg) { std::cout << msg << std::endl; }
using namespace std;

int components_count = 0;
char buffer[2048];
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
        delete[] matrix[i];
    }
    delete[] matrix;
}
void Component::EnterMatrix(int n, int m)
{
    this->n = n;
    this->m = m;
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
    trace("query");
    if (iid == iid_IUnknown_)
    {
        *ppv = (IUnknown_ *)(IEnterIntMatrix *)this;
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
        return E_NOINTERFACE__;
    }
    AddRef();
    return S_OK__;
}

H_RESULT Factory::CreateInstance(I_ID iid, void **ppv)
{
    trace("create");
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
        return E_NOINTERFACE__;
    }
    AddRef();
    return S_OK__;
}
extern "C" H_RESULT __declspec(dllexport) DLL_GetClassObject(CLS_ID clsid, I_ID IClassFactory_id, void **ppv)
{
    if (clsid == clsidServ)
    {
        IClassFactory_ *fact = new Factory();
        fact->QueryInterface(IClassFactory_id, ppv);
    }
}

int DelModulePath()
{
    ifstream file_in("manager/wheredll.txt");
    if (!file_in)
    {
        return -1;
    }
    CLS_ID fileCLS_ID;
    string s;
    string filedata = "";

    while (getline(file_in, s))
    {
        istringstream is(s, istringstream::in);
        is >> fileCLS_ID;
        if (fileCLS_ID != clsidServ)
        {
            filedata += s + "\n";
        }
    }
    file_in.close();
    ofstream file_out("manager/wheredll.txt");
    if (!file_out)
    {
        return -1;
    }
    file_out << filedata;
    file_out.close();

    return 0;
}
int SetModulePath()
{
    DelModulePath();
    ofstream file("manager/wheredll.txt", ios_base::app);
    if (!file)
    {
        return -1;
    }
    file << clsidServ << " " << buffer;
    file.close();
    return 0;
}
extern "C" STDAPI __declspec(dllexport) DllRegisterServer()
{
    if (SetModulePath() == 0)
    {
        return S_OK;
    }
    else
    {
        return S_FALSE;
    }
}
extern "C" STDAPI __declspec(dllexport) DllUnregisterServer()
{
    if (DelModulePath() == 0)
    {
        return S_OK;
    }
    else
    {
        return S_FALSE;
    }
}
extern "C" STDAPI __declspec(dllexport) DllCanUnloadNow()
{
    if (components_count == 0)
    {
        return S_OK;
    }
    else
    {
        return S_FALSE;
    }
}

BOOL APIENTRY DllMain(HINSTANCE hinstDLL, DWORD fdwReason, LPVOID lpvReserved)
{
    GetModuleFileName(hinstDLL, buffer, sizeof(buffer));
    std::cout << buffer << std::endl;
    switch (fdwReason)
    {
    case DLL_PROCESS_ATTACH:
        // attach to process
        // return FALSE to fail DLL load
        break;

    case DLL_PROCESS_DETACH:
        // detach from process
        break;

    case DLL_THREAD_ATTACH:
        // attach to thread
        break;

    case DLL_THREAD_DETACH:
        // detach from thread
        break;
    }
    return TRUE; // succesful
}
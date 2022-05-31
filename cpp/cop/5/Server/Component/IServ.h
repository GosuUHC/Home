#include <windows.h>

const IID iid_IUnknown = {0x00000000,0x0000,0x0000,{0xC0,0x00,0x00,0x00,0x00,0x00,0x00,0x46}};
const IID iid_IEnter = {0x00000001,0x0000,0x0000,{0xC0,0x00,0x00,0x00,0x00,0x00,0x00,0x46}};
const IID iid_ITandP = {0x00000001,0x0000,0x0000,{0xC0,0x00,0x00,0x00,0x00,0x00,0x00,0x47}};;
const IID iid_IClassFactory = {0x00000001,0x0000,0x0000,{0xC0,0x00,0x00,0x00,0x00,0x00,0x00,0x40}};;
const CLSID clsidServ = {0x92A42CAA,0x2577,0x4E80,{0x93,0x4E,0x07,0xDE,0x64,0x50,0x2F,0xD6}};


class IEnterIntMatrix : public IUnknown
{
public:
  virtual void EnterMatrix(int n, int m) = 0;
  virtual int **getMatr() = 0;
  virtual int getN() = 0;
  virtual int getM() = 0;
};

class ITransposeAndPrintAnyMatrix : public IUnknown
{
public:
  virtual void TransposeMatrix() = 0;
  virtual void PrintMatrix() = 0;
};



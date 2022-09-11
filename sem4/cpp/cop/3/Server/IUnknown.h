typedef int H_RESULT;
typedef int I_ID;
typedef int CLS_ID;
typedef int ULONG_;
const I_ID iid_IUnknown_ = 0;
const I_ID iid_IEnter = 1;
const I_ID iid_ITandP = 2;
const I_ID iid_IClassFactory = 5;
const H_RESULT S_OK__ = 1000;
const H_RESULT S_FALSE__ = -1000;
const H_RESULT E_NOINTERFACE__ = -1;
const CLS_ID clsidServ = 12;

class IUnknown_
{
public:
  virtual H_RESULT QueryInterface(I_ID iid, void **ppv) = 0;
  virtual ULONG_ AddRef() = 0;
  virtual ULONG_ Release() = 0;
};

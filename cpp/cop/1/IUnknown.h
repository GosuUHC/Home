typedef int H_RESULT;
typedef int I_ID;
typedef int CLS_ID;
class IUnknown_ 
{
  public:
  virtual H_RESULT __stdcall QueryInterface(I_ID iid, void** ppv) = 0;
};
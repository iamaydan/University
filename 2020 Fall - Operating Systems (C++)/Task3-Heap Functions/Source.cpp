#include <iostream>
#include <Windows.h>
#include <tchar.h>
#include <stdio.h>
#include <intsafe.h>

using namespace std;
    int __cdecl _tmain()
    {
        HANDLE heap1;
        BOOL lockheap1 = 0;
        BOOL unlockheap1 = 0;
        BOOL destroyheap1 = 0;
        PHANDLE aHeaps;
        DWORD NumberOfHeaps;
        HRESULT Result;
        SIZE_T BytesToAllocate;


        heap1 = HeapCreate(
            0, 
            0, 
            0
        );

        if (heap1 == NULL) {
            cout << "HeapCreate Failed! " << GetLastError() << endl;
        }
        else cout << "HeapCreate Success\n";

        NumberOfHeaps = GetProcessHeaps(0, NULL);
        if (NumberOfHeaps == 0) {
            cout << "GetProcessHeaps Failed! " << GetLastError() << endl;
        }
        else cout << "GetProcessHeaps Success\n";


        Result = SIZETMult(NumberOfHeaps, sizeof(*aHeaps), &BytesToAllocate);
        if (Result != S_OK) {
            cout << "SIZETMult Failed! " << GetLastError() << endl;
        }
        else cout << "SIZETMult Success\n";


        aHeaps = (PHANDLE)HeapAlloc(heap1, 0, BytesToAllocate);
        if (aHeaps == NULL) {
            cout << "HeapAlloc Failed! " << BytesToAllocate << endl;
        }
        else cout << "HeapAlloc Success\n";

        lockheap1 = HeapLock(heap1);


        if (lockheap1 == FALSE) {
            cout << "HeapLock Failed! " << GetLastError() << endl;
        }
        else cout << "HeapLock Success\n";

        cout << "Heap NO: " << heap1 << endl;

        unlockheap1 = HeapUnlock(heap1);

        if (unlockheap1 == FALSE) {
            cout << "HeapUnLock Failed! " << GetLastError() << endl;
        }
        else cout << "HeapUnLock Success\n";

        aHeaps = (PHANDLE)HeapAlloc(heap1, 0, BytesToAllocate);
        if (aHeaps == NULL) {
            _tprintf(TEXT("HeapAlloc failed to allocate %d bytes.\n"),
                BytesToAllocate);
            return 1;
        }

        if (HeapFree(heap1, 0, aHeaps) == FALSE) {
            _tprintf(TEXT("Failed to free allocation from default process heap.\n"));
        }

        destroyheap1 = HeapDestroy(heap1);

        if (destroyheap1 == FALSE) {
            cout << "HeapDestroy Failed! " << GetLastError() << endl;
        }
        else cout << "HeapDestroy Success\n";

        return 0;
    }
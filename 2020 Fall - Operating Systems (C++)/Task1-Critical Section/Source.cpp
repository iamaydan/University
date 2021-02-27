#include <Windows.h>
#include <iostream>
#include <chrono> 

using namespace std;

CRITICAL_SECTION CriticalSection;

DWORD WINAPI ThreadProc(LPVOID lpParameter){
	auto now = chrono::high_resolution_clock::now();
	auto dur = now.time_since_epoch();
	EnterCriticalSection(&CriticalSection);
	cout << "thread created time: "<< dur.count()<< endl;
	LeaveCriticalSection(&CriticalSection);

	return 1;
}

int main() {
	int data1, data2, data3;
	 
	HANDLE thread1 = 0;
	HANDLE thread2 = 0;
	HANDLE thread3 = 0;
	HANDLE arr[3];

	if (!InitializeCriticalSectionAndSpinCount(&CriticalSection,
		0x00000400)) {
		return 0;
	}

	thread1 = CreateThread(
		NULL,
		0,
		ThreadProc,
		&data1,
		0,
		NULL
	);

	thread2 = CreateThread(
		NULL,
		0,
		ThreadProc,
		&data2,
		0,
		NULL
	);

	thread3 = CreateThread(
		NULL,
		0,
		ThreadProc,
		&data3,
		0,
		NULL
	);

	arr[0] = thread1;
	arr[1] = thread2;
	arr[2] = thread3;

	WaitForMultipleObjects(3, arr, TRUE, INFINITE);
	
	for (int i = 0; i < 3; i++) {
		CloseHandle(arr[i]);
		cout << "closed thread ID: " << arr[i+1] << endl;
	}
	DeleteCriticalSection(&CriticalSection);

}
#include <Windows.h>
#include <iostream>

using namespace std;

int main() {

	HANDLE hFileMap;
	BOOL bResult;
	PCHAR lpBuffer = NULL;
	
	hFileMap = OpenFileMapping(
		FILE_MAP_ALL_ACCESS,
		FALSE,
		L"Local\\MyFileMap"
	);

	if (hFileMap == NULL)
	{
		cout << "OpenFileMapping failed " << GetLastError() << endl;
	}
	else
	{
		cout << "OpenFileMapping success";
	}

	lpBuffer = (PCHAR)MapViewOfFile(
		hFileMap,
		FILE_MAP_ALL_ACCESS,
		0,
		0,
		256
	);

	if (lpBuffer == NULL)
	{
		cout << "MapViewOfFile failed " << GetLastError() << endl;
	}
	else
	{
		cout << "MapViewOfFile success" << endl;
	}

	cout << "YOU HAVE A MESSAGE FROM SERVER  ==>>  " << lpBuffer << endl;


	bResult = UnmapViewOfFile(lpBuffer);

	if (bResult == NULL)
	{
		cout << "Unmapping failed " << GetLastError() << endl;
	}
	else
	{
		cout << "Unmapping success" << endl;
	};

	CloseHandle(hFileMap);

	system("PAUSE");
	return 0;

}










#include <Windows.h>
#include <iostream>

using namespace std;

int main() {

	HANDLE hFileMap;
	BOOL bResult;
	PCHAR lpBuffer = NULL;
	char Buffer[1024] = "Greetings from server: Hello, teacher! :)";
	size_t szBuffer = sizeof(Buffer);

	hFileMap = CreateFileMapping(
		INVALID_HANDLE_VALUE,
		NULL,
		PAGE_READWRITE,
		0,
		256,
		L"Local\\MyFileMap"
	);

	if (hFileMap == FALSE)
	{
		cout << "CreateFileMapping failed " << GetLastError() << endl;

	}
	else
	{
		cout << "CreateFileMapping success\n";
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


	CopyMemory(lpBuffer, Buffer, szBuffer);

	bResult = UnmapViewOfFile(lpBuffer);

	if (bResult == FALSE)
	{
		cout << "Unmapping failed " << GetLastError() << endl;
	}
	else
	{
		cout << "Unmapping success" << endl;
	}

	system("PAUSE");
	return 0;

}










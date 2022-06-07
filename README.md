# Virtual-File-System-with-Protection-Layer
Simulate the allocation and de-allocation of files and folders using different allocation techniques.
# Project Description
- Allocation and de-allocation of files and folders using the following techinques:
  -	Contiguous Allocation (Using Best Fit allocation) 
  -	Indexed Allocation
  -	Linked Allocation
- Each memory block is 1KB in size.
- Build a protection layer that does the following:
- Create Users
- Assign capabilities to users
- Each folder will have two capabilities Create/Delete

## Commands used in interacting with the system
- CreateFile root/file.txt 100	create with 100 KB size under the path “root”.
Pre-requests
1-	The path already exists
2-	No file with the same name is already created under this path
3-	Enough space exists
- CreateFolder root/folder1	Create a new folder named under the path “root”
Pre-requests:
1-	The path is already exist
2-	No folder with the same name is already created under this path. 
- DeleteFile root/folder1/file.txt Delete file from the path "root/folder1". Any blocks allocated by this file will be de-allocated.
Pre-requests:
1-	The file is already exist under the path specified 
- DeleteFolder root/folder1	Delete folder from the path "root". All files and subdirectories of this folder will also be deleted.
Pre-requests:
1-	The folder is already exist under the path specified
- DisplayDiskStatus	Used to display the status of your Driver the status contains the following information:
1-	Empty space
2-	Allocated space
3-	Empty Blocks in the Disk
4-	Allocated  Blocks in the Disk
- DisplayDiskStructure Display the files and folders in your system file in a tree structure 
- TellUser Display the name of the current logged in user.
- CUser ahmed pass123 Create user 
Comments:
1- Only the admin can use such
command
Pre-requests:
1- No user with the same
name already created
- Grant ahmed VFSD:\Folder1 10 Create Delete capabilities
0 0 No create, No delete
0 1 No Create, Delete
1 0 Create, No Delete
1 1 Create, Delete
Notes: 
 1- The create capability allow the user  to create any folder or file under the folder “VFSD:\Folder1”.
 2- The delete capability allow the user  to delete any folder or file under the folder “VFSD:\Folder1”.
 - Login ahmed pass123

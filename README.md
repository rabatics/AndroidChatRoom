# AndroidChatRoom
This Project is an Android Interface for ChatRoom -The Node.js web application in the node-practice repository.
The launching activity is LoginActivity - the login screen. After a successful login the intent is transfered to the NavigationDrawer activity. 
At this point the NavDrawer Activity is the controlling activity. The NavDrawer has menu options like Profile, Friends, Groups, Find and Exit.
The 'Find' navigation item is used to to find other friends on the network by their email address. 
'Friends' is used to access the list of friends and 'Groups' is used to access the list of groups the user is in. 
The NavDrawer activity also maintains a record of the users recent contacts (for that session only). 
The method used for recent contacts is unique to this app. The recent contacts are accessed from the Options menu.
The entire application is controlled by the NavDrawer activity which creates and inflates Fragments for each use-case.
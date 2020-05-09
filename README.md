# Java Android Application

# Title of Project : Contact Manager

1.	Purpose 

The purpose of this document is to specify the requirements and preview some elements of the analysis model of the program CONTACT MANAGER APP.

Contact Manager app is a simple application which consists of four programs: 
•	Contact List Manager.
•	Utilities.
•	Contact Activity.
•	AndroidBuilding Contact Manager Activity.

a.	ContactManager:
It is a class named as ContactMangaer.java and this program is written to fetch all the music tracks in the android device.

b.	ContactListActivity:
It is an Activity named as ContactListActivity.java and this program is written to form another screen in the app and displays all the audio tracks on the android device in the form of aa list.
c.	AndroidBuilding ContactdbHelper Activity:
It is an Activity named as AndroidBuilding ContactdbHelper.java and is used to form the basic layout of the main screen of the app and to do the intended operations as the choice of the user.
d.	Utilities:
It is a java class named as Utilities.java and is used for providing extra utilities like conversion of time into seconds and milliseconds format for the seek bar.

Requirements Specification

•	Latest Android Studio Canary Build: 1.4 Beta 4
•	SDK  tool

2.	Document Conventions
 In general, this document prioritizes in writing the schema of the fetching the *data from Android device about the audio tracks mechanism that takes hold in this project and then analyzing in detail the tools that are available in the Contact Manager App. Therefore, there are lots of abstractions to represent in a more convenient way the objects and their behaviour on the system. Every requirement statement is assumed to have its own priority as to define in most appropriate way the system behaviour.
3.	 Intended Audience
This document is intended for any individual user, developer, tester, or documentation writer that needs to understand the basic system architecture and its specifications. Here are the potential uses for each one of the reader types:
•	Developer:
The developer who wants to create, read, change, modify or add new requirements into the existing program, must first consult this document and update the requirements with appropriate manner so as to not destroy the actual meaning of them and pass the information correctly to the next phases of the development process. 
In this program Developer developed the UI and fetched the data about the existing Contact Names in the system using ContactName class in Android.
•	Tester: 
The tester needs this document to validate that the initial requirements of this programs actually corresponds to the executable program correctly.
•	User:
 The user will perfume the user UAT(User acceptance testing ) and cheek if all desired function exist in application  then he will provide his acceptance that the application working according to requirement 
4.	 Project Scope
With the continuous development in Science and Technology, mobile is no longer just a device used for communication but a management of contacts platform that provides the ability to choose contact. Choosing the contact , calling ,messaging, add any event in calendar for a person, email, search location of particular address, update, delete,insert formats are done in this application. Present scenario for Contact Manager provide support for some calling , messaging format and recently facilities for providing the subtitles is included in the existing system. 


# SOFTWARE  REQUIREMENT

FRONT END:- 
1.	Android Studio- Android studio is a sftare development kit for developing Android projects. It supports languages like Java, XML, CSS and many more. Google recently has published officialy that Cotlin wi be the official language for Android Studio replacing Java from the platform.
Several developers are working in this platform to develop Android applications. 
2.	XML:- XML ,stands for eXtensible Markup Language, is a markup language for structure. It provides a means to create structured layouts in Android. The layout of any activity in android is written in the XML code. XML has user-defined tags and XML is given importance in all android projects. It is used for purposes like :
a.	Layout desiging.
b.	Accessing permissions.
c.	Adding components to activities.


#Description Of Project

The contact manager app is a combination of various categories of features involved that can be performed on the contact list and the list of contacts .
Basic Features of the Contact Manager App (CRUD Operations):

1.)	Add new contact details or choose name and phone number of  person from your contact list and then further add other details.

2.)	Read the contact list saved.

3.)	Update the contact already saved.

4.)	Delete either all or a particular contact.


# STEP BY WORKING OF APPLICATION

1.)	When the user opens the application the front activity consists  of the Basic Activity which consists of the temporary front page, which disappears after adding a contact to the contact list.

2.)	It also consists of the Action Bar having menu items : Insert dummy data(which fills up the default data each time) and delete all contacts option.

3.)	It also consists of the Floating Action Button at the bottom of screen which directs to add new contact details activity.

4.)	The next activity is the Empty Activity which contains the TextViews , EditTexts , Spinners in which details of person are to be filled . 

5.)	This activity also consists of  Choose From Contacts Button which allows the user a choice to pick the particular contact name and contact number from the phone already existing contact list. And it automatically fills up for the chosen contact .

6.)	The Action Bar consists of the menu items:

a.)	Call Number: As the user selects this item, the saved contact number of the person will automatically shift to the Call Activity with the filled dialpad with the specified phone number.

b.)	Send Message: As the user selects this item, the saved contact number will shift directly to the Message Activity with filled phone number  and by adding content of message by choice the message can be sent.

c.)	Send Mail: As the user selects this item, the saved contact email id get automatically filled into the Gmail Compose Mail Activity along with the user filled id (i.e., TO and FROM are prefilled).

d.)	Search Location : This menu item locates user to the filled location for its direction by having an access to the gps and network connection in Google Maps Activity. 

e.)	Add Event: This menu item is used to have the reminder service or adding any event for the saved person by shifting to Event Activity. The user have a choice to add the reminder time limit and the date limit of its choice. Also the addition of timing to be added for the event to remind before the event is supposed to happen.

f.)	Delete Contact : This menu item deletes a particular record and then shifts back to the main Contact List Activity.

# Project Screenshots
![1](https://user-images.githubusercontent.com/63814054/81483757-314e0800-925e-11ea-9002-9c3f97aca2e3.jpg) ![2](https://user-images.githubusercontent.com/63814054/81483972-96eec400-925f-11ea-94d1-36959ed6e34f.jpg) ![3](https://user-images.githubusercontent.com/63814054/81483973-99511e00-925f-11ea-8236-9d017a940021.jpg) ![4](https://user-images.githubusercontent.com/63814054/81483976-9b1ae180-925f-11ea-975b-ffeb71e0df8a.jpg) ![5](https://user-images.githubusercontent.com/63814054/81483980-a2da8600-925f-11ea-9614-a40fb900f5bc.jpg) ![6](https://user-images.githubusercontent.com/63814054/81483984-a79f3a00-925f-11ea-8da3-39e427c2dbc4.jpg) ![7](https://user-images.githubusercontent.com/63814054/81483985-aa9a2a80-925f-11ea-8af8-7a26846abec6.jpg) ![8](https://user-images.githubusercontent.com/63814054/81483987-ad951b00-925f-11ea-8621-813130c5031d.jpg) ![9](https://user-images.githubusercontent.com/63814054/81483988-aff77500-925f-11ea-9afc-bc6bcd524df2.jpg) ![10](https://user-images.githubusercontent.com/63814054/81483989-b259cf00-925f-11ea-8fe8-fdf0d9486d40.jpg) ![11](https://user-images.githubusercontent.com/63814054/81483992-b685ec80-925f-11ea-8cce-9e922898e308.jpg)





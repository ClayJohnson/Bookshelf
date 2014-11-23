Bookshelf
=========

Bookshelf Project for CS5103 Software Engineering

Projects are built for Android 5.0 and compile at API 21.  If you import these projects you will likely have to fix build path problems created by git.  In project properties->Android, add your appcompat_7 as a library and remove the old one.  In project properties->Java Build Path->Libraries, add External JARs for android-support-v4.jar and android-support-v7-appcompat.jar and remove the old ones.  This should fix the build errors and allow you to run the project.

Bookshelf_InitialPage is the integrated Bookshelf project.  
Bookshelf_InitialPageTest is a project that unit tests Bookshelf_InitialPage.  Run it as Android JUnit Test instead of as Android Application.
PDFReader is the module for reading PDFs which we never got integrated properly.
The remaining projects were used for testing out new functionality before it was integrated.

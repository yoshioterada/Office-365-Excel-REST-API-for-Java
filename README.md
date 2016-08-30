# Office-365-Excel-REST-API-for-Java
This is the sample application of Excel REST API for Java

On 3rd Aug 2016, Office 365 Excel REST API was released as GA.

https://blogs.office.com/2016/08/03/announcing-the-general-availability-of-the-microsoft-excel-api-to-expand-the-power-of-office-365/

Graph API for Excel 

https://graph.microsoft.io/ja-jp/docs/api-reference/v1.0/resources/excel

And I created the sample application of it  and created the demo video (by Japanese). 
So I would like to share the the code.


[![](http://img.youtube.com/vi/rdS7VPOzU0M/0.jpg)](https://www.youtube.com/watch?v=rdS7VPOzU0M)


Demo Video :
https://youtu.be/rdS7VPOzU0M

The Above Demo Senario:

1. Start my sample application from IDE (NetBeans)

2. Login to the sample application via Azure AD auth.

3. Login to the portal screen of Office 365

4. List all of the file in the Onedrive

5. Open the excel file on Ondrive with browser

6. Open the excel file from my sample application

7. Open the worksheet of the file from my sample application.

8. Select the table on the worksheet

9. Add one row data to the table

10.Confirm the data which added  in the above

11.Add new file to Ondrive and check again from the application

In my sample, I wrote as a Java EE 7 application.
However it may be run on servlet container like Tomcat/Jetty.

I used following technologies.

I used the JSF as a front view technology. However it is not important.Any framework for Front technologies will be available.

In order to implement the Excel application with this classes,
the most important class is in the com.yoshio3.restclient.services package.
Please refere to the above package as a start point ?

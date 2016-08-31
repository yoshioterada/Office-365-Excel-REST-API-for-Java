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

# Configuration Step
![](https://c1.staticflickr.com/9/8449/29321901506_0d5d02f69e.jpg)
![](https://c1.staticflickr.com/9/8483/29321901426_e04f178c5e.jpg)
![](https://c1.staticflickr.com/9/8371/28734379163_940f842a9d.jpg)
![](https://c1.staticflickr.com/9/8035/29321901336_e6f55ef2eb.jpg)
![](https://c1.staticflickr.com/9/8344/29321901276_e14853539d.jpg)
![](https://c1.staticflickr.com/9/8309/28734379003_065d3f897f.jpg)
![](https://c1.staticflickr.com/9/8575/29321901166_52ec714d74.jpg)
![](https://c1.staticflickr.com/9/8843/28734378883_0af373cc69.jpg)
![](https://c1.staticflickr.com/9/8552/29321901006_7f9848b08d.jpg)
![](https://c1.staticflickr.com/9/8087/28734378673_117877b8cb.jpg)
![](https://c1.staticflickr.com/9/8833/29321900806_fa3ca1c106.jpg)
![](https://c1.staticflickr.com/9/8509/28734378293_9ecfbd1d4f.jpg)
![](https://c1.staticflickr.com/9/8163/29321900656_22f5be5746.jpg)
![](https://c2.staticflickr.com/8/7759/29321900596_ffeaa1fe4e.jpg)
![](https://c1.staticflickr.com/9/8808/28734378033_dee5f79bd9.jpg)
![](https://c1.staticflickr.com/9/8273/29321900486_4a32d0c9ef.jpg)
![](https://c1.staticflickr.com/9/8808/29321900406_3b4e55ce6d.jpg)
![](https://c1.staticflickr.com/9/8411/28734377853_4292a6e6b3.jpg)
![](https://c1.staticflickr.com/9/8162/28734377813_787cce13a3.jpg)
![](https://c1.staticflickr.com/9/8404/29321900266_57f355767a.jpg)
![](https://c1.staticflickr.com/9/8367/29322381406_b4bc40bbfc.jpg)
![](https://c1.staticflickr.com/9/8486/28734377613_6665637664_z.jpg)
![](https://c1.staticflickr.com/9/8471/29321900166_3f7ca2e85f.jpg)
![](https://c1.staticflickr.com/9/8289/28734377663_0bce156997.jpg)
![](https://c2.staticflickr.com/8/7667/29321900126_a924171af2.jpg)
![](https://c1.staticflickr.com/9/8307/29321900016_e3f4b515a7.jpg)
![](https://c1.staticflickr.com/9/8400/28734377493_1f3fc4fcbc.jpg)
![](https://c1.staticflickr.com/9/8552/29321899916_4750380cbb.jpg)
![](https://c1.staticflickr.com/9/8862/29321899876_f3dba43632.jpg)
![](https://c1.staticflickr.com/9/8546/28734377393_c73e9513bc.jpg)
![](https://c1.staticflickr.com/9/8843/29321899766_b61ca6e4ed.jpg)
![](https://c2.staticflickr.com/8/7524/29321899726_67bf62a49b.jpg)
![](https://c1.staticflickr.com/9/8194/28734377193_f7a670256a.jpg)
![](https://c1.staticflickr.com/9/8033/29321899706_cc9089ea3b.jpg)
![](https://c1.staticflickr.com/9/8323/29321899566_9a336c33da.jpg)
![](https://c1.staticflickr.com/9/8588/28734377213_1ec145934f.jpg)
![](https://c1.staticflickr.com/9/8539/28734376963_9056cc267a.jpg)
![](https://c1.staticflickr.com/9/8282/29321899456_f8c18b1431.jpg)
![](https://c1.staticflickr.com/9/8347/29321899376_6c52d04631.jpg)
![](https://c1.staticflickr.com/9/8501/28734376743_89ef65fdf4.jpg)
![](https://c1.staticflickr.com/9/8388/29321899146_a694fc8b9d.jpg)
![](https://c1.staticflickr.com/9/8344/29321899126_846fc67f92.jpg)
![](https://c1.staticflickr.com/9/8431/28734376623_d0c1cced17.jpg)
![](https://c1.staticflickr.com/9/8459/29276779601_03c11a8754.jpg)
![](https://c1.staticflickr.com/9/8272/29321898926_546abb8c1b.jpg)
![](https://c1.staticflickr.com/9/8426/29321898936_e86c7f585b.jpg)
![](https://c1.staticflickr.com/9/8345/28734376313_fa6e891b2c.jpg)
![](https://c1.staticflickr.com/9/8135/29321898806_265abd1a1a.jpg)
![](https://c1.staticflickr.com/9/8367/29322381406_b4bc40bbfc.jpg)
![](https://c1.staticflickr.com/9/8392/29068589370_5f5d10ccaf.jpg)
![](https://c1.staticflickr.com/9/8475/28734376123_542df197ec.jpg)
![](https://c1.staticflickr.com/9/8075/29321898686_041355b73c.jpg)
![](https://c1.staticflickr.com/9/8261/29276778511_54c0b24478.jpg)
![](https://c1.staticflickr.com/9/8059/28734375823_23c6a6d00d.jpg)
![](https://c1.staticflickr.com/9/8396/29321898496_6ef808a1db.jpg)
![](https://c1.staticflickr.com/9/8274/28734375693_07c6110d18.jpg)
![](https://c1.staticflickr.com/9/8135/29321898476_b19ec72cb8.jpg)
![](https://c1.staticflickr.com/9/8123/28734375503_4348343dca.jpg)
![](https://c1.staticflickr.com/9/8097/29321898326_4effbd79cf.jpg)
![](https://c1.staticflickr.com/9/8412/28734375323_9e09c6d3df.jpg)
![](https://c1.staticflickr.com/9/8168/28734375483_8933da1bb8.jpg)
![](https://c1.staticflickr.com/9/8454/28734375133_0b31a7cb21.jpg)
![](https://c1.staticflickr.com/9/8053/29321897986_b2d3662a71.jpg)
![](https://c1.staticflickr.com/9/8406/28734374863_1c1a32519e.jpg)



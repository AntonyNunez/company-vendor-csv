# company-vendor-csv
Test: Merge company csv with vendor csv.

###### **Introduction** 

A company receives data in csv format from a vendor every 2 weeks, this csv contains full set of the data, there is no representation in the file if a record has changed, not changed, added or deleted. 
Company has a another csv that contains data in its own format.  

Purpose of this exercise to write a Java program ideally using spring boot to create company's copy of csv to match the changes that vendor has done. 

Output csv always contains full set of data i.e. unchanged and changed. To limit the scope of this exercise any addition or deletion of rows in vendor csv can be ignored.

###### **Details**
_vendor-place csv:_

Attached is an input csv file that is received from data vendor called vendor-place csv which has columns

**PLACE ID**: Place Id is vendor unique ID for a place, this maps to vendor_place_id in company csv
 
**PLACE NAME**:  Place name is name of place

**LATITUDE**: Latitude is latitude of a given place 

**UNLOCODE**: UNLOCCODE is universal standard to represent a place  

_company-place csv_

Attached is a company-place csv file that has company's copy of data, converted to company format.

**id**: Id is unique record id, think of this as primary key 

**name**: Name is name of the place
  
**is_active**: Is_active marks if given record is active or deleted
 
**created_at**: Create_at is time when record is created in format “2018-10-12 12:46:21+00”
 
**updated_at**: Update_at is time when record is updated in format “2018-10-12 12:46:21+00”
 
**UNLOCODE**: UNLOCODE is universal standard to represent a place 

**place_identity_id**: Place_identity_id is unique value for a given place that company assigns - this value remains same if for example a place name was to change.
 
**vendor_place_id**: Vendor_place_id is vendor unique ID for a place, this maps to Id in vendor csv file
 
_output-place csv_

Output csv has been attached to show what output file must look like.
 
###### **Problem**

Program needs to compare vendor csv to company csv and create a new output csv files. 
UNLOCCODE without any spaces is to be used to match the records for comparison, if UNLOCODE is missing then Place name is to be used for comparison.
This solution is to deal with only difference; ignore new or deleted records.
 
Output csv must contain all the records, i.e. any unchanged records as it is. If any records has changes like vendor_id missing, latitude changed or place name changed then that record in output csv is to marked is_active false and a new record is to be added

For e,g.
In vendor csv value is 
PLACE ID,PLACE NAME,LATITUDE,UNLOCODE
1191,Eleuthera Is.,25.3833333,BS ELE

And company csv 
id,name,is_active,created_at,updated_at,UNLOCODE,place_identity_id,vendor_place_id
1,Eleuthera Is.,t,2018-10-12 12:46:21+00,2018-10-12 12:46:21+00,BSELE,PI1B95964,

As can be seen, vendor_id is missing in company csv so output csv must 
id,name,is_active,created_at,updated_at,UNLOCODE,place_identity_id,vendor_place_id
1,Eleuthera Is.,f,2018-10-12 12:46:21+00,2018-10-12 12:46:21+00,BSELE,PI1B95964,
17,Eleuthera Is.,t,2018-10-12 12:46:21+00,2020-07-22 09:09:57+0100,BSELE,PI1B95964,1191

For current row 
Current record is marked as is_active false
Modified date is changed to current time

For new row to be added
Id is incremented as is to considered as unique primary key.
place_identity_id remains same 
Vendor_place_id is same as id in vendor csv.
New created and modified date added

**Objective and Goal**
Interview Objective is assess candidate coding style, OO concepts, spring knowledge and candidates use good software development principals

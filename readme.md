### insert
curl --location --request POST 'localhost:8080/api/v1/user' \
--header 'Content-Type: application/json' \
--data-raw '{"name": "Jack", "dob":"1980-10-10", "updatedDate":"2020-07-21 08:29:00"}'

### select v1 jpa
curl --location --request GET 'localhost:8080/api/v1/user/1'
### response 1
```
{
    "id": 1,
    "name": "Jack",
    "dob": "1980-10-10",
    "updatedDate": "2020-07-21 08:29:00"
}
```
### select v2 jdbcTemplate using BeanPropertyRowMapper
curl --location --request GET 'localhost:8080/api/v2/user/1'

### response 2 เหมือน 1
```
{
    "id": 1,
    "name": "Jack",
    "dob": "1980-10-10",
    "updatedDate": "2020-07-21 08:29:00"
}
```
### select v3 jdbcTemplate return Map<String, Object>
curl --location --request GET 'localhost:8080/api/v3/user/1'

### response 3 เหมือนกัน แต่ field ตัวใหญ่ถ้าใช้ h2 แต่ถ้า mysql ชื่อ filed update_date
***ถ้าเปลี่ยน region ในเครื่อง windows เป็น thai จะได้ updated_date เป็น พ.ศ. 2563 แทน***
```
{
    "ID": 1,
    "NAME": "Jack",
    "DOB": "1987-11-04",
    "UPDATED_DATE": "2020-07-21 08:29:00"
}
```
# Usuarios-BCI
Springboot API creación Usuarios

Request y response solo en json

Emails habilitados solo del dominio "@dominio.cl"

Password minimo debe tener una mayuscula, tres letras minúsculas y dos numeros

## Endpoints


### Registrar usuarios
Al registrar usuario se entrega todos los datos mas un jwt para poder hacer consultas.

POST http://localhost:8082/api/auth/register
<details>
  <summary>request/response</summary>

#### Request
```json
{
  "name": "Juan Rodriguez",
  "email": "juan@dominio.cl",
  "password": "hunterA22",
  "phones": [
    {
      "number": "1234567",
      "citycode": "1",
      "countrycode": "57"
    }
  ]
}
```
#### Response
```json
{
  "message": {
    "id": "9264e940-5823-4fa2-aaf7-87c14ca4a75a",
    "name": "Juan Rodriguez",
    "email": "juan@dominio.cl",
    "password": "hunterA22",
    "created": "2024-05-02",
    "modified": "2024-05-02",
    "last_login": "2024-05-02",
    "token": "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJqdWFuQGRvbWluaW8uY2wiLCJleHAiOjE3MTQ2OTEyNjYsImlhdCI6MTcxNDY4NzY2Nn0.7DA3EHyrzwGT-Z4xXx2Pto9p4fi7iZL7pDTl7wJ5o10",
    "isactive": "1",
    "phones": [
      {
        "id": 1,
        "number": "1234567",
        "citycode": "1",
        "countrycode": "57"
      }
    ]
  },
  "status": "200"
}
```
</details>

### Login usuarios
Al hacer login se retorna el jwt asociado al usuario, si esta caducada genera una nueva.

POST http://localhost:8082/api/auth/login
<details>
  <summary>request/response</summary>

#### Request
```json
{
  "email":"juan@dominio.cl",
  "password":"hunterA22"
}
```
#### Response
```json
{
  "message": "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJqdWFuQGRvbWluaW8uY2wiLCJleHAiOjE3MTQ2OTEyNjYsImlhdCI6MTcxNDY4NzY2Nn0.7DA3EHyrzwGT-Z4xXx2Pto9p4fi7iZL7pDTl7wJ5o10",
  "status": "200"
}
```
</details>

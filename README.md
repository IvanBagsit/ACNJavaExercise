Requirements:
- Postman
- git clone https://github.com/IvanBagsit/ACNJavaExercise.git

Steps to create .txt file:
1. run program in local
2. use curl below
3. send request

Note: 
- file created for the result can be found under 'tmp' folder as 'persons.txt'
- Java version used is 17

Curl:
curl --location 'http://localhost:8080/fileWriter/createFile' \
--header 'Content-Type: application/json' \
--data '[
    {
        "firstName":"Mary",
        "age":21
    },
    {
        "firstName":"Alina",
        "age":22
    },
    {
        "firstName":"John",
        "age":23
    },
    {
        "firstName":"Nicole",
        "age":24
    },
    {
        "firstName":"Mike",
        "age":25
    },
    {
        "firstName":"Ivan",
        "age":26
    }
]'

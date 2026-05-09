##Setup
1. Start Docker with MongoDB 
docker compose up -d

2.Build Image
docker build -t recipe-app:1.0 .

3.Run App 
docker run -d --name recipe-app -p 8080:8080 --env-file .env -e SPRING_DATA_MONGODB_URI=mongodb://host.docker.internal:27017/cpsc449final recipe-app:1.0

Group Members:
Joshua Andrada CWID: 861131696
Britney Cheng CWID: 886210228
Evan Wenzel CWID: 888971421
Karl Orquita CWID: 842282873


Video: https://youtu.be/X3NE8puhyf4


<img width="1919" height="1006" alt="image" src="https://github.com/user-attachments/assets/1146309c-1343-4a42-a65e-3d28816a1f3b" />

<img width="1860" height="981" alt="image" src="https://github.com/user-attachments/assets/764f2de3-e4a2-462b-8f63-d2c31f98cc42" />


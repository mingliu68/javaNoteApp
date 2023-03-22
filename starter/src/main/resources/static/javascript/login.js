function el(id) {
    return document.getElementById(id);
}

const form = el("login-form");
const username = el("username");
const password = el("password");

const headers = {
    'Content-type':'application/json'
}

const baseUrl = 'http://localhost:8080/api/users';

const handlingSubmit = async (event) => {
    event.preventDefault();

    const bodyObj = {
        username : username.value,
        password : password.value
    }

    const response = await fetch(`${baseUrl}/login`, {
        method: "POST",
        body: JSON.stringify(bodyObj),
        headers: headers
        }).catch(error => console.error(error.message));
        
    const responseArr = await response.json();

    if(response.status === 200) {
        document.cookie = `userId=${responseArr[1]}`;
        window.location.replace("http://localhost:8080/home.html");
    }
}

form.addEventListener("submit", handlingSubmit);
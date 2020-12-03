function onSignIn(googleUser) {
    var profile = googleUser.getBasicProfile();
    console.log('ID: ' + profile.getId());
    console.log('Name: ' + profile.getName());
    console.log('Image URL: ' + profile.getImageUrl());
    console.log('Email: ' + profile.getEmail());

    // send data to LoginController
    var xhr = new XMLHttpRequest();
    xhr.open('POST', 'http://localhost:8084/J3LP0011_YellowMoon/login');
    xhr.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded');
    xhr.onload = function () {
        console.log('Signed in as: ' + xhr.responseText);
    };

    xhr.onreadystatechange = function () {
        if (this.readyState == 4 && this.status == 200) {
            window.location.replace("http://localhost:8084/J3LP0011_YellowMoon/indexPage");
        }
    }

    xhr.send(`email=${profile.getEmail()}&name=${profile.getName()}&password=${profile.getId()}&action=google`);
}